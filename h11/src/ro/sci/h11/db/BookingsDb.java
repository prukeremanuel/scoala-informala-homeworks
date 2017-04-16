package ro.sci.h11.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Booking Data Base object.
 * Edit the user and password fields in order to connect properly.
 *
 * @author Emanuel Pruker
 */
public class BookingsDb {

    public Connection connect() throws BookingsDbException, SQLException {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            Connection connection = null;
            DriverManager.setLoginTimeout(60);
            String url = new StringBuilder()
                    .append("jdbc:")
                    .append("postgresql")
                    .append("://")
                    .append("localhost")
                    .append(":")
                    .append(5432)
                    .append("/")
                    .append("booking")
                    .append("?user=")
                    .append("postgres")      //postgres user
                    .append("&password=")
                    .append("1").toString();  //postgres password
            return DriverManager.getConnection(url);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new BookingsDbException("Could not load DB driver.", e);
        }
    }
}