package com.jiahaoliuliu.robolectricsample;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;

/**
 * Created by jiahao on 2/15/15.
 */
@Implements(AppUtils.class)
public class AppUtilsShadow {

    public void __constructor__() {
        System.out.println("The constructor of the app utils shadow is invoked");
    }
    
    @Implementation
    public int getNumberUsersRandomly() {
        // Shadowed result
        return 2000;
    }
}
