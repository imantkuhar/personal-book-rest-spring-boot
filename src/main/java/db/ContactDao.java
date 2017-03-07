package db;

import model.Contact;
import utils.ConnectionService;
import utils.PropertiesHolder;
import utils.converter.ResultSetConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by Imant on 14.11.16.
 */

public class ContactDao {

    public static final String CREATE_TABLE_CONTACT_PREPARED_STATEMENT = "CREATE TABLE IF NOT EXISTS CONTACT (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " name VARCHAR(30), phoneNumber VARCHAR(10), address VARCHAR(15), groups VARCHAR(15), date VARCHAR (25))";

    public static final String ADD_CONTACT_PREPARED_STATEMENT = "INSERT INTO CONTACT (name, phoneNumber, address, groups, date)" +
            " VALUES (?, ?, ?, ?, ?)";

    public static final String GET_CONTACT_ID_PREPARED_STATEMENT = "SELECT id, date FROM CONTACT ORDER BY id DESC LIMIT 1";

    public static final String DELETE_CONTACT_PREPARED_STATEMENT = "DELETE FROM CONTACT WHERE id = ?";

    public static final String EDIT_CONTACT_PREPARED_STATEMENT = "UPDATE CONTACT SET name = ?, phoneNumber = ?, address = ?, " +
            "groups = ? WHERE id = ?";

    public static final String GET_ALL_CONTACTS_PREPARED_STATEMENT = "SELECT * FROM CONTACT";

    public ContactDao() {
        createContactTable();
    }


    public boolean createContactTable() {
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TABLE_CONTACT_PREPARED_STATEMENT)) {
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Contact addContact(Contact contact) {
        String name = contact.getName();
        String phoneNumber = contact.getPhoneNumber();
        String address = contact.getAddress();
        String group = contact.getGroup();

        String DATE_TIME_FORMAT = PropertiesHolder.getProperty("DATE_TIME_FORMAT");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        LocalDateTime dateTime = LocalDateTime.now();
        String formattedDateTime = dateTime.format(formatter);

        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement1 = connection.prepareStatement(ADD_CONTACT_PREPARED_STATEMENT);
             PreparedStatement preparedStatement2 = connection.prepareStatement(GET_CONTACT_ID_PREPARED_STATEMENT)) {

            preparedStatement1.setString(1, name);
            preparedStatement1.setString(2, phoneNumber);
            preparedStatement1.setString(3, address);
            preparedStatement1.setString(4, group);
            preparedStatement1.setString(5, formattedDateTime);
            preparedStatement1.execute();

            ResultSet resultSet = preparedStatement2.executeQuery();
            String contactId = resultSet.getString("id");
            String contactDate = resultSet.getString("date");
            contact.setId(Integer.parseInt(contactId));
            contact.setDate(contactDate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contact;
    }

    public boolean deleteContact(int id) {
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CONTACT_PREPARED_STATEMENT)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Contact updateContact(Contact contact) {
        int id = contact.getId();
        String name = contact.getName();
        String phoneNumber = contact.getPhoneNumber();
        String address = contact.getAddress();
        String group = contact.getGroup();
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(EDIT_CONTACT_PREPARED_STATEMENT)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, phoneNumber);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, group);
            preparedStatement.setInt(5, id);
            if (preparedStatement.execute()) {
                return contact;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Contact();
    }

    public List<Contact> getAllContacts() {
        List<Contact> contactList = null;
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_CONTACTS_PREPARED_STATEMENT)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            contactList = ResultSetConverter.convertResultSetToContactList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactList;
    }
}
