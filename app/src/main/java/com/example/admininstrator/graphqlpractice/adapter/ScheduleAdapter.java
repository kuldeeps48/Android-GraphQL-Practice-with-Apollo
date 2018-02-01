package com.example.admininstrator.graphqlpractice.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admininstrator.graphqlpractice.R;
import com.example.admininstrator.graphqlpractice.model.MovieListQuery;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {
    private List<MovieListQuery.Movie> mResults;
    private Context mContext;

    public ScheduleAdapter(List<MovieListQuery.Movie> resultList, Context context) {
        mResults = resultList;
        mContext = context;

    }

    @Override
    public ScheduleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycler, parent, false);
        return new ScheduleAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ScheduleAdapter.ViewHolder holder, int position) {
        holder.txtTitle.setText(mResults.get(position).title());
        holder.txtRelease.setText(String.format("In %d", mResults.get(position).year()));
        holder.txtRating.setText(String.format("Rated: %s", mResults.get(position).imdbRating()));

        Picasso.with(mContext)
                .load(mResults.get(position).poster())
                .error(R.drawable.poster_placeholder)
                .into(holder.imgPoster);

    }

    @Override
    public int getItemCount() {
        return mResults.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtRating, txtRelease;
        ImageView imgPoster;

        ViewHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_title);
            txtRating = itemView.findViewById(R.id.txt_rating);
            txtRelease = itemView.findViewById(R.id.txt_year);
            imgPoster = itemView.findViewById(R.id.img_poster);
        }
    }

}
