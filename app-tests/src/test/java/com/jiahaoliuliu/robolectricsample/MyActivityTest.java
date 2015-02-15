package com.jiahaoliuliu.robolectricsample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;

import static org.assertj.android.api.Assertions.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
@Config(shadows = AppUtilsShadow.class)
public class MyActivityTest {

  private MainActivity mActivity;

  @Test
  public void testMyActivityAppearsAsExpectedInitially() {
      mActivity = Robolectric.buildActivity(MainActivity.class).create().get();
      assertThat(mActivity.my_hello_text_view).isVisible();
      assertThat(mActivity.my_hello_text_view).hasText("Hello world!");
      assertThat(mActivity.mClickMeBtn).hasText("Click Me");
  }

}
