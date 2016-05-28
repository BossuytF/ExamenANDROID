package com.example.fabrice.diary;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.fabrice.diary.persistence.MyDB;
import com.example.fabrice.diary.persistence.MyDBHelper;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

@SuppressLint("NewApi")
public class MainActivity extends AppCompatActivity implements DiaryListFragment.OnFragmentInteractionListener{
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    MyDB myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        myDb = new MyDB(this);
        myDb.open();
        setSupportActionBar(toolbar);

        if(findViewById(R.id.fragment_container) != null && savedInstanceState == null){
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, new DiaryListFragment());
            fragmentTransaction.commit();
        }
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

    @Override
    public void onFragmentInteraction(DiaryEntry entry) {
        toolbar.setTitle(entry.getTitle());
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        if (findViewById(R.id.fragment_container) != null){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right,R.anim.enter_from_right,R.anim.exit_to_right );
            }
            fragmentTransaction.replace(R.id.fragment_container, new DiaryDetailFragment().newInstance(entry));
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    @OnClick(R.id.fab)
    public void addDiaryEntry(View v){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, new NewDiaryFragment().newInstance());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
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
}
