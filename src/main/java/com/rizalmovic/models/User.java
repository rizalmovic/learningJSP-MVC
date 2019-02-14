package com.rizalmovic.models;

import org.mindrot.jbcrypt.BCrypt;

public class User {

    private int id;
    private String name;
    private String email;
    private String password;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * @param password password to be verified
     * @return boolean
     */
    public boolean verifyPassword(String password) {
        return BCrypt.checkpw(password, this.password);
    }

}