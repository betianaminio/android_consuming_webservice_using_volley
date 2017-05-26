package com.betianaminio.webserviceconsumer.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.betianaminio.webserviceconsumer.R;
import com.betianaminio.webserviceconsumer.models.Exercise;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String URL = "YOUR_WEB_SERVICE_URL/Consumer.php";
    private static final String JSON_TAG = "exercises";

    @BindView(R.id.recycler_view_exercises_list)
    RecyclerView mRecyclerExercisesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {

                fetchList(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);

    }

    private void fetchList( JSONObject data ){

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        this.mRecyclerExercisesList.setLayoutManager(layoutManager);

        if (data.has(JSON_TAG)) {

            try {

                JSONArray exercises_list = data.getJSONArray(JSON_TAG);

                Gson gson = new Gson();
                Exercise[] exercises = gson.fromJson(exercises_list.toString(), Exercise[].class);

                ExerciseAdapter exercisesAdapter = new ExerciseAdapter(this.getApplicationContext(), Arrays.asList(exercises));

                this.mRecyclerExercisesList.setAdapter(exercisesAdapter);

            }catch (JSONException e){

            }
        }

    }
}
