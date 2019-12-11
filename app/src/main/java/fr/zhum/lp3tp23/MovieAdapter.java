package fr.zhum.lp3tp23;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

// Adpateur de Film (Movie)
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    ArrayList<Movie> movieList;


    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        private TextView title, year;

        MovieViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.titleFragmentCell);
            year = itemView.findViewById(R.id.yearFragmentCell);
        }

        public void display(Movie movie) {
            title.setText(movie.getTitle());
            year.setText(movie.getYear());
        }
    }


    public MovieAdapter(ArrayList<Movie> movieList) {
        this.movieList = movieList;
    }


    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.movie_cell, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.display(movieList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.movieList.size();
    }

    public Movie getMovie(int position) {
        return movieList.get(position);
    }

}
