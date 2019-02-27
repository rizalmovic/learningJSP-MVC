package com.rizalmovic.controllers;

import com.rizalmovic.models.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterController extends Controller {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = new User();

            user.setName(request.getParameter("name"));
            user.setEmail(request.getParameter("email"));
            user.setPassword(request.getParameter("password"));

            if(user.validate() && user.save()) {
                response.sendRedirect("/login");
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
