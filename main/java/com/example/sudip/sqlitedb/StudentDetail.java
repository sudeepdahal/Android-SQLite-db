package com.example.sudip.sqlitedb;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StudentDetail extends AppCompatActivity {
    TextView jbo1,jbo2,jbo3,jbo4,jbo5;
    Button gback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);


        jbo1 = (TextView)findViewById(R.id.btv1);
        jbo2 = (TextView)findViewById(R.id.btv2);
        jbo3 = (TextView)findViewById(R.id.btv3);
        jbo4 = (TextView)findViewById(R.id.btv4);
        jbo5 = (TextView)findViewById(R.id.btv5);
        Intent i = getIntent();
        String did =  i.getStringExtra("id");
        String dname =  i.getStringExtra("name");
        String daddress =  i.getStringExtra("address");
        String dfaculty =  i.getStringExtra("faculty");
        final String dphone =  i.getStringExtra("phone");
        String demail =  i.getStringExtra("email");

        jbo1.setText("Name:"+dname);
        jbo2.setText("Address:"+daddress);
        jbo3.setText("Faculty:"+dfaculty);
        jbo4.setText(dphone);
        jbo5.setText("Email:"+demail);

        jbo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii= new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"+dphone));
                startActivity(ii);
            }
        });

        gback=(Button)findViewById(R.id.goback);
        gback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
