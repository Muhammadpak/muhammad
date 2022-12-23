package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class update extends AppCompatActivity {
EditText editfname,editlname,editemail;
Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        editfname=findViewById(R.id.editfname);
        editlname=findViewById(R.id.editlname);
        editemail=findViewById(R.id.editemail);
        btn=findViewById(R.id.editbtn);
        Intent i = getIntent();
        int id = i.getIntExtra("id",0);
        editfname.setText(i.getStringExtra("fname"));
        editlname.setText(i.getStringExtra("lname"));
        editemail.setText(i.getStringExtra("email"));
        DatabaseHelper databaseHelper = new DatabaseHelper(update .this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fnamestr =editfname.getText().toString();
                String lnamestr = editlname.getText().toString();
                String emailstr = editemail.getText().toString();
                boolean res = databaseHelper.updateRecord(fnamestr,lnamestr,emailstr,id);
                if(res){
                    Toast.makeText(update.this, "Record updated", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(update.this,Dblist.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(update.this, "Record not updated", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(update.this,Dblist.class);
                    startActivity(i);
                    finish();
                }
            }
        });

    }
}