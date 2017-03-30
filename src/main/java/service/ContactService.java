package service;

import model.Contact;

import java.util.List;

/**
 * Represents methods which you can implements if
 * you want to work with contacts
 */
public interface ContactService {

    /**
     * Saves a contact
     *
     * @param contact contact you want to save
     * @return added contact
     */
    Contact addContact(Contact contact);

    /**
     * Deletes a contact
     *
     * @param id id of contact you want to delete
     * @return true if contact is deleted and false if not
     */
    boolean deleteContactById(int id);

    /**
     * Updates a contact
     *
     * @param contact contact you want to update
     * @return updated contact
     */
    Contact updateContact(Contact contact);

    /**
     * Returns all contacts
     *
     * @return list of contacts
     */
    List<Contact> getAllContacts();
}
