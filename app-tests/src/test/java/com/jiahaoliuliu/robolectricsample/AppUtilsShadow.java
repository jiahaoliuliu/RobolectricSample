package com.jiahaoliuliu.robolectricsample;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;
import org.robolectric.annotation.RealObject;
import org.robolectric.shadows.ShadowView;

/**
 * Created by jiahao on 2/15/15.
 */
@Implements(AppUtils.class)
public class AppUtilsShadow {

    @Implementation
    public int generateNumberUsersRandomly() {
        return 2000;
    }
}