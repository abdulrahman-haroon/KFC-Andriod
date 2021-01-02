package com.example.kfc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Menu extends AppCompatActivity {
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<MenuDataModel> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        Toolbar toolbar = findViewById(R.id.menu_toolbar);
        setSupportActionBar(toolbar);//setting the Action bar of this activity.

        recyclerView = (RecyclerView) findViewById(R.id.menu_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
        data = new ArrayList<>();
        String[] menuTitles;
        String[] db=getResources().getStringArray(R.array.dbNames);
        menuTitles = getResources().getStringArray(R.array.menuItems);
        Integer[] menuImages = {R.drawable.value,R.drawable.meal,R.drawable.box,R.drawable.sharing,R.drawable.snacks};
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