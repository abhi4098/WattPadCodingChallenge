package com.example.wattpadcodingchallenge.api;

import android.content.Context;


import com.example.wattpadcodingchallenge.generated.model.StoryFeed;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiAdapter {



    private ApiAdapter() {
    }


    public static <A> A createRestAdapter(Class<A> AdapterClass, String baseUrl, Context context){


        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(StoryFeed.class,new UserProfileDeserializer());
        Gson gson = gsonBuilder.serializeNulls().create();

        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(Client.getClientInstance(context))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return restAdapter.create(AdapterClass);


    }


}
