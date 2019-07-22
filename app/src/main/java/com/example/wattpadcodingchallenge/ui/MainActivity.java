package com.example.wattpadcodingchallenge.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;



import com.example.wattpadcodingchallenge.R;
import com.example.wattpadcodingchallenge.db.StoryModel;
import com.example.wattpadcodingchallenge.viewModel.GetStoryViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        toolbar.setTitle("WattPad ");

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<StoryModel>());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);

        GetStoryViewModel getStoryViewModel = ViewModelProviders.of(this).get(GetStoryViewModel.class);
        getStoryViewModel.getStoryList().observe(MainActivity.this, new Observer<List<StoryModel>>() {
            @Override
            public void onChanged(List<StoryModel> storyModels) {

                    recyclerViewAdapter.addItems(storyModels);                }

        });

    }
}
