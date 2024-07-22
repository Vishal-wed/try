package com.example.atry;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class tryActivity extends AppCompatActivity {

    TextView name,email,number,password,gender;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_try);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sp = getSharedPreferences(ConstantSp.pref, MODE_PRIVATE);

        name = findViewById(R.id.show_name);
        email = findViewById(R.id.show_email);
        number = findViewById(R.id.show_number);
        password = findViewById(R.id.show_password);
        gender = findViewById(R.id.show_gender);

        name.setText(sp.getString(ConstantSp.name, ""));
        email.setText(sp.getString(ConstantSp.email, ""));
        number.setText(sp.getString(ConstantSp.number, ""));
        password.setText(sp.getString(ConstantSp.password, ""));
        gender.setText(sp.getString(ConstantSp.gender, ""));
    }
}