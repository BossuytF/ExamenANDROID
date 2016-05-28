package com.example.fabrice.diary;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fabrice.diary.persistence.Constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewDiaryFragment extends Fragment {

    @Bind(R.id.new_diary_title)
    EditText newDiaryTitle;

    @Bind(R.id.new_diary_content)
    EditText newDiaryContent;

    public static NewDiaryFragment newInstance() {
        NewDiaryFragment fragment = new NewDiaryFragment();
        return fragment;
    }

    public NewDiaryFragment() {
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
        View view = inflater.inflate(R.layout.fragment_new_diary, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    @OnClick(R.id.add_entry_btn)
    public void addEntry(View v){
        String title = newDiaryTitle.getText().toString();
        String content = newDiaryContent.getText().toString();

        Uri mNewUri;
        Date date = new Date();

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        if(title.isEmpty()) {
            newDiaryTitle.setError(getText(R.string.error_empty_title));
        } else if(content.isEmpty()) {
            newDiaryContent.setError(getText(R.string.error_empty_content));
        } else {
            ContentValues contentValues = new ContentValues();

            contentValues.put(Constants.DIARY_COLUMN_CONTENT, content);
            contentValues.put(Constants.DIARY_COLUMN_TITLE, title);
            contentValues.put(Constants.DIARY_COLUMN_DATE, dateFormat.format(date));

            mNewUri = getActivity().getContentResolver().insert(Constants.CONTENT_URL, contentValues);

            Toast.makeText(getActivity().getBaseContext(), "New Diary-entry Added", Toast.LENGTH_SHORT).show();

            getActivity().onBackPressed();
        }
    }

}
