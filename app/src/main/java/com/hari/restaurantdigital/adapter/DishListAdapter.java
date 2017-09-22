package com.hari.restaurantdigital.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hari.restaurantdigital.Model.Dish;
import com.hari.restaurantdigital.R;
import com.hari.restaurantdigital.interfaces.DishSelectedInterface;

import java.util.ArrayList;

/**
 * Created by CHAITANYA on 9/19/2017.
 */

public class DishListAdapter extends RecyclerView.Adapter<DishListAdapter.DishListViewHolder> {

    ArrayList<Dish> mDishArrayList = new ArrayList<>();

    DishSelectedInterface mDishSelectedInterface;

    public DishListAdapter(DishSelectedInterface pDishSelectedInterface) {
        mDishSelectedInterface = pDishSelectedInterface;

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
