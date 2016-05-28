package com.example.fabrice.a9reddit;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fabrice.a9reddit.models.ListingData;
import com.example.fabrice.a9reddit.persistence.Constants;
import com.example.fabrice.a9reddit.persistence.RedditContentProvider;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ListingsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private OnFragmentInteractionListener mListener;
    private RecyclerView.LayoutManager mLayoutManager;
    @Bind(R.id.rv)
    public RecyclerView mRecyclerView;
    private ListingsAdapter mAdapter;
    public static final String ACTION_UPDATE_SUBREDDITS = "com.example.fabrice.a9reddit.ACTION_UPDATE_SUBREDDITS";


    public static ListingsFragment newInstance() {
        ListingsFragment fragment = new ListingsFragment();
        return fragment;
    }

    public ListingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        ((ListingsAdapter) mAdapter).setOnItemCLickListener(
                new ListingsAdapter.MyClickListener() {
                    @Override
                    public void onItemClick(ListingData listing, View v) {
                        mListener.onFragmentInteraction(listing);
                    }
                }
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_listings, container, false);
        ButterKnife.bind(this, view);
        getLoaderManager().initLoader(0, null, this);


        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ListingsAdapter(null, getActivity().getBaseContext());
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public  void onDestroyView(){
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(), RedditContentProvider.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.setData(data);
        getLoaderManager().destroyLoader(loader.getId());

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.setData(null);
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            getLoaderManager().initLoader(0, null, ListingsFragment.this);

        }
    };

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(ListingData listing);
    }

    public static class ListingsReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            //Bij het ontvangen van de intent moet de data opnieuw uit de DBA gelezen worden.
            context.sendBroadcast(new Intent(Constants.INTENT_UPDATE));
        }
    }

}
