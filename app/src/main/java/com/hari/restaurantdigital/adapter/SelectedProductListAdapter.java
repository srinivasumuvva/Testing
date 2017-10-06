package com.hari.restaurantdigital.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hari.restaurantdigital.Model.SelectProductModel;
import com.hari.restaurantdigital.R;
import com.hari.restaurantdigital.interfaces.DishSelectedInterface;
import com.hari.restaurantdigital.ui.MainActivity;

import java.util.ArrayList;

/**
 * Created by CHAITANYA on 9/19/2017.
 */

public class SelectedProductListAdapter extends RecyclerView.Adapter<SelectedProductListAdapter.SelectedProductsListViewHolder> {
    private int mProductDisplayType = MainActivity.mProductDisplayType;
    private int mDeviceWidth, mDeviceHeight;
    private static final String TAG = SelectedProductListAdapter.class.getSimpleName();
    ArrayList<SelectProductModel> mSelectedProductList = new ArrayList<>();

    DishSelectedInterface mDishSelectedInterface;

    /*public SelectedProductListAdapter(DishSelectedInterface pDishSelectedInterface) {
        mDishSelectedInterface = pDishSelectedInterface;
        mDeviceHeight = MainActivity.mDeviceHeight;
        mDeviceWidth = MainActivity.mDeviceWidth;
        mProductDisplayType = MainActivity.mProductDisplayType;
    }*/

    @Override
    public SelectedProductsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.selected_product_list_row, parent, false);
        SelectedProductsListViewHolder selectedProductsListViewHolder = new SelectedProductsListViewHolder(view);
        return selectedProductsListViewHolder;
    }

    @Override
    public void onBindViewHolder(SelectedProductsListViewHolder holder, int position) {
        SelectProductModel selectProductModel=mSelectedProductList.get(position);
        holder.productTitle.setText(selectProductModel.getProductTitle());
        holder.productPrice.setText((selectProductModel.getProductQuantity()*selectProductModel.getProductUnitPrice())+selectProductModel.getProductPriceSymbol());
        holder.productQuantity.setText(selectProductModel.getProductQuantity()+"");
    }

    @Override
    public int getItemCount() {
        return mSelectedProductList.size();
    }

    public class SelectedProductsListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView productQuantity, productTitle, productPrice;
        ImageView productImg, productDelete, productQuantityIncrease, productQuantityDecrease;

        public SelectedProductsListViewHolder(View itemView) {
            super(itemView);
            productQuantity = (TextView) itemView.findViewById(R.id.product_quantity);
            productTitle = (TextView) itemView.findViewById(R.id.product_title);
            productPrice = (TextView) itemView.findViewById(R.id.product_price);
            productImg = (ImageView) itemView.findViewById(R.id.product_image);
            productDelete = (ImageView) itemView.findViewById(R.id.product_delete);
            productQuantityIncrease = (ImageView) itemView.findViewById(R.id.product_quantity_increase);
            productQuantityDecrease = (ImageView) itemView.findViewById(R.id.product_quantity_decrease);
            productQuantityIncrease.setOnClickListener(this);
            productQuantityDecrease.setOnClickListener(this);
            productDelete.setOnClickListener(this);
            itemView.setTag(itemView);
        }

        @Override
        public void onClick(View v) {
            SelectProductModel selectProductModel=mSelectedProductList.get(getAdapterPosition());
            switch (v.getId()) {
                case R.id.product_quantity_decrease:
                    if(selectProductModel.getProductQuantity()!=1) {
                        v.setClickable(false);
                        selectProductModel.setProductQuantity(selectProductModel.getProductQuantity() - 1);
                        notifyDataSetChanged();
                        v.setClickable(true);
                    }
                    break;
                case R.id.product_quantity_increase:
                    v.setClickable(false);
                    selectProductModel.setProductQuantity(selectProductModel.getProductQuantity() +1);
                    notifyDataSetChanged();
                    v.setClickable(true);
                    break;
                case R.id.product_delete:
                    mSelectedProductList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    break;
            }
        }
    }

    public void setData(ArrayList<SelectProductModel> pDishArrayList) {
        mSelectedProductList = pDishArrayList;
        notifyDataSetChanged();
    }
}
