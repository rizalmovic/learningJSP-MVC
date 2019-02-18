package com.rizalmovic.libraries.DB;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB implements DBInterface {

    private Connection connection;
    private String host;
    private String dbname;
    private String username;
    private String password;

    public DB() throws SQLException, IOException {
        String filename = "env.properties";
        Properties prop = new Properties();
        InputStream file = getClass().getClassLoader().getResourceAsStream(filename);

        if(file != null) {
            prop.load(file);

            this.host = prop.getProperty("dbhost");
            this.dbname = prop.getProperty("dbname");
            this.username = prop.getProperty("dbusername");
            this.password = prop.getProperty("dbpassword");

            this.setupConnection();
        }
    }

    public DB(String host, String dbname, String username, String password) throws SQLException {
        this.host = host;
        this.dbname = dbname;
        this.username = username;
        this.password = password;

        this.setupConnection();
    }

    @Override
    public void setupConnection() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:mysql://" + this.host + "/" + this.dbname, this.username, this.password);
    }

    public ResultSet execute(String query) throws SQLException {
        Statement statement = this.connection.createStatement();
        return statement.executeQuery(query);
    }

}