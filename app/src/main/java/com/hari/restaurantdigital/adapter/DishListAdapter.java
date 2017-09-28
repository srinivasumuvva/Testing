package com.hari.restaurantdigital.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hari.restaurantdigital.Model.Dish;
import com.hari.restaurantdigital.R;
import com.hari.restaurantdigital.interfaces.DishSelectedInterface;
import com.hari.restaurantdigital.ui.MainActivity;

import java.util.ArrayList;

/**
 * Created by CHAITANYA on 9/19/2017.
 */

public class DishListAdapter extends RecyclerView.Adapter<DishListAdapter.DishListViewHolder> {
    private int mProductDisplayType = 4;
    private int mDeviceWidth, mDeviceHeight;
    private static final String TAG=DishListAdapter.class.getSimpleName();
    ArrayList<Dish> mDishArrayList = new ArrayList<>();

    DishSelectedInterface mDishSelectedInterface;

    public DishListAdapter(DishSelectedInterface pDishSelectedInterface) {
        mDishSelectedInterface = pDishSelectedInterface;
        mDeviceHeight= MainActivity.mDeviceHeight;
        mDeviceWidth=MainActivity.mDeviceWidth;

    }

    @Override
    public DishListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_display_layout, parent, false);
        DishListViewHolder dishListViewHolder = new DishListViewHolder(view);
        return dishListViewHolder;
    }

    @Override
    public void onBindViewHolder(DishListViewHolder holder, int position) {
        int size = 0, height = 0, span = 0;

        final View itemView = holder.itemView;
              lp.setMargins(1, 1, 1, 1);
        if (mProductDisplayType == 1) {
            size = mDeviceWidth;
            height = mDeviceHeight / 2;
            span = 2;
        } else if (mProductDisplayType == 2) {
            size = mDeviceWidth / 2;
            height = mDeviceHeight / 2;
            span = 1;
        } else if (mProductDisplayType == 3) {
            size = mDeviceWidth / 2;
            height = mDeviceHeight / 3;
            span = 1;
        } else if (mProductDisplayType == 4) {
            if (position % 6 == 0 || position % 6 == 1) {
                Log.e(TAG, "from %6==0 or ==1 position is  " + position);
                size = mDeviceWidth;
                height = (int) mDeviceHeight / 4;
                span = 2;
            } else {
                Log.e(TAG, "from mProductDisplayType ==4 else position is  " + position);
                size = mDeviceWidth / 2;
                height = (int) mDeviceHeight / 4;
                span = 1;
            }

            Log.e(TAG, "SPAN IS " + span);
            Log.e(TAG, "widht from type  IS " + size);
            Log.e(TAG, "height from type  IS " + height);
        } else if (mProductDisplayType == 5) {
            if (position % 3 == 0) {
                Log.e(TAG, "from %6==0 or ==1   mProductDisplayType ==5 if  position is  " + position);
                size = mDeviceWidth / 2;
                height = (int) mDeviceHeight / 2;
                span = 1;
            } else {
                Log.e(TAG, "from mProductDisplayType ==5 else position is  " + position);
                size = mDeviceWidth / 2;
                height = (int) mDeviceHeight / 4;
                span = 1;
            }

            Log.e(TAG, "SPAN IS " + span);
            Log.e(TAG, "widht from type  IS " + size);
            Log.e(TAG, "height from type  IS " + height);
        }

        final org.lucasr.twowayview.widget.StaggeredGridLayoutManager.LayoutParams lp =
                (org.lucasr.twowayview.widget.StaggeredGridLayoutManager.LayoutParams) itemView.getLayoutParams();
        lp.span = span;
        lp.width = size;
        lp.height = height;

        itemView.setLayoutParams(lp);
        holder.dishImage.setBackgroundResource(mDishArrayList.get(position).getDishImage());
        holder.dishTitle.setText(mDishArrayList.get(position).getDishName());
        holder.dishPrice.setText(mDishArrayList.get(position).getDishPrice());
        holder.dishEstimatedTime.setText(mDishArrayList.get(position).getDishEstimatedTime());
        if (mDishArrayList.get(position).isSelected()) {
            holder.dishFavourite.setBackgroundResource(R.drawable.cart_selected);
        } else {
            holder.dishFavourite.setBackgroundResource(R.drawable.cart_normal);
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
            dishFavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDishSelectedInterface.onDishSelected(mDishArrayList.get(getPosition()), getPosition());
                    if (mDishArrayList.get(getPosition()).isSelected()) {
                        mDishArrayList.get(getPosition()).setSelected(false);
                        dishFavourite.setBackgroundResource(R.drawable.cart_normal);
                    } else {
                        mDishArrayList.get(getPosition()).setSelected(true);
                        dishFavourite.setBackgroundResource(R.drawable.cart_selected);
                    }


                }

            });

        }
    }

    public void setData(ArrayList<Dish> pDishArrayList) {
        mDishArrayList = pDishArrayList;
        notifyDataSetChanged();
    }
}
