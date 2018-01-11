package com.hari.restaurantdigital.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hari.restaurantdigital.Model.Dish;
import com.hari.restaurantdigital.R;
import com.hari.restaurantdigital.fragment.SlideshowDialogFragment;
import com.hari.restaurantdigital.interfaces.DishSelectedInterface;
import com.hari.restaurantdigital.ui.MainActivity;
import com.hari.restaurantdigital.ui.ProductDetailsRecyclerView;

import java.util.ArrayList;

/**
 * Created by CHAITANYA on 9/19/2017.
 */

public class DishListAdapter extends RecyclerView.Adapter<DishListAdapter.DishListViewHolder> {
    private int mProductDisplayType = MainActivity.mProductDisplayType;
    private int mDeviceWidth, mDeviceHeight;
    private static final String TAG = DishListAdapter.class.getSimpleName();
    ArrayList<Dish> mDishArrayList = new ArrayList<>();
    Context mContext;
    DishSelectedInterface mDishSelectedInterface;

    public DishListAdapter(DishSelectedInterface pDishSelectedInterface) {
        mDishSelectedInterface = pDishSelectedInterface;
        mDeviceHeight = MainActivity.mDeviceHeight;
        mDeviceWidth = MainActivity.mDeviceWidth;
        mProductDisplayType = MainActivity.mProductDisplayType;
    }

    @Override
    public DishListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_display_layout_white, parent, false);
        mContext = parent.getContext();
        DishListViewHolder dishListViewHolder = new DishListViewHolder(view);
        return dishListViewHolder;
    }

    @Override
    public void onBindViewHolder(DishListViewHolder holder, int position) {
        int size = 0, height = 0, span = 0;

        final View itemView = holder.itemView;
        //  itemView.setPadding(5,5,5,5);
     /*   final org.lucasr.twowayview.widget.StaggeredGridLayoutManager.LayoutParams layoutParams =
                (org.lucasr.twowayview.widget.StaggeredGridLayoutManager.LayoutParams) itemView.getLayoutParams();*/

        StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) itemView.getLayoutParams();
        // layoutParams.setMargins(4,4,4,4);

        if (mProductDisplayType == 1) {
            size = mDeviceWidth;
            height = mDeviceHeight / 2;
            Log.e(TAG, "ITEM HEIGHT IS " + height);
            layoutParams.setFullSpan(true);
            span = 2;
        } else if (mProductDisplayType == 2) {
            size = mDeviceWidth / 2;
            height = mDeviceHeight / 2;
            span = 1;
            layoutParams.setFullSpan(false);
        } else if (mProductDisplayType == 3) {
            size = mDeviceWidth / 2;
            height = mDeviceHeight / 3;
            span = 1;
            layoutParams.setFullSpan(false);
        } else if (mProductDisplayType == 4) {
            if (position % 6 == 0 || position % 6 == 1) {
                Log.e(TAG, "from %6==0 or ==1 position is  " + position);
                size = mDeviceWidth;
                height = mDeviceHeight / 4;
                span = 2;
                layoutParams.setFullSpan(true);
            } else {
                Log.e(TAG, "from mProductDisplayType ==4 else position is  " + position);
                size = mDeviceWidth / 2;
                height = mDeviceHeight / 4;
                span = 1;
                layoutParams.setFullSpan(false);
            }

            Log.e(TAG, "SPAN IS " + span);
            Log.e(TAG, "widht from type  IS " + size);
            Log.e(TAG, "height from type  IS " + height);
        } else if (mProductDisplayType == 5) {
            if (position % 3 == 0) {
                Log.e(TAG, "from %6==0 or ==1   mProductDisplayType ==5 if  position is  " + position);
                size = mDeviceWidth / 2;
                height = mDeviceHeight / 2;
                span = 1;
                layoutParams.setFullSpan(false);
            } else {
                Log.e(TAG, "from mProductDisplayType ==5 else position is  " + position);
                size = mDeviceWidth / 2;
                height = mDeviceHeight / 4;
                span = 1;
                layoutParams.setFullSpan(false);
                layoutParams.setMargins(2, 2, 2, 2);
            }

            Log.e(TAG, "SPAN IS " + span);
            Log.e(TAG, "widht from type  IS " + size);
            Log.e(TAG, "height from type  IS " + height);
        } else if (mProductDisplayType == 6) {
            if (position % 3 == 0) {
                Log.e(TAG, "from %6==0 or ==1   mProductDisplayType ==5 if  position is  " + position);
                size = mDeviceWidth;
                height = mDeviceHeight / 2;
                span = 2;
                layoutParams.setFullSpan(true);
            } else {
                Log.e(TAG, "from mProductDisplayType ==5 else position is  " + position);
                size = mDeviceWidth / 2;
                height = mDeviceHeight / 2;
                span = 1;
                layoutParams.setFullSpan(false);
            }

            Log.e(TAG, "SPAN IS " + span);
            Log.e(TAG, "widht from type  IS " + size);
            Log.e(TAG, "height from type  IS " + height);
        }


        //  layoutParams.span = span;
        layoutParams.width = size;
        layoutParams.height = height;

        itemView.setLayoutParams(layoutParams);
        holder.dishImage.setBackgroundResource(mDishArrayList.get(position).getDishImage());
        holder.dishTitle.setText(mDishArrayList.get(position).getDishName());
        holder.dishPrice.setText(mDishArrayList.get(position).getDishPrice());
        holder.dishEstimatedTime.setText(mDishArrayList.get(position).getDishEstimatedTime());
        if (mDishArrayList.get(position).isSelected()) {
            holder.dishFavourite.setBackgroundResource(R.mipmap.cart_remove);
        } else {
            holder.dishFavourite.setBackgroundResource(R.mipmap.cart_add);
        }

    }

    @Override
    public int getItemCount() {
        return mDishArrayList.size();
    }

    public class DishListViewHolder extends RecyclerView.ViewHolder {
        TextView dishTitle, dishPrice, dishEstimatedTime;
        ImageView dishImage, dishFavourite;

        public DishListViewHolder(View itemView) {
            super(itemView);
            dishEstimatedTime = (TextView) itemView.findViewById(R.id.menu_display_estimatedTime);
            dishPrice = (TextView) itemView.findViewById(R.id.menu_display_price);
            dishTitle = (TextView) itemView.findViewById(R.id.menu_display_dish_title);
            dishImage = (ImageView) itemView.findViewById(R.id.menu_display_img);
            dishFavourite = (ImageView) itemView.findViewById(R.id.menu_display_favourite);
            dishImage.setTag(R.id.menu_display_img);
            dishFavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDishSelectedInterface.onDishSelected(mDishArrayList.get(getPosition()), getPosition());
                    if (mDishArrayList.get(getPosition()).isSelected()) {
                        mDishArrayList.get(getPosition()).setSelected(false);
                        dishFavourite.setBackgroundResource(R.mipmap.cart_add);
                    } else {
                        mDishArrayList.get(getPosition()).setSelected(true);
                        dishFavourite.setBackgroundResource(R.mipmap.cart_remove);
                    }


                }

            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDishSelectedInterface.onDishSelected(mDishArrayList.get(getPosition()), getPosition());
                    Toast.makeText(mContext,"from selected item ",Toast.LENGTH_LONG).show();

                }
            });

        }
    }

    public void setData(ArrayList<Dish> pDishArrayList) {
        mDishArrayList = pDishArrayList;
        notifyDataSetChanged();
    }
}
