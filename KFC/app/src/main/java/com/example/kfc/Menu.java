package com.example.kfc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class Menu extends AppCompatActivity {
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<MenuDataModel> data;
    String[] menuTitles;
    String[] db;
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;

    String firstName="",lastName="",userEmail="";
    TextView nav_title,nav_subtitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        Toolbar toolbar = findViewById(R.id.menu_toolbar);
        setSupportActionBar(toolbar);//setting the Action bar of this activity.

        SharedPreferences sharedPreferences=getSharedPreferences(MainActivity.SHARED_PREFS,MODE_PRIVATE);
        firstName=sharedPreferences.getString("firstName","");
        lastName=sharedPreferences.getString("lastName","");
        userEmail=sharedPreferences.getString("userEmail","");


        nav=findViewById(R.id.navigation_menu_menu);
        drawerLayout=findViewById(R.id.drawer_menu);
        View headerView=nav.getHeaderView(0);
        nav_title=(TextView) headerView.findViewById(R.id.nav_header_title);
        nav_subtitle=(TextView) headerView.findViewById(R.id.nav_header_subtitle);

        nav_title.setText(firstName+" "+lastName);
        nav_subtitle.setText(userEmail);

        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_nav,R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()){
                    case R.id.nav_home:

                        intent=new Intent(getApplicationContext(),Home_Page.class);
                        startActivity(intent);
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


        recyclerView =  findViewById(R.id.menu_recycler_view);
        //used for optimization.
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        data = new ArrayList<>();

        db=getResources().getStringArray(R.array.dbNames);
        menuTitles = getResources().getStringArray(R.array.menuItems);
        Integer[] menuImages = {R.drawable.value,R.drawable.meal,R.drawable.box,R.drawable.sharing,R.drawable.fries,R.drawable.promotion};
        for (int i = 0; i < menuTitles.length; i++) {
            data.add(new MenuDataModel(
                    menuTitles[i],
                    menuImages[i],
                    db[i]

            ));
        }
        MenuCustomAdapter customAdapter=new MenuCustomAdapter(data,this,Menu.this);
        recyclerView.setAdapter(customAdapter);
    }
}