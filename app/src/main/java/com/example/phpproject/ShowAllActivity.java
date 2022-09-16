package com.example.phpproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.phpproject.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ShowAllActivity extends AppCompatActivity {
    ActivityShowAllBinding binding;
    NotesAdapter notesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityShowAllBinding.inflate (getLayoutInflater());
        setContentView(binding.getRoot());
        notesAdapter= new NotesAdapter(this);
        binding.showAllRecycler.setAdapter(notesAdapter);
        binding.showAllRecycler.setLayoutManager (new LinearLayoutManager(this));
        getAllNotes();
    }
    private void getAllNotes(){
        String url="http://192.168.1.119/CRUD/get_all.php";
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i=0;i<response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);
                                NotesModel notesModel=new NotesModel(object.getString("id"),object.getString("title"),
                                        object.getString("description"));
                                notesAdapter.add(notesModel);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(ShowAllActivity.this,error.toString(),Toast.LENGTH_SHORT).show();
                        }
                    };
                    RequestQueue queue= Volley.newRequestQueue(this);
                     queue.add(request);
                });
    }
}