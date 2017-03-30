package service;

import db.ContactDao;
import model.Contact;

import java.util.List;


/**
 * Provides methods that allow you to interact with contacts
 */
public class ContactServiceImpl implements ContactService {

    private ContactDao contactDao = new ContactDao();

    /**
     * Returns added contact
     *
     * @param contact contact you want to save
     * @return instance of Contact
     */
    public Contact addContact(Contact contact) {
        return contactDao.addContact(contact);
    }

    /**
     * Returns a boolean value
     *
     * @param id id of contact you want to delete
     * @return true if contact is deleted and false if not
     */
    public boolean deleteContactById(int id) {
        return contactDao.deleteContact(id);
    }

    /**
     * Returns updated contact
     *
     * @param contact contact you want to update
     * @return updated contact
     */
    public Contact updateContact(Contact contact) {
        return contactDao.updateContact(contact);
    }

    /**
     * Returns all contacts
     *
     * @return list of contacts
     */
    public List<Contact> getAllContacts() {
        return contactDao.getAllContacts();
    }
}
