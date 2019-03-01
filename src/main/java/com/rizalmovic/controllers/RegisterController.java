package com.rizalmovic.controllers;

import com.rizalmovic.models.User;
import com.rizalmovic.repositories.UserRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterController extends Controller {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            UserRepository repo = new UserRepository();
            User user = new User();

            user.setName(request.getParameter("name"));
            user.setEmail(request.getParameter("email"));
            user.setPassword(request.getParameter("password"));

            if(repo.save(user) instanceof User) {
                response.sendRedirect("/login");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
