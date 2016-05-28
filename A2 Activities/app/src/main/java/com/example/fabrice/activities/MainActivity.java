package com.example.fabrice.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int onCreateCounter=0, onStartCounter=0, onPauseCounter=0, onDestroyCounter=0, onStopCounter=0, onResumeCounter=0, onRestartCounter=0;
    private TextView createCounterTxt, startCounterTxt, pauseCounterTxt, destroyCounterTxt, stopCounterTxt, resumeCounterTxt, restartCounterTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("Created", "onCreate called");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        createCounterTxt = (TextView) findViewById(R.id.onCreate_count);
        startCounterTxt = (TextView) findViewById(R.id.onStart_count);
        pauseCounterTxt = (TextView)findViewById(R.id.onPause_count);
        destroyCounterTxt = (TextView)findViewById(R.id.onDestroy_count);
        stopCounterTxt = (TextView)findViewById(R.id.onStop_count);
        resumeCounterTxt = (TextView)findViewById(R.id.onResume_count);
        restartCounterTxt = (TextView)findViewById(R.id.onRestart_count);

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

    public void setCounters(){
        createCounterTxt.setText(Integer.toString(onCreateCounter));
        startCounterTxt.setText(Integer.toString(onStartCounter));
        pauseCounterTxt.setText(Integer.toString(onPauseCounter));
        destroyCounterTxt.setText(Integer.toString(onDestroyCounter));
        stopCounterTxt.setText(Integer.toString(onStopCounter));
        resumeCounterTxt.setText(Integer.toString(onResumeCounter));
        restartCounterTxt.setText(Integer.toString(onRestartCounter));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void startActTwo(View view){
        Intent intent = new Intent(this, ActivityTwo.class);
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        Log.i("Stopped","onStop called");
        onStopCounter++;
        super.onStop();
        setCounters();

    }

    @Override
    protected void onPause() {
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
        Log.i("Destroyed","onDestroy called");
        onDestroyCounter++;
        super.onDestroy();
        setCounters();
    }

    @Override
    public void onSaveInstanceState(Bundle bundle){
        super.onSaveInstanceState(bundle);

        bundle.putInt("onCreateCounter", onCreateCounter);
        bundle.putInt("onStartCounter", onStartCounter);
        bundle.putInt("onPauseCounter", onPauseCounter);
        bundle.putInt("onDestroyCounter", onDestroyCounter);
        bundle.putInt("onStopCounter", onStopCounter);
        bundle.putInt("onResumeCounter", onResumeCounter);
        bundle.putInt("onRestartCounter", onRestartCounter);
    }
}
