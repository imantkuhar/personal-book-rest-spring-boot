package utils;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Imant on 28.11.16.
 */
public class ConnectionService {

    private final static Logger logger = Logger.getLogger(ConnectionService.class);

    public static Connection createConnection() {
        Connection connection = null;

        try {
            Class.forName("org.sqlite.JDBC");
            final String DB_URL = PropertiesHolder.getProperty("DB_URL");
            connection = DriverManager.getConnection(DB_URL);
            logger.info("Connection is created.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
