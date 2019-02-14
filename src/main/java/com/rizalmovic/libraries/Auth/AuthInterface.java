package com.rizalmovic.libraries.Auth;

public interface AuthInterface {
    boolean login (String username, String password);
    void logout ();
    boolean resetPassword(String username);
}