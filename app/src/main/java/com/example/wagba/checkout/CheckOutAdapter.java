package com.example.wagba.checkout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.wagba.R;

import java.util.ArrayList;

public class CheckOutAdapter extends RecyclerView.Adapter<CheckOutAdapter.ViewHolder> {


    public interface OnFoodClickListener {
        void onFoodClick(CheckoutItem item);
    }

    ArrayList<CheckoutItem> userModelsIntenals;
    private final OnFoodClickListener listener;
    Context context;

    public CheckOutAdapter(ArrayList<CheckoutItem> foodModel , OnFoodClickListener listener) {
        this.userModelsIntenals = foodModel;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_checkout , parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CheckoutItem restaurantModel = userModelsIntenals.get(position);
        holder.bind(restaurantModel, listener);
    }

    void setFood( ArrayList<CheckoutItem> newList){
        userModelsIntenals = newList;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return userModelsIntenals.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView foodName;
        TextView quantity;
        TextView foodPrice;
        ImageView foodImage;
        ImageButton deleteItem;
        int ItemID;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.checkout_tv);
            quantity = itemView.findViewById(R.id.checkoutQuantity_tv);
            foodPrice = itemView.findViewById(R.id.checkoutAmount_tv);
            foodImage = itemView.findViewById(R.id.checkout_iv);
            deleteItem = itemView.findViewById(R.id.deleteItem_btn);
        }
        public void bind(final CheckoutItem item, final OnFoodClickListener listener) {

            ItemID = item.getId();
            foodPrice.setText(item.getPrice());
            foodName.setText(item.getFoodName());
            quantity.setText(item.getQuantity());
            Glide.with(context)
                    .load(item.getImage())
                    .into(foodImage);

            deleteItem.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onFoodClick(item);
                }
            });
        }
    }
}
