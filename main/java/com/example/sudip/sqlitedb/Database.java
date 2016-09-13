package com.example.sudip.sqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sudip on 6/16/2016.
 */
public class Database extends SQLiteOpenHelper{

    public static final String DATABASE_NAME="db_student";
    public static final int DATABASE_Version=1;
    public static final String TABLE_STUDENT="students";

    //students table column names
    public static final String KEY_ID="id";
    public static final String KEY_NAME="name";
    public static final String KEY_ADDRESS="address";
    public static final String KEY_FACULTY="faculty";
    public static final String KEY_PHONE="phone";;
    public static final String KEY_EMAIL="email";

    public static final String CREATE_TABLE="CREATE TABLE "+TABLE_STUDENT+"("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+KEY_NAME+" TEXT,"
            +KEY_ADDRESS+" TEXT,"+KEY_FACULTY+" TEXT,"+KEY_PHONE+" TEXT,"+KEY_EMAIL+" TEXT)";



   // (id int primary key,name text,address text,faculty text,phone text,email text"


    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Log.d("tbl", "created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addStudent(Student st)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_NAME,st.getName());
        values.put(KEY_ADDRESS,st.getAddress());
        values.put(KEY_FACULTY,st.getFaculty());
        values.put(KEY_PHONE,st.getPhone());
        values.put(KEY_EMAIL,st.getEmail());
        db.insert(TABLE_STUDENT,null,values);
        Log.d("insert","sucessful");
        db.close();
    }

    public int getCount()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        int rows=(int) DatabaseUtils.queryNumEntries(db,TABLE_STUDENT);
        return rows;
    }

    public List<Student> getAllValues()
    {
        List<Student> studentList=new ArrayList<>();
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor cursor= db.rawQuery("SELECT * FROM " + TABLE_STUDENT, null);
        Student student;
        if(cursor.getCount()>0)
        {
            for(int i=0;i<cursor.getCount();i++)
            {
                cursor.moveToNext();
                student=new Student();
                student.setId(cursor.getInt(0));
                student.setName(cursor.getString(1));
                student.setAddress(cursor.getString(2));
                student.setFaculty(cursor.getString(3));
                student.setPhone(cursor.getString(4));
                student.setEmail(cursor.getString(5));

                studentList.add(student);
            }
        }
        cursor.close();
        return studentList;
    }

    public void deleteRecord(Student student)
    {SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_STUDENT,KEY_ID+"= ?",new String[]{String.valueOf(student.getId())});
        db.close();
    }
    public void updateRecord(Student st)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_NAME,st.getName());
        values.put(KEY_ADDRESS,st.getAddress());
        values.put(KEY_FACULTY,st.getFaculty());
        values.put(KEY_PHONE,st.getPhone());
        values.put(KEY_EMAIL,st.getEmail());
        db.update(TABLE_STUDENT, values, KEY_ID + "= ?", new String[]{String.valueOf(st.getId())});
        db.close();
    }
}
