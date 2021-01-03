package com.example.kfc;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PrefConfig {
    private static final String LIST_KEY ="list_key" ;

    public static void writeListInPref(Context context, List<FoodItemModel> list){
        Gson gson=new Gson();
        String jsonString=gson.toJson(list);
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(LIST_KEY,jsonString);
        editor.apply();
    }
    public static  List<FoodItemModel> readListFromPref(Context context){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        String jsonString=sharedPreferences.getString(LIST_KEY,"");

        Gson gson=new Gson();
        Type type=new TypeToken<ArrayList<FoodItemModel>>(){}.getType();
        List<FoodItemModel> list=gson.fromJson(jsonString,type);
        return list;
    }
}
