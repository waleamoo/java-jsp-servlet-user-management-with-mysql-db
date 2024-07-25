package com.techqwerty.registration;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.techqwerty.dao.ApplicationDAO;
import com.techqwerty.dto.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get the values from the form
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        RequestDispatcher dispatcher = null;

        // validate the user entries 
        if(username == null || username.equals("")){
            req.setAttribute("status", "invalidEmail");
            dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);
        }

        if(password == null || password.equals("")){
            req.setAttribute("status", "invalidPassword");
            dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);
        }

        // connet to database  
        ApplicationDAO dao = new ApplicationDAO();
        dao.connect();
        // attempt login 
        User user = dao.loginUser(username, password);

        if (user != null) {
            session.setAttribute("name", user.getFullname());
            dispatcher = req.getRequestDispatcher("/home/profile.jsp");
        }else{
            req.setAttribute("status", "failed");
            dispatcher = req.getRequestDispatcher("index.jsp");
        }
        dispatcher.forward(req, resp);

    }
}
