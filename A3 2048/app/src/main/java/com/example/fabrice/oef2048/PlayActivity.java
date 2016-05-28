package com.example.fabrice.oef2048;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fabrice.oef2048.Models.Board;
import com.example.fabrice.oef2048.Models.Card;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PlayActivity extends AppCompatActivity {

    @Bind(R.id.activity_play_textView_score)
    TextView txtScore;

    @Bind(R.id.activity_play_board)
    Board board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle b = getIntent().getExtras();
        Log.i("BUNDLE", "INSIDE ONCREATE");

        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater)getApplicationContext().getSystemService(infService);

        if (savedInstanceState != null){
            Log.i("STATE", savedInstanceState.toString());
            board = (Board) savedInstanceState.getSerializable("Board");
            li.inflate(R.layout.board_grid_layout, board, true);
        }

        ButterKnife.bind(this);
    }

    @Override
    protected void onStart(){
        super.onStart();

                board.setOnTouchListener(new SwipeListener(getApplicationContext()) {
                    public void onSwipeTop() {
                        board.getCardBoard()[2][1].setNumber(128);
                    }

                    public void onSwipeRight() {
                        board.getCardBoard()[0][0].setNumber(128);
                        Toast.makeText(getApplicationContext(), "right", Toast.LENGTH_SHORT).show();
                    }

                    public void onSwipeLeft() {
                        board.getCardBoard()[2][0].setNumber(128);
                        Toast.makeText(getApplicationContext(), "left", Toast.LENGTH_SHORT).show();
                    }

                    public void onSwipeBottom() {
                        board.getCardBoard()[3][2].setNumber(128);
                        Toast.makeText(getApplicationContext(), "bottom", Toast.LENGTH_SHORT).show();
                    }

                    public boolean onTouch(View v, MotionEvent event) {
                        return gestureDetector.onTouchEvent(event);
                    }
                });
    }

    @Override
    public void onPause(){
        super.onPause();
        SharedPreferences settings = getSharedPreferences("GAME_BOARD", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle state) {
        state.putSerializable("Board", board);
        Log.i("STATE", "STATE IS SAVED");

        super.onSaveInstanceState(state);
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
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
        }else{
            recreate();
        }

        return super.onOptionsItemSelected(item);
    }
}
