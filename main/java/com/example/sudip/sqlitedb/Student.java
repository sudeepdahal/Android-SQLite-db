package com.example.sudip.sqlitedb;

/**
 * Created by Sudip on 6/16/2016.
 */
//model class
public class Student {

    int id;
    String name,address,faculty,email,phone;

    public Student() {
    }

    public Student(String name, String address, String faculty, String phone, String email) {
        this.name = name;
        this.address = address;
        this.faculty = faculty;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
