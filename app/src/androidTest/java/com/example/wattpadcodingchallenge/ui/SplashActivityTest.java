package com.example.wattpadcodingchallenge.ui;


import android.view.View;


import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.wattpadcodingchallenge.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
@RunWith(AndroidJUnit4.class)
public class SplashActivityTest {

    @Rule
    public ActivityTestRule<SplashActivity> mActivityTestRule = new ActivityTestRule<SplashActivity>(SplashActivity.class);
    SplashActivity mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }





    @Test
    public void testLaunch ()
    {
        View view =mActivity.findViewById(R.id.error_text);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {


        mActivity = null;

    }
}