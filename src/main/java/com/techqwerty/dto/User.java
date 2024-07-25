package com.techqwerty.dto;
import java.util.Objects;

public class User {
    private int id;
    private String username;
    private String fullname;
    private String password;
    private String email;
    private String phone;

    
    public User() {
    }

    public User(int id, String username, String fullname, String password, String email, String phone) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return this.fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}

