package com.example.kfc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ItemDetails extends AppCompatActivity {
    int items = 1,total_price=0;
    TextView name, price, count;
    ImageView item_image;
    String item_name, item_price, imageURL;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        Intent i = getIntent();

        item_name = i.getStringExtra("ITEM_NAME");
        item_price = i.getStringExtra("ITEM_PRICE");
        imageURL = i.getStringExtra("ITEM_IMAGE");
        count = (TextView) findViewById(R.id.item_count);
        name = (TextView) findViewById(R.id.item_name);
        price = (TextView) findViewById(R.id.item_price);
        item_image = (ImageView) findViewById(R.id.item_image_img);
        name.setText(item_name);
        total_price=Integer.valueOf(item_price);
        price.setText("PKR " + item_price);
        Picasso.get().load(imageURL).into(item_image);
    }

    public void onClickAdd(View view) {
        items++;
        count.setText(String.valueOf(items));
        total_price = Integer.parseInt(item_price);
        total_price = total_price * items;
        price.setText("PKR " + String.valueOf(total_price));

    }

    public void onClickSubtract(View view) {
        if (items > 1) {
            items--;
            count.setText(String.valueOf(items));
             total_price = Integer.parseInt(item_price);
            total_price = total_price * items;
            price.setText("PKR " + String.valueOf(total_price));
        }
    }

    public void AddToBucket(View view) {
        Toast.makeText(this, "Add To Bucket", Toast.LENGTH_SHORT).show();
//        FoodItemModel foodItemModel=new FoodItemModel();
//        foodItemModel.name=item_name;
//        foodItemModel.price=Integer.valueOf(item_price);
//        foodItemModel.image=imageURL;
//        foodItemModel.quantity=items;
//        List<FoodItemModel> foodItemModels = new ArrayList<>();
//        foodItemModels.add(foodItemModel);
//
//        PrefConfig.writeListInPref(getApplicationContext(),foodItemModels);
        SharedPreferences sharedPreferences=getSharedPreferences(MainActivity.SHARED_PREFS,MODE_PRIVATE);
        String phoneNo=sharedPreferences.getString("phoneNo","");

        rootNode=FirebaseDatabase.getInstance();
        reference=rootNode.getReference("usersBucket");
        FoodItemModel foodItemModel=new FoodItemModel();
        foodItemModel.name = item_name;
        foodItemModel.price = total_price;
        foodItemModel.image = imageURL;
        foodItemModel.quantity = items;
        foodItemModel.phoneNo=phoneNo;
        foodItemModel.actualPrice=Integer.parseInt(item_price);
        reference.child(phoneNo).child(item_name).setValue(foodItemModel);
    }
}