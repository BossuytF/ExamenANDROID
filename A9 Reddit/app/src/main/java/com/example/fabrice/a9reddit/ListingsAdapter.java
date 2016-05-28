package com.example.fabrice.a9reddit;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fabrice.a9reddit.models.ListingData;
import com.example.fabrice.a9reddit.persistence.Constants;
import com.example.fabrice.a9reddit.persistence.RedditDB;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ListingsAdapter extends RecyclerView.Adapter<ListingsAdapter.MainViewHolder>{

    private ArrayList<ListingData> data;
    public static MyClickListener myClickListener;
    public Context mContext;

    public class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @Bind(R.id.title)
        TextView listingTitle;
        @Bind(R.id.thumbnail)
        ImageView listingThumbnail;

        public MainViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(data.get(getLayoutPosition()), v);
        }
    }

    public ListingsAdapter(RedditDB dba, Context context) {
        mContext = context;
        data = new ArrayList<>();
    }

    public  void setData(Cursor c){
        setDataToNull();
        if (c != null){
            if (c.moveToFirst()) {
                do {
                    String title = c.getString(c.getColumnIndex(Constants.LISTING_NAME));
                    String thumbnail = c.getString(c.getColumnIndex(Constants.THUMBNAIL));
                    String url = c.getString(c.getColumnIndex(Constants.URL));
                    ListingData entry = new ListingData(title, url, thumbnail);
                    data.add(entry);
                }
                while (c.moveToNext());
                notifyDataSetChanged();
            }
        }
    }

    public void setDataToNull(){
        data.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(data != null){
            return data.size();
        }else
            return 0;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.thumbnailview, parent, false);
        MainViewHolder viewHolder = new MainViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListingsAdapter.MainViewHolder holder, int position) {
        ListingData l = data.get(position);
        holder.listingTitle.setText(l.getTitle());
        if (!l.getThumbnail().equals("")) {
            Picasso.with(mContext).load(l.getThumbnail()).into(holder.listingThumbnail);
        }
    }

    public void setOnItemCLickListener(MyClickListener myClickListener){
        this.myClickListener = myClickListener;
    }

    public ArrayList<ListingData> getDiaries() {
        return data;
    }

    public void setDiaries(ArrayList<ListingData> diaries) {
        this.data = diaries;
    }

    public interface MyClickListener {
        public void onItemClick(ListingData entry, View v);
    }
}
