package com.example.wattpadcodingchallenge.ui;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.example.wattpadcodingchallenge.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.junit.Assert.*;

public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class,true,true);
    private MainActivity mActivity = null;


    @Before
    public void setUp() throws Exception {

        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch ()
    {
        View view =mActivity.findViewById(R.id.recyclerView);
        assertNotNull(view);
    }

    @Test
    public void testSampleRecyclerVisible() {
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(mActivity.getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testCaseForRecyclerClick() {
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(mActivity.getWindow().getDecorView())))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }


    @Test
    public void testCaseForRecyclerScroll() {

        // Get total item of RecyclerView
        RecyclerView recyclerView = mActivity.findViewById(R.id.recyclerView);
        int itemCount = recyclerView.getAdapter().getItemCount();

        // Scroll to end of page with position
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(mActivity.getWindow().getDecorView())))
                .perform(RecyclerViewActions.scrollToPosition(itemCount - 1));
    }


    @Test
    public void testCaseForRecyclerItemView() {

        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(mActivity.getWindow().getDecorView())))
                .check(matches(withViewAtPosition(1, Matchers.allOf(
                        ViewMatchers.withId(R.id.recyclerView_item), isDisplayed()))));
    }


    public Matcher<View> withViewAtPosition(final int position, final Matcher<View> itemMatcher) {
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                itemMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(RecyclerView recyclerView) {
                final RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
                return viewHolder != null && itemMatcher.matches(viewHolder.itemView);
            }
        };

    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }
}