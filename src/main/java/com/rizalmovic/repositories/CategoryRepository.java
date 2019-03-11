package com.rizalmovic.repositories;

import com.rizalmovic.models.Category;

import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository extends Repository implements RepositoryInterface<Category> {
    @Override
    public Category find(int id) {
        try {
            return manager.find(Category.class, id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Category> all() {
        List<Category> categories = new ArrayList<Category>();
        try {
            return manager.createQuery("SELECT c from Category c").getResultList();
        } catch (Exception e) {
            return categories;
        }
    }

    @Override
    public Category save(Category category) {
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();
            if(category.getId() == 0) {
                manager.persist(category);
            } else {
                manager.merge(category);
            }
            transaction.commit();
        } catch (Exception e) {
            if(transaction.isActive())
                transaction.rollback();
            return null;
        }

        return category;
    }

    @Override
    public boolean delete(int id) {
        EntityTransaction transaction = manager.getTransaction();

        try {
            Category category = this.find(id);
            transaction.begin();
            manager.remove(category);
            transaction.commit();
        } catch (Exception e) {
            if(transaction.isActive())
                transaction.rollback();

            return false;
        }

        return true;
    }
}
