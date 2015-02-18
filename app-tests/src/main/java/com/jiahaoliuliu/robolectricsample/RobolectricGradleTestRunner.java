package com.jiahaoliuliu.robolectricsample;

import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.internal.bytecode.ClassInfo;
import org.robolectric.internal.bytecode.InstrumentingClassLoaderConfig;
import org.robolectric.manifest.AndroidManifest;
import org.robolectric.res.Fs;

public class RobolectricGradleTestRunner extends RobolectricTestRunner {
    private static final int MAX_SDK_SUPPORTED_BY_ROBOLECTRIC = 18;

    /**
     * The list of the customized shadows classes name.
     * Note they should be full qualified.
     */
    private static final String[] CUSTOMIZED_SHADOWS_NAME = {
        "com.jiahaoliuliu.robolectricsample.AppUtils"
    };

    // Since the Android manifest is in another module, System.getProperty
    // for "android.manifest", "android.resources" and "android.assets" returns null,
    // so it is not possible use them.
    // Instead of that, the directory are hard coded
    private static final String APP_ROOT_DIRECTORY = "../app/src/main/";
    private static final String ANDROID_MANIFEST_FILE_NAME = "AndroidManifest.xml";
    private static final String RES_DIR = "res";
    private static final String ASSETS_DIR = "assets";

    public RobolectricGradleTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
    }

    @Override
    protected AndroidManifest getAppManifest(Config config) {

        String manifestPath = APP_ROOT_DIRECTORY + ANDROID_MANIFEST_FILE_NAME;
        String resDir = APP_ROOT_DIRECTORY + RES_DIR;
        String assetsDir = APP_ROOT_DIRECTORY + ASSETS_DIR;
        AndroidManifest manifest = new AndroidManifest(Fs.fileFromPath(manifestPath),
                Fs.fileFromPath(resDir),
                Fs.fileFromPath(assetsDir)) {
            @Override
            public int getTargetSdkVersion() {
                return MAX_SDK_SUPPORTED_BY_ROBOLECTRIC;
            }
        };

        return manifest;
    }

    @Override
    public InstrumentingClassLoaderConfig createSetup() {
        return new InstrumentingClassLoaderConfig() {
            @Override
            public boolean shouldInstrument(ClassInfo classInfo) {
                for (String customizedShadowsName : CUSTOMIZED_SHADOWS_NAME) {
                    if (classInfo.getName().equalsIgnoreCase(customizedShadowsName)) {
                        return true;
                    }
                }
                return super.shouldInstrument(classInfo);
            };
        };
    }
}