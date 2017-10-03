package com.hari.restaurantdigital.base;

import android.app.Application;
import android.util.DisplayMetrics;

/**
 * Created by MUVVASR on 9/28/2017.
 */

public class ApplicationObj extends Application {
    public static Application mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication=this;

    }

}
