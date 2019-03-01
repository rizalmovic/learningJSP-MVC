package com.rizalmovic.repositories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

abstract class Repository {

    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("db");;
    public static final EntityManager manager = factory.createEntityManager();
}
