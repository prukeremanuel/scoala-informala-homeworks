package ro.sci.h11;

import ro.sci.h11.db.BookingsDb;
import ro.sci.h11.db.BookingsDbException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class represents the test data base.
 */
public class TestBookingsDb extends BookingsDb {

    /**
     * This method creates the test data base and creates tables.
     *
     * @throws BookingsDbException
     * @throws SQLException
     */
    public static void setUpTestDB() throws BookingsDbException, SQLException {
        TestBookingsDb tdb = new TestBookingsDb();
        try (Connection connection = tdb.connectToPostgreSQL()) {
            Statement statement = connection.createStatement();
            statement.execute("CREATE DATABASE bookings_test;");
        }

        try (Connection connection = tdb.connect()) {
            StringBuilder builder = new StringBuilder();
            builder.append("CREATE SEQUENCE accomodation_ids;");
            builder.append("CREATE TABLE accomodation(id int PRIMARY KEY DEFAULT NEXTVAL('accomodation_ids'), type VARCHAR(32), bed_type VARCHAR(32), max_guests INT, description VARCHAR(512));");
            builder.append("CREATE SEQUENCE room_fair_ids;");
            builder.append("CREATE TABLE room_fair(id int PRIMARY KEY DEFAULT NEXTVAL('room_fair_ids'), value NUMERIC, season VARCHAR(32));");
            builder.append("CREATE SEQUENCE accomodation_fair_relation_ids;");
            builder.append("CREATE TABLE accomodation_fair_relation(id INT PRIMARY KEY DEFAULT NEXTVAL('accomodation_fair_relation_ids'), id_accomodation INT REFERENCES accomodation(id), id_room_fair INT REFERENCES room_fair(id));");

            Statement statement = connection.createStatement();
            statement = connection.createStatement();
            statement.execute(builder.toString());
        }
    }

    /**
     * This method deletes the test data base.
     *
     * @throws BookingsDbException
     * @throws SQLException
     */
    public static void dropTestDB() throws BookingsDbException, SQLException {
        TestBookingsDb tdb = new TestBookingsDb();
        try (Connection connection = tdb.connectToPostgreSQL()) {
            Statement statement = connection.createStatement();
            statement.execute("DROP DATABASE bookings_test;");
        }
    }

    // Creates a connection to the PostgreSQL without selecting a database
    private Connection connectToPostgreSQL() throws SQLException, BookingsDbException {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            Connection connection = null;
            DriverManager.setLoginTimeout(60);
            String url = new StringBuilder()
                    .append("jdbc:")
                    .append("postgresql")       // “mysql” / “db2” / “mssql” / “oracle” / ...
                    .append("://")
                    .append("localhost")
                    .append(":")
                    .append(5432)
                    .append("/")
                    .append("?user=")
                    .append("postgres")
                    .append("&password=")
                    .append("1").toString();
            return DriverManager.getConnection(url);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new BookingsDbException("Could not load DB driver.", e);
        }
    }

    @Override
    public Connection connect() throws BookingsDbException, SQLException {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            Connection connection = null;
            DriverManager.setLoginTimeout(60);
            String url = new StringBuilder()
                    .append("jdbc:")
                    .append("postgresql")       // “mysql” / “db2” / “mssql” / “oracle” / ...
                    .append("://")
                    .append("localhost")
                    .append(":")
                    .append(5432)
                    .append("/")
                    .append("bookings_test")
                    .append("?user=")
                    .append("postgres")
                    .append("&password=")
                    .append("1").toString();
            return DriverManager.getConnection(url);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new BookingsDbException("Could not load DB driver.", e);
        }
    }

    /**
     * This method deletes the data from the test data base tables.
     *
     * @throws BookingsDbException
     * @throws SQLException
     */
    public void dropDataFromTables() throws BookingsDbException, SQLException {
        try (Connection connection = connect()) {
            StringBuilder builder = new StringBuilder();
            builder.append("DELETE FROM accomodation_fair_relation;");
            builder.append("DELETE FROM accomodation;");
            builder.append("DELETE FROM room_fair;");


            Statement statement = connection.createStatement();
            statement = connection.createStatement();
            statement.execute(builder.toString());
        }
    }
}
