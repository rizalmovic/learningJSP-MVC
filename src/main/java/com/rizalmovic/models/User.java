package com.rizalmovic.models;

import com.rizalmovic.libraries.DB.Query;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User extends Query<User> {

    public User() throws IOException, SQLException {
        super();

        this.table = "users";
    }

    private int id;
    private String name;
    private String email;
    private String password;

    @Override
    public User mapping(ResultSet result) throws IllegalAccessException, InstantiationException {
        User obj = getClass().newInstance();

        try {
            obj.setId(result.getInt("id"));
            obj.setName(result.getString("name"));
            obj.setEmail(result.getString("email"));
            obj.setPassword(result.getString("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return obj;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the name to set
     */
    public void setId(int id) {
        this.id = id;
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

    /**
     * @param name name of the table
     */
    public void setTable(String name) { this.table = name; }

    /**
     * @return the table name
     */
    public String getTable() { return this.table; }
}