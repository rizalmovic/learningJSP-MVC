package com.rizalmovic.libraries.Auth;

import com.rizalmovic.models.User;
import com.rizalmovic.repositories.UserRepository;

import java.io.IOException;
import java.sql.SQLException;

public class AuthMySQL implements AuthInterface {

    private final UserRepository repo = new UserRepository();

    @Override
    public boolean login(String username, String password) {
        try {
            User user = repo.findByEmail(username.trim());
            return User.verifyPassword(user.getPassword(), password.trim());
        } catch (Exception e) {
            // @todo Log failed authentication attempt
            return false;
        }
    }

    @Override
    public void logout() {}

    @Override
    public boolean resetPassword(String username) { return false; }
}
