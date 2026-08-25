package se.lexicon.booking;
import se.lexicon.classroom.Classroom;
import se.lexicon.customer.Customer;
import se.lexicon.storage.BookingDAO;
import se.lexicon.storage.StorageDAOImpl;


import java.sql.SQLException;
import java.util.Date;

public class Booking {
    private int id;
    //private int booked_by;
    //private int booked_classroom;
    private Classroom room = null;
    private Customer customer = null;
    public Booking()
    {

    }
    public Booking(int id, Customer customer, Classroom room, Date from, Date to) {
        setId(id);
        setCustomer(customer);
        setRoom(room);

    }

    public void listAllBookings() {
        BookingDAO booking = new BookingDAO();
        try
        {
            booking.listBookings();
        } catch (SQLException e)
        {
            IO.println("Err:" +  e.getMessage());
        }


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Classroom getRoom() {
        return room;
    }

    public void setRoom(Classroom room) {
        this.room = room;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
