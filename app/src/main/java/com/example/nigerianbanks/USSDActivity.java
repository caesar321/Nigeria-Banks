package com.example.nigerianbanks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class USSDActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList myModals;
    private AdapterClass adapterClass;
    private RequestQueue requestQueue;
    private ImageView goBack;
    private MaterialButton btnUssd,btnNoUssd,All;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ussdactivity);
        recyclerView = findViewById(R.id.recyclerview1);
        myModals = new ArrayList();
        goBack = findViewById(R.id.back);
       // btnUssd = findViewById(R.id.ussd);
        requestQueue = Volley.newRequestQueue(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        parseBanksviaUssd();
    }
    private void parseBanksviaUssd() {
        String url = "https://nigerianbanks.xyz/";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String jnames = jsonObject.getString("name");
                        String jussd = jsonObject.getString("ussd");
                        String jimage = jsonObject.getString("logo");
                        if(!jussd.isEmpty()){
                            myModals.add(new modal(jnames, jussd, jimage));
                        }

                    }
                    adapterClass = new AdapterClass(USSDActivity.this, myModals);
                    recyclerView.setAdapter(adapterClass);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle the error
                error.printStackTrace();
            }
        });

        // Add the request to the RequestQueue
        requestQueue.add(stringRequest);

    }
}