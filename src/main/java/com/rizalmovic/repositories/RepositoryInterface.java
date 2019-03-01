package com.rizalmovic.repositories;


import java.util.HashMap;
import java.util.List;

interface RepositoryInterface <T> {
    T find(int id);
    List<T> all();
    T save(T obj);
    boolean delete(int id);
}