package com.example.wattpadcodingchallenge.ui;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wattpadcodingchallenge.R;
import com.example.wattpadcodingchallenge.db.StoryModel;
import com.squareup.picasso.Picasso;


import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private List<StoryModel> storyModelList;


    RecyclerViewAdapter(List<StoryModel> storyModelList) {
        this.storyModelList = storyModelList;

    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder holder, int position) {
        StoryModel storyModel = storyModelList.get(position);
        holder.storyTitle.setText(storyModel.getTitle());
        holder.authorName.setText(storyModel.getUserName());
        Picasso.get().load(storyModel.getUserAvatar()).into(holder.authorPhoto);
        Picasso.get().load(storyModel.getCover()).resize(600,600).into(holder.coverPhoto);


    }

    @Override
    public int getItemCount() {
        return storyModelList.size();
    }

    void addItems(List<StoryModel> storyModelList) {
        this.storyModelList = storyModelList;
        notifyDataSetChanged();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView storyTitle;
        private TextView authorName;
        private ImageView authorPhoto;
        private ImageView coverPhoto;


        RecyclerViewHolder(View view) {
            super(view);
          storyTitle = view.findViewById(R.id.story_title);
          authorName = view.findViewById(R.id.author_name);
          authorPhoto = view.findViewById(R.id.author_photo);
          coverPhoto = view.findViewById(R.id.cover_photo);

        }
    }
}