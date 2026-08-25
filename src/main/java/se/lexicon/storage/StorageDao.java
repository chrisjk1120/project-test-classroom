package se.lexicon.storage;
import se.lexicon.storage.exceptions.ConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface StorageDao {

    Connection conn = null;

     private Connection connect() throws ConnectionException
    {
        try (Connection conn = DriverManager.getConnection(URL, user, password)){
            return conn;
        } catch (SQLException e) {
            throw new ConnectionException("ERR: Something seems wrong with the connection.");
        }


    }

}
