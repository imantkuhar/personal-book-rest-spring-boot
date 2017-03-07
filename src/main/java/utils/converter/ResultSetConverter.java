package utils.converter;

import model.Contact;
import utils.PropertiesHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Imant on 23.11.16.
 */
public class ResultSetConverter {

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
