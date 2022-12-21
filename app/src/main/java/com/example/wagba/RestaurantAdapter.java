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

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {



    public interface OnRestaurantClickListener {
        void onRestaurantClick(RestaurantModel RestaurantName);
    }

    ArrayList<RestaurantModel> userModelsIntenals;
    private final OnRestaurantClickListener listener;


    public RestaurantAdapter(ArrayList<RestaurantModel> restaurantModel , OnRestaurantClickListener listener) {
        this.userModelsIntenals = restaurantModel;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_restaurant , parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RestaurantModel restaurantModel = userModelsIntenals.get(position);
        holder.bind(restaurantModel, listener);
    }

    void setRestaurants( ArrayList<RestaurantModel> newList){
        userModelsIntenals = newList;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return userModelsIntenals.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView restaurantName;
        TextView restaurantDescription;
        TextView deliveryTime;
        TextView deliveryPrice;
        TextView restaurantStars;
        ImageView restaurantImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantName = itemView.findViewById(R.id.restaurantName_tv);
            restaurantDescription = itemView.findViewById(R.id.restaurantDescription_tv);
            deliveryTime = itemView.findViewById(R.id.deliveryTime_tv);
            deliveryPrice = itemView.findViewById(R.id.deliveryPrice_tv);
            restaurantStars = itemView.findViewById(R.id.restaurantStars_tv);
            restaurantImage = itemView.findViewById(R.id.restaurantImage_imv);

        }
        public void bind(final RestaurantModel item, final OnRestaurantClickListener listener) {

            deliveryPrice.setText(item.getPrice().toString());
            deliveryTime.setText(item.getTime().toString());
            restaurantName.setText(item.getRestaurantName());
            restaurantDescription.setText(item.getDescription());
            restaurantStars.setText(item.getStars().toString());
            Context context =   restaurantImage.getContext();
            int id = context.getResources().getIdentifier(item.getImage(), "drawable", context.getPackageName());
            Log.d("onRestaurantClick", "onRestaurantClick");
            restaurantImage.setImageResource(id);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Log.d("onRestaurantClick", "onRestaurantClick");
                    listener.onRestaurantClick(item);
                }
            });
        }
    }
}
