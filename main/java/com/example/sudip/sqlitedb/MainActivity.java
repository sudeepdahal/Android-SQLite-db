package com.example.sudip.sqlitedb;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.widget.TextView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Database db;
    TextView tv;
    ListView listView;
    List<String> st;
    String[] days={"apple","banana","orange"};
    List<Student> studentList;
    Student std;
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new Database(MainActivity.this);//
        listView=(ListView)findViewById(R.id.lv);
        st = new ArrayList<>();
        studentList = new ArrayList<>();

        getValue();
       adapter=new MyAdapter(MainActivity.this,R.layout.custom_row,studentList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student student = studentList.get(position);
                Toast.makeText(MainActivity.this, student.getName()+" Loading", Toast.LENGTH_SHORT).show();
                Intent ei = new Intent(MainActivity.this,StudentDetail.class);
                ei.putExtra("id",student.getId());
                ei.putExtra("name",student.getName());
                ei.putExtra("address",student.getAddress());
                ei.putExtra("faculty",student.getFaculty());
                ei.putExtra("phone",student.getPhone());
                ei.putExtra("email",student.getEmail());
                startActivity(ei);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final Student student = studentList.get(position);
                CharSequence[] sequences={"update","Delete"};
                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Title");
                 alert.setItems(sequences, new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         if (which == 0) {
                             Intent intent = new Intent(MainActivity.this,UpdateRecord.class);
                             intent.putExtra("myid",student.getId());
                             intent.putExtra("name",student.getName());
                             intent.putExtra("address",student.getAddress());
                             intent.putExtra("faculty",student.getFaculty());
                             intent.putExtra("phone",student.getPhone());
                             intent.putExtra("email",student.getEmail());
                             startActivityForResult(intent,1);
                             Toast.makeText(MainActivity.this, "up", Toast.LENGTH_SHORT).show();
                         }
                         if (which == 1) {
                             Student student = studentList.get(position);
                             db.deleteRecord(student);
                             studentList.clear();
                             getValue();
                             adapter.notifyDataSetChanged();
                             //Toast.makeText(MainActivity.this, "del", Toast.LENGTH_SHORT).show();
                         }
                     }
                 });
                alert.create()
                        .show();

                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == resultCode)
        {
            studentList.clear();
            getValue();
            adapter.notifyDataSetChanged();
        }
    }

    private void getValue() {
        for (int i = 0; i < db.getAllValues().size(); i++) {
            st.add(db.getAllValues().get(i).getName());
            Log.d("name", db.getAllValues().get(i).getName());

            Student student = new Student();
            student.setId(db.getAllValues().get(i).getId());
            student.setName(db.getAllValues().get(i).getName());
            student.setAddress(db.getAllValues().get(i).getAddress());
            student.setFaculty(db.getAllValues().get(i).getFaculty());
            student.setPhone(db.getAllValues().get(i).getPhone());
            student.setEmail(db.getAllValues().get(i).getEmail());

            studentList.add(student);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();
        if(id==R.id.exit){
            Toast.makeText(MainActivity.this,"Exiting",Toast.LENGTH_SHORT).show();
            System.exit(0);
        }
        if(id==R.id.add){
            Intent intent = new Intent(MainActivity.this,AddAccount.class);
            startActivity(intent);
        }

        return true;
    }
}
