package com.example.wattpadcodingchallenge.ui;

import android.content.Context;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import androidx.lifecycle.Observer;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.wattpadcodingchallenge.db.AppDatabase;
import com.example.wattpadcodingchallenge.db.StoryModel;
import com.example.wattpadcodingchallenge.db.StoryModelDao;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;


import java.util.Collections;
import java.util.List;



@Config(manifest=Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class SplashActivityUnitTest {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    private AppDatabase database;
    private StoryModelDao dao;


    @Mock
    private Observer<List<StoryModel>> observer;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                .allowMainThreadQueries().build();
        dao = database.storyFeedModel();

    }

    @After
    public void tearDown() throws Exception {
        database.close();
    }

    @Test
    public void addStory() throws Exception {
        // given
        StoryModel storyModels = new StoryModel("Test RoomDb", "Test", "User test Avatar","User test Cover");


        dao.addStory(storyModels);
        dao.getAllStoryItems().observeForever(observer);
        assert (Collections.singletonList(storyModels).get(0).getTitle().equals("Test RoomDb"));
        assert (Collections.singletonList(storyModels).get(0).getCover().equals("User test Cover"));
        assert (Collections.singletonList(storyModels).get(0).getUserAvatar().equals("User test Avatar"));
        assert (Collections.singletonList(storyModels).get(0).getUserName().equals("Test"));


    }

}