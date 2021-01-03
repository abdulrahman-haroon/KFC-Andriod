package com.example.kfc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity {
    TextView country_tv, locality_tv, phone_no_checkout,total_price_checkout;
    String country, locality,totalsum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        SharedPreferences sharedPreferences1 = getSharedPreferences(MainActivity.SHARED_PREFS, MODE_PRIVATE);
        final String phoneNo = sharedPreferences1.getString("phoneNo", "");

        country_tv = findViewById(R.id.country_tv);
        locality_tv = findViewById(R.id.locality_tv);
        phone_no_checkout = findViewById(R.id.phone_no_checkout);
        total_price_checkout = findViewById(R.id.total_price_checkout);

        Intent intent = getIntent();
        country = intent.getStringExtra("country");
        locality = intent.getStringExtra("locality");
        totalsum=intent.getStringExtra("total_sum");

        country_tv.setText("Country: "+country);
        locality_tv.setText("Locality: "+locality);
        phone_no_checkout.setText("Contact No: "+phoneNo);
        total_price_checkout.setText("Total Price: "+totalsum);

    }

    public void Checkout(View view) {
        Toast.makeText(this, "Successfully Checkout", Toast.LENGTH_SHORT).show();


        SharedPreferences sharedPreferences1 = getSharedPreferences(MainActivity.SHARED_PREFS, MODE_PRIVATE);
        final String phoneNo = sharedPreferences1.getString("phoneNo", "");

        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("usersBucket").child(phoneNo);
        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<FoodItemModel> itemModels = new ArrayList<>();
                if (snapshot.exists()) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        FoodItemModel foodItemModel = ds.getValue(FoodItemModel.class);
                        itemModels.add(foodItemModel);
                    }
                    DatabaseReference reference3 = FirebaseDatabase.getInstance().getReference("usersCheckout");
                    reference3.child(phoneNo).setValue(itemModels);
                    DatabaseReference reference4 = FirebaseDatabase.getInstance().getReference("usersLocation");
                    reference4.child(phoneNo).setValue(country+" "+locality);
                    DatabaseReference reference5 = FirebaseDatabase.getInstance().getReference("usersPayment");
                    reference5.child(phoneNo).setValue(totalsum);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        DatabaseReference reference3 = FirebaseDatabase.getInstance().getReference("usersBucket");
        reference3.removeValue();
        Intent intent = new Intent(this, Home_Page.class);
        startActivity(intent);
    }

}