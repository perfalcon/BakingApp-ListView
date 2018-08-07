package com.example.balav.bakingapp_utils.utils;

import android.util.Log;

import com.example.balav.bakingapp_utils.model.Baking;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class GsonUtils {
    private static final String TAG = GsonUtils.class.getSimpleName();

    public GsonUtils(){
        //Nothing to do
    }

    public List<Baking> populateBaking(String strJson){

        Gson gson = new Gson ();
        List<Baking> mBakinglist= gson.fromJson (strJson, new TypeToken<List<Baking>>(){}.getType ());
            for (Baking mBaking : mBakinglist) {
            Log.v(TAG,"Baking -->"+mBaking.toString ());
            }
            return mBakinglist;
    }
}
