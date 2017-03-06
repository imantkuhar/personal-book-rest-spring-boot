package controller;

import model.Contact;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ContactServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Imant on 27.02.17.
 */


@RestController
public class ContactController {

    private ContactServiceImpl contactService = new ContactServiceImpl();

    @RequestMapping(value = "/contacts", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Contact>> listAllUsers() {
        List<Contact> contacts = contactService.getAllContacts();
        if(contacts.isEmpty()){
            return new ResponseEntity<List<Contact>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Contact>>(contacts, HttpStatus.OK);
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity addContact(@RequestBody Contact contact) {
        if (contactService.addContact(contact)) {
            return new ResponseEntity(HttpStatus.OK);
        } else return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/contacts/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteContactById(@PathVariable("id") int id) {
        if (contactService.deleteContactById(id)) {
            return new ResponseEntity(HttpStatus.OK);
        } else return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/contacts/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<String> updateContact(@PathVariable("id") int id, @RequestBody Contact contact) {
        if (contactService.updateContact(contact)) {
            return new ResponseEntity<String>(HttpStatus.OK);
        } else return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/contacts/{name}", method = RequestMethod.GET, consumes = "application/json")
    public List<Contact> getAllContactsByString(@PathVariable("name") String  name) {
        List<Contact> contactList = contactService.getAllContacts();
        List<Contact> contactListByString = new ArrayList<>();

        for (Contact contact : contactList) {
            if (contact.toStringForSearch().contains(name))
                contactListByString.add(contact);
        }
        return contactListByString;
    }
}
