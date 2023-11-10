package com.example.nigerianbanks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList myModals;
    private AdapterClass adapterClass;
    private RequestQueue requestQueue;
    private SearchView searchEdt;
    private MaterialButton btnUssd,btnNoUssd,All;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        myModals = new ArrayList();
        adapterClass = new AdapterClass(MainActivity.this, myModals);
        All = findViewById(R.id.all);
//        searchEdt=findViewById(R.id.searchView);
        btnUssd = findViewById(R.id.ussd);
        btnNoUssd = findViewById(R.id.noussd);
        requestQueue = Volley.newRequestQueue(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        parseBanks();
/*    searchEdt.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            //filteredList(newText);
            return true;
        }
    });*/

        All.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnUssd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,USSDActivity.class);
                startActivity(intent);
            }
        });
        btnNoUssd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,NOUSSDActivity.class);
                startActivity(intent);
            }
        });

       // parseBanksViaNoUssd();
    }

    /*private void filteredList(String text) {
        ArrayList<modal> filteredList = new ArrayList<>();
        for (modal modal : myModals){
            if (modal.getBankName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(modal);
            }
        }
        if(filteredList.isEmpty()){
            Toast.makeText(this, "not found", Toast.LENGTH_SHORT).show();
        }
    }*/


    private void parseBanksViaNoUssd() {
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

                            myModals.add(new modal(jnames, jussd, jimage));


                    }

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



    private void parseBanks() {
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
                        myModals.add(new modal(jnames, jussd, jimage));
                    }
                    adapterClass = new AdapterClass(MainActivity.this, myModals);
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
