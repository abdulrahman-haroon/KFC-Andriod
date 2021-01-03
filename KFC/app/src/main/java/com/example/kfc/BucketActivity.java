package com.example.kfc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BucketActivity extends AppCompatActivity {
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    String firstName = "Guest", lastName = "", userEmail = "Welcome";
    TextView nav_title, nav_subtitle, bucket_tv;

    ImageView bucket_img;
    Button bucket_button;

    List<FoodItemModel> foodItemModels;
    RecyclerView bucketRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    BucketCustomAdapter bucketCustomAdapter;

    ConstraintLayout constraintLayout;
    boolean runOnce = false;

    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bucket);
        final TextView total_overall_price;
        Toolbar toolbar = findViewById(R.id.bucket_toolbar);
        setSupportActionBar(toolbar);//setting the Action bar of this activity.
        //Initializing fusedLocationProviderClient
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        bucket_tv = findViewById(R.id.bucket_empty_tv);
        bucket_img = findViewById(R.id.bucket_empty_img);
        bucket_button = findViewById(R.id.bucket_empty_btn);
        bucketRecyclerView = findViewById(R.id.bucket_recycler);
        constraintLayout = findViewById(R.id.select_area_bucket);

        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREFS, MODE_PRIVATE);
        firstName = sharedPreferences.getString("firstName", "");
        lastName = sharedPreferences.getString("lastName", "");
        userEmail = sharedPreferences.getString("userEmail", "");


        nav = findViewById(R.id.navigation_menu_bucket);
        drawerLayout = findViewById(R.id.drawer_bucket);
        View headerView = nav.getHeaderView(0);
        nav_title = (TextView) headerView.findViewById(R.id.nav_header_title);
        nav_subtitle = (TextView) headerView.findViewById(R.id.nav_header_subtitle);
        total_overall_price = (TextView) findViewById(R.id.bucket_total_price);


        nav_title.setText(firstName + " " + lastName);
        nav_subtitle.setText(userEmail);


        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        Intent inte = new Intent(getApplicationContext(), Home_Page.class);
                        startActivity(inte);
                        break;
                    case R.id.nav_menu:
                        intent = new Intent(getApplicationContext(), Menu.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_bucket:
                        intent = new Intent(getApplicationContext(), BucketActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_more:
                        intent = new Intent(getApplicationContext(), MoreActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_logout:

                        intent = new Intent(getApplicationContext(), MainActivity.class);
                        Toast.makeText(BucketActivity.this, "Successfully Logout", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });
        SharedPreferences sharedPreferences1 = getSharedPreferences(MainActivity.SHARED_PREFS, MODE_PRIVATE);
        final String phoneNo = sharedPreferences1.getString("phoneNo", "");


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("usersBucket");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(phoneNo)) {
                    bucket_tv.setVisibility(View.INVISIBLE);
                    bucket_img.setVisibility(View.INVISIBLE);
                    bucket_button.setVisibility(View.INVISIBLE);
                    bucketRecyclerView.setVisibility(View.VISIBLE);
                    constraintLayout.setVisibility(View.VISIBLE);
                    layoutManager = new LinearLayoutManager(getApplicationContext());
                    bucketRecyclerView.setLayoutManager(layoutManager);

                    FirebaseRecyclerOptions<FoodItemModel> options = new FirebaseRecyclerOptions.Builder<FoodItemModel>().setQuery(FirebaseDatabase.getInstance().getReference().child("usersBucket").child(phoneNo), FoodItemModel.class).build();
                    bucketCustomAdapter = new BucketCustomAdapter(options, getApplicationContext(), BucketActivity.this);
                    bucketRecyclerView.setAdapter(bucketCustomAdapter);
                    bucketCustomAdapter.startListening();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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
                    // Iterate and add all costs to sum
                    double sum = 0;
                    for (FoodItemModel b : itemModels) {
                        sum += Integer.parseInt(String.valueOf(b.getPrice()));
                    }
                    total_overall_price.setText("PKR " + String.valueOf(sum));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        bucketCustomAdapter.startListening();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        bucketCustomAdapter.stopListening();
//    }

    public void onClickStartOrder(View view) {
        Intent intent;
        intent = new Intent(getApplicationContext(), Menu.class);
        startActivity(intent);
    }

    public void onClickSelectArea(View view) {
        if (ActivityCompat.checkSelfPermission(BucketActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getLocation();
        } else {
            ActivityCompat.requestPermissions(BucketActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                TextView total_overall_price = (TextView) findViewById(R.id.bucket_total_price);
                //Initializing Location
                Location location = task.getResult();
                if (location != null) {
                    try {
                        Geocoder geocoder = new Geocoder(BucketActivity.this, Locale.getDefault());
                        //Initializing address list
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                        Intent intent=new Intent(getApplicationContext(),CheckoutActivity.class);
                        intent.putExtra("country",addresses.get(0).getCountryName());
                        intent.putExtra("locality",addresses.get(0).getLocality());
                        intent.putExtra("total_sum",total_overall_price.getText().toString());
                        startActivity(intent);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }
}