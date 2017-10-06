package com.hari.restaurantdigital.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hari.restaurantdigital.Model.IngredientsModel;
import com.hari.restaurantdigital.Model.SelectProductModel;
import com.hari.restaurantdigital.R;
import com.hari.restaurantdigital.interfaces.DishSelectedInterface;
import com.hari.restaurantdigital.ui.MainActivity;

import java.util.ArrayList;

/**
 * Created by CHAITANYA on 9/19/2017.
 */

public class IngredientsListAdapter extends RecyclerView.Adapter<IngredientsListAdapter.IngredientsListViewHolder> {

    private static final String TAG = IngredientsListAdapter.class.getSimpleName();
    ArrayList<IngredientsModel> mIngredientsModelArrayList = new ArrayList<>();

    @Override
    public IngredientsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingredients_list_row, parent, false);
        IngredientsListViewHolder selectedProductsListViewHolder = new IngredientsListViewHolder(view);
        return selectedProductsListViewHolder;
    }

    @Override
    public void onBindViewHolder(IngredientsListViewHolder holder, int position) {
        IngredientsModel ingredientsModel= mIngredientsModelArrayList.get(position);
        holder.productTitle.setText(ingredientsModel.getTitle());
        holder.productQuantity.setText(ingredientsModel.getQuantity());
    }

    @Override
    public int getItemCount() {
        return mIngredientsModelArrayList.size();
    }

    public class IngredientsListViewHolder extends RecyclerView.ViewHolder {
        TextView productQuantity, productTitle;

        public IngredientsListViewHolder(View itemView) {
            super(itemView);
            productQuantity = (TextView) itemView.findViewById(R.id.ingredient_quantity);
            productTitle = (TextView) itemView.findViewById(R.id.ingredient_title);
            itemView.setTag(itemView);
        }

    }

    public void setData(ArrayList<IngredientsModel> pDishArrayList) {
        mIngredientsModelArrayList = pDishArrayList;
        notifyDataSetChanged();
    }
}
