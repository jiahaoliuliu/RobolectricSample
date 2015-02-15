package com.jiahaoliuliu.robolectricsample;

import java.util.List;

/**
 * Created by jiahao on 2/12/15.
 */
public class ApplicationController {
    
    private List<Boolean> notInitializedList;
    
    private static ApplicationController mInstance;
    
    private ApplicationController() {
    }

    public static ApplicationController getInstance() {
        if (mInstance == null) {
            mInstance = new ApplicationController();
        }
        
        return mInstance;
    }
    
    public boolean isUAE() {
        // This should return nullPointerException
        return notInitializedList.get(0);
    }
}
