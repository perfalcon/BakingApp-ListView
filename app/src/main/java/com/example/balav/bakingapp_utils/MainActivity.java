package com.example.balav.bakingapp_utils;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.balav.bakingapp_utils.model.Baking;
import com.example.balav.bakingapp_utils.utils.GsonUtils;
import com.example.balav.bakingapp_utils.utils.NetworkUtils;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private TextView btnRequest;
    public List<Baking> mBaking;

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;

    private String BAKING_DATA_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        /*btnRequest = (TextView) findViewById(R.id.tv_text);

        btnRequest.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v){
                                              sendAndRequestResponse();
                                          }
                                      }

        );*/

        sendAndRequestResponse();

    }

    protected  void displayBakingDetails(){
        for (Baking baking : mBaking) {
            Log.v(TAG,"Baking -->"+baking.toString ());
        }
        loadRecipesView();
    }


    public void sendAndRequestResponse() {
        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);
        //String Request initialized
        mStringRequest = new StringRequest (Request.Method.GET, BAKING_DATA_URL, new ResponseListener (), new ErrorListener ());
        mRequestQueue.add(mStringRequest);

    }

    private class ResponseListener implements Response.Listener{
        @Override
        public void onResponse(Object response) {
            //Toast.makeText(getApplicationContext (),"Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen
             mBaking= new GsonUtils ().populateBaking (response.toString ());
            displayBakingDetails();
        }
    }

    private class ErrorListener implements Response.ErrorListener{
        @Override
        public void onErrorResponse(VolleyError error){
            Log.i(TAG,"Error :" + error.toString());
        }
    }
    private void loadRecipesView(){
        RecyclerView rvRecipe = findViewById(R.id.rv_recipe);
        int no_cols= calculateNoOfColumns(this);

        if(no_cols>1){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, no_cols);
            rvRecipe.setLayoutManager (gridLayoutManager);
        }else{
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            rvRecipe.setLayoutManager (layoutManager);
        }



        rvRecipe.setHasFixedSize(true);
        RecipeAdapter recipeAdapter = new RecipeAdapter (mBaking);
        rvRecipe.setAdapter (recipeAdapter);
    }

    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int scalingFactor = 200;
        int noOfColumns = (int) (dpWidth / scalingFactor);
        if(noOfColumns < 2)
            noOfColumns = 2;
        return noOfColumns;
    }
}
