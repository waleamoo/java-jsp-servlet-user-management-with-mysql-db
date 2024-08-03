package com.techqwerty.dto;

public class Student {

    private int id;
    private String studentName;
    private String studentEmail;
    private String studentCountry;
    private int userId;

    
    public Student() {
    }
    
    public Student(int id, String studentName, String studentEmail, String studentCountry, int userId) {
        this.id = id;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.studentCountry = studentCountry;
        this.userId = userId;
    }

    public Student(String studentName, String studentEmail, String studentCountry, int userId) {
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.studentCountry = studentCountry;
        this.userId = userId;
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return this.studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentCountry() {
        return this.studentCountry;
    }

    public void setStudentCountry(String studentCountry) {
        this.studentCountry = studentCountry;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
}
