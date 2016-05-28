package com.example.fabrice.recyclerview;

import android.app.Activity;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link .} interface
 * to handle interaction events.
 * Use the {@link CharacterDescritpionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CharacterDescritpionFragment extends Fragment {

    private static final String ARG_POSITION = "CHARACTER_POSITION";
    private int position;

    @Bind(R.id.character_description)
    public TextView characterDescription;
    @Bind(R.id.character_image)
    public ImageView characterImage;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param position Parameter 1.
     * @return A new instance of fragment CharacterDescritpionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CharacterDescritpionFragment newInstance(int position) {
        CharacterDescritpionFragment fragment = new CharacterDescritpionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    public CharacterDescritpionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(ARG_POSITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_character_descritpion, container, false);
        ButterKnife.bind(this, view);

        if (savedInstanceState != null){
            position = savedInstanceState.getInt(ARG_POSITION);
        }

        updateView(position);
        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        if (getArguments() != null) {
            updateView(getArguments().getInt(ARG_POSITION));
        }
    }

    @Override
    public  void onDestroyView(){
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void updateView(int position){
        characterImage.setImageResource(Characters.characterPictures[position]);
        characterDescription.setText(Characters.characterDescriptions[position]);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current article selection in case we need to recreate the fragment
        outState.putInt(ARG_POSITION, position);
    }

}
