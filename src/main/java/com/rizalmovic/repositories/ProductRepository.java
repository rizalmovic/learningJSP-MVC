package com.rizalmovic.repositories;

import com.rizalmovic.models.Product;

import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository extends Repository implements RepositoryInterface<Product> {
    @Override
    public Product find(int id) {
        try {
            return manager.find(Product.class, id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Product> all() {
        List<Product> products = new ArrayList<Product>();
        try {
            return manager.createQuery("SELECT c from Product c").getResultList();
        } catch (Exception e) {
            return products;
        }
    }

    @Override
    public Product save(Product product) {
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();
            if(product.getId() == 0) {
                manager.persist(product);
            } else {
                manager.merge(product);
            }
            transaction.commit();
        } catch (Exception e) {
            if(transaction.isActive())
                transaction.rollback();
            return null;
        }

        return product;
    }

    @Override
    public boolean delete(int id) {
        EntityTransaction transaction = manager.getTransaction();

        try {
            Product product = this.find(id);
            transaction.begin();
            manager.remove(product);
            transaction.commit();
        } catch (Exception e) {
            if(transaction.isActive())
                transaction.rollback();

            return false;
        }

        return true;
    }
}
