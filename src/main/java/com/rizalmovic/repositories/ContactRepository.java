package com.rizalmovic.repositories;

import com.rizalmovic.models.Contact;

import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

public class ContactRepository extends Repository implements RepositoryInterface<Contact> {

    @Override
    public Contact find(int id) {
        try {
            return manager.find(Contact.class, id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Contact> all() {
        List<Contact> contacts = new ArrayList<Contact>();
        try {
            return manager.createQuery("SELECT c from Contact c").getResultList();
        } catch (Exception e) {
            return contacts;
        }
    }

    @Override
    public Contact save(Contact contact) {
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();
            if(contact.getId() == 0) {
                manager.persist(contact);
            } else {
                manager.merge(contact);
            }
            transaction.commit();
        } catch (Exception e) {
            if(transaction.isActive())
                transaction.rollback();
            return null;
        }

        return contact;
    }

    @Override
    public boolean delete(int id) {
        EntityTransaction transaction = manager.getTransaction();

        try {
            Contact contact = this.find(id);
            transaction.begin();
            manager.remove(contact);
            transaction.commit();
        } catch (Exception e) {
            if(transaction.isActive())
                transaction.rollback();

            return false;
        }

        return true;
    }
}
