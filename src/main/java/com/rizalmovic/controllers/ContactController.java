package com.rizalmovic.controllers;

import com.rizalmovic.models.Contact;
import com.rizalmovic.repositories.ContactRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/contact")
public class ContactController extends Controller {

    private static ContactRepository repository = new ContactRepository();

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

            List<Contact> contacts = repository.all();
            this.data.put("contacts", contacts);

            render("contacts/index", response);
        }
    }

    protected void doEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");

        Contact contact = repository.find( Integer.valueOf(id));
        this.data.put("contact", contact);

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

                if(repository.save(contact) instanceof Contact) {
                    response.sendRedirect("/contact?error=\"Error on saving data\"");
                } else {
                    response.sendRedirect("/contact?success=\"Data saved.\"");
                }

            } catch (IOException e) {
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
            Contact contact = repository.find(Integer.valueOf(id));

            // Assign parameters to contact object
            contact.setName(name);
            contact.setEmail(email);
            contact.setPhone(phone);
            contact.setMobile(mobile);

            if(repository.save(contact) instanceof Contact) {
                response.sendRedirect("/contact?error=\"Error on saving data\"");
            } else {
                response.sendRedirect("/contact?success=\"Data saved.\"");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");

        try {
            if(repository.delete(Integer.valueOf(id))) {
                response.sendRedirect("/contact?error=\"Error on deleting data\"");
            } else {
                response.sendRedirect("/contact?success=\"Data deleted.\"");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}