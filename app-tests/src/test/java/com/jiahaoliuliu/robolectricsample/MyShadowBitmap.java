package com.jiahaoliuliu.robolectricsample;

import android.graphics.Bitmap;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;
import org.robolectric.annotation.RealObject;
import org.robolectric.shadows.ShadowBitmap;

import java.io.OutputStream;

/**
 * Created by jiahao on 2/15/15.
 */
@Implements(Bitmap.class)
public class MyShadowBitmap extends ShadowBitmap {
    @RealObject
    private Bitmap realBitmap;
    private int bitmapQuality = -1;

    @Implementation
    public boolean compress(Bitmap.CompressFormat format, int quality, OutputStream stream) {
        bitmapQuality = quality;
        System.out.println("Using the shadow to compress");
        return true;
    }
}