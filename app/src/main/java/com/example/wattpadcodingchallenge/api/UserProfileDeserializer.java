package com.example.wattpadcodingchallenge.api;



import com.example.wattpadcodingchallenge.generated.model.StoryFeed;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;


public class UserProfileDeserializer implements JsonDeserializer<StoryFeed> {

    @Override
    public StoryFeed deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new GsonBuilder().create();

        StoryFeed storyFeedResponse = gson.fromJson(json, StoryFeed.class);

        JsonObject jsonObject = json.getAsJsonObject();


        return storyFeedResponse;
    }
}
