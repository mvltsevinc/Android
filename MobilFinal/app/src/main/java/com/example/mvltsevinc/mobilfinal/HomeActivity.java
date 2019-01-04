package com.example.mvltsevinc.mobilfinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    ListView customListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        customListView = findViewById(R.id.customListView);

        // 1- Request olustur
        StringRequest request = new StringRequest("", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                    parseJsonData(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        // 2- Request Queue
        RequestQueue rQueue = Volley.newRequestQueue(getApplicationContext());
        rQueue.add(request);


    }

    private void parseJsonData(String string){
        try {
            JSONObject object = new JSONObject(string);
            JSONArray fruitsArray = object.getJSONArray("fruits");
            ArrayList list = new ArrayList();
            for(int i = 0; i< fruitsArray.length(); i++){
                list.add(fruitsArray.getString(i));
            }

            ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,list);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
