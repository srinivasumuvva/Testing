package com.hari.restaurantdigital.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hari.restaurantdigital.fragment.IngredientsFragment;
import com.hari.restaurantdigital.fragment.ProductDetailsFragment;

/**
 * Created by MUVVASR on 10/5/2017.
 */

public class ProductDetailsFragmentsAdapter extends FragmentPagerAdapter {
    Fragment mFragment;
    public String[] mProductDetailsTitleArray = {"Details", "Ingredients", "Gallery"};

    public ProductDetailsFragmentsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return ProductDetailsFragment.getInstance(new Bundle());
        else
            return IngredientsFragment.getInstance(new Bundle());

    }

    @Override
    public int getCount() {
        return mProductDetailsTitleArray.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mProductDetailsTitleArray[position];
    }
}
