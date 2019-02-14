package com.rizalmovic.libraries.Auth;


public class AuthDummy implements AuthInterface {

    private String username;
    private String password;

    public AuthDummy() {
        this.username = "mail@example.net";
        this.password = "password";
    }

    @Override
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    @Override
    public void logout() {

    }

    @Override
    public boolean resetPassword(String username) {
        return false;
    }

}