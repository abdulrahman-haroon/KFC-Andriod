package com.example.kfc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class ItemDetails extends AppCompatActivity {
    int items=1;
TextView name,price,count;
ImageView item_image;
String item_name,item_price,imageURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        Intent i=getIntent();

       item_name= i.getStringExtra("ITEM_NAME");
       item_price=i.getStringExtra("ITEM_PRICE");
       imageURL=i.getStringExtra("ITEM_IMAGE");
       count=(TextView)findViewById(R.id.item_count);
        name=(TextView)findViewById(R.id.item_name);
        price=(TextView)findViewById(R.id.item_price);
        item_image=(ImageView)findViewById(R.id.item_image_img);
        name.setText(item_name);
        price.setText("PKR "+item_price);
        Picasso.get().load(imageURL).into(item_image);
    }

    public void onClickAdd(View view) {
        items++;
        count.setText(String.valueOf(items));
        int total_price=Integer.parseInt(item_price);
        total_price=total_price*items;
        price.setText("PKR "+String.valueOf(total_price));

    }

    public void onClickSubtract(View view) {
        if(items>1) {
            items--;
            count.setText(String.valueOf(items));
            int total_price=Integer.parseInt(item_price);
            total_price=total_price*items;
            price.setText("PKR "+String.valueOf(total_price));
        }
    }

    public void AddToBucket(View view) {
        Toast.makeText(this, "Add To Bucket", Toast.LENGTH_SHORT).show();
    }
}