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

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MenuCustomAdapter extends    RecyclerView.Adapter<MenuCustomAdapter.MyViewHolder>{
    private ArrayList<MenuDataModel> dataSet;
    Context context;
    Activity activity;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout mainLayout;
        TextView menuTitle;

        ImageView menuImage;
        public MyViewHolder(View itemView) {
            super(itemView);
            this.menuTitle = (TextView)
                    itemView.findViewById(R.id.menu_title);

            this.menuImage = (ImageView) itemView.findViewById(R.id.menu_image);
            this.mainLayout=itemView.findViewById(R.id.mainLayout);
        }
    }
    public MenuCustomAdapter(ArrayList<MenuDataModel> data, Context context, Activity activity) {
        this.dataSet = data;
        this.context=context;
        this.activity=activity;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_row, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        TextView textViewTitle = holder.menuTitle;

        ImageView imageView = holder.menuImage;
        textViewTitle.setText(dataSet.get(listPosition).getName());

        imageView.setImageResource(dataSet.get(listPosition).getImage());
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Items", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(context, MenuDetails.class);
//                intent.putExtra("DB_NAME", dataSet.get(listPosition).getDbName());
//                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

}
