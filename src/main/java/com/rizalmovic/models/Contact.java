package com.rizalmovic.models;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.rizalmovic.libraries.DB.Query;

public class Contact extends Query<Contact> {

    protected String table = "contacts";
    
    public Contact() throws SQLException, IOException {
        super();
    }

    private int id;
    private String name;
    private String email;
    private String phone;
    private String mobile;
    private Timestamp created_at;
    private Timestamp updated_at;

    @Override
    public Contact mapping(ResultSet result) throws IllegalAccessException, InstantiationException {
        Contact obj = getClass().newInstance();

        try {
            obj.setId(result.getInt("id"));
            obj.setName(result.getString("name"));
            obj.setEmail(result.getString("email"));
            obj.setPhone(result.getString("phone"));
            obj.setMobile(result.getString("mobile"));
            obj.setCreatedAt(result.getString("created_at"));
            obj.setUpdateAt(result.getString("updated_at"));
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
                this.setQuery("INSERT INTO " + this.table);
                this.appendQuery("(name, email, phone, mobile, created_at)");
                this.appendQuery("VALUES ('" + this.name + "', '" + this.email + "', '" + this.phone + "', '" + this.mobile + "', '" + timestamp + "')");
                break;
            case "UPDATE":
                this.setQuery("UPDATE " + this.table);
                this.appendQuery("SET name='" + this.name + "', email='" + this.email + "', phone='" + this.phone + "', mobile='" + this.mobile + "', updated_at='" + timestamp + "'");
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

    @Override
    public boolean validate() {

        if(this.name == null || this.email == null) {
            return false;
        }

        return true;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return this.mobile;
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

    public String getTable() {
        return this.table;
    }
}