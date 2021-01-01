package com.example.kfc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Promotion_Deals extends AppCompatActivity {

    ImageView imageView;
    CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion__deals);


    }


    public void ClickPromotionOne(View view) {
        Intent i=new Intent(this,Home_Page.class);

        Bundle bundle=getIntent().getExtras();

        if(bundle!=null)
        {
            int resId=bundle.getInt("Promotion");
            imageView.setImageResource(resId);
        }
        startActivity(i);

    }
}