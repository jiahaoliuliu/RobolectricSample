package com.jiahaoliuliu.robolectricsample;

import android.app.Activity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertTrue;

/**
 * Created by jiahao on 2/3/15.
 */

@Config(manifest = "./src/main/AndroidManifest.xml", emulateSdk = 18)
@RunWith(MyRobolectricTestRunner.class)
public class MainActivityTest {

    @Test
    public void sampleTest() throws Exception {
        Activity activity = Robolectric.buildActivity(MainActivity.class).create().get();
        assertTrue(activity != null);

    }
}