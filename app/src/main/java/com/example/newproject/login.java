package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {
    EditText username,password;
    Button btn;
    TextView errormsg;

    @SuppressLint("MissingInflateId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        errormsg = findViewById(R.id.errormsg);
        if(username.equals("Admin") && password.equals("admin123")){
            SharedPreferences share = getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = share.edit();
            editor.putString("username",username.getText().toString());
            editor.putString("password",password.getText().toString());
            editor.apply();


        }
        else{
            errormsg.setText("Username or Password is incorrect");
        }
    }
}