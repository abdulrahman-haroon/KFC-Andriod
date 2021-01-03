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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemViewAdapter {

    private Context context;
    private ItemAdapter itemAdapter;
    private Activity activity;
    public void setConfig(RecyclerView recyclerView, Context context, List<FoodItemModel> promotions, List<String > keys,Activity activity)
    {
        this.context=context;
        this.activity=activity;
        itemAdapter= new ItemAdapter(promotions,keys,this.context,this.activity);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(itemAdapter);
    }

    class ItemAdapter extends RecyclerView.Adapter<DataItemView>
    {

        private List<FoodItemModel> itemsList;
        private  List<String> keys;

        public ItemAdapter(List<FoodItemModel> mpromotionsList, List<String> keys, Context context, Activity activity) {
            this.itemsList = mpromotionsList;
            this.keys = keys;
        }

        @NonNull
        @Override
        public DataItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new DataItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull DataItemView holder, final int position) {
            holder.Bind(itemsList.get(position),keys.get(position));
            holder.item_row_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, itemsList.get(position).getDescription(), Toast.LENGTH_SHORT).show();
                    // Your Logic here
                    Intent intent = new Intent(context, ItemDetails.class);
                    intent.putExtra("ITEM_NAME", itemsList.get(position).getName());
                    intent.putExtra("ITEM_PRICE", String.valueOf(itemsList.get(position).getPrice()));
                    intent.putExtra("ITEM_IMAGE", itemsList.get(position).getImage());
                    intent.putExtra("ITEM_DESCRIPTION", itemsList.get(position).getDescription());
                    activity.startActivityForResult(intent, 1);

                }
            });

        }

        @Override
        public int getItemCount() {
            return itemsList.size();
        }
    }

    class DataItemView extends RecyclerView.ViewHolder
    {
        private TextView name;
        private ImageView image;
        private  TextView price;
        private String keys;
        LinearLayout item_row_layout;

        public DataItemView(@NonNull ViewGroup parent) {
            super(LayoutInflater.from(context).inflate(R.layout.item_row, parent,false));
            name=(TextView) itemView.findViewById(R.id.name_tv);
            price=(TextView) itemView.findViewById(R.id.price_tv);
            image=(ImageView) itemView.findViewById((R.id.item_image));
            this.item_row_layout=itemView.findViewById(R.id.item_row_layout);

        }
        public  void Bind(FoodItemModel promotions, String key)
        {
            name.setText(promotions.getName());
            price.setText("PKR: "+String.valueOf(promotions.getPrice()));
            Picasso.get().load(promotions.getImage()).resize(300,260).into(image);
            this.keys=key;

        }

    }
}
