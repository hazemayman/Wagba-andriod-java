package com.example.wagba.food;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.wagba.R;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {


    public interface OnFoodClickListener {
        void onFoodClick(FoodModel item);
    }

    ArrayList<FoodModel> userModelsIntenals;
    private final OnFoodClickListener listener;
    Context context;

    public FoodAdapter(ArrayList<FoodModel> foodModel , OnFoodClickListener listener) {
        this.userModelsIntenals = foodModel;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_resturant_food , parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodModel restaurantModel = userModelsIntenals.get(position);
        holder.bind(restaurantModel, listener);
    }

    void setRestaurants( ArrayList<FoodModel> newList){
        userModelsIntenals = newList;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return userModelsIntenals.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView foodName;
        TextView foodDescription;
        TextView foodPrice;
        ImageView foodImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.foodName_tv);
            foodDescription = itemView.findViewById(R.id.foodDescription_tv);
            foodPrice = itemView.findViewById(R.id.foodPrice_tv);
            foodImage = itemView.findViewById(R.id.foodImage_imv);

        }
        public void bind(final FoodModel item, final OnFoodClickListener listener) {

            foodPrice.setText(item.getPrice().toString());
            foodName.setText(item.getFoodName());
            foodDescription.setText(item.getDescription());

            Glide.with(context)
                    .load(item.getImage())
                    .into(foodImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onFoodClick(item);
                }
            });
        }
    }
}
