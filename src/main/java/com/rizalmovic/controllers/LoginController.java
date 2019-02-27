package com.rizalmovic.controllers;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rizalmovic.libraries.Auth.AuthInterface;
import com.rizalmovic.libraries.Auth.AuthMySQL;

@WebServlet("/login")
public class LoginController extends Controller {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.render("login", response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        try {
            AuthInterface auth = new AuthMySQL();

            String email = request.getParameter("email");
            String password = request.getParameter("password");

            if(auth.login(email, password) == true) {
                session.setAttribute("username", email);
                session.setAttribute("isLoggedIn", true);
                response.sendRedirect("/");
            } else {
                response.sendError(403, "Invalid username & password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}