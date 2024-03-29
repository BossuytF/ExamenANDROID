package com.example.fabrice.a9reddit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {
    /**
     * TAG die gebruikt wordt om output te loggen. Gebruik enkel deze tag om te loggen!
     */
    public static final String TAG = FullscreenActivity.class.getName();

    /**
     * TAG gebruikt om de titel mee te geven bij een intent
     */
    public static final String TITLE = "title";

    /**
     * TAG gebruikt om een url mee te geven bij een intent.
     */
    public static final String URL = "URL";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        ImageView image = (ImageView)findViewById(R.id.image);
        TextView fullscreenContent = (TextView)findViewById(R.id.fullscreenTitle);
        if(getIntent() != null){
            Intent intent =getIntent();
            String title = intent.getStringExtra(TITLE);
            String url = intent.getStringExtra(URL);
            Picasso.with(getApplicationContext()).load(url).into(image);
            fullscreenContent.setText(title);
        }
    }
}
