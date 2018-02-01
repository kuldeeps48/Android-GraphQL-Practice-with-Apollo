package com.example.admininstrator.graphqlpractice;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.admininstrator.graphqlpractice.model.ExtraMovieDetailsQuery;
import com.example.admininstrator.graphqlpractice.model.SimilarMoviesQuery;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.annotation.Nonnull;

import okhttp3.OkHttpClient;

public class ExtraInfoFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private String title;

    View v;
    TextView txtPlot, txtGenres, txtTitle, txtSim0, txtSim1, txtSim2;
    LinearLayout layoutSim0, layoutSim1;
    ImageView imgSim0, imgSim1, imgSim2;
    ProgressBar progressBar;
    Button btnSimilar;
    ApolloClient apolloClient;
    OkHttpClient okHttpClient;

    public ExtraInfoFragment() {
        // Required empty public constructor
    }


    public static ExtraInfoFragment newInstance(String title) {
        ExtraInfoFragment fragment = new ExtraInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_PARAM1);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_extra_info, container, false);
        txtPlot = v.findViewById(R.id.txt_plot);
        txtGenres = v.findViewById(R.id.txt_genres);
        txtSim0 = v.findViewById(R.id.txt_sim_0);
        txtSim1 = v.findViewById(R.id.txt_sim_1);
        txtSim2 = v.findViewById(R.id.txt_sim_2);
        imgSim0 = v.findViewById(R.id.img_sim_poster_0);
        imgSim1 = v.findViewById(R.id.img_sim_poster_1);
        imgSim2 = v.findViewById(R.id.img_sim_poster_2);
        layoutSim0 = v.findViewById(R.id.layout_sim_0);
        layoutSim0.setVisibility(View.INVISIBLE);
        layoutSim1 = v.findViewById(R.id.layout_sim_1);
        layoutSim1.setVisibility(View.INVISIBLE);

        txtTitle = v.findViewById(R.id.txt_title);
        txtTitle.setText(title);

        progressBar = v.findViewById(R.id.progressBar);

        btnSimilar = v.findViewById(R.id.btn_similar);
        btnSimilar.setOnClickListener(this);

        //Initialize Apollo GraphQL endpoint
        okHttpClient = new OkHttpClient.Builder()
                .build();
        apolloClient = ApolloClient.builder()
                .serverUrl("https://3wzp7qnjv.lp.gql.zone/graphql")
                .okHttpClient(okHttpClient)
                .build();

        //Get extra movie information and display
        getExtraInfo(txtPlot, txtGenres, progressBar);
        return v;
    }

    private void getExtraInfo(final TextView txtPlot, final TextView txtGenres, final ProgressBar progressBar) {
        ExtraMovieDetailsQuery query = new ExtraMovieDetailsQuery(title);
        ApolloCall<ExtraMovieDetailsQuery.Data> call = apolloClient.query(query);
        call.enqueue(new ApolloCall.Callback<ExtraMovieDetailsQuery.Data>() {
            @Override
            public void onResponse(@Nonnull final Response<ExtraMovieDetailsQuery.Data> response) {
                final StringBuilder builder = new StringBuilder();
                //Get single movie entry. Need to add Null-Checks
                for (String genres : response.data().movies().get(0).genres()) {
                    builder.append("⁍ ").append(genres).append("\n");
                }

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.INVISIBLE);
                        txtPlot.setText(response.data().movies().get(0).plot());
                        txtGenres.setText(builder.toString());
                    }
                });

            }

            @Override
            public void onFailure(@Nonnull final ApolloException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btnSimilar.getId()) {
            progressBar.setVisibility(View.VISIBLE);
            getSimilarMovies(title);
        }
    }

    private void getSimilarMovies(String title) {
        SimilarMoviesQuery similarMoviesQuery = new SimilarMoviesQuery(title);
        ApolloCall<SimilarMoviesQuery.Data> call = apolloClient.query(similarMoviesQuery);

        call.enqueue(new ApolloCall.Callback<SimilarMoviesQuery.Data>() {
            @Override
            public void onResponse(@Nonnull Response<SimilarMoviesQuery.Data> response) {
                SimilarMoviesQuery.Data data = response.data();
                final List<SimilarMoviesQuery.Similar> similarList = data.movies().get(0).similar();

                //Response Always return 3 movie suggestions. No time to create adapters.
                //Bad way
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.INVISIBLE);
                        txtSim0.setText(similarList.get(0).title());
                        txtSim1.setText(similarList.get(1).title());
                        txtSim2.setText(similarList.get(2).title());

                        Picasso.with(getActivity())
                                .load(similarList.get(0).poster())
                                .error(R.drawable.poster_placeholder)
                                .into(imgSim0);

                        Picasso.with(getActivity())
                                .load(similarList.get(1).poster())
                                .error(R.drawable.poster_placeholder)
                                .into(imgSim1);

                        Picasso.with(getActivity())
                                .load(similarList.get(2).poster())
                                .error(R.drawable.poster_placeholder)
                                .into(imgSim2);

                        layoutSim0.setVisibility(View.VISIBLE);
                        layoutSim1.setVisibility(View.VISIBLE);
                    }
                });
            }

            @Override
            public void onFailure(@Nonnull final ApolloException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
