package com.rizalmovic.controllers;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rizalmovic.libraries.TemplateEngine;

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

    protected void render(String view, HttpServletResponse response) throws IOException {
        TemplateEngine.getInstance(getServletContext());

        this.data.put("session", this.session); // set session
        String renderedView = TemplateEngine.render(view, this.data); // render view

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(renderedView);
    }
}
