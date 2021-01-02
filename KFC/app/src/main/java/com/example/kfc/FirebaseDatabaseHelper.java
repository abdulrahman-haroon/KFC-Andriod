package com.example.kfc;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private List<FoodItemModel> itemsList=new ArrayList<>();
    public interface  DataStatus
    {
        void DataIsLoaded(List<FoodItemModel> promotions, List<String> keys);
        void DataIsInserted();
        void DataIsDeleted();
        void DataIsUpdated();

    }

    public FirebaseDatabaseHelper(String dbName) {
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference(dbName);

    }
    public void readData(final DataStatus dataStatus)
    {
        if(databaseReference!=null)
        {
            System.out.println("Reference fetched");
        }
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                itemsList.clear();
                List<String> keys= new ArrayList<>();
                for(DataSnapshot keynode: snapshot.getChildren())
                {
                    keys.add(keynode.getKey());
                    FoodItemModel item =keynode.getValue(FoodItemModel.class);
                    itemsList.add(item);
                }
                dataStatus.DataIsLoaded(itemsList,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
