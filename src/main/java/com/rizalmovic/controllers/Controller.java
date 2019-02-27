package com.rizalmovic.controllers;

import com.rizalmovic.libraries.TemplateEngine;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet
public class Controller extends HttpServlet {

    Map<String, Object> data;
    HttpSession session;

    public Controller() {
        this.data = new HashMap<String, Object>();
    }

    protected void isAuthenticated(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("isLoggedIn") == null) {
            response.sendRedirect("/login");
        }
    }

    protected void render(String view, HttpServletResponse response) throws IOException {
        TemplateEngine.getInstance(getServletContext());

        this.data.put("session", this.session); // Set Session
        this.data.put("path", getServletContext().getContextPath());
        String renderedView = TemplateEngine.render(view, this.data); // Render View

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(renderedView);
    }
}
