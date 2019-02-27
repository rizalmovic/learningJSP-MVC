package com.rizalmovic.libraries.DB;

import java.util.List;

public interface QueryInterface <T> {

    public String table = "";

    public List<T> findAll();
    public T findById(String id);
    public default boolean save() {  return false;  }
    public default boolean update() { return false; }
    public default boolean validate() { return false; }
    public default String getTable() { return this.table; }
}
