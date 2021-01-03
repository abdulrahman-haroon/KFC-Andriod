package com.example.kfc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class BucketItemDetailsActivity extends AppCompatActivity {
    int items = 1, actualPrice,total_price,overall_price=0;
    TextView name, price, count,total_overall_price;
    ImageView item_image;
    String item_name, item_price, imageURL, quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bucket_item_details);
        Intent i = getIntent();

        item_name = i.getStringExtra("ITEM_NAME");
        item_price = i.getStringExtra("ITEM_PRICE");
        imageURL = i.getStringExtra("ITEM_IMAGE");
        quantity = i.getStringExtra("ITEM_QUANTITY");
        actualPrice = Integer.valueOf(i.getStringExtra("ITEM_ACTUAL_PRICE"));

        count = (TextView) findViewById(R.id.bucket_item_count);
        name = (TextView) findViewById(R.id.bucket_item_name);
        price = (TextView) findViewById(R.id.bucket_item_price);
        item_image = (ImageView) findViewById(R.id.bucket_item_image_img);
        total_overall_price=(TextView)findViewById(R.id.bucket_total_price);

        name.setText(item_name);
        price.setText("PKR " + item_price);
        count.setText(quantity);
        Picasso.get().load(imageURL).into(item_image);
        items = Integer.valueOf(quantity);


//        SharedPreferences sharedPreferences=getSharedPreferences(MainActivity.SHARED_PREFS,MODE_PRIVATE);
//        SharedPreferences.Editor editor=sharedPreferences.edit();
//        editor.putInt("total_overall_price",total_price);
//        editor.apply();
//
//        SharedPreferences sharedPreferences2=getSharedPreferences(MainActivity.SHARED_PREFS,MODE_PRIVATE);
//        int totalSum=sharedPreferences2.getInt("total_overall_price",0)+total_price;
//        total_overall_price.setText(String.valueOf(totalSum));
    }

    public void RemoveItem(View view) {
        SharedPreferences sharedPreferences1 = getSharedPreferences(MainActivity.SHARED_PREFS, MODE_PRIVATE);
        String phoneNo = sharedPreferences1.getString("phoneNo", "");
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("usersBucket").child(phoneNo).child(item_name);
        reference.removeValue();
        Toast.makeText(this, "Successfully Removed.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, BucketActivity.class);
        startActivityForResult(intent, 1);
    }

    public void UpdateItem(View view) {
        SharedPreferences sharedPreferences1 = getSharedPreferences(MainActivity.SHARED_PREFS, MODE_PRIVATE);
        String phoneNo = sharedPreferences1.getString("phoneNo", "");
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("usersBucket").child(phoneNo).child(item_name);
        reference.child("price").setValue(total_price);
        reference.child("quantity").setValue(items);
        Toast.makeText(getApplicationContext(), "Successfully Updated.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, BucketActivity.class);
        startActivityForResult(intent, 1);
    }

    public void onClickAdd(View view) {
        items++;
        count.setText(String.valueOf(items));
         total_price = actualPrice;
        total_price = total_price * items;
        price.setText("PKR " + String.valueOf(total_price));

    }

    public void onClickSubtract(View view) {
        if (items > 1) {
            items--;
            count.setText(String.valueOf(items));
             total_price = actualPrice;
            total_price = total_price * items;
            price.setText("PKR " + String.valueOf(total_price));
        }
    }
}