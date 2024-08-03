package com.techqwerty.registration;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.techqwerty.dao.ApplicationDAO;
import com.techqwerty.dto.User;

@WebServlet(description = "Registration servlet", urlPatterns = { "/register" })
public class RegistrationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ApplicationDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new ApplicationDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get form paramters 
        String username = req.getParameter("username");
        String fullname = req.getParameter("fullname");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String con_password = req.getParameter("con_password");

        // PrintWriter out = resp.getWriter();
        // out.print(username);
        RequestDispatcher dispatcher = null;

        // server-side validation for the user entries 
        if(username == null || username.equals("")){
            req.setAttribute("status", "invalidUsername");
            dispatcher = req.getRequestDispatcher("/home/register.jsp");
            dispatcher.forward(req, resp);
        }
        if(fullname == null || fullname.equals("")){
            req.setAttribute("status", "invalidFullname");
            dispatcher = req.getRequestDispatcher("/home/register.jsp");
            dispatcher.forward(req, resp);
        }

        if(phone.length() < 10 || !phone.matches("\\d+")){
            req.setAttribute("status", "invalidPhone");
            dispatcher = req.getRequestDispatcher("/home/register.jsp");
            dispatcher.forward(req, resp);
        }

        if(email == null || email.equals("")){
            req.setAttribute("status", "invalidEmail");
            dispatcher = req.getRequestDispatcher("/home/register.jsp");
            dispatcher.forward(req, resp);
        }

        if(!password.equals(con_password)){
            req.setAttribute("status", "invalidPassword");
            dispatcher = req.getRequestDispatcher("/home/register.jsp");
            dispatcher.forward(req, resp);
        }

        // create a user object 
        User user = new User(0, username, fullname, password, email, phone);
        // add the user to the database 
        try {
            
            int rowCount = dao.registerUser(user);
            dispatcher = req.getRequestDispatcher("/home/register.jsp");
            if (rowCount > 0) {
                req.setAttribute("status", "success");
            }else{
                req.setAttribute("status", "failed");
            }
            dispatcher.forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/home/register.jsp");;
        dispatcher.forward(req, resp);

        //resp.sendRedirect("home/register.jsp");
    }
}
