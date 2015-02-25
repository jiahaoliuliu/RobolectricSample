package com.jiahaoliuliu.robolectricsample;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import static org.assertj.android.api.Assertions.assertThat;

/**
 * Created by jiahao on 2/25/15.
 */
@RunWith(RobolectricGradleTestRunner.class)
public class StaggeredGridViewActivityTest {
    
    private StaggeredGridViewActivity mStaggeredGridViewActivity;

    @Before
    public void setup() {
        // Create the class
        mStaggeredGridViewActivity = Robolectric.buildActivity(StaggeredGridViewActivity.class).create().get();
    }

    @Test
    public void testLinkedViews() {
        // Check if the activity is not null
        assertThat(mStaggeredGridViewActivity).isNotNull();
    }



}
