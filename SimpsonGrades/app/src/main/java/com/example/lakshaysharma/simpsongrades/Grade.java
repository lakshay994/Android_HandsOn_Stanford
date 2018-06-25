package com.example.lakshaysharma.simpsongrades;

import java.io.Serializable;

public class Grade implements Serializable{
    public int course_id;
    public String course_name;
    public String grade;
    public int student_id;

    public Grade() {}

    public String toString() {
        return "Grade {course_id=" + course_id + ", course_name=" + course_name
                + ", student_id=" + student_id + ", grade=" + grade + "}";
    }
}

