package com.example.wattpadcodingchallenge.api;
import com.example.wattpadcodingchallenge.generated.model.StoryFeed;



import retrofit2.Call;
import retrofit2.http.GET;

public class RetrofitInterface {

    public interface StroryFeedClient {
        @GET("api/v3/stories?offset=0&limit=10&fields=stories(id,title,cover,user)&filter=new")
       Call<StoryFeed> storyFeedList();
    }



}
