package com.example.wattpadcodingchallenge.api;
import com.example.wattpadcodingchallenge.generated.model.StoryFeed;



import retrofit2.Call;
import retrofit2.http.GET;

public class RetrofitInterface {

    public interface StroryFeedClient {
        @GET("api/v3/stories?fields=stories%28id%2Ctitle%2Ccover%2Cuser%29&filter=new&limit=10&offset=10")
        Call<StoryFeed> storyFeedList();
    }



}
