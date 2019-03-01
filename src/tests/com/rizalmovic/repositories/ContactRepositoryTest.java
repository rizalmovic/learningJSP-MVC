package com.rizalmovic.repositories;

import com.rizalmovic.models.Contact;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Contact repository test")
class ContactRepositoryTest {

    private static final ContactRepository repository = new ContactRepository();

    @Test
    public void shouldAddContact() {
        Contact contact = new Contact();
        contact.setName("Habli Muhammad Rizal");
        contact.setEmail("rizalmovic2@gmail.com");
        contact.setMobile("081310860320");
        assertNotNull(repository.save(contact));
    }

    @Test
    public void shouldNotAddContact() {
        Contact contact = repository.all().get(0);

        Contact willBeAdded = new Contact();
        willBeAdded.setName("Tester");
        willBeAdded.setEmail(contact.getEmail());

        assertFalse(repository.save(willBeAdded) instanceof Contact);
    }

    @Test
    public void shouldGetContactList() {
        List<Contact> contacts = repository.all();
        assertTrue(contacts.size() > 0);
    }

    @Test
    public void shouldDeleteContact() {
        Contact contact = new Contact();
        contact.setName("will be deleted.");
        contact.setEmail("delete.me@example.net");
        repository.save(contact);
        System.out.println(contact);
        assertTrue(contact instanceof Contact);
        assertTrue(repository.delete(contact.getId()));
    }
}