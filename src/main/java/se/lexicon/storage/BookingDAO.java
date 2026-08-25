package se.lexicon.storage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import se.lexicon.booking.Booking;
import se.lexicon.classroom.Classroom;
import se.lexicon.customer.Company;
import se.lexicon.customer.Customer;
import se.lexicon.customer.CustomerTypes;
import se.lexicon.customer.Individual;
import se.lexicon.storage.exceptions.InvalidBookingException;

public class BookingDAO implements StorageDao {
    private StorageDAOImpl storage = null;

    public BookingDAO()  {
        // Connect
        this.storage = new StorageDAOImpl();
    }

    @Override
    public void save(Customer customer) {

    }

    @Override
    public void save(Booking booking)
    {
        //TODO: implement;
    }
    public void updateBooking(Booking booking)
    {
        String sql="UPDATE bookings SET booking_start=?,booking_end=?,booked_by=?,booked_classroom=? WHERE id=?";
        try {
            PreparedStatement stmt = storage.conn.prepareStatement(sql);
        } catch (SQLException e)
        {
            IO.println(e);
        }
    }
    public List<Booking> getBookings()  {

            String sql =
                    """
select b.id as booking_id,
b.book_start as booking_start,
b.book_end as booking_end,
c.name as customer_name,
c.email as customer_email,
c.type as customer_type,
c.id as customer_id,
r.id as room_id,
r.name as room_name,
r.capacity as room_capacity,
r.accessibility as room_accessibility
from bookings  b
join customers c on b.booked_by = c.id
join classroom r on b.booked_classroom  = r.id
where b.book_start  > now();

""";



// Next comes a list of declarations for all tvariables.
// They are not really needed, but helps redability


int booking_id=0;
String customer_name
                    ="";
String customer_email = "";
CustomerTypes customer_type;
int customer_id=0;
int room_id=0;
String room_name="";
int room_capacity=0;
boolean room_accessibility=false;
Date booking_start = null;
Date booking_end = null;
Customer cust=null;
        List<Booking> bookings = new ArrayList<>();

        try (Statement stmt = storage.conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);) {
            while (rs.next()) {
                // As we try to query both customer and classroom within the same query
                //   we also have to retrieve data and put it into those objects.
                try {
                    booking_id = rs.getInt("booking_id");
                    customer_name = rs.getString("customer_name");
                    customer_email = rs.getString("customer_email");
                    //customer_type = ("customer_type");
                    customer_id = rs.getInt("customer_id");
                    room_id = rs.getInt("room_id");
                    room_name = rs.getString("room_name");
                    room_capacity = rs.getInt("room_capacity");
                    room_accessibility = rs.getBoolean("room_accessibility");
                    booking_start = rs.getDate("booking_start");
                    booking_end = rs.getDate("booking_end");
                } catch (SQLException e){
                throw new InvalidBookingException("Data for booking id:" + booking_id + " seems to be corrupt\n" + e.getMessage() );
            }
                if (rs.getString("customer_type").equals("INDIVIDUAL")) {
                    cust = new Individual(customer_id,customer_name,customer_email);
                } else {
                    cust = new Company(customer_id,customer_name,customer_email);
                }
                Classroom room = new Classroom(room_id,room_name,room_capacity,room_accessibility);
                // Create a new booking

                Booking booking = new Booking(booking_id,cust,room,booking_start,booking_end);
                bookings.add(booking);

            }
            return bookings;
        } catch (SQLException e) {
            IO.println("Error with SQL:" + sql + "\n" + e.getMessage());
        }
        return bookings;
    }
}


