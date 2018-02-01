package com.example.admininstrator.graphqlpractice;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.admininstrator.graphqlpractice.adapter.ScheduleAdapter;
import com.example.admininstrator.graphqlpractice.model.MovieListQuery;
import com.example.admininstrator.graphqlpractice.util.RecyclerItemClickListener;

import java.util.List;

import javax.annotation.Nonnull;

import okhttp3.OkHttpClient;


public class MainActivity extends AppCompatActivity {

    List<MovieListQuery.Movie> users;
    FragmentManager manager;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = getSupportFragmentManager();

        progressBar = findViewById(R.id.main_progressBar);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        //Launch More Details fragment on item click
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                manager.beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_side, R.anim.slide_out_side,
                                R.anim.slide_in_up, R.anim.slide_out_up)
                        .add(R.id.mainActivity_framelayout, ExtraInfoFragment.newInstance(
                                users.get(position).title()), "MoreInfo")
                        .addToBackStack("MoreInfo")
                        .commit();
            }

            @Override
            public void onLongItemClick(View view, int position) {
            }
        }));

        getDataAndSetAdapter(recyclerView);
    }



    private void getDataAndSetAdapter(final RecyclerView recyclerView) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();
        final ApolloClient apolloClient = ApolloClient.builder()
                .serverUrl("https://3wzp7qnjv.lp.gql.zone/graphql")
                .okHttpClient(okHttpClient)
                .build();

        MovieListQuery query = new MovieListQuery("ma");
        ApolloCall<MovieListQuery.Data> call = apolloClient.query(query);
        call.enqueue(new ApolloCall.Callback<MovieListQuery.Data>() {
            @Override
            public void onResponse(@Nonnull Response<MovieListQuery.Data> response) {
                MovieListQuery.Data data = response.data();
                assert data != null;
                users = data.movies();

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setAdapter(new ScheduleAdapter(users, getApplicationContext()));
                    }
                });
            }

            @Override
            public void onFailure(@Nonnull ApolloException e) {
                Log.d("Main", "onFailure: ");
            }
        });
    }


    @Override
    public void onBackPressed() {
        if (manager.getBackStackEntryCount() > 0) {
            manager.popBackStack();
        } else
            super.onBackPressed();
    }
}
