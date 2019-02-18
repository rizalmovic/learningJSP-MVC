package com.rizalmovic.libraries.DB;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Query<T> {

    private DB db;
    public String table;
    private T dataObject;
    private String selection;
    private String query;

    public Query() throws IOException, SQLException {
        this.db = new DB();
    }

    public void select(ArrayList<String> columns) {
        this.selection = "SELECT " + columns.stream().reduce("", String::concat) + " FROM " + this.table + " ";
    }

    public T mapping(ResultSet result) throws IllegalAccessException, InstantiationException {
        T obj = (T) getClass().newInstance();
        return obj;
    }

    public List<T> findAll() throws SQLException, InstantiationException, IllegalAccessException {
        List<T> datas = new ArrayList<T>();
        this.select(new ArrayList<String>(Arrays.asList("*")));
        ResultSet result = this.db.execute(this.selection);

        while (result.next()) {
            datas.add(this.mapping(result));
        }

        return datas;
    }


    public T findById(String id) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        this.query = "WHERE id = " + id;
        ResultSet result = this.db.execute(this.selection + " " + this.query);
        ResultSetMetaData meta = result.getMetaData();
        Field[] fields = this.dataObject.getClass().getDeclaredFields();

        while (result.next()) {
            for(int i = 0; i < meta.getColumnCount(); i++) {
                String columnName = meta.getColumnName(i + 1);
                Object columnValue = result.getObject(i + 1);

                for(Field field : fields) {
                    Method method = this.dataObject.getClass().getMethod("set" + columnName.substring(0,1).toUpperCase() + columnName.substring(1));
                    method.invoke(columnValue);
                }
            }
        }

        return this.dataObject;
    }

//    public T create(T data) {}
//    public T update(T data) {}
}
