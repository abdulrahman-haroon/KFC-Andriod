package com.example.kfc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MenuDetails extends AppCompatActivity {
    private RecyclerView recyclerView;
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_details);
        Toolbar toolbar = findViewById(R.id.Menu_Details_toolbar);
        setSupportActionBar(toolbar);//setting the Action bar of this activity.


        nav=findViewById(R.id.navigation_menu_details);
        drawerLayout=findViewById(R.id.drawer_menu_details);

        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_nav,R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()){
                    case R.id.nav_home:

                        break;
                    case R.id.nav_menu:

                        intent=new Intent(getApplicationContext(),Menu.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_bucket:
                        intent=new Intent(getApplicationContext(),BucketActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_more:
                        intent=new Intent(getApplicationContext(),MoreActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_logout:
                        intent=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });
        Intent intent=getIntent();
        String dbname=intent.getStringExtra("DB_NAME");


        recyclerView=(RecyclerView)findViewById(R.id.menu_details_recyclerview);
        new FirebaseDatabaseHelper(dbname).readData(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<FoodItemModel> promotions, List<String> keys) {
                findViewById(R.id.progressBar).setVisibility(View.GONE);
                new ItemViewAdapter().setConfig(recyclerView,MenuDetails.this,promotions,keys,MenuDetails.this);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsDeleted() {

            }

            @Override
            public void DataIsUpdated() {

            }
        });
    }
}