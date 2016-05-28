package com.example.fabrice.diary;

import android.app.Activity;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fabrice.diary.persistence.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link DiaryListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiaryListFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private OnFragmentInteractionListener mListener;

    private RecyclerView.LayoutManager mLayoutManager;
    @Bind(R.id.rv)
    public RecyclerView mRecyclerView;
    private DiaryAdapter mAdapter;

    public static DiaryListFragment newInstance(String param1, String param2) {
        DiaryListFragment fragment = new DiaryListFragment();
        return fragment;
    }

    public DiaryListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_diary_list, container, false);
        ButterKnife.bind(this, view);
        getLoaderManager().initLoader(0, null, this);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new DiaryAdapter(null, null);
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
        return new CursorLoader(getActivity(), Constants.CONTENT_URL, null, null, null, null);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((DiaryAdapter) mAdapter).setOnItemCLickListener(
                new DiaryAdapter.MyClickListener() {
              @Override
               public void onItemClick(DiaryEntry entry, View v) {
                    mListener.onFragmentInteraction(entry);
                 }
             }
        );
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

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(DiaryEntry entry);
    }

}
