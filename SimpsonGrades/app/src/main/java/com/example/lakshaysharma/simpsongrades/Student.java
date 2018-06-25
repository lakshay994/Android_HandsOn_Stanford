package com.example.lakshaysharma.simpsongrades;

import java.io.Serializable;

public class Student implements Serializable{

    public String email;
    public String name;
    public String password;
    public int id;

    public Student(){}

    public String toString(){

        return "Student {id=" + id + ", name=" + name
                + ", email=" + email + ", password=" + password + "}";

    }

}
