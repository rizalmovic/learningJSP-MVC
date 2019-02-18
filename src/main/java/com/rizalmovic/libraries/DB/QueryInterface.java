package com.rizalmovic.libraries.DB;

import java.util.List;

public interface QueryInterface <T> {

    public List<T> findAll();
    public T findById(String id);
}
