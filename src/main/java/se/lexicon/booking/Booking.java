package se.lexicon.booking;
import se.lexicon.classroom.Classroom;
import se.lexicon.customer.Customer;
import se.lexicon.customer.CustomerTypes;
import se.lexicon.storage.BookingDAO;
import se.lexicon.storage.StorageDAOImpl;


import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Booking {
    private int id;
    //private int booked_by;
    //private int booked_classroom;
    private Classroom room = null;
    private Customer customer = null;
    private Date start_date;
    private String end_date="";
    public Booking()
    {
        // Do nothing
    }
    public Booking(int id, Customer customer, Classroom room, Date start_date)
    {
            this.setId(id);
            this.setCustomer(customer);
            this.setRoom(room);
            this.setStart_date(start_date);
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
            // TODO: List all bookings - stopped working implementing classroom
            //booking.getBookings();
        } catch (Exception e)
        {
            IO.println("Err:" +  e.getMessage());
        }


    }
    public Booking parse(ResultSet rs) {
        try {
            Classroom classr = new Classroom();
            classr.setName(rs.getString("room_name"));
            classr.setCapacity(rs.getInt("room_capacity"));
            classr.setEquipment(rs.getString("room_equipment"));
            classr.setRoom_id(rs.getInt("room_id"));

            Customer customer = new Customer();
            // Next we have to parse customer data.
            customer.setCustomer_name(rs.getString("customer_name"));
            customer.setEmail(rs.getString("customer_email"));
            customer.setType(CustomerTypes.valueOf(rs.getString("customer_type")));
            this.setId(rs.getInt("booking_id"));
            this.setStart_date(rs.getDate("booking_start"));
            this.setRoom(classr);
            this.setCustomer(customer);
        } catch (SQLException e) {
            throw new RuntimeException("Error parsing booking: " + e.getMessage());

        }


        return this;
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

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }
}
