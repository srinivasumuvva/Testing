package com.hari.restaurantdigital.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hari.restaurantdigital.Model.IngredientsModel;
import com.hari.restaurantdigital.R;
import com.hari.restaurantdigital.adapter.IngredientsListAdapter;
import com.hari.restaurantdigital.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by MUVVASR on 10/5/2017.
 */

public class IngredientsFragment extends BaseFragment {

    ArrayList<IngredientsModel> mIngredientsModelArrayList = new ArrayList<>();
    private RecyclerView mIngredientRecyclerView;
    IngredientsListAdapter mIngredientsListAdapter;

    public static Fragment getInstance(Bundle pBundle) {
        IngredientsFragment productDetailsFragment = new IngredientsFragment();
        productDetailsFragment.setArguments(pBundle);
        return productDetailsFragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initViews(view);
    }

    private void initData() {
        IngredientsModel ingredientsModel = new IngredientsModel("1/4 kg", "Maidha");
        mIngredientsModelArrayList.add(ingredientsModel);
        ingredientsModel = new IngredientsModel("1/4 kg", "Maidha");
        mIngredientsModelArrayList.add(ingredientsModel);
        ingredientsModel = new IngredientsModel("1/3 kg", "Maidha");
        mIngredientsModelArrayList.add(ingredientsModel);
        ingredientsModel = new IngredientsModel("1/2 kg", "Maidha");
        mIngredientsModelArrayList.add(ingredientsModel);
        ingredientsModel = new IngredientsModel("2/4 kg", "Maidha");
        mIngredientsModelArrayList.add(ingredientsModel);
        ingredientsModel = new IngredientsModel("3/4 kg", "Maidha");
        mIngredientsModelArrayList.add(ingredientsModel);
        ingredientsModel = new IngredientsModel("2/4 kg", "Maidha");
        mIngredientsModelArrayList.add(ingredientsModel);

    }

    private void initViews(View pView) {
        mIngredientRecyclerView = (RecyclerView) pView.findViewById(R.id.ingredient_recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        mIngredientRecyclerView.setLayoutManager(gridLayoutManager);
        mIngredientRecyclerView.setHasFixedSize(true);
        mIngredientsListAdapter = new IngredientsListAdapter();
        mIngredientsListAdapter.setData(mIngredientsModelArrayList);
        mIngredientRecyclerView.setAdapter(mIngredientsListAdapter);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.ingredients;
    }

    @Override
    protected String getTitleResId() {
        return null;
    }
}
