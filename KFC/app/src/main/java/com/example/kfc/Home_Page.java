package com.example.kfc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Home_Page extends AppCompatActivity {

    ImageView imageView1,imageView2,imageView3;
    CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__page);

        cardView=(CardView) findViewById(R.id.cardView);
        imageView1=(ImageView) findViewById(R.id.homepic_1);
        imageView2=(ImageView) findViewById(R.id.homepic_2);
        imageView3=(ImageView) findViewById(R.id.homepic_3);
    }

    public void onClickDeals(View view) {

        Intent i=new Intent(this,Promotion_Deals.class);
        i.putExtra("Promotion",R.drawable.pro_1);
        i.putExtra("Title",R.id.textdeal1);
        startActivity(i);


    }

    public void onClickDeal_2(View view) {
        Intent i=new Intent(this,Promotion_Deals.class);
        i.putExtra("Promotion",R.drawable.deal2);
      //  i.putExtra("Title",R.id.textdeal2);
        startActivity(i);
    }

    public void onClickDeals_3(View view) {
        Intent i=new Intent(this,Promotion_Deals.class);
        i.putExtra("Promotion",R.drawable.deal3);
       // i.putExtra("Title",R.id.textdeal3);
        startActivity(i);
    }
}