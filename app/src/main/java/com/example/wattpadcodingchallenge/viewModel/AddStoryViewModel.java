package com.example.wattpadcodingchallenge.viewModel;

import android.app.Application;
import android.os.AsyncTask;


import androidx.lifecycle.AndroidViewModel;

import com.example.wattpadcodingchallenge.db.AppDatabase;
import com.example.wattpadcodingchallenge.db.StoryModel;

public class AddStoryViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;

    public AddStoryViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());
    }



    public void addStory(final StoryModel storyModel) {
        new addAsyncTask(appDatabase).execute(storyModel);
    }



    private static class addAsyncTask extends AsyncTask<StoryModel, Void, Void> {

        private AppDatabase db;

        addAsyncTask(AppDatabase appDatabase) {
            db  = appDatabase;
        }

        @Override
        protected Void doInBackground( final StoryModel... params) {
            db.storyFeedModel().addStory(params[0]);
            return null;
        }
    }


}
