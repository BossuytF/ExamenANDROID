package com.example.fabrice.oefparadoxfragments;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ParadoxListFragment.OnParadoxSelectedListener{

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paradoxes);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if(findViewById(R.id.fragment_container) != null){
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, new ParadoxListFragment());
            fragmentTransaction.commit();
        }else{
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        toolbar.setTitle(getTitle());

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


    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
            toolbar.setTitle(getTitle());
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void OnParadoxSelected(int id) {
        toolbar.setTitle(Paradoxes.ParadoxNames[id]);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        Log.i("CLICKEDDDDD", "PARADOX CLICKED");

        if (findViewById(R.id.fragment_container) != null){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right,R.anim.enter_from_right,R.anim.exit_to_right );
            }
            fragmentTransaction.replace(R.id.fragment_container, new ParadoxDescriptionFragment().newInstance(id));
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }else{
            ParadoxDescriptionFragment myFragment =
                    (ParadoxDescriptionFragment)getFragmentManager().findFragmentById(R.id.fragment_description);
            myFragment.updateView(id);
        }
    }
}
