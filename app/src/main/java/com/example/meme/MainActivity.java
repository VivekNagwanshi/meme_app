package com.example.meme;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Button bt;
    String url =      "https://meme-api.herokuapp.com/gimme";
    ProgressBar progressBar1;
    ProgressBar progressBar;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar1 = findViewById(R.id.parogressbar);
        bt=findViewById(R.id.button);
        progressBar = findViewById(R.id.progress);
        lodememe();

    }
    private void lodememe(){
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {


                    bt.setVisibility(View.VISIBLE);
                    imageView = findViewById(R.id.imageView3);
                    Glide.with(MainActivity.this).load(response.getString("url")).into(imageView);
                    progressBar.setVisibility(View.GONE);
                    progressBar1.setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonObjectRequest);
    }

    public void memeload(View view) {
        progressBar1.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        lodememe();
        bt.setVisibility(View.GONE);

    }

    public void sharememe(View view) {

        Intent myIntent = new Intent(Intent.ACTION_SEND);
        myIntent.setType("text/plain");

        String body = "Your body here , $currentImageUrl";
        String sub = "Your Subject";
        String Url = url;
        ImageView image =imageView;
        myIntent.putExtra(Intent.EXTRA_SUBJECT,sub);
        myIntent.putExtra(Intent.EXTRA_TEXT,Url);

        startActivity(Intent.createChooser(myIntent, "Share Using"));

    }
}