package com.example.kfc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText email_et,pass_et;
    FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth=FirebaseAuth.getInstance();
        email_et=findViewById(R.id.login_phone_no);
        pass_et=findViewById(R.id.login_pass_et);

    }
    public void onClickRegister(View view){
        Intent intent=new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
    public void onClickGuest(View view){
        Intent intent=new Intent(this,MoreActivity.class);
        startActivity(intent);
    }
    public void onClickLogin(View view){
        final String email=email_et.getText().toString();
        final String pass=pass_et.getText().toString();
        if(email.isEmpty()){
            email_et.setError("Please enter email");
            email_et.requestFocus();
        }
        else if (pass.isEmpty()){
            pass_et.setError("Please enter password");
            pass_et.requestFocus();
        }else if(email.isEmpty() && pass.isEmpty()){
            Toast.makeText(this, "Fields Are Empty!", Toast.LENGTH_SHORT).show();
        }else if(!(email.isEmpty() && pass.isEmpty())){
            DatabaseReference reference= FirebaseDatabase.getInstance().getReference("users");
            Query checkUser=reference.orderByChild("phoneNo").equalTo(email);

            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        email_et.setError(null);


                        String passwordFromDb=snapshot.child(email).child("pass").getValue(String.class);

                        if(passwordFromDb.equals(pass)){
                            pass_et.setError(null);

                            Toast.makeText(MainActivity.this, "Succuessfully Login", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            pass_et.setError("Wrong password");
                            pass_et.requestFocus();
                        }
                    }
                    else{
                        email_et.setError("No such user exists.");
                        email_et.requestFocus();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }
}