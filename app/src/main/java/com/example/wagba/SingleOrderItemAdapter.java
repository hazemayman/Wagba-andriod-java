package com.example.wagba;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SingleOrderItemAdapter extends RecyclerView.Adapter<SingleOrderItemAdapter.ViewHolder> {


    ArrayList<SingleOrderItem> userModelsIntenals;

    public SingleOrderItemAdapter(ArrayList<SingleOrderItem> restaurantModel ) {
        this.userModelsIntenals = restaurantModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_order_item , parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SingleOrderItem orderItem = userModelsIntenals.get(position);
        holder.bind(orderItem);
    }

    void setOrderItems( ArrayList<SingleOrderItem> newList){
        userModelsIntenals = newList;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return userModelsIntenals.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{


        TextView singleOrderItemName;
        TextView singleOrderItemPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            singleOrderItemName = itemView.findViewById(R.id.singleOrdeRItemName_tv);
            singleOrderItemPrice = itemView.findViewById(R.id.singleOrderItemPrice_tv);

        }
        public void bind(final SingleOrderItem item) {

            singleOrderItemName.setText(item.getItemName().toString());
            singleOrderItemPrice.setText(item.getItemPrice());

        }
    }
}
