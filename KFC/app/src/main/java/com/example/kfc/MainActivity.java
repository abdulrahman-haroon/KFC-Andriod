package com.example.kfc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText phone_no_et, pass_et;
    FirebaseAuth mFirebaseAuth;
    String firstName = "", lastName = "", userEmail = "";
    public static final String SHARED_PREFS = "sharedPrefs";
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        phone_no_et = findViewById(R.id.login_phone_no);
        pass_et = findViewById(R.id.login_pass_et);
        progressBar = findViewById(R.id.login_progress_bar);
        progressBar.setVisibility(View.INVISIBLE);

        Intent serviceIntent = new Intent(this, MyForegroundServiceClass.class);
        startService(serviceIntent);
    }

    public void onClickRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void onClickGuest(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("firstName", "Guest");
        editor.putString("lastName", " ");
        editor.putString("userEmail", "Welcome");
        editor.apply();
        Intent intent = new Intent(this, Home_Page.class);
        startActivity(intent);
    }

    public void onClickLogin(View view) {
        progressBar.setVisibility(View.VISIBLE);
        final String phoneNo = phone_no_et.getText().toString();
        final String pass = pass_et.getText().toString();
        if (phoneNo.isEmpty()) {
            phone_no_et.setError("Please enter email");
            phone_no_et.requestFocus();
        } else if (pass.isEmpty()) {
            pass_et.setError("Please enter password");
            pass_et.requestFocus();
        } else if (phoneNo.isEmpty() && pass.isEmpty()) {
            Toast.makeText(this, "Fields Are Empty!", Toast.LENGTH_SHORT).show();
        } else if (!(phoneNo.isEmpty() && pass.isEmpty())) {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
            Query checkUser = reference.orderByChild("phoneNo").equalTo(phoneNo);

            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        phone_no_et.setError(null);


                        String passwordFromDb = snapshot.child(phoneNo).child("pass").getValue(String.class);

                        if (passwordFromDb.equals(pass)) {
                            pass_et.setError(null);
                            firstName = snapshot.child(phoneNo).child("firstname").getValue(String.class);
                            lastName = snapshot.child(phoneNo).child("lastname").getValue(String.class);
                            userEmail = snapshot.child(phoneNo).child("email").getValue(String.class);

                            Toast.makeText(MainActivity.this, "Succuessfully Login", Toast.LENGTH_SHORT).show();
                            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("firstName", firstName);
                            editor.putString("lastName", lastName);
                            editor.putString("userEmail", userEmail);
                            editor.apply();

                            Intent intent = new Intent(getApplicationContext(), Home_Page.class);
//
//                            intent.putExtra("firstName", firstName);
//                            intent.putExtra("lastName", lastName);
//                            intent.putExtra("userEmail", userEmail);
                            startActivity(intent);
                        } else {
                            pass_et.setError("Wrong password");
                            pass_et.requestFocus();
                        }
                    } else {
                        phone_no_et.setError("No such user exists.");
                        phone_no_et.requestFocus();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }
    /**
     * For stopping the service that was being used.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent serviceIntent = new Intent(this, MyForegroundServiceClass.class);
        stopService(serviceIntent);
    }
}
