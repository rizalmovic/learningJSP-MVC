package com.rizalmovic.repositories;

import com.rizalmovic.models.User;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UserRepository extends Repository implements RepositoryInterface<User> {

    @Override
    public User find(int id) {
        return manager.find(User.class, id);
    }

    public User findByEmail(String email) {
        try  {
            return manager.createQuery("SELECT u FROM User u WHERE u.email = :email ", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<User> all() {
        List<User> users = new ArrayList<User>();
        try {
            return manager.createQuery("SELECT u FROM User u", User.class).getResultList();
        } catch (Exception e) {
            // Do log or something
            return users;
        }
    }

    @Override
    public User save(User user) {
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();
            if(user.getId() == 0) {
                manager.persist(user);
            } else {
                manager.merge(user);
            }
            transaction.commit();
        } catch (Exception e) {
            if(transaction.isActive())
                transaction.rollback();
            return null;
        }

        return user;
    }

    @Override
    public boolean delete(int id) {

        EntityTransaction transaction = manager.getTransaction();

        try {
            User user = this.find(id);
            transaction.begin();
            manager.remove(user);
            transaction.commit();
        } catch (Exception e) {
            if(transaction.isActive())
                transaction.rollback();

            return false;
        }

        return true;
    }
}