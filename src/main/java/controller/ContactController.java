package controller;

import model.Contact;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ContactServiceImpl;

import java.util.List;

/**
 * Created by Imant on 27.02.17.
 */

@RestController
public class ContactController {

    private final static Logger logger = Logger.getLogger(ContactController.class);

    private ContactServiceImpl contactService = new ContactServiceImpl();

    @RequestMapping(value = "/contacts", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Contact>> getAllContacts() {
        List<Contact> contacts = contactService.getAllContacts();
        if (contacts.isEmpty()) {
            logger.info("ContactController.getAllContacts(), HttpStatus.NO_CONTENT");
            return new ResponseEntity<List<Contact>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Contact>>(contacts, HttpStatus.OK);
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
        Contact addedContact = contactService.addContact(contact);
        if (addedContact != null) {
            return new ResponseEntity<Contact>(addedContact, HttpStatus.OK);
        } else logger.info("ContactController.addContact(), HttpStatus.NO_CONTENT");
        return new ResponseEntity<Contact>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/contacts/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<Contact> updateContact(@RequestBody Contact contact, @PathVariable("id") int id) {
        Contact updatedContact = contactService.updateContact(contact);
        if (updatedContact.getId() == contact.getId()) {
            return new ResponseEntity<Contact>(updatedContact, HttpStatus.OK);
        } else logger.info("ContactController.updateContact(), HttpStatus.BAD_REQUEST");
        return new ResponseEntity<Contact>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/contacts/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteContactById(@PathVariable("id") int id) {
        if (contactService.deleteContactById(id)) {
            return new ResponseEntity(HttpStatus.OK);
        } else logger.info("ContactController.deleteContactById(), HttpStatus.BAD_REQUEST");
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
