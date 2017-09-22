package com.hari.restaurantdigital.fragment;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hari.restaurantdigital.Model.Dish;
import com.hari.restaurantdigital.R;
import com.hari.restaurantdigital.adapter.DishListAdapter;
import com.hari.restaurantdigital.interfaces.DishSelectedInterface;

import java.util.ArrayList;
import java.util.HashSet;


/**
 * Created by Admin on 04-06-2015.
 */
public class ContentFragment extends Fragment implements DishSelectedInterface {

    RecyclerView mRecyclerView;
    LinearLayoutManager mLinearLayoutManager;
    DishListAdapter mDishListAdapter;
    ArrayList mDishArrayList;
    int dishImages[] = {R.drawable.nv_item1,R.drawable.nv_item2, R.drawable.nv_item3, R.drawable.nv_item4,R.drawable.nv_item5,R.drawable.nv_item6};
    ArrayList<Dish> mSelectedList = new ArrayList<>();
    HashSet<Integer> mSelectedListPosition=new HashSet<>();
    MenuItem menuItem;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_main, container, false);
        prepareData();
        initViews(view);
        return view;
    }

    private void prepareData() {
        mDishArrayList = new ArrayList();
        Dish dish = new Dish(1, dishImages[getRandomNumberInRange(0, 5)], "Time : 5 Min", "Price : 5$", "Chicken 65");
        mDishArrayList.add(dish);
        dish = new Dish(1, dishImages[getRandomNumberInRange(0, 5)], "Time : 2 Min", "Price : 5$", "Guntur Chicken ");
        mDishArrayList.add(dish);
        dish = new Dish(1, dishImages[getRandomNumberInRange(0, 5)], "Time : 2 Min", "Price : 5$", "Chicken Fry");
        mDishArrayList.add(dish);
        dish = new Dish(1, dishImages[getRandomNumberInRange(0, 5)], "Time : 2 Min", "Price : 5$", "Chicken Biriyani");
        mDishArrayList.add(dish);
        dish = new Dish(1, dishImages[getRandomNumberInRange(0, 5)], "Time : 2 Min", "Price : 5$", "Mutton BIriyani");
        mDishArrayList.add(dish);
        dish = new Dish(1, dishImages[getRandomNumberInRange(0, 5)], "Time : 2 Min", "Price : 5$", "Fish Biriyani");
        mDishArrayList.add(dish);
        dish = new Dish(1, dishImages[getRandomNumberInRange(0, 5)], "Time : 2 Min", "Price : 5$", "Fish Fry");
        mDishArrayList.add(dish);
        dish = new Dish(1, dishImages[getRandomNumberInRange(0, 5)], "Time : 2 Min", "Price : 5$", "Chicken Tikka");
        mDishArrayList.add(dish);
        dish = new Dish(1, dishImages[getRandomNumberInRange(0, 5)], "Time : 2 Min", "Price : 5$", "Chilli Chicken ");
        mDishArrayList.add(dish);
        dish = new Dish(1, dishImages[getRandomNumberInRange(0, 5)], "Time : 2 Min", "Price : 5$", "Mutton khimma");
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
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager gaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 0);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mDishListAdapter = new DishListAdapter(ContentFragment.this);
        mDishListAdapter.setData(mDishArrayList);
        mRecyclerView.setAdapter(mDishListAdapter);


    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear(); //Empty the old menu
        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);
        menuItem = menu.findItem(R.id.action_cart);
        menuItem.setIcon(buildCounterDrawable(mSelectedList.size(), R.drawable.cart));
        super.onCreateOptionsMenu(menu, inflater);
    }


    private Drawable buildCounterDrawable(int count, int backgroundImageId) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.add_cart_counter, null);
        view.setBackgroundResource(backgroundImageId);

        if (count == 0) {
            View counterTextPanel = view.findViewById(R.id.counterValuePanel);
            counterTextPanel.setVisibility(View.GONE);
        } else {
            TextView textView = (TextView) view.findViewById(R.id.count);
            textView.setText("" + count);
        }

        view.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);

        return new BitmapDrawable(getResources(), bitmap);
    }

    @Override
    public void onDishSelected(Dish pDish,int pPostion) {
        if(mSelectedListPosition.add(pPostion)) {
            mSelectedList.add(pDish);
            ActivityCompat.invalidateOptionsMenu(getActivity());
        }
    }
}
