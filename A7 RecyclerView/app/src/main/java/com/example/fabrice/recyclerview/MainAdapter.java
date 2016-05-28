package com.example.fabrice.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder>{

    private ArrayList<Character> characterses;
    public static MyClickListener myClickListener;

    public class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @Bind(R.id.character_image)
        ImageView charImage;
        @Bind(R.id.character_name)
        TextView charName;

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

    public MainAdapter (ArrayList<Character> characterses){
        this.characterses = characterses;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        MainViewHolder viewHolder = new MainViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MainAdapter.MainViewHolder holder, int position) {
        holder.charImage.setImageResource(characterses.get(position).getImageId());
        holder.charName.setText(characterses.get(position).getCharacterName());
    }

    @Override
    public int getItemCount() {
        return characterses.size();
    }

    public void setOnItemCLickListener(MyClickListener myClickListener){
        this.myClickListener = myClickListener;
    }

    public ArrayList<Character> getCharacterses() {
        return characterses;
    }

    public void setCharacterses(ArrayList<Character> characterses) {
        this.characterses = characterses;
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}

