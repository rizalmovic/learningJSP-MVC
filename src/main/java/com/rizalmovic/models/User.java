package com.rizalmovic.models;

import com.rizalmovic.libraries.DB.Query;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

public class User extends Query<User> {

    protected String table = "users";

    public User() throws IOException, SQLException {
        super();
    }

    private int id;
    private String name;
    private String email;
    private String password;
    private Timestamp created_at;
    private Timestamp updated_at;

    @Override
    public User mapping(ResultSet result) throws IllegalAccessException, InstantiationException {
        User obj = getClass().newInstance();

        try {
            obj.setId(result.getInt("id"));
            obj.setName(result.getString("name"));
            obj.setEmail(result.getString("email"));
            obj.setPassword(result.getString("password"), false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return obj;
    }

    @Override
    public String toQuery(String type) {
        Date date = new Date(System.currentTimeMillis());
        Timestamp timestamp = new Timestamp(date.getTime());

        switch (type) {
            case "INSERT":
                this.setQuery("INSERT INTO users");
                this.appendQuery("(name, email, password, created_at)");
                this.appendQuery("VALUES ('" + this.name + "', '" + this.email + "', '" + this.password + "', '" + timestamp + "')");
                break;
            case "UPDATE":
                this.setQuery("UPDATE users");
                this.appendQuery("SET name='" + this.name + "', email='" + this.email + "', password='" + this.password + "', updated_at='" + timestamp + "'");
                this.appendQuery("WHERE id = " + this.id);
                break;
            case "DELETE":
            default:
                this.setQuery("DELETE from " + this.table);
                this.appendQuery("WHERE id = " + this.id);
                break;
        }

        return this.getQuery();
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
     * @param password plain password
     * @param hash set to `true` to hash the password
     */
    public void setPassword(String password, boolean hash) {
        if(hash == false) {
            this.password = password;
        } else {
            this.setPassword(password);
        }
    }

    public void setCreatedAt(String created_at) {
        this.created_at = (created_at != null) ? Timestamp.valueOf(created_at) : null;
    }

    public Timestamp getCreatedAt() {
        return this.created_at;
    }

    public void setUpdateAt(String updated_at) {
        this.updated_at = (updated_at != null) ? Timestamp.valueOf(updated_at) : null;
    }

    public Timestamp getUpdateAt() {
        return this.updated_at;
    }

    /**
     * @param password password to be verified
     * @return boolean
     */
    public static boolean verifyPassword(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }

    /**
     * getTable get table
     * @return the table name
     */
    public String getTable() { return this.table; }

    /**
     * findByEmail
     * @param email
     * @return User
     * @throws SQLException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public User findByEmail(String email) throws SQLException, InstantiationException, IllegalAccessException, IOException {
        this.select(new ArrayList<String>(Arrays.asList("*")));
        this.setQuery("WHERE email = '" + email + "' LIMIT 1");
        ResultSet result = this.getDB().executeQuery(this.getSelection() + " " + this.getQuery());

        while (result.next()) {
            return this.mapping(result);
        }

        return new User();
    }

    /**t
     * Validate user
     * @return boolean
     */
    @Override
    public boolean validate() {
        try {
            User user = this.findByEmail(this.email);

            if(user.email == this.email) {
                return false;
            }

            if(this.name == null || this.email == null || this.password == null) {
                return false;
            }

        } catch (SQLException | InstantiationException | IllegalAccessException | IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}