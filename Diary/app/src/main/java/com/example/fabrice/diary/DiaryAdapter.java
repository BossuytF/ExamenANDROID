package com.example.fabrice.diary;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fabrice.diary.persistence.Constants;
import com.example.fabrice.diary.persistence.MyDB;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.MainViewHolder> {

    //private MyDB dba;
    private ArrayList<DiaryEntry> diaries;
    public static MyClickListener myClickListener;

    public class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @Bind(R.id.diary_title)
        TextView diaryTitle;
        @Bind(R.id.diary_date)
        TextView diaryDate;

        public MainViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getLayoutPosition(), v);
        }
    }

    public DiaryAdapter(MyDB dba, Context context) {
        //this.dba = dba;
    }

    /**
     * Retrieves the data from the database and populates the arraylist
     */
    public  void setData(Cursor c){
        if (c.moveToFirst()) {
            do {
                String title = c.getString(c.getColumnIndex(Constants.DIARY_COLUMN_TITLE));
                String content = c.getString(c.getColumnIndex(Constants.DIARY_COLUMN_CONTENT));
                String date = c.getString(c.getColumnIndex(Constants.DIARY_COLUMN_DATE));
                DiaryEntry entry = new DiaryEntry(title, content, date);
                diaries.add(entry);
            }
            while (c.moveToNext());
            notifyDataSetChanged();
        }
    }

    public void setDataToNull(){
        diaries.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return diaries.size();
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        MainViewHolder viewHolder = new MainViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DiaryAdapter.MainViewHolder holder, int position) {
        holder.diaryDate.setText(diaries.get(position).getRecordedDate());
        holder.diaryTitle.setText(diaries.get(position).getTitle());
    }

    public void setOnItemCLickListener(MyClickListener myClickListener){
        this.myClickListener = myClickListener;
    }

    public ArrayList<DiaryEntry> getDiaries() {
        return diaries;
    }

    public void setDiaries(ArrayList<DiaryEntry> diaries) {
        this.diaries = diaries;
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}
