package com.example.wattpadcodingchallenge.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.wattpadcodingchallenge.db.AppDatabase;
import com.example.wattpadcodingchallenge.db.StoryModel;

import java.util.List;

public class GetStoryViewModel extends AndroidViewModel {

    private final LiveData<List<StoryModel>> storyList;

    private AppDatabase appDatabase;

    public GetStoryViewModel(@NonNull Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());
        storyList =appDatabase.storyFeedModel().getAllStoryItems();
    }

    public LiveData<List<StoryModel>> getStoryList() {
           return storyList;
    }
}
