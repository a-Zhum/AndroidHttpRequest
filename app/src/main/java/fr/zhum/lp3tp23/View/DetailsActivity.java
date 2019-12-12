package fr.zhum.lp3tp23.View;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

import fr.zhum.lp3tp23.R;

// Activité Secondaire qui contient notre recyclerView de film
public class DetailsActivity extends AppCompatActivity {

    private ImageView imgMovie;
    private TextView lblTitleDetails, lblYearDetails, lblRuntimeDetails, lblGenderDetails, lblDirectorDetails;
    private Button btnLienDetails;
    private RequestQueue myRequestQueue;
    //clé API
    private String apiKey = "2375f757";
    //lien pré configuré
    private String urlApi = "http://www.omdbapi.com/?apikey="+apiKey+"&i=";


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
        btnLienDetails = findViewById(R.id.btnLienDetails);

        // On renvoit le détail d'un film à l'aide de son ID transmit à l'Intente
        Intent intent = getIntent();
        String idMovie = intent.getStringExtra("imdbID");
        getMovieById(idMovie);
    }

    protected void getMovieById(final String idMovie) {
        myRequestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest getMovieById = new JsonObjectRequest(Request.Method.GET, urlApi + idMovie, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    // Récupération des champs que nous voulons obtenir dans notre vue de détails
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

        // Lors du clique sur le bouton détails
        btnLienDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.imdb.com/title/" + idMovie;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }
}
