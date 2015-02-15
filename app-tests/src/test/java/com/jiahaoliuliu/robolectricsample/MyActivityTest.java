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
@Config(shadows = {AppUtilsShadow.class})
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

  @Test
  public void testMockitoIntegration() {
      //Testing mockito
      List mockedList = mock(List.class);

      // Using mock object
      mockedList.add("one");
      mockedList.clear();

      // Verification
      verify(mockedList).add("one");
      verify(mockedList).clear();
  }
}
