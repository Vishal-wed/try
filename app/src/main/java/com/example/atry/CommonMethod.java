package com.example.atry;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class CommonMethod {

    CommonMethod(Context context, String mess){
        Toast.makeText(context, mess, Toast.LENGTH_LONG).show();
    }
    CommonMethod(View view, String mess){
        Snackbar.make(view, mess, Snackbar.LENGTH_LONG).show();
    }


}
