package com.rizalmovic.libraries.DB;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Query<T> implements QueryInterface {

    private DB db;
    private T dataObject;
    private String selection;
    private String query;

    public Query() throws IOException, SQLException {
        this.db = new DB();
    }

    public void setQuery(String query) {
        this.query = query;
    }
    public void appendQuery(String query) { this.query += " " + query; }
    public void prependQuery(String query) { this.query = query + " " + this.query; }
    public String getQuery() { return this.query; }
    public String getSelection() { return this.selection; }
    public DB getDB() { return this.db; }

    public void select(ArrayList<String> columns) {
        this.selection = "SELECT " + columns.stream().reduce("", String::concat) + " FROM " + this.getTable() + " ";
    }

    public T mapping(ResultSet result) throws IllegalAccessException, InstantiationException {
        T obj = (T) getClass().newInstance();
        return obj;
    }

     public String toQuery(String type) {
        return "";
     }

    public List<T> findAll() {
        List<T> datas = new ArrayList<T>();
        this.select(new ArrayList<String>(Arrays.asList("*")));
        ResultSet result = null;
        try {
            result = this.db.executeQuery(this.selection);
            while (result.next()) {
                datas.add(this.mapping(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return datas;
    }

    public T findById(String id) {
        this.select(new ArrayList<String>(Arrays.asList("*")));
        this.query = "WHERE id = " + id + " LIMIT 1";
        ResultSet result = null;
        try {
            result = this.db.executeQuery(this.selection + " " + this.query);
            while (result.next()) {
                this.dataObject = this.mapping(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return this.dataObject;
    }

    public boolean save() {
        String query = this.toQuery("INSERT");
        try {
            return this.db.executeUpdate(query) > 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update() {
        String query = this.toQuery("UPDATE");
        try {
            return this.db.executeUpdate(query) > 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete() {
        String query = this.toQuery("DELETE");
        try {
            return this.db.executeUpdate(query) > 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
