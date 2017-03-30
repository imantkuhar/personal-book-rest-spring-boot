package utils.converter;

import model.Contact;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides method that allow you to convert resultSet to list
 */
public class ResultSetConverter {

    /**
     * Returns list of contacts
     *
     * @param resultSet resultSet you want to convert to list
     * @return list of contacts
     */
    public static List<Contact> convertResultSetToContactList(ResultSet resultSet) throws SQLException {
        List<Contact> contactList = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String phoneNumber = resultSet.getString("phoneNumber");
            String address = resultSet.getString("address");
            String group = resultSet.getString("groups");
            String date = resultSet.getString("date");

            Contact contact = new Contact();
            contact.setId(id);
            contact.setName(name);
            contact.setPhoneNumber(phoneNumber);
            contact.setAddress(address);
            contact.setGroup(group);
            contact.setDate(date);

            contactList.add(contact);
        }
        return contactList;
    }
}
