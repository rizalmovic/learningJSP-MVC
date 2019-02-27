package com.rizalmovic.controllers;

import com.rizalmovic.models.Contact;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/contact")
public class ContactController extends Controller {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.isAuthenticated(request, response);
        this.session = request.getSession();

        String err = request.getParameter("error");
        String succ = request.getParameter("success");
        String mode = request.getParameter("mode");

        if(mode != null) {
            this.doEdit(request, response);
        } else {

            this.data.put("error", err);
            this.data.put("success", succ);

            try {
                Contact contact = new Contact();
                List<Contact> contacts = contact.findAll();
                this.data.put("contacts", contacts);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            render("contacts/index", response);
        }
    }

    protected void doEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");

        try {
            Contact contact = new Contact();
            contact = contact.findById(id);

            this.data.put("contact", contact);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        render("contacts/edit", response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String method = request.getParameter("_method");

        if(id != null && method != null && method.equals("delete")) {
            this.doDelete(request, response);
        } else if(id != null) {
            this.doPut(request, response);
        } else {

            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String mobile = request.getParameter("mobile");

            try {
                Contact contact = new Contact();

                // Assign parameters to contact object
                contact.setName(name);
                contact.setEmail(email);
                contact.setPhone(phone);
                contact.setMobile(mobile);

                if(!contact.validate() || !contact.save()) {
                    response.sendRedirect("/contact?error=\"Error on saving data\"");
                } else {
                    response.sendRedirect("/contact?success=\"Data saved.\"");
                }

            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String mobile = request.getParameter("mobile");

        try {
            Contact contact = new Contact();
            contact = contact.findById(id);

            // Assign parameters to contact object
            contact.setName(name);
            contact.setEmail(email);
            contact.setPhone(phone);
            contact.setMobile(mobile);

            if(!contact.validate() || !contact.update()) {
                response.sendRedirect("/contact?error=\"Error on saving data\"");
            } else {
                response.sendRedirect("/contact?success=\"Data saved.\"");
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");

        try {
            Contact contact = new Contact();
            contact = contact.findById(id);

            if(!contact.delete()) {
                response.sendRedirect("/contact?error=\"Error on deleting data\"");
            } else {
                response.sendRedirect("/contact?success=\"Data deleted.\"");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}