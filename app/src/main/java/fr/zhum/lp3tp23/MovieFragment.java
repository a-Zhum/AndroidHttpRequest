package fr.zhum.lp3tp23;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class MovieFragment extends Fragment {

    private RecyclerView myMovieRecyclerView;
    private static MovieAdapter myAdapter;
    private RecyclerView.LayoutManager myLayoutManager;
    private static ArrayList<Movie> movieList = new ArrayList<>();


    public MovieFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_movie, container, false);

        myMovieRecyclerView = v.findViewById(R.id.myRecyclerView);

        myLayoutManager = new LinearLayoutManager(getContext());
        myMovieRecyclerView.setLayoutManager(myLayoutManager);

        myAdapter = new MovieAdapter(movieList);
        myMovieRecyclerView.setAdapter(myAdapter);

        configureOnClickRecyclerView();

        return v;
    }

    public static void resetM() {
        movieList.clear();
        myAdapter.notifyDataSetChanged();
    }

    public static void addM(Movie m) {
        movieList.add(m);
        myAdapter.notifyDataSetChanged();
    }

    protected void configureOnClickRecyclerView(){
        RecyclerItemClickListener.addTo(myMovieRecyclerView, R.layout.fragment_movie)
                .setOnItemClickListener(new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Intent intent = new Intent(getContext(), DetailsActivity.class);
                        intent.putExtra("imdbID", myAdapter.getMovie(position).getIdMovie());
                        startActivity(intent);
                    }
                });
    }
}
