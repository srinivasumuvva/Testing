package com.hari.restaurantdigital.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.hari.restaurantdigital.R;
import com.hari.restaurantdigital.base.BaseFragment;

/**
 * Created by MUVVASR on 10/5/2017.
 */

public class ProductDetailsFragment extends BaseFragment {
    static  int mPosition;
    public static Fragment getInstance(Bundle pBundle,int pPosition) {
        mPosition=pPosition;
        ProductDetailsFragment productDetailsFragment = new ProductDetailsFragment();
        productDetailsFragment.setArguments(pBundle);
        return productDetailsFragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.product_details_activity;
    }

    @Override
    protected String getTitleResId() {
        return null;
    }
}
