package com.example.kfc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BucketCustomAdapter extends FirebaseRecyclerAdapter<FoodItemModel,BucketCustomAdapter.MyViewHolder> {
    private List<FoodItemModel> dataSet;
    Context context;
    Activity activity;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public BucketCustomAdapter(@NonNull FirebaseRecyclerOptions<FoodItemModel> options,Context context,Activity activity) {
        super(options);
        this.context=context;
        this.activity=activity;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout mainLayout;
        TextView menuTitle,menuSubtitle,menuPrice;
        ImageView menuImage;
        public MyViewHolder(View itemView) {
            super(itemView);
            this.menuTitle = (TextView) itemView.findViewById(R.id.bucket_menu_title);
            this.menuImage = (ImageView) itemView.findViewById(R.id.bucket_menu_img);
            this.menuSubtitle = (TextView) itemView.findViewById(R.id.bucket_menu_subtitle);
            this.menuPrice = (TextView) itemView.findViewById(R.id.bucket_menu_price);
            this.mainLayout=itemView.findViewById(R.id.bucket_main_Layout);
        }
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bucket_menu_item, parent, false);
        BucketCustomAdapter.MyViewHolder myViewHolder = new BucketCustomAdapter.MyViewHolder(view);
        return myViewHolder;
    }



    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int i, @NonNull final FoodItemModel foodItemModel) {
        holder.menuTitle.setText(foodItemModel.getName());
        holder.menuSubtitle.setText(String.valueOf(foodItemModel.getQuantity())+" x PKR "+String.valueOf(foodItemModel.getActualPrice()));
        holder.menuPrice.setText("PKR "+String.valueOf(foodItemModel.getPrice()));
//        Glide.with(holder.menuImage.getContext()).load(foodItemModel.getImage()).into(holder.menuImage);
        Picasso.get().load(foodItemModel.getImage()).resize(300,260).into(holder.menuImage);
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "Item Clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, BucketItemDetailsActivity.class);
                intent.putExtra("ITEM_NAME", foodItemModel.getName());
                intent.putExtra("ITEM_PRICE", String.valueOf(foodItemModel.getPrice()));
                intent.putExtra("ITEM_ACTUAL_PRICE", String.valueOf(foodItemModel.getActualPrice()));
                intent.putExtra("ITEM_QUANTITY", String.valueOf(foodItemModel.getQuantity()));
                intent.putExtra("ITEM_IMAGE", foodItemModel.getImage());

                activity.startActivityForResult(intent, 1);
            }
        });
    }
}
