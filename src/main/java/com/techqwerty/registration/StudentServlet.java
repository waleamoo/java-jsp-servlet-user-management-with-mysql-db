package com.techqwerty.registration;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.techqwerty.dao.ApplicationDAO;
import com.techqwerty.dto.Student;

@WebServlet("/")
public class StudentServlet extends HttpServlet {

    private ApplicationDAO applicationDAO;

    @Override
    public void init() throws ServletException {
        applicationDAO = new ApplicationDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/new":
                showNewForm(req, resp);
                break;
            case "/add":
                insertStudent(req, resp);
                break;
            case "/delete":
                deleteStudent(req, resp);
                break;
            case "/edit":
                showEditForm(req, resp);
                break;
            case "/update":
                updateStudent(req, resp);
                break;
            default: // "list" action 
                listStudent(req, resp);
                break;
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("/home/user-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        int user_id = Integer.valueOf(request.getParameter("user_id"));

        Student newStudent = new Student(name, email, country, user_id);
        applicationDAO.addStudent(newStudent);
        response.sendRedirect("list");
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        try {
            applicationDAO.deleteStudent(user_id, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        response.sendRedirect("list");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        int user_id = Integer.parseInt(request.getParameter("user_id"));

        Student existingStudent;
        try {
            existingStudent = applicationDAO.getStudent(id, user_id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/home/user-form.jsp");
            request.setAttribute("student", existingStudent);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");

        Student existingStudent;
        try {
            existingStudent = applicationDAO.getStudent(id, user_id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/home/user-form.jsp");
            request.setAttribute("student", existingStudent);
            dispatcher.forward(request, response);

            Student newStudent = new Student(id, name, email, country, user_id);
            applicationDAO.updateStudent(newStudent, user_id, id);
            response.sendRedirect("list");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("user_id");
        try {
            List<Student> students = applicationDAO.getAllStudents(userId);
            request.setAttribute("listStudent", students);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/home/profile.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
