package com.rizalmovic.controllers;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rizalmovic.libraries.TemplateEngine;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet
public class Controller extends HttpServlet {

    private HttpSession session;

    protected void render(String view, HttpServletResponse response) throws IOException {
        TemplateEngine.getInstance(getServletContext());
        Map<String, Object> data = new HashMap<String, Object>();

        data.put("session", this.session);
        String renderedView = TemplateEngine.render(view, data);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(renderedView);
    }

    protected void render(String view, Map<String, Object> data, HttpServletResponse response) throws IOException {
        TemplateEngine.getInstance(getServletContext());

        data.put("session", this.session);
        String renderedView = TemplateEngine.render(view, data);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(renderedView);
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }
}
