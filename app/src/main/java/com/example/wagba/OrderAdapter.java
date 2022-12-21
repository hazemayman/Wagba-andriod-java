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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {


    ArrayList<OrderItem> userModelsIntenals;
    Context c;
    private RecyclerView.RecycledViewPool
            viewPool
            = new RecyclerView
            .RecycledViewPool();
    public OrderAdapter(ArrayList<OrderItem> restaurantModel  , Context c) {
        this.userModelsIntenals = restaurantModel;
        this.c = c;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_active_order , parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderItem item = userModelsIntenals.get(position);

        holder.orderState.setText(item.getOrderState().toString());
        holder.orderPrice.setText(item.getOrderPrice());
        holder.orderGate.setText(item.getOrderGate());
        holder.orderTime.setText(item.getOrderTime().toString());
        holder.orderRestaurant.setText(item.getOrderRestaurant().toString());
        LinearLayoutManager layoutManager = new LinearLayoutManager(c,
                LinearLayoutManager.HORIZONTAL, false);
        layoutManager.setInitialPrefetchItemCount(
                item.getItemList().size()
        );
        SingleOrderItemAdapter adapter
                = new SingleOrderItemAdapter(
                item.getItemList()
        );
        holder.itemList.setLayoutManager(layoutManager);
        holder.itemList.setAdapter(adapter);
        holder.itemList.setRecycledViewPool(viewPool);




        Context context =    holder.orderImage.getContext();
        int id = context.getResources().getIdentifier("ic_order", "drawable", context.getPackageName());
        Log.d("onRestaurantClick", "onRestaurantClick");
        holder.orderImage.setImageResource(id);
    }

    void setOrders( ArrayList<OrderItem> newList){
        userModelsIntenals = newList;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return userModelsIntenals.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{


        RecyclerView itemList;
        TextView orderState;
        TextView orderPrice;
        TextView orderGate;
        TextView orderTime;
        TextView orderRestaurant;
        ImageView orderImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemList = itemView.findViewById(R.id.orderItems_rv);
            orderState = itemView.findViewById(R.id.orderState_tv);
            orderPrice = itemView.findViewById(R.id.orderTotalPrice_tv);
            orderGate = itemView.findViewById(R.id.orderGate_tv);
            orderTime = itemView.findViewById(R.id.orderTime_tv);
            orderImage = itemView.findViewById(R.id.order_iv);
            orderRestaurant = itemView.findViewById(R.id.orderRestaurant);

        }
        public void bind(final OrderItem item) {


        }
    }
}
