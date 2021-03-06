package com.example.popeyes.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popeyes.R;
import com.example.popeyes.routes.Constant;
import com.example.popeyes.utils.FoodItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.popeyes.adapter.FoodMenuAdapter.MyViewHolder;

public class FoodMenuAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private ArrayList<FoodItem> mList;
    private OnClicked listener;

    public FoodMenuAdapter(ArrayList<FoodItem> mList, OnClicked listener) {
        this.mList = mList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_food_menu, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final FoodItem foodItem = mList.get(position);

        String url = Constant.IMAGE + foodItem.getImage();

        holder.foodMenuTitle.setText(foodItem.getTitle());

        Picasso.with(holder.itemView.getContext())
                .load(url)
                .fit()
                .centerInside()
                .into(holder.foodMenuImageView);

        holder.foodMenuImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickListener(foodItem.getId());
            }
        });
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView foodMenuImageView;
        private TextView foodMenuTitle;

        MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            foodMenuTitle = itemView.findViewById(R.id.foodMenuTitle);
            foodMenuImageView = itemView.findViewById(R.id.foodMenuImageView);
        }
    }

    public interface OnClicked {
        void onClickListener(int id);
    }

}
