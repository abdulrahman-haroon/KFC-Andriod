package com.example.kfc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class BucketActivity extends AppCompatActivity {
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bucket);

        Toolbar toolbar = findViewById(R.id.bucket_toolbar);
        setSupportActionBar(toolbar);//setting the Action bar of this activity.

        nav=findViewById(R.id.navigation_menu_bucket);
        drawerLayout=findViewById(R.id.drawer_bucket);

        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_nav,R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()){
                    case R.id.nav_home:
                        Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_menu:
                        Toast.makeText(getApplicationContext(), "Menu", Toast.LENGTH_SHORT).show();
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
    }
    public void onClickStartOrder(View view){
        Toast.makeText(this, "Goes to MENU", Toast.LENGTH_SHORT).show();
    }
}