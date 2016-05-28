package com.example.fabrice.choices;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.urlText)
    EditText urlText;

    @Bind(R.id.queryTxt)
    EditText queryTxt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

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

    @OnClick(R.id.urlBtn)
    public void goToUrl(View v){
        String url = urlText.getText().toString();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

        // Start an activity if it's safe
        isIntentSafe(intent);
    }

    @OnClick(R.id.openContactsBtn)
    public void openContacts(View v){
        Intent intent= new Intent(Intent.ACTION_PICK,  ContactsContract.Contacts.CONTENT_URI);
        isIntentSafe(intent);

    }

    @OnClick(R.id.openDialerBtn)
    public void openDialer(View v){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        isIntentSafe(intent);
    }

    @OnClick(R.id.searchGoogleBtn)
    public void searchGoogle(View v){
        String query = queryTxt.getText().toString();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com/search?q=" + query));
        isIntentSafe(intent);
    }

    @OnClick(R.id.startVoiceCommandBtn)
    public void startVoiceCommand(View v){
        Intent intent = new Intent(Intent.ACTION_VOICE_COMMAND);
        isIntentSafe(intent);
    }

    public void isIntentSafe(Intent intent){

        PackageManager packageManager = getPackageManager();
        ComponentName cn = intent.resolveActivity(packageManager);

        if (cn != null){
            startActivity(intent);
        }
        else {
            Toast t = Toast.makeText(getApplicationContext(),
                    "Opps! No activity can be started",
                    Toast.LENGTH_SHORT);
            t.show();
        }
    }



}
