package com.example.kfc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Home_Page extends AppCompatActivity {

    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;

    String firstName = "", lastName = "", userEmail = "";
    TextView nav_title, nav_subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__page);

        Toolbar toolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);


        SharedPreferences sharedPreferences=getSharedPreferences(MainActivity.SHARED_PREFS,MODE_PRIVATE);
        firstName=sharedPreferences.getString("firstName","");
        lastName=sharedPreferences.getString("lastName","");
        userEmail=sharedPreferences.getString("userEmail","");

        nav = findViewById(R.id.navigation_menu_home);
        drawerLayout = findViewById(R.id.drawer_home);
        View headerView=nav.getHeaderView(0);
        nav_title=(TextView) headerView.findViewById(R.id.nav_header_title);
        nav_subtitle=(TextView) headerView.findViewById(R.id.nav_header_subtitle);
        nav_title.setText(firstName+" "+lastName);
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
                        Intent i = new Intent(getApplicationContext(), Promotion_Deals.class);
                        i.putExtra("Promotion", R.drawable.pro_1);
                        startActivity(i);
                    case R.id.nav_menu:
                        //Toast.makeText(getApplicationContext(), "Menu", Toast.LENGTH_SHORT).show();
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
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });


    }

    public void DealoneClick(View view) {

        Intent i = new Intent(this, Promotion_Deals.class);
        i.putExtra("Promotion", R.drawable.pro_1);
        //i.putExtra("Title",R.id.textdeal1);
        startActivity(i);


    }

    public void onClickDeal_2(View view) {
        Intent i = new Intent(this, Promotion_Deals.class);
        i.putExtra("Promotion", R.drawable.deal2);
        //  i.putExtra("Title",R.id.textdeal2);
        startActivity(i);
    }

    public void onClickDeals_3(View view) {
        Intent i = new Intent(this, Promotion_Deals.class);
        i.putExtra("Promotion", R.drawable.deal3);
        // i.putExtra("Title",R.id.textdeal3);
        startActivity(i);
    }
}