package com.hari.restaurantdigital.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MenuItem;
import android.view.View;

import com.hari.restaurantdigital.Model.Dish;
import com.hari.restaurantdigital.R;
import com.hari.restaurantdigital.adapter.DishListAdapter;
import com.hari.restaurantdigital.base.BaseFragment;
import com.hari.restaurantdigital.interfaces.DishSelectedInterface;
import com.hari.restaurantdigital.ui.MainActivity;

import java.util.ArrayList;
import java.util.HashSet;


/**
 * Created by Admin on 04-06-2015.
 */
public class ContentFragment extends BaseFragment implements DishSelectedInterface {

    // TwoWayView mRecyclerView;
    RecyclerView mRecyclerView;
    LinearLayoutManager mLinearLayoutManager;
    DishListAdapter mDishListAdapter;
    public static ArrayList mDishArrayList;
    int dishImages[] = {R.drawable.nv_item1, R.drawable.nv_item2, R.drawable.nv_item3, R.drawable.nv_item4, R.drawable.nv_item5, R.drawable.nv_item6};
    ArrayList<Dish> mSelectedList = new ArrayList<>();
    HashSet<Integer> mSelectedListPosition = new HashSet<>();
    MenuItem menuItem;

    @Override
    protected int getLayoutResId() {
        return R.layout.content_fragment;
    }

    @Override
    protected String getTitleResId() {
        return "";
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prepareData();
        initViews(view);
    }


    private void prepareData() {
        mDishArrayList = new ArrayList();
        Dish dish = new Dish(1, dishImages[getRandomNumberInRange(0, 5)], "Time : 5 Min", "Price : 5$", "Chicken 65", "jlsjfakljsafdklj");
        mDishArrayList.add(dish);
        dish = new Dish(1, dishImages[getRandomNumberInRange(0, 5)], "Time : 2 Min", "Price : 5$", "Guntur Chicken ", "khasfkjnhakfj");
        mDishArrayList.add(dish);
        dish = new Dish(1, dishImages[getRandomNumberInRange(0, 5)], "Time : 2 Min", "Price : 5$", "Chicken Fry", "jaskhklahklg");
        mDishArrayList.add(dish);
        dish = new Dish(1, dishImages[getRandomNumberInRange(0, 5)], "Time : 2 Min", "Price : 5$", "Chicken Biriyani", "jagajhaasfkjlhsafk");
        mDishArrayList.add(dish);
        dish = new Dish(1, dishImages[getRandomNumberInRange(0, 5)], "Time : 2 Min", "Price : 5$", "Mutton BIriyani", "afjkljaafo");
        mDishArrayList.add(dish);
        dish = new Dish(1, dishImages[getRandomNumberInRange(0, 5)], "Time : 2 Min", "Price : 5$", "Fish Biriyani", "ajklaklgjjsaf");
        mDishArrayList.add(dish);
        dish = new Dish(1, dishImages[getRandomNumberInRange(0, 5)], "Time : 2 Min", "Price : 5$", "Fish Fry", "jafafklaf");
        mDishArrayList.add(dish);
        dish = new Dish(1, dishImages[getRandomNumberInRange(0, 5)], "Time : 2 Min", "Price : 5$", "Chicken Tikka", "jafsafkl");
        mDishArrayList.add(dish);
        dish = new Dish(1, dishImages[getRandomNumberInRange(0, 5)], "Time : 2 Min", "Price : 5$", "Chilli Chicken ", "jaklafal;fsal;fkjjkl");
        mDishArrayList.add(dish);
        dish = new Dish(1, dishImages[getRandomNumberInRange(0, 5)], "Time : 2 Min", "Price : 5$", "Mutton khimma", "jsafklsajflsaf");
        mDishArrayList.add(dish);
    }

    private static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }

    private void initViews(View pView) {
        mRecyclerView = (RecyclerView) pView.findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        //  mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setHasFixedSize(true);
        mDishListAdapter = new DishListAdapter(ContentFragment.this);
        mDishListAdapter.setData(mDishArrayList);
        mRecyclerView.setAdapter(mDishListAdapter);


    }


    @Override
    public void onDishSelected(Dish pDish, int pPostion) {
        if (mSelectedListPosition.add(pPostion)) {
            mSelectedList.add(pDish);
            ActivityCompat.invalidateOptionsMenu(getActivity());
        } else {
            mSelectedListPosition.remove(pPostion);
            ActivityCompat.invalidateOptionsMenu(getActivity());
        }

        ((MainActivity) getActivity()).cartSelected(mSelectedListPosition.size());
    }

    @Override
    public void onFavouirteSelected(int pPosition) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("images", mDishArrayList);
        bundle.putInt("position", pPosition);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        SlideshowDialogFragment newFragment = SlideshowDialogFragment.newInstance();
        newFragment.setArguments(bundle);
        newFragment.show(ft, "slideshow");
    }
}
