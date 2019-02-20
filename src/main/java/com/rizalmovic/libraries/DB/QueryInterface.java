package com.rizalmovic.libraries.DB;

import java.util.List;

public interface QueryInterface <T> {

    public List<T> findAll();
    public T findById(String id);
    public default boolean save(T obj) {  return false;  }
    public default boolean update(T obj) { return false; }
}
