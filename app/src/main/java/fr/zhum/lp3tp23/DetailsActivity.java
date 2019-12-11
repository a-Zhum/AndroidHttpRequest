package fr.zhum.lp3tp23;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailsActivity extends AppCompatActivity {

    private ImageView imgMovie;
    private TextView lblTitleDetails, lblYearDetails, lblRuntimeDetails, lblGenderDetails, lblDirectorDetails;
    private RequestQueue myRequestQueue;
    private String urlApi = "http://www.omdbapi.com/?apikey=2375f757&i=";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_movie);

        imgMovie = findViewById(R.id.imgMovie);
        lblTitleDetails = findViewById(R.id.lblTitleDetails);
        lblYearDetails = findViewById(R.id.lblYearDetails);
        lblDirectorDetails = findViewById(R.id.lblDirectorDetails);
        lblGenderDetails = findViewById(R.id.lblGenderDetails);
        lblRuntimeDetails = findViewById(R.id.lblRuntimeDetails);

        Intent intent = getIntent();
        String idMovie = intent.getStringExtra("imdbID");
        getMovieById(idMovie);
    }

    protected void getMovieById(String idMovie) {
        myRequestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest getMovieById = new JsonObjectRequest(Request.Method.GET, urlApi + idMovie, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Picasso.get().load(response.getString("Poster")).into(imgMovie);
                    lblTitleDetails.setText(response.getString("Title"));
                    lblYearDetails.setText(response.getString("Year"));
                    lblDirectorDetails.setText(response.getString("Director"));
                    lblGenderDetails.setText(response.getString("Genre"));
                    lblRuntimeDetails.setText(response.getString("Runtime"));
                }
                catch (JSONException error) {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), R.string.searchError, Toast.LENGTH_LONG).show();
            }
        });

        myRequestQueue.add(getMovieById);
    }
}
