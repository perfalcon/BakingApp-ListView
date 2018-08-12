package com.example.balav.bakingapp_utils;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.balav.bakingapp_utils.model.Step;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;
import java.util.List;

public class StepDetail extends AppCompatActivity {
    public static final String TAG = StepDetail.class.getSimpleName ();
    public static final String STEP_KEY="step";
    public static final String STEP_ID="step_id";
    private SimpleExoPlayer mExoPlayer;
    private SimpleExoPlayerView mPlayerView;
    private int[] mButtonIDs = {R.id.btn_prev_step, R.id.btn_next_step};
    private Button[] mButtons;
    private  int current_position;
    private  List <Step> listSteps;

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected (item);
    }*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_android_me);
        this.getSupportActionBar ().setDisplayHomeAsUpEnabled (true);

        Intent intent = getIntent ();
        int step_id = intent.getIntExtra (STEP_ID,0);
        Log.v (TAG, "Step CLICKED-->" + step_id);
        listSteps = intent.getParcelableArrayListExtra (STEP_KEY);

       // Create a new recipeFragment
        StepFragment stepFragment = new StepFragment ();
        stepFragment.setSteps (listSteps);
        stepFragment.setCurrentPosition (step_id);

        // Add the fragment to its container using a FragmentManager and a Transaction
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.step_detail_container, stepFragment)
                .commit();
    }
    public  void HideNavigationBar(){
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    @Override
    protected void onResume() {
        super.onResume ();
         HideNavigationBar();
    }
}

