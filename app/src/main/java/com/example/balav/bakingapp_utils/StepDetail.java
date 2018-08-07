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
/*    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        Log.v(TAG,"in Step Detail");
        setContentView (R.layout.step_detail);
        int step_id=0;

        if(savedInstanceState!=null){
            Log.v (TAG, "Restoring State");
            if (savedInstanceState.containsKey (STEP_KEY)) {
                step_id = savedInstanceState.getInt (STEP_ID);
                listSteps =savedInstanceState.getParcelableArrayList (STEP_KEY);
            }
        }else{
            Intent intent = getIntent ();
            if (intent == null) {
                //closeOnError ();
            }
            step_id = intent.getIntExtra (STEP_ID,0);
            Log.v (TAG, "Step CLICKED-->" + step_id);
            listSteps = intent.getParcelableArrayListExtra (STEP_KEY);
        }
        current_position =step_id;
        Log.v(TAG,"step--->"+listSteps.get (step_id));
        Log.v (TAG,"Step Name -->"+listSteps.get (step_id).getShortDescription ());
        populateUI(listSteps.get (step_id));
    }

    private void populateUI(Step step) {
        Log.v(TAG,"Calling Populate UI -->"+step.toString ());
        if(!isLandScape ()){
            TextView mRecipeName = (TextView) findViewById(R.id.tv_step_description);
            mRecipeName.setText(step.getDescription ());
            // Initialize the buttons with the composers names.
            mButtons = initializeButtons();
        }

        // Initialize the player view.
        mPlayerView = (SimpleExoPlayerView) findViewById(R.id.playerView);
        Log.v (TAG,"The Video-->"+step.getVideoURL ());
        // Initialize the player.
        if(step.getVideoURL ()!=null && !step.getVideoURL ().isEmpty ()){
            Log.v (TAG,"Updating the Video-->"+step.getVideoURL ());
            initializePlayer(Uri.parse(step.getVideoURL ()));
        }


    }
    *//**
     * Initialize ExoPlayer.
     * @param mediaUri The URI of the sample to play.
     *//*
    private void initializePlayer(Uri mediaUri) {
        // Prepare the MediaSource.
        String userAgent = Util.getUserAgent(this, "BakingApp");
        MediaSource mediaSource = new ExtractorMediaSource (mediaUri, new DefaultDataSourceFactory (
                this, userAgent), new DefaultExtractorsFactory (), null, null);
        if (mExoPlayer == null) {
            // Create an instance of the ExoPlayer.
            TrackSelector trackSelector = new DefaultTrackSelector ();
            LoadControl loadControl = new DefaultLoadControl ();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector, loadControl);
            mPlayerView.setPlayer(mExoPlayer);
        }else{
            //Setting env to play the next passed video
            mExoPlayer.stop();
            mExoPlayer.seekTo(0L);
            }
        mExoPlayer.prepare(mediaSource);
        mExoPlayer.setPlayWhenReady(true);
    }


    *//**
     * Release ExoPlayer.
     *//*
    private void releasePlayer() {
        mExoPlayer.stop();
        mExoPlayer.release();
        mExoPlayer = null;
    }
    *//**
     * Release the player when the activity is destroyed.
     *//*
    @Override
    protected void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState (outState);
        super.onSaveInstanceState (outState);
        Log.v (TAG, "Saving the State");
        outState.putInt (STEP_ID,current_position);
        outState.putIntegerArrayList (STEP_KEY,(ArrayList)listSteps);
    }

    *//**
     * Initializes the button to the correct views, and sets the text to the composers names,
     * and set's the OnClick listener to the buttons.
     *
     *
     * @return The Array of initialized buttons.
     *//*
    private Button[] initializeButtons() {
        Button[] buttons = new Button[mButtonIDs.length];
        for (int i = 0; i < buttons.length; i++) {
            Button currentButton = (Button) findViewById(mButtonIDs[i]);
            buttons[i] = currentButton;
            currentButton.setOnClickListener (this);
        }
        return buttons;
    }

    @Override
    public void onClick(View v) {
        // Get the button that was pressed.
        Button pressedButton = (Button) v;
       Log.v(TAG, "Pressed Button-->"+pressedButton.toString ());
       Log.v(TAG,"Button ID -->"+pressedButton.getId ());
       Log.v(TAG,"Current Postion-->"+current_position);
       if(pressedButton.getId() == mButtonIDs[0]){ // prev
           if(current_position<=0){
              current_position=0;
           }else{
               current_position--;
           }
        }else if(pressedButton.getId () == mButtonIDs[1]){//next
           if(current_position>=listSteps.size ()-1){
               current_position=listSteps.size ()-1;
           }else{
               current_position++;
           }
       }
        Log.v(TAG,"Updaed Current Postion-->"+current_position);
       // mExoPlayer=null;
        populateUI(listSteps.get (current_position));
    }
    public boolean isLandScape(){
        return (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE);
        *//*if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            //Do some stuff
        }*//*
    }*/
}

