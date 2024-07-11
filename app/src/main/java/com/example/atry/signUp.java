package com.example.atry;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class signUp extends AppCompatActivity {
    Button back, submit;
    EditText phone,pass, cpass,email,name;
    String emailPatten = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]+";
    ImageView hide,show,chide,cshow;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        name=findViewById(R.id.createname);
        phone=findViewById(R.id.createNumber);
        pass=findViewById(R.id.createPass);
        cpass=findViewById(R.id.createPassf);
        email=findViewById(R.id.createEmail);

       back = findViewById(R.id.back_button);
       submit = findViewById(R.id.submit_button);

       back.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               onBackPressed();
           }
       });

       submit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick (View view){
               if (name.getText().toString().trim().equals("")){
                   name.setError("E-mail is required");
               }
               else if (email.getText().toString().trim().equals("")) {
                   email.setError("na is required");
               }
               else if (!email.getText().toString().trim().matches(emailPatten)){
                   email.setError("Wrong Email");
               }
               else if (phone.getText().toString().trim().equals("")){
                   phone.setError("Phone number is required");
               }
               else if (phone.getText().toString().trim().length() < 10){
                   phone.setError("Wrong phone number");
               }
               else if (pass.getText().toString().trim().equals("")){
                   pass.setError("Password is required");
               }
               else if (pass.getText().toString().trim().length() < 6) {
                   pass.setError("Minimum 6 letter required");
               }
               else if (cpass.getText().toString().trim().equals("")){
                   cpass.setError("Password is required");
               }
               else if (cpass.getText().toString().trim().length() < 6){
                   cpass.setError("Minimum 6 letter required");
               }
               else if(!pass.getText().toString().equals(cpass.getText().toString())) {
                   cpass.setError("password Don't match. enter again");
               }
               else {
                   onBackPressed();
               }
           }
       });

       show = findViewById(R.id.sign_show);
       hide = findViewById(R.id.sign_hide);
       cshow = findViewById(R.id.sign_cshow);
       chide = findViewById(R.id.sign_chide);

        show.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                show.setVisibility(View.GONE);
                hide.setVisibility(View.VISIBLE);
                pass.setTransformationMethod(null);
            }
        });

        hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show.setVisibility(View.VISIBLE);
                hide.setVisibility(View.GONE);
                pass.setTransformationMethod(new PasswordTransformationMethod());
            }
        });

        cshow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                cshow.setVisibility(View.GONE);
                chide.setVisibility(View.VISIBLE);
                cpass.setTransformationMethod(null);
            }
        });

        chide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cshow.setVisibility(View.VISIBLE);
                chide.setVisibility(View.GONE);
                cpass.setTransformationMethod(new PasswordTransformationMethod());
            }
        });

    }
}