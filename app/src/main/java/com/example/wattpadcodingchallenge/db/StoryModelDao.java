package com.example.wattpadcodingchallenge.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface StoryModelDao {

    @Query("select * from StoryModel")
    LiveData<List<StoryModel>> getAllStoryItems();

    @Insert(onConflict = REPLACE)
    void addStory(StoryModel storyModel);
}
