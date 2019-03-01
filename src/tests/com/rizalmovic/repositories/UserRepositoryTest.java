package com.rizalmovic.repositories;

import com.rizalmovic.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("User repository test")
public class UserRepositoryTest {

    private UserRepository repo;

    @BeforeEach
    protected void setUp() {
        this.repo = new UserRepository();
    }

    @Test
    public void shouldCreateUser() {
        User user = new User();
        user.setName("Jane Doe");
        user.setEmail("jane.doe.2nd@example.net");
        user.setPassword("password");
        assertNotNull(this.repo.save(user));
    }

    @Test
    public void shouldDeleteUser() {
        assertTrue(this.repo.delete(2));
    }

    @Test
    public void shouldGetAUser() {
        User user = this.repo.find(1);
        assertTrue(user instanceof User);
    }

    @Test
    public void shouldGetUserByEmailAddress() {
        User user = this.repo.findByEmail("rizalmovic@gmail.com");
        assertTrue(user instanceof User);
    }

    @Test
    public void shouldGetUsers() {
        List<User> users = this.repo.all();
        assertTrue(users.size() > 0);
    }

    @Test
    public void shouldUpdatePassword() {
        User user = this.repo.find(1);
        user.setPassword("password", true);
        user = this.repo.save(user);
        assertTrue(user instanceof User);
    }

    @Test
    public void passwordShouldMatch() {
        User user = this.repo.find(1);
        assertTrue(User.verifyPassword(user.getPassword(), "password"));
    }

    @Test
    public void passwordShouldNotMatch() {
        User user = this.repo.find(1);
        assertFalse(User.verifyPassword(user.getPassword(), "notmatched"));
    }
}