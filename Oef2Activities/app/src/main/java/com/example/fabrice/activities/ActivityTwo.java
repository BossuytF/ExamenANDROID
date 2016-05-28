package com.example.fabrice.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ActivityTwo extends AppCompatActivity {

    private int onCreateCounter=0, onStartCounter=0, onPauseCounter=0, onDestroyCounter=0, onStopCounter=0, onResumeCounter=0, onRestartCounter=0;
    private TextView createCounterTxt, startCounterTxt, pauseCounterTxt, destroyCounterTxt, stopCounterTxt, resumeCounterTxt, restartCounterTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_two);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        createCounterTxt = (TextView) findViewById(R.id.onCreate_count2);
        startCounterTxt = (TextView) findViewById(R.id.onStart_count2);
        pauseCounterTxt = (TextView)findViewById(R.id.onPause_count2);
        destroyCounterTxt = (TextView)findViewById(R.id.onDestroy_count2);
        stopCounterTxt = (TextView)findViewById(R.id.onStop_count2);
        resumeCounterTxt = (TextView)findViewById(R.id.onResume_count2);
        restartCounterTxt = (TextView)findViewById(R.id.onRestart_count2);

        if (savedInstanceState != null){

            onCreateCounter = savedInstanceState.getInt("onCreateCounter");
            onStartCounter = savedInstanceState.getInt("onStartCounter");
            onPauseCounter = savedInstanceState.getInt("onPauseCounter");
            onDestroyCounter = savedInstanceState.getInt("onDestroyCounter");
            onStopCounter = savedInstanceState.getInt("onStopCounter");
            onResumeCounter = savedInstanceState.getInt("onResumeCounter");
            onRestartCounter = savedInstanceState.getInt("onRestartCounter");
        }

        onCreateCounter++;
        setCounters();
    }

    private void setCounters(){
        createCounterTxt.setText(Integer.toString(onCreateCounter));
        startCounterTxt.setText(Integer.toString(onStartCounter));
        pauseCounterTxt.setText(Integer.toString(onPauseCounter));
        destroyCounterTxt.setText(Integer.toString(onDestroyCounter));
        stopCounterTxt.setText(Integer.toString(onStopCounter));
        resumeCounterTxt.setText(Integer.toString(onResumeCounter));
        restartCounterTxt.setText(Integer.toString(onRestartCounter));
    }
    //Als de activity niet vernietigd moet worden om instance state te kunnen behouden roep dan vorige activity op door intent
    //Door finish te roepen wordt geen instance state opgeslaan
    public void stopAct(View view){
        finish();
    }

    @Override
    protected void onStop() {

        Log.i("Stopped", "onStop called");
        onStopCounter++;
        super.onStop();
        setCounters();
    }

    @Override
    protected void onPause() {
        Bundle savedInstanceState = new Bundle();

        savedInstanceState.putInt("onCreateCounter", onCreateCounter);
        savedInstanceState.putInt("onStartCounter", onStartCounter);
        savedInstanceState.putInt("onPauseCounter", onPauseCounter);
        savedInstanceState.putInt("onDestroyCounter", onDestroyCounter);
        savedInstanceState.putInt("onStopCounter", onStopCounter);
        savedInstanceState.putInt("onResumeCounter", onResumeCounter);
        savedInstanceState.putInt("onRestartCounter", onRestartCounter);

        super.onSaveInstanceState(savedInstanceState);

        Log.i("Paused","onPause called");
        onPauseCounter++;
        super.onPause();
        setCounters();

    }

    @Override
    protected void onResume() {
        Log.i("Reumed","onResume called");
        onResumeCounter++;
        super.onResume();
        setCounters();
    }

    @Override
    protected void onStart() {
        Log.i("Started","onSTart called");
        onStartCounter++;
        super.onStart();
        setCounters();
    }

    @Override
    protected void onRestart() {
        Log.i("Restarted","onRestart called");
        onRestartCounter++;
        super.onRestart();
        setCounters();

    }

    @Override
    protected void onDestroy(){
        Log.i("Destroyed", "onDestroy called");
        onDestroyCounter++;
        super.onDestroy();
        setCounters();
    }

}
