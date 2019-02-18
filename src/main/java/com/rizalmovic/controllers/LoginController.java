package com.rizalmovic.controllers;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rizalmovic.libraries.Auth.AuthDummy;
import com.rizalmovic.libraries.Auth.AuthInterface;

@WebServlet("/login")
public class LoginController extends Controller {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.render("login", response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        AuthInterface auth = new AuthDummy();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(auth.login(username, password)) {
            session.setAttribute("username", username);
            session.setAttribute("isLoggedIn", true);
            response.sendRedirect("/WebAppServlet");
        } else {
            response.sendError(403, "Invalid username & password");
        }
    }

}