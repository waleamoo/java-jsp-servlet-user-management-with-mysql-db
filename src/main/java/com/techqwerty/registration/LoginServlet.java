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
import com.techqwerty.dto.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private ApplicationDAO applicationDAO;

    @Override
    public void init() throws ServletException {
        applicationDAO = new ApplicationDAO();
    }

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

        // attempt login 
        User user = applicationDAO.loginUser(username, password);

        if (user != null) {
            session.setAttribute("name", user.getFullname());
            session.setAttribute("user_id", user.getId());
            session.setAttribute("user_name", user.getFullname());
            // get the login user's students 
            List<Student> students = applicationDAO.getAllStudents((int) session.getAttribute("user_id"));
            req.setAttribute("listStudent", students);
            dispatcher = req.getRequestDispatcher("/home/profile.jsp");
        }else{
            req.setAttribute("status", "failed");
            dispatcher = req.getRequestDispatcher("index.jsp");
        }
        dispatcher.forward(req, resp);

    }
}
