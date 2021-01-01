package com.example.kfc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText email,pass,repeatPass,firstname,lastname,address,postal,phoneNo;
    String city="";

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.reg_toolbar);
        setSupportActionBar(toolbar);//setting the Action bar of this activity.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        email=findViewById(R.id.reg_email_et);
        pass=findViewById(R.id.reg_password_et);
        repeatPass=findViewById(R.id.reg_repeat_pass_et);
        firstname=findViewById(R.id.reg_first_name_et);
        lastname=findViewById(R.id.reg_last_name_et);
        address=findViewById(R.id.reg_address_et);
        postal=findViewById(R.id.reg_postal_et);
        phoneNo=findViewById(R.id.reg_phone_no_et);

//        Spinner spinner=findViewById(R.id.spinner_country);
//        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.country,android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(this);

        Spinner spinner2=findViewById(R.id.spinner_city);
        ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource(this,R.array.city,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text=parent.getItemAtPosition(position).toString();
        city=text;
//        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void onClickLoginRegister(View view){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void onClickCreateAccount(View view){
        rootNode=FirebaseDatabase.getInstance();
        reference=rootNode.getReference("users");
        String reg_email=email.getText().toString();
        String reg_pass=pass.getText().toString();
        String reg_rep_pass=repeatPass.getText().toString();
        String reg_firstName=firstname.getText().toString();
        String reg_lastName=lastname.getText().toString();
        String reg_address=address.getText().toString();
        String reg_postal=postal.getText().toString();
        String reg_phoneNo=phoneNo.getText().toString();

        if(reg_email.isEmpty()){
            email.setError("Please enter email");
            email.requestFocus();
        }else if(reg_pass.isEmpty()){
            pass.setError("Please enter password");
            pass.requestFocus();
        }
        else if(reg_rep_pass.isEmpty()){
            repeatPass.setError("Please enter repeated pass");
            repeatPass.requestFocus();
        }
        else if(reg_firstName.isEmpty()){
            firstname.setError("Please enter first name");
            firstname.requestFocus();
        }
        else if(reg_lastName.isEmpty()){
            lastname.setError("Please enter last name");
            lastname.requestFocus();
        }
        else if(reg_address.isEmpty()){
            address.setError("Please enter address");
            address.requestFocus();
        }
        else if(reg_postal.isEmpty()){
            postal.setError("Please enter postal/zip code");
            postal.requestFocus();
        }
        else if(reg_phoneNo.isEmpty()){
            phoneNo.setError("Please enter phone no");
            phoneNo.requestFocus();
        }
        else if(reg_email.isEmpty()&&reg_pass.isEmpty()&&reg_rep_pass.isEmpty()&&reg_firstName.isEmpty()&&reg_lastName.isEmpty()&&reg_address.isEmpty()&&reg_postal.isEmpty()&&reg_phoneNo.isEmpty()&&city.isEmpty()){
            Toast.makeText(this, "Fields Are Empty!", Toast.LENGTH_SHORT).show();

        }
        else if(!reg_email.isEmpty()&&!reg_pass.isEmpty()&&!reg_rep_pass.isEmpty()&&!reg_firstName.isEmpty()&&!reg_lastName.isEmpty()&&!reg_address.isEmpty()&&!reg_postal.isEmpty()&&!reg_phoneNo.isEmpty()&&!city.isEmpty()){
            if(reg_pass.equals(reg_rep_pass)){
                UserRegHelper user=new UserRegHelper(reg_email,reg_pass,reg_firstName,reg_lastName,reg_address,reg_postal,reg_phoneNo,"Pakistan",city);
                reference.child(reg_phoneNo).setValue(user);
            }
            else
            {
                Toast.makeText(this, "Password Didn't Match", Toast.LENGTH_SHORT).show();
            }
        }


    }
}