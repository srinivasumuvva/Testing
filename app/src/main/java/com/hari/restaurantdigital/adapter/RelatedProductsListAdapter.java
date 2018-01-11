package com.hari.restaurantdigital.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hari.restaurantdigital.Model.Dish;
import com.hari.restaurantdigital.R;

import java.util.ArrayList;

/**
 * Created by CHAITANYA on 9/19/2017.
 */

public class RelatedProductsListAdapter extends RecyclerView.Adapter<RelatedProductsListAdapter.ProductListRecyclerViewHolder> {

    private static final String TAG = RelatedProductsListAdapter.class.getSimpleName();
    ArrayList<Dish> mProductArrayList;


    @Override
    public ProductListRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.related_product_list_adapter, parent, false);
        ProductListRecyclerViewHolder selectedProductsListViewHolder = new ProductListRecyclerViewHolder(view);
        return selectedProductsListViewHolder;
    }

    @Override
    public void onBindViewHolder(ProductListRecyclerViewHolder holder, int position) {
        Dish dish=mProductArrayList.get(position);
        holder.productTime.setText(dish.getDishName());
        holder.productPrice.setText(dish.getDishPrice());
        holder.productTitle.setText(dish.getDishContent());
    }

    @Override
    public int getItemCount() {
        return mProductArrayList.size();
    }

    public class ProductListRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView productTitle, productTime, productPrice;

        public ProductListRecyclerViewHolder(View itemView) {
            super(itemView);
            productTitle = (TextView) itemView.findViewById(R.id.menu_display_dish_title);
            productTime = (TextView) itemView.findViewById(R.id.menu_display_estimatedTime);
            productPrice = (TextView) itemView.findViewById(R.id.menu_display_price);
            itemView.setTag(itemView);
        }


        @Override
        public void onClick(View v) {

        }
    }

    public void setData(ArrayList<Dish> pProductArrayList) {
        mProductArrayList=new ArrayList<>();
        mProductArrayList = pProductArrayList;
        notifyDataSetChanged();
    }
}
