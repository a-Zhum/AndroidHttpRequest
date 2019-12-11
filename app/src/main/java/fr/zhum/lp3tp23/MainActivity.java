package fr.zhum.lp3tp23;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.service.voice.VoiceInteractionSession;
import android.service.voice.VoiceInteractionSession.Request;
import android.view.View;
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

import static android.service.voice.VoiceInteractionSession.Request.*;

public class MainActivity extends AppCompatActivity {

    private EditText txtBoxSearch;
    private Button btnSearch;
    private RequestQueue myRequestQueue;
    private String urlApi = "http://www.omdbapi.com/?apikey=2375f757&s=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtBoxSearch = findViewById(R.id.txtBoxSearchMovie);
        btnSearch = findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MovieSearch = txtBoxSearch.getText().toString();

                if (MovieSearch.isEmpty()) {
                    txtBoxSearch.setError(getString(R.string.emptyTxtBox));
                }else {
                    txtBoxSearch.setText("");
                    searchMovie(MovieSearch);
                }
            }
        });
    }

    protected void searchMovie(String movie) {
        myRequestQueue = Volley.newRequestQueue(this);

            JsonObjectRequest getMovies;
        getMovies = new JsonObjectRequest(com.android.volley.Request.Method.GET, urlApi + movie, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray movies = (JSONArray) response.get("Search");
                    MovieFragment.resetM();

                    for (int i = 0; i < movies.length(); i++) {
                        JSONObject m = movies.getJSONObject(i);
                        String idMovie = m.getString("imdbID");
                        String title = m.getString("Title");
                        String year = m.getString("Year");
                        Movie movie = new Movie(idMovie, title, year);
                        MovieFragment.addM(movie);
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