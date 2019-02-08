package com.rizalmovic.controllers;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.rizalmovic.libraries.TemplateEngine;

@WebServlet("/")
public class MainController extends Controller {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TemplateEngine.getInstance(getServletContext());
        String view = TemplateEngine.render("index");
        render(view, response);
    }

}
