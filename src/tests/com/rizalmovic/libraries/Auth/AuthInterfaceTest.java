package com.rizalmovic.libraries.Auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AuthInterfaceTest {
    private static AuthInterface auth = new AuthMySQL();

    @BeforeEach
    void setUp() {
    }

    @Test
    public void shouldSuccessfullyLoggedIn() {
        assertTrue(auth.login("rizalmovic@gmail.com", "password"));
    }
}