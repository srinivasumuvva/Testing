package com.hari.restaurantdigital.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hari.restaurantdigital.fragment.ContentFragment;
import com.hari.restaurantdigital.fragment.ProductDetailsFragment;

/**
 * Created by MUVVASR on 10/5/2017.
 */

public class ProductDetailsFragmentsAdapter extends FragmentPagerAdapter {


    public ProductDetailsFragmentsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        return ProductDetailsFragment.getInstance(new Bundle(), position);


    }

    @Override
    public int getCount() {
        return ContentFragment.mDishArrayList.size();
    }


}
