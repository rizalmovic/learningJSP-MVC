package com.rizalmovic.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.rizalmovic.models.User;

@WebServlet("/")
public class MainController extends Controller {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            User user = new User();
            List<User> users = user.findAll();
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("users", users);
            render("index", data, response);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


//        render("index", response);
    }

}
