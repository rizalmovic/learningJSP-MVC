package com.rizalmovic.libraries.DB;

import java.sql.ResultSet;
import java.sql.SQLException;

interface DBInterface {

    public void setupConnection() throws SQLException;
    public ResultSet executeQuery(String query) throws SQLException;
    public int executeUpdate(String query) throws SQLException;
}