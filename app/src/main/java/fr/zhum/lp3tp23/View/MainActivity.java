package fr.zhum.lp3tp23.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.zhum.lp3tp23.Model.Movie;
import fr.zhum.lp3tp23.R;

// Activité Principale
public class MainActivity extends AppCompatActivity {

    private EditText txtBoxSearch;
    private Button btnSearch;
    private RequestQueue myRequestQueue;
    // Clé API
    private String apiKey = "2375f757";
    // type = movie (on veut uniquement des films)
    private String urlApi = "http://www.omdbapi.com/?apikey="+apiKey+"&type=movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtBoxSearch = findViewById(R.id.txtBoxSearchMovie);
        btnSearch = findViewById(R.id.btnSearch);

        // Lors du clique sur bouton Search
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MovieSearch = txtBoxSearch.getText().toString();

                if (MovieSearch.isEmpty()) {
                    txtBoxSearch.setError(getString(R.string.emptyTxtBox));
                }else {
                    txtBoxSearch.setText("");
                    MovieFragment.resetM();
                    searchMovie(MovieSearch, 1);
                }

                // On masque le clavier (on en a plus besoin après le click sur le boutton
                InputMethodManager clv = (InputMethodManager)getApplicationContext().getSystemService(INPUT_METHOD_SERVICE);
                clv.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });
    }


    // Fonction de recherche de Film
    protected void searchMovie(final String movie, final int page) {
        myRequestQueue = Volley.newRequestQueue(this);

            JsonObjectRequest getMovies;
            getMovies = new JsonObjectRequest(com.android.volley.Request.Method.GET, urlApi +"&s="+movie+"&page="+page, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray movies = (JSONArray) response.get("Search");

                    // Ajout des éléments de l'Objet JSON 1 à 1
                    for (int i = 0; i < movies.length(); i++) {
                        JSONObject m = movies.getJSONObject(i);
                        String idMovie = m.getString("imdbID");
                        String title = m.getString("Title");
                        String year = m.getString("Year");
                        Movie newMovie = new Movie(idMovie, title, year);
                        MovieFragment.addM(newMovie);
                    }

                    // on reprend l'objet JSON response et non le movie ou le m
                    int totalResults = response.getInt("totalResults");
                    if (totalResults > (page * 10) ){
                        // on appelle rappelle la même méthode (récursivité)
                        searchMovie(movie,page+1);
                    }
                } catch (JSONException error) {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), R.string.searchError, Toast.LENGTH_LONG).show();
            }
        });


        myRequestQueue.add(getMovies);
    }
}
