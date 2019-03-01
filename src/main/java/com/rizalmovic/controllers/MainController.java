package com.rizalmovic.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.rizalmovic.models.User;

@WebServlet("")
public class MainController extends Controller {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        render("index", response);
    }

}
