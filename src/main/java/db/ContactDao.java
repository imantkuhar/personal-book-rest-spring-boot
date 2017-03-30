package db;

import model.Contact;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
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
 * Provides methods that allow you to interact with SQLite DB
 */

public class ContactDao {

    private final static Logger logger = Logger.getLogger(ContactDao.class);

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

    /**
     * Create table for contacts
     *
     * @return true if table is created and false if not
     */
    public boolean createContactTable() {
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TABLE_CONTACT_PREPARED_STATEMENT)) {
            preparedStatement.execute();
            logger.info("Contact Table is created.");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.ERROR, "Exception", e);
            return false;
        }
    }


    /**
     * Returns added contact
     *
     * @param contact contact you want to save
     * @return instance of Contact
     */
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
            logger.info("Contact : [" + contact.toStringForLog() + "] is saved in DB.");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.ERROR, "Exception", e);
        }
        return contact;
    }

    /**
     * Returns a boolean value
     *
     * @param id id of contact you want to delete
     * @return true if contact is deleted and false if not
     */
    public boolean deleteContact(int id) {
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CONTACT_PREPARED_STATEMENT)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            logger.info("Contact is deleted from DB.");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.ERROR, "Exception", e);
            return false;
        }
    }

    /**
     * Returns updated contact
     *
     * @param contact contact you want to update
     * @return updated contact
     */
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
                logger.info("Contact : [" + contact.toStringForLog() + "] is updated in DB.");
                return contact;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.ERROR, "Exception", e);
        }
        return new Contact();
    }

    /**
     * Returns all contacts
     *
     * @return list of contacts
     */
    public List<Contact> getAllContacts() {
        List<Contact> contactList = null;
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_CONTACTS_PREPARED_STATEMENT)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            contactList = ResultSetConverter.convertResultSetToContactList(resultSet);
            logger.info("List of contacts has been gotten from DB.");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.ERROR, "Exception", e);
        }
        return contactList;
    }
}
