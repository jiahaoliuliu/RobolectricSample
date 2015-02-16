package com.jiahaoliuliu.robolectricsample;

import com.jiahaoliuliu.robolectricsample.MainActivity;
import com.jiahaoliuliu.robolectricsample.RobolectricGradleTestRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import static org.assertj.android.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricGradleTestRunner.class)
@Config(shadows = {AppUtilsShadow.class, MyShadowBitmap.class})
public class MyActivityTest {

  private MainActivity mActivity;

  @Test
  @Config(shadows = AppUtilsShadow.class)
  public void testMyActivityAppearsAsExpectedInitially() {
      mActivity = Robolectric.buildActivity(MainActivity.class).create().get();
    assertThat(mActivity.my_hello_text_view).isVisible();
    assertThat(mActivity.my_hello_text_view).hasText("Hello world!");
    assertThat(mActivity.mClickMeBtn).hasText("Click Me");
  }
}
