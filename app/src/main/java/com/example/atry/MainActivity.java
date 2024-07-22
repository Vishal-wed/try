package com.example.atry;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.health.connect.datatypes.units.Length;
import android.os.Bundle;
import android.se.omapi.Session;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    String patten = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]+";
    Button login;
    TextView showAns, signUp;
    EditText user,pass;
    ImageView hide,show;
    SQLiteDatabase db;
    SharedPreferences sp;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        sp = getSharedPreferences(ConstantSp.pref, MODE_PRIVATE);

        signUp = findViewById(R.id.signUp);
        login = findViewById(R.id.login_btn);
        user = findViewById(R.id.email);
        pass = findViewById(R.id.password);

        db = openOrCreateDatabase("diploma.db", MODE_PRIVATE, null);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, com.example.atry.signUp.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {

            public void onClick (View view) {
                if (user.getText().toString().trim().equals("")) {
                    user.setError("E-mail is required");
                }
                else if(!user.getText().toString().trim().matches(patten)){
                    user.setError("Invalid Email");
                }
                else if (pass.getText().toString().trim().equals("")){
                    pass.setError("Password is required");
                }
                else if (pass.getText().toString().trim().length() < 6){
                    pass.setError("Minimum 6 letter required");
                } else {
                    String Check_user = "select * from users where email = '"+user.getText().toString()+"' and passsword = '"+pass.getText().toString()+"';";
                    Cursor cursor = db.rawQuery(Check_user, null);
                    if (cursor.getCount() > 0) {

                        while (cursor.moveToNext()){
                            String sname = cursor.getString(0);
                            String semail = cursor.getString(1);
                            String snumber = cursor.getString(2);
                            String spassword = cursor.getString(3);
                            String sgender = cursor.getString(4);

                            sp.edit().putString(ConstantSp.name, sname).commit();
                            sp.edit().putString(ConstantSp.email, semail).commit();
                            sp.edit().putString(ConstantSp.number, snumber).commit();
                            sp.edit().putString(ConstantSp.password, spassword).commit();
                            sp.edit().putString(ConstantSp.gender, sgender).commit();

                        }

                        new CommonMethod(MainActivity.this, "Login successfully");
                        Intent intent = new Intent(MainActivity.this, tryActivity.class);
                        startActivity(intent);
                    } else {
                        new CommonMethod(MainActivity.this, "Wrong email/password");
                    }
                }
           }
        });

        show = findViewById(R.id.login_show);
        hide = findViewById(R.id.login_hide);

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
    }
}