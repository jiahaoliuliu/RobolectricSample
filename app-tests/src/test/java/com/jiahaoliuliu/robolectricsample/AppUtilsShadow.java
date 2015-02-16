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

    @RealObject
    private AppUtils appUtils;
    
    public AppUtilsShadow() {}
    
    // Trying to create the constructor for it.
    public void __constructor__() {}

    @Implementation
    public int generateNumberUsersRandomly() {
        System.out.println("Invoking the real object method");
        return appUtils.generateNumberUsersRandomly();
    }
}