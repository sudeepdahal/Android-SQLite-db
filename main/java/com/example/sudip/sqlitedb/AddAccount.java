package com.example.sudip.sqlitedb;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddAccount extends AppCompatActivity {
    Button btn;
    EditText ed1,ed2,ed3,ed4,ed5;
    String n1,n2,n3,n4,n5;
  Database db;
    @Override

        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);



                ed1 = (EditText) findViewById(R.id.ed1);
                ed2 = (EditText) findViewById(R.id.ed2);
                ed3 = (EditText) findViewById(R.id.ed3);
                ed4 = (EditText) findViewById(R.id.ed4);
                ed5 = (EditText) findViewById(R.id.ed5);

        db=new Database(AddAccount.this);
              //  ed2 = (EditText) findViewById(R.id.ed2);
                btn = (Button) findViewById(R.id.btn);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        n1=ed1.getText().toString();
                        n2=ed2.getText().toString();
                        n3=ed3.getText().toString();
                        n4=ed4.getText().toString();
                        n5=ed5.getText().toString();

                        db.addStudent(new Student(n1,n2,n3,n4,n5));
                        Intent intent = new Intent(AddAccount.this,MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(AddAccount.this, n1+" A/C Created", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }

    }
