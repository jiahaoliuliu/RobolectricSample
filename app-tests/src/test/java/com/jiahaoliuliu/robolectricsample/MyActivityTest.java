package com.jiahaoliuliu.robolectricsample;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.jar.Manifest;

import static org.assertj.android.api.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(emulateSdk = 18, manifest = "../app/src/main/AndroidManifest.xml", shadows = {AppUtilsShadow.class})
public class MyActivityTest {

  private MainActivity mActivity;

  @Before
  public void setup() {
      mActivity = Robolectric.buildActivity(MainActivity.class).create().get();
  }

  @Test
  public void testMyActivityAppearsAsExpectedInitially() {
//      AppUtils appUtils = new AppUtils();
//      System.out.println("Random user number is " + appUtils.getNumberUsersRandomly());

      AppUtilsShadow appUtilsShadow = new AppUtilsShadow();
      System.out.println("Calling the shadow object " + appUtilsShadow.getNumberUsersRandomly());
      
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
