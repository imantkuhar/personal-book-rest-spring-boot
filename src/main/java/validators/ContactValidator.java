package validators;

import model.Contact;

/**
 * Represents method with which you can validate contacts
 */
public class ContactValidator {

    /**
     * Validates the all text field. They cannot be empty
     *
     * @param contact contact you want to check all text field
     * @return true if all text field is filled and false if not
     */
    public boolean checkAllTextField(Contact contact) {
        return !(contact.getName().isEmpty()
                || contact.getPhoneNumber().isEmpty()
                || contact.getAddress().isEmpty()
                || contact.getGroup().isEmpty());
    }
}
