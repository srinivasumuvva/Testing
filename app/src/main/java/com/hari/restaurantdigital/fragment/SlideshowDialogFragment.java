package com.hari.restaurantdigital.fragment;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.hari.restaurantdigital.Model.Dish;
import com.hari.restaurantdigital.R;
import com.hari.restaurantdigital.adapter.RelatedProductsListAdapter;

import java.util.ArrayList;


public class SlideshowDialogFragment extends DialogFragment {
    private String TAG = SlideshowDialogFragment.class.getSimpleName();
    private ArrayList<Dish> images;
    private ViewPager viewPager;
    private RecyclerView mRecyclerView;
    private MyViewPagerAdapter myViewPagerAdapter;
    private RelatedProductsListAdapter mProductListRecyclerViewAdapter;
    private int selectedPosition = 0;
    private ArrayList<Dish> mRelatedProducts = new ArrayList<>();

    static SlideshowDialogFragment newInstance() {
        SlideshowDialogFragment f = new SlideshowDialogFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_slid_show, container, false);
        viewPager = (ViewPager) v.findViewById(R.id.fragment_slid_show_viewpager);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.fragment_slid_show_recycler_view);
        images = (ArrayList<Dish>) getArguments().getSerializable("images");
        selectedPosition = getArguments().getInt("position");

        Log.e(TAG, "position: " + selectedPosition);
        Log.e(TAG, "images size: " + images.size());

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mProductListRecyclerViewAdapter = new RelatedProductsListAdapter();
        mRecyclerView.setAdapter(mProductListRecyclerViewAdapter);
        mRecyclerView.smoothScrollToPosition(0);
        mRecyclerView.getLayoutManager().scrollToPosition(0);
        setCurrentItem(selectedPosition);
        return v;
    }

    public void onResume() {
        // Store access variables for window and blank point
        Window window = getDialog().getWindow();
        Point size = new Point();
        // Store dimensions of the screen in `size`
        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);
        // Set the width of the dialog proportional to 75% of the screen width
        window.setLayout((int) (size.x * 0.90), (int) (size.y * 0.90));
        window.setGravity(Gravity.CENTER);
        // Call super onResume after sizing
        super.onResume();
    }

    private void setCurrentItem(int position) {
        viewPager.setCurrentItem(position, false);
        displayRelatedProducts(selectedPosition);
    }

    private void displayRelatedProducts(int selectedPosition) {
        mRelatedProducts.clear();
        for (int index = 0; index < 5; index++) {
            Dish dish = new Dish(index, 0, "Time : " + index * selectedPosition + " Min", "Price : " + index * selectedPosition + "$", "Chicken " + index * selectedPosition, "jlsjfa555555kljsafdklj" + index * selectedPosition);
            mRelatedProducts.add(dish);
        }
        mProductListRecyclerViewAdapter.setData(mRelatedProducts);
    }

    //	page change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            displayRelatedProducts(position);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
    }

    //	adapter
    public class MyViewPagerAdapter extends PagerAdapter {

        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View itemView = layoutInflater.inflate(R.layout.product_details_activity, container, false);
            TextView productContent, productTitle, productPrice;
            productContent = (TextView) itemView.findViewById(R.id.product_description);
            productTitle = (TextView) itemView.findViewById(R.id.product_details_titile);
            productPrice = (TextView) itemView.findViewById(R.id.product_details_product_price);
            ImageView dishImage = (ImageView) itemView.findViewById(R.id.dish_image);
            Dish image = images.get(position);
            productContent.setText(image.getDishContent());
            productTitle.setText(image.getDishName());
            productPrice.setText(image.getDishPrice());
            dishImage.setBackgroundResource(image.getDishImage());
            container.addView(itemView);

            return itemView;
        }

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == ((View) obj);
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
