package com.hari.restaurantdigital.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.hari.restaurantdigital.R;
import com.hari.restaurantdigital.adapter.ProductDetailsFragmentsAdapter;
import com.hari.restaurantdigital.base.BaseActivity;

/**
 * Created by MUVVASR on 10/5/2017.
 */

public class ProductDetailsActivity extends BaseActivity {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private ProductDetailsFragmentsAdapter mViewPagerAdapter;


    public int[] mProductDetailsIconArray={R.drawable.ic_details_black_36dp,R.drawable.ic_ingredients_list,R.drawable.ic_photo_library_black_36dp};
    @Override
    protected int getLayoutResourceId() {
        return R.layout.product_details;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewPager();
        initTabIcons();
    }

    private void initTabIcons() {
        for(int index=0;index<mProductDetailsIconArray.length;index++){
            mTabLayout.getTabAt(index).setIcon(mProductDetailsIconArray[index]);
        }
    }


    private void initViewPager() {
        mViewPager=(ViewPager)findViewById(R.id.product_details_viewpager);
        mTabLayout=(TabLayout)findViewById(R.id.product_details_tabs);
        mViewPager.setOffscreenPageLimit(2);
        mViewPagerAdapter = new ProductDetailsFragmentsAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);


    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle("Details ");
        }
    }
}
