package com.example.root.sqliteexample;

/**
 * Created by root on 16/3/16.
 */
public class Employee {
    String name;
    String designation;
    String tagline;
    String department;
    int empcode;
    int _id;
    byte[] _image;


    public Employee() {}

    public Employee(String name, String designation, String tagline, String department, int empcode,byte[] image) {
        this.name = name;
        this.designation = designation;
        this.tagline = tagline;
        this.department = department;
        this.empcode = empcode;
        this._image=image;
    }

    public byte[] get_image() {
        return _image;
    }

    public void set_image(byte[] _image) {
        this._image = _image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getEmpcode() {
        return empcode;
    }

    public void setEmpcode(int empcode) {
        this.empcode = empcode;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
