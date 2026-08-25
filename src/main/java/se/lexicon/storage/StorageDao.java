package se.lexicon.storage;
import se.lexicon.booking.Booking;
import se.lexicon.customer.Customer;
import se.lexicon.storage.exceptions.ConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface StorageDao {

    Connection conn = null;

    default public void save(Booking booking)
    {
        throw new IllegalArgumentException("Cannot be used in interface");
    }
    default public void save(Customer customer)
    {
        throw new IllegalArgumentException("Cannot be used in interface");
    }
     private Connection connect() throws ConnectionException
    {
        try (Connection conn = DriverManager.getConnection(DbConfig.URL, DbConfig.user,  DbConfig.password)){
            return conn;
        } catch (SQLException e) {
            throw new ConnectionException("ERR: Something seems wrong with the connection.");
        }


    }

}
