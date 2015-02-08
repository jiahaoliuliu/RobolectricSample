package com.jiahaoliuliu.robolectricsample;

import com.jiahaoliuliu.robolectricsample.MainActivity;
import com.jiahaoliuliu.robolectricsample.RobolectricGradleTestRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.android.api.Assertions.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
@Config(emulateSdk = 18, reportSdk = 18)
public class MyActivityTest {

  private MainActivity mActivity;

  @Before
  public void setup() {
    mActivity = Robolectric.buildActivity(MainActivity.class).create().get();
  }

  @Test
  public void testMyActivityAppearsAsExpectedInitially() {
    assertThat(mActivity.my_hello_text_view).isVisible();
    assertThat(mActivity.my_hello_text_view).hasText("Hello world!");
    assertThat(mActivity.mClickMeBtn).hasText("Click Me");
  }

  @Test
  public void testClickingClickMeButtonChangesHelloWorldText() {
    assertThat(mActivity.my_hello_text_view).hasText(R.string.hello_world);
    mActivity.mClickMeBtn.performClick();
    assertThat(mActivity.my_hello_text_view).hasText(R.string.ok_thanks);
  }
}
