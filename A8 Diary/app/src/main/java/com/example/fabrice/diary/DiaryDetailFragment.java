package com.example.fabrice.diary;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link .} interface
 * to handle interaction events.
 * Use the {@link DiaryDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiaryDetailFragment extends Fragment {
    private static final String ARG_ENTRY = "DIARY_ENTRY";
    private DiaryEntry entry;

    @Bind(R.id.diary_detail_content)
    TextView contentTxtView;
    @Bind(R.id.diary_detail_title)
    TextView titleTxtView;
    @Bind(R.id.diary_detail_date)
    TextView dateTxtView;

    public static DiaryDetailFragment newInstance(DiaryEntry entry) {
        DiaryDetailFragment fragment = new DiaryDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_ENTRY, entry);
        fragment.setArguments(args);
        return fragment;
    }

    public DiaryDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            entry = (DiaryEntry) getArguments().getSerializable(ARG_ENTRY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_diary_detail, container, false);

        ButterKnife.bind(this, view);

        if (savedInstanceState != null){
            entry = (DiaryEntry) savedInstanceState.getSerializable(ARG_ENTRY);
        }

        updateView(entry);
        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        if (getArguments() != null) {
            updateView((DiaryEntry)getArguments().getSerializable(ARG_ENTRY));
        }
    }

    @Override
    public  void onDestroyView(){
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void updateView(DiaryEntry entry){
        contentTxtView.setText(entry.getContent());
        titleTxtView.setText(entry.getTitle());
        dateTxtView.setText(entry.getRecordedDate());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current article selection in case we need to recreate the fragment
        outState.putSerializable(ARG_ENTRY, entry);
    }



}
