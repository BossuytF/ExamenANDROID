package com.example.fabrice.oefparadoxfragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.zip.Inflater;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ParadoxDescriptionFragment} interface
 * to handle interaction events.
 * Use the {@link ParadoxDescriptionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ParadoxDescriptionFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_ID = "PARADOX_ID";
    private int id;

    @Bind(R.id.paradox_description)
    TextView descriptionTxtView;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param id Parameter 1.
     * @return A new instance of fragment ParadoxDescriptionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ParadoxDescriptionFragment newInstance(int id) {
        ParadoxDescriptionFragment fragment = new ParadoxDescriptionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    public ParadoxDescriptionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt(ARG_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_paradox_description, container, false);

        ButterKnife.bind(this, view);

        if (savedInstanceState != null){
            id = savedInstanceState.getInt(ARG_ID);
        }

        descriptionTxtView.setText(Paradoxes.ParadoxDescription[id]);
        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        if (getArguments() != null) {
            updateView(getArguments().getInt(ARG_ID));
        }
    }

    @Override
    public  void onDestroyView(){
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void updateView(int id){
        descriptionTxtView.setText(Paradoxes.ParadoxDescription[id]);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the current article selection in case we need to recreate the fragment
        outState.putInt(ARG_ID, id);
    }
}

