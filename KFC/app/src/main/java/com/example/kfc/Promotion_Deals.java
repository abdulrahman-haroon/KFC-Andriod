package com.example.kfc;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class Promotion_Deals extends AppCompatActivity {

    ImageView imageView;
    CardView cardView,cardView1;
    TextView textView1,textView2,textView3,textView4,textView5;
    Button btn;
    RadioGroup radioGroup;
    String deals="";


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion__deals);

        Toolbar toolbar = findViewById(R.id.pro_toolbar);
        setSupportActionBar(toolbar);




    }
    //deal image 1 view
    public void onRadioButtonClicked(View view) {
        boolean checked=((RadioButton) view).isChecked();

        RadioButton radioButton;
        switch (view.getId()) {
            case R.id.buffalowings:
                if(checked)
                {

                }
                break;
        }
    }

    public void DealoneClick(View view) {
        Intent i = new Intent(this, Home_Page.class);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            int resId = bundle.getInt("Promotion");
            imageView.setImageResource(resId);
        }
        startActivity(i);
    }
}