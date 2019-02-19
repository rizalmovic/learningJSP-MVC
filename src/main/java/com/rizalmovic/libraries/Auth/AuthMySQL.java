package com.rizalmovic.libraries.Auth;

import com.rizalmovic.models.User;

import java.io.IOException;
import java.sql.SQLException;

public class AuthMySQL implements AuthInterface {

    User user;

    public AuthMySQL() throws IOException, SQLException {
        user = new User();
    }

    @Override
    public boolean login(String username, String password) {
        return false;
    }

    @Override
    public void logout() {

    }

    @Override
    public boolean resetPassword(String username) {
        return false;
    }
}
