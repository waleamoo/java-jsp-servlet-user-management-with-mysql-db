package com.techqwerty.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.techqwerty.dto.Student;
import com.techqwerty.dto.User;

public class ApplicationDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/jsp_user_db?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";
    private String jdbcDriver = "com.mysql.cj.jdbc.Driver";

    private static final String LOGIN_USER_BY_USERNAME = "SELECT * FROM users WHERE username = ? AND password = ?";
    private static final String REGISTER_USER = "INSERT INTO `users`(`id`, `fullname`, `username`, `password`, `email`, `phone`) VALUES ( ? , ?, ?, ?, ?, ?);";
    private static final String REGISTER_STUDENT = "INSERT INTO `students`(`student_name`, `student_email`, `student_country`, `user_id`) VALUES (?, ?, ?, ?, ?);";
    private static final String GET_STUDENT_BY_ID_AND_USER = "SELECT * FROM `students` WHERE `user_id` = ? AND `id` = ?;";
    private static final String GET_STUDENTS_BY_ID_AND_USER = "SELECT * FROM `students` WHERE `user_id` = ?;";
    private static final String DELETE_STUDENT_BY_ID_AND_USER = "DELETE FROM `students` WHERE `user_id` = ? AND `id` = ?;";
    private static final String UPDATE_STUDENT_BY_ID_AND_USER = "UPDATE `students` SET `student_name`= ?, `student_email`= ?, `student_country`= ? WHERE `user_id` = ? AND `id` = ?;";


    public ApplicationDAO(){

    }

    protected Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

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
        try {
            PreparedStatement pst = con.prepareStatement(REGISTER_USER); 
            con.prepareStatement(REGISTER_USER);
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
            PreparedStatement pst = con.prepareStatement(LOGIN_USER_BY_USERNAME);
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

    // student action methods 
    public void addStudent(Student student){
        System.out.println(REGISTER_STUDENT);
        try (Connection connection = getConnection(); 
            PreparedStatement preparedStatement = connection.prepareStatement(REGISTER_STUDENT);){
            preparedStatement.setString(1, student.getStudentName());
            preparedStatement.setString(2, student.getStudentEmail());
            preparedStatement.setString(3, student.getStudentCountry());
            preparedStatement.setInt(4, student.getUserId());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Student getStudent(int userId, int studentId){
        Student student = null;
        try (Connection connection = getConnection(); 
            PreparedStatement preparedStatement = connection.prepareStatement(GET_STUDENT_BY_ID_AND_USER);){
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, studentId);
            System.out.println(preparedStatement);
            ResultSet rs =  preparedStatement.executeQuery();
            if(rs.next()){
                student = new Student(rs.getInt("id"), rs.getString("student_name"), rs.getString("student_email"), rs.getString("student_country"), rs.getInt("user_id"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return student; // returns a null student or the found student 
    }

    public List<Student> getAllStudents(int userId){
        List<Student> students = new ArrayList<>();
        try (Connection connection = getConnection(); 
            PreparedStatement preparedStatement = connection.prepareStatement(GET_STUDENTS_BY_ID_AND_USER);){
            preparedStatement.setInt(1, userId);
            System.out.println(preparedStatement);
            ResultSet rs =  preparedStatement.executeQuery();
            while(rs.next()){
                students.add(new Student(rs.getInt("id"), rs.getString("student_name"), rs.getString("student_email"), rs.getString("student_country"), rs.getInt("user_id")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return students; 
    }

    public boolean updateStudent(Student student, int userId, int studentId){
        boolean rowUpdated = false;
        try (Connection connection = getConnection(); 
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT_BY_ID_AND_USER);){
            preparedStatement.setString(1, student.getStudentName());
            preparedStatement.setString(2, student.getStudentEmail());
            preparedStatement.setString(3, student.getStudentCountry());
            preparedStatement.setInt(4, userId);
            preparedStatement.setInt(5, studentId);
            System.out.println(preparedStatement);
            
            rowUpdated = preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rowUpdated; 
    }

    public boolean deleteStudent(int userId, int studentId){
        boolean rowDeleted = false;
        try (Connection connection = getConnection(); 
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT_BY_ID_AND_USER);){
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, studentId);
            System.out.println(preparedStatement);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rowDeleted; 
    }

}
