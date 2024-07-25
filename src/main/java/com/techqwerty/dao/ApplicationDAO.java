package com.techqwerty.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.techqwerty.dto.User;

public class ApplicationDAO {

    Connection con = null;

    public void connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jsp_user_db", "root", "");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /*
     * Action Method to perform CRUD on the database 
     */

    public int registerUser(User u) throws SQLException{
        String query = "INSERT INTO `users`(`id`, `fullname`, `username`, `password`, `email`, `phone`) VALUES ( ? , ?, ?, ?, ?, ?);";
        try {
            PreparedStatement pst = con.prepareStatement(query); 
            con.prepareStatement(query);
            pst.setInt(1, 0);
            pst.setString(2, u.getFullname());
            pst.setString(3, u.getUsername());
            pst.setString(4, u.getPassword());
            pst.setString(5, u.getEmail());
            pst.setString(6, u.getPhone());
            int rowCount = pst.executeUpdate();
            return rowCount;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally{
            con.close();
        }
        return 0;
    }

    public User loginUser(String username, String password){
        try {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);
            //Statement st = con.createStatement(); // since we are passing a parameter no need to use statement 
            //ResultSet rs = st.executeQuery(query);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                return user;
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
