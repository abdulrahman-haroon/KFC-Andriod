package com.example.kfc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MoreActivity extends AppCompatActivity {
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    String firstName="",lastName="",userEmail="";
    TextView nav_title,nav_subtitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        Toolbar toolbar = findViewById(R.id.more_toolbar);
        setSupportActionBar(toolbar);//setting the Action bar of this activity.

        SharedPreferences sharedPreferences=getSharedPreferences(MainActivity.SHARED_PREFS,MODE_PRIVATE);
        firstName=sharedPreferences.getString("firstName","");
        lastName=sharedPreferences.getString("lastName","");
        userEmail=sharedPreferences.getString("userEmail","");

        nav=findViewById(R.id.navigation_menu);
        drawerLayout=findViewById(R.id.drawer_more);
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
                        Intent inte=new Intent(getApplicationContext(),Home_Page.class);
                        startActivity(inte);
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
                        Toast.makeText(MoreActivity.this, "Successfully Logout", Toast.LENGTH_SHORT).show();
                        startActivity(intent);

                        break;
                }
                return false;
            }
        });
    }
    public void onClickStore(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.kfcpakistan.com/page/location"));
        startActivity(browserIntent);
    }
    public void onClickContactUs(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.kfcpakistan.com/page/contact"));
        startActivity(browserIntent);
    }
    public void onClickAboutUs(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.kfcpakistan.com/page/about-us"));
        startActivity(browserIntent);
    }
    public void onClickMitao(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.kfcpakistan.com/page/mitao-bhook"));
        startActivity(browserIntent);
    }
    public void onClickSecret(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.kfcpakistan.com/page/nutritional-information"));
        startActivity(browserIntent);
    }
    public void onClickPrivacy(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.kfcpakistan.com/page/privacy-policy"));
        startActivity(browserIntent);
    }
    public void onClickTerms(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.kfcpakistan.com/page/terms-and-conditions"));
        startActivity(browserIntent);
    }

    public void DealoneClick(View view) {
        Intent i=new Intent(this,Promotion_Deals.class);
        startActivity(i);
    }

    public void DealtwoClick(View view) {
        Intent i=new Intent(this,Promotion_Deals.class);
        startActivity(i);
    }

    public void DealthreeClick(View view) {
        Intent i=new Intent(this,Promotion_Deals.class);
        startActivity(i);
    }
}