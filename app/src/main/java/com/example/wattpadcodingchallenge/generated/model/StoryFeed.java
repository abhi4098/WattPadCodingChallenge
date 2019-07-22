
package com.example.wattpadcodingchallenge.generated.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StoryFeed {

    @SerializedName("stories")
    @Expose
    private List<Story> stories = null;
    @SerializedName("nextUrl")
    @Expose
    private String nextUrl;

    public List<Story> getStories() {
        return stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    public String getNextUrl() {
        return nextUrl;
    }

    public void setNextUrl(String nextUrl) {
        this.nextUrl = nextUrl;
    }

}
