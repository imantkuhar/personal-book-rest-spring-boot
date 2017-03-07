package service;

import model.Contact;

import java.util.List;

/**
 * Created by Imant on 16.11.16.
 */
public interface ContactService {

    Contact addContact(Contact contact);

    boolean deleteContactById(int id);

    Contact updateContact(Contact contact);

    List<Contact> getAllContacts();
}
