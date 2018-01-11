package com.hari.restaurantdigital.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hari.restaurantdigital.R;
import com.hari.restaurantdigital.adapter.RelatedProductsListAdapter;
import com.hari.restaurantdigital.fragment.ContentFragment;

/**
 * Created by MUVVASR on 1/10/2018.
 */

public class ProductDetailsRecyclerView extends Activity {
    RecyclerView mRecyclerView;
    RelatedProductsListAdapter mProductListRecyclerViewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }


    private void initViews() {
        setContentView(R.layout.product_details_recyclerview);
        mRecyclerView = (RecyclerView) findViewById(R.id.product_details_recylerecycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        mProductListRecyclerViewAdapter=new RelatedProductsListAdapter();
        mProductListRecyclerViewAdapter.setData(ContentFragment.mDishArrayList);
        mRecyclerView.setAdapter(mProductListRecyclerViewAdapter);
    }


}
