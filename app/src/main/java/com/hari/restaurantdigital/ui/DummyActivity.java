package com.hari.restaurantdigital.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hari.restaurantdigital.R;

/**
 * Created by MUVVASR on 11/8/2017.
 */

public class DummyActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filters);
    }
}
