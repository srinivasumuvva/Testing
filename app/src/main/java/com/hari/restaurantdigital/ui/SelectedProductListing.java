package com.hari.restaurantdigital.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.hari.restaurantdigital.Model.SelectProductModel;
import com.hari.restaurantdigital.R;
import com.hari.restaurantdigital.adapter.SelectedProductListAdapter;
import com.hari.restaurantdigital.base.BaseActivity;

import java.util.ArrayList;

/**
 * Created by MUVVASR on 10/5/2017.
 */

public class SelectedProductListing extends BaseActivity {

    ArrayList<SelectProductModel> mSelectProductModelArrayList = new ArrayList<>();
    RecyclerView mRecyclerView;
    SelectedProductListAdapter mSelectedProductListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_order:
                Toast.makeText(SelectedProductListing.this,"Order is submited ",Toast.LENGTH_LONG).show();
                return  true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.save_order, menu);
        //  MenuItem menuItem = menu.findItem(id.action_cart);
        //  menuItem.setIcon(buildCounterDrawable(2, R.drawable.shoping_cart));
        return true;
    }

    private void initData() {
        SelectProductModel selectProductModel = new SelectProductModel("Chicken Biriyani", 1.00f, 1, "rs");
        mSelectProductModelArrayList.add(selectProductModel);
        selectProductModel = new SelectProductModel("Chicken Biriyani", 1.00f, 1, "rs");
        mSelectProductModelArrayList.add(selectProductModel);
        selectProductModel = new SelectProductModel("Chicken Biriyani", 4.00f, 1, "rs");
        mSelectProductModelArrayList.add(selectProductModel);
        selectProductModel = new SelectProductModel("Chicken Biriyani", 3.00f, 1, "rs");
        mSelectProductModelArrayList.add(selectProductModel);
        selectProductModel = new SelectProductModel("Chicken Biriyani", 3.00f, 1, "rs");
        mSelectProductModelArrayList.add(selectProductModel);
        selectProductModel = new SelectProductModel("Chicken Biriyani", 2.00f, 1, "rs");
        mSelectProductModelArrayList.add(selectProductModel);
        selectProductModel = new SelectProductModel("Chicken Biriyani", 2.00f, 1, "rs");
        mSelectProductModelArrayList.add(selectProductModel);
        selectProductModel = new SelectProductModel("Chicken Biriyani", 5.00f, 1, "rs");
        mSelectProductModelArrayList.add(selectProductModel);
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.selected_product_list_recyclerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(SelectedProductListing.this, 1);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mSelectedProductListAdapter = new SelectedProductListAdapter();
        mSelectedProductListAdapter.setData(mSelectProductModelArrayList);
        mRecyclerView.setAdapter(mSelectedProductListAdapter);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.selected_product_list;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle("Ordered List");
        }
    }
}
