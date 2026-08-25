package se.lexicon.storage;
import se.lexicon.booking.Booking;
import se.lexicon.customer.Customer;
import java.sql.Connection;


public interface StorageDao {

    Connection conn = null;
    default public void update(Booking booking)
    {
        throw new IllegalArgumentException("Cannot be used from interface");
    }

    default public void save(Booking booking)
    {
        throw new IllegalArgumentException("Cannot be used in interface");
    }
    default public void save(Customer customer)
    {
        throw new IllegalArgumentException("Cannot be used in interface");
    }

}
