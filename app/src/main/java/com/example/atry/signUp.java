package com.example.atry;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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
    CheckBox check;
    RadioGroup radio;
    String sradio;
    SQLiteDatabase db;


    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
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

        db = openOrCreateDatabase("diploma.db", MODE_PRIVATE, null);

        String create_table = "create table if not exists users(name varchar(20), email varchar(50), number bigint(10), passsword varchar(20), gender varchar(6))";
        db.execSQL(create_table);

        name = findViewById(R.id.createname);
        phone = findViewById(R.id.createNumber);
        pass = findViewById(R.id.createPass);
        cpass = findViewById(R.id.createPassf);
        email = findViewById(R.id.createEmail);
        radio = findViewById(R.id.gender);
        check = findViewById(R.id.team);
        submit = findViewById(R.id.submit_button);

        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rd = findViewById(i);
                sradio = rd.getText().toString();
                new CommonMethod(signUp.this, sradio);
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
               else if (!check.isChecked()){
                   new CommonMethod(signUp.this, "check Team & Condition");
               } else if (radio.getCheckedRadioButtonId() == -1) {
                   new CommonMethod(signUp.this, "select gender");
               } else {
                   String exist_check = "select * from users where email = '"+email.getText().toString()+"' or number = "+phone.getText().toString()+";";
                   Cursor cursor = db.rawQuery(exist_check, null);

                   if (cursor.getCount() > 0){

                       
                       new CommonMethod(signUp.this, "this user is already exist");
                   }
                    else {
                       new CommonMethod(signUp.this, "clicked");

                       String insert_value = "insert into users values('" + name.getText().toString() + "','" + email.getText().toString() + "', " + phone.getText().toString() + ", '" + pass.getText().toString() + "', '" + sradio + "')";
                       db.execSQL(insert_value);
                       Intent i = new Intent(signUp.this, MainActivity.class);
                       startActivity(i);
                   }
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