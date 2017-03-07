package service;

import db.ContactDao;
import model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Imant on 14.11.16.
 */
public class ContactServiceImpl implements ContactService {


    private ContactDao contactDao = new ContactDao();

    public Contact addContact(Contact contact) {
        return contactDao.addContact(contact);
    }

    public boolean deleteContactById(int id) {
        return contactDao.deleteContact(id);
    }

    public Contact updateContact(Contact contact) {
        return contactDao.updateContact(contact);
    }

    public List<Contact> getAllContacts() {
        return contactDao.getAllContacts();
     }
}
