package com.example.sudip.sqlitedb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateRecord extends AppCompatActivity {
    Button btn;
    EditText ed1,ed2,ed3,ed4,ed5;
    String n1,n2,n3,n4,n5;
    Database db;
    Student st;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_record);


        ed1 = (EditText) findViewById(R.id.ed1);
        ed2 = (EditText) findViewById(R.id.ed2);
        ed3 = (EditText) findViewById(R.id.ed3);
        ed4 = (EditText) findViewById(R.id.ed4);
        ed5 = (EditText) findViewById(R.id.ed5);
        btn = (Button) findViewById(R.id.btn2);

        final Intent i = getIntent();
        final int did = i.getIntExtra("myid",0);
        String dname =  i.getStringExtra("name");
        String daddress =  i.getStringExtra("address");
        String dfaculty =  i.getStringExtra("faculty");
        String dphone =  i.getStringExtra("phone");
        String demail =  i.getStringExtra("email");

        ed1.setText(dname);
        ed2.setText(daddress);
        ed3.setText(dfaculty);
        ed4.setText(dphone);
        ed5.setText(demail);

        db=new Database(UpdateRecord.this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Student st=new Student();
                st.setId(did);
                st.setName(ed1.getText().toString());
                st.setAddress(ed2.getText().toString());
                st.setFaculty(ed3.getText().toString());
                st.setPhone(ed4.getText().toString());
                st.setEmail(ed5.getText().toString());
                db.updateRecord(st);
                Toast.makeText(UpdateRecord.this, "Update Sucessful!", Toast.LENGTH_SHORT).show();

                setResult(1);
                finish();
            }
        });
    }
}
