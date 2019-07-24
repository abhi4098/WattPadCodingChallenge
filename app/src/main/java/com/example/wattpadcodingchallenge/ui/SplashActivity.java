package com.example.wattpadcodingchallenge.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.wattpadcodingchallenge.R;
import com.example.wattpadcodingchallenge.api.ApiAdapter;
import com.example.wattpadcodingchallenge.api.RetrofitInterface;
import com.example.wattpadcodingchallenge.db.StoryModel;
import com.example.wattpadcodingchallenge.generated.model.Story;
import com.example.wattpadcodingchallenge.generated.model.StoryFeed;
import com.example.wattpadcodingchallenge.utils.LoadingDialog;
import com.example.wattpadcodingchallenge.utils.NetworkUtils;
import com.example.wattpadcodingchallenge.viewModel.AddStoryViewModel;
import com.example.wattpadcodingchallenge.viewModel.GetStoryViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.wattpadcodingchallenge.api.ApiEndPoints.BASE_URL;


public class SplashActivity extends AppCompatActivity {

    Context mContext;
    AddStoryViewModel addStoryViewModel;
    private GetStoryViewModel getStoryViewModel;
    TextView errorText;

    private RetrofitInterface.StroryFeedClient StoryFeedAdapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        mContext = SplashActivity.this;
        errorText = findViewById(R.id.error_text);
        getStoryViewModel = ViewModelProviders.of(this).get(GetStoryViewModel.class);
        addStoryViewModel = ViewModelProviders.of(this).get(AddStoryViewModel.class);
        LoadData();
    }


    private void LoadData() {
        setUpRestAdapter();
        getStoryFeedList();
    }

    private void getStoryFeedList() {
        LoadingDialog.showLoadingDialog(this,"Sync Data...");
         Call<StoryFeed> call = StoryFeedAdapter.storyFeedList();
        if (NetworkUtils.isNetworkConnected(this)) {
            call.enqueue(new Callback<StoryFeed>() {

                @Override
                public void onResponse(@NonNull Call<StoryFeed>  call, @NonNull Response<StoryFeed> response) {
                    if (response.isSuccessful()) {

                        saveStoryFeedList(response);
                        LoadingDialog.cancelLoading();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<StoryFeed> call, @NonNull Throwable t) {


                    LoadingDialog.cancelLoading();
                }


            });


        }
        else {

            getStoryViewModel.getStoryList().observe(SplashActivity.this, new Observer<List<StoryModel>>() {
                @Override
                public void onChanged(List<StoryModel> storyModels) {

                    if(storyModels.size() != 0)
                        openDialogBox();
                    else
                        errorText.setVisibility(View.VISIBLE);

                }});

            LoadingDialog.cancelLoading();
        }
    }

    private void openDialogBox() {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Use App in offline Mode ?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        openNextActivity();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();

    }

    private void saveStoryFeedList(Response<StoryFeed> response) {

        assert response.body() != null;
        List<Story> storyResponse = response.body().getStories();

       for(int i=0; i< storyResponse.size(); i++) {
           addStoryViewModel.addStory( new StoryModel(
                   storyResponse.get(i).getTitle(),
                   storyResponse.get(i).getUser().getName(),
                   storyResponse.get(i).getUser().getAvatar(),
                   storyResponse.get(i).getCover()
           ));
       }

        openNextActivity();
    }

    private void openNextActivity() {
        Intent i = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private void setUpRestAdapter() {
        StoryFeedAdapter = ApiAdapter.createRestAdapter(RetrofitInterface.StroryFeedClient.class, BASE_URL, this);

    }

}


