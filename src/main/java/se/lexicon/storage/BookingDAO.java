package se.lexicon.storage;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import se.lexicon.booking.Booking;
import se.lexicon.classroom.Classroom;
import se.lexicon.customer.Company;
import se.lexicon.customer.Customer;
import se.lexicon.customer.CustomerTypes;
import se.lexicon.customer.Individual;
import se.lexicon.presentation.Bookings;
import se.lexicon.storage.exceptions.BookingStorageException;
import se.lexicon.storage.exceptions.InvalidBookingException;

public class BookingDAO implements StorageDao {
    private StorageDAOImpl storage = null;

    public BookingDAO()  {
        // Connect
        this.storage = new StorageDAOImpl();
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
    static private Timestamp stringToTimestamp(String ts)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date parsedDate = dateFormat.parse(ts);
            java.sql.Timestamp timestamp = new Timestamp(parsedDate.getTime());
            return timestamp;
        } catch (ParseException e)
        {
            throw new IllegalArgumentException("Invalid date format");
        }

    }
    public void addBooking(int custId, int roomId,String start, String end)
    {

        String sql="INSERT INTO bookings (booked_by,booked_classroom,book_start,book_end) VALUES(?,?,?,?)";

        // First convert strings to timestamp
        Timestamp ts_start = stringToTimestamp(start);
        Timestamp ts_end = stringToTimestamp(end);
        try {
            PreparedStatement stmt = this.storage.conn.prepareStatement(sql);
            stmt.setInt(1, custId);
            stmt.setInt(2, roomId);
            stmt.setTimestamp(3, ts_start);
            stmt.setTimestamp(4, ts_end);
            stmt.executeUpdate();
        } catch ( SQLException e )
        {
            throw new BookingStorageException("Error saving booking to DB. " + e.getMessage());
        }
    }

    public List<Booking> getBookings(String availability, String name, int capacity, String equipment) {

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
r.accessibility as room_accessibility,
r.equipment as room_equipment
from bookings  b
join customers c on b.booked_by = c.id
join classroom r on b.booked_classroom  = r.id
where b.book_start  > ? AND r.name LIKE ? AND r.capacity > ? AND equipment LIKE ?  ;

""";



// Next comes a list of declarations for all tvariables.
// They are not really needed, but helps redability


int booking_id =0;
String customer_name ="";
String customer_email = "";
CustomerTypes customer_type;
int customer_id=0;
int room_id=0;
String room_name = "";
int room_capacicity=0;
boolean room_accessbility=false;
Date booking_start = null;
Date booking_end =
        null;
Customer cust=null;

List<Booking > booking = new ArrayList<>();
    try {

        PreparedStatement stmt = this.storage.conn.prepareStatement(sql);
        stmt.setTimestamp(1, stringToTimestamp(availability));
        stmt.setString(2,"%" + name + "%");
        stmt.setInt(3, capacity);
        stmt.setString(4,"%" + equipment + "%");
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){

            // Parse all classroom things and return a classroom Object. Add it to the list.
            Booking book = new Booking().parse(rs);


        }
    } catch ( SQLException e) {
        throw new RuntimeException("Error listing bookings" + e.getMessage());
    }
        return booking;
    }

    public List<Booking> getBookings(int customerId) {

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
r.accessibility as room_accessibility,
r.equipment as room_equipment
from bookings  b
join customers c on b.booked_by = c.id
join classroom r on b.booked_classroom  = r.id
WHERE c.id = ? ;

""";



// Next comes a list of declarations for all tvariables.
// They are not really needed, but helps redability




        List<Booking > booking = new ArrayList<>();
        try {
            PreparedStatement stmt = this.storage.conn.prepareStatement(sql);
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){

                // Parse all classroom things and return a classroom Object. Add it to the list.
                Booking book = new Booking().parse(rs);
                booking.add(book);


            }
        } catch ( SQLException e) {
            IO.println("Error: " + e.getMessage());
            throw new RuntimeException("Error listing bookings" + e.getMessage());
        }
        return booking;
    }
    public List<Booking> getBookings(String availability) {

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
r.accessibility as room_accessibility,
r.equipment as room_equipment
from bookings  b
join customers c on b.booked_by = c.id
join classroom r on b.booked_classroom  = r.id
where b.book_start  > ? ;

""";



// Next comes a list of declarations for all tvariables.
// They are not really needed, but helps redability


        int booking_id =0;
        String customer_name ="";
        String customer_email = "";
        CustomerTypes customer_type;
        int customer_id=0;
        int room_id=0;
        String room_name = "";
        int room_capacicity=0;
        boolean room_accessbility=false;
        Date booking_start = null;
        Date booking_end =
                null;
        Customer cust=null;

        List<Booking > booking = new ArrayList<>();
        try {

            PreparedStatement stmt = this.storage.conn.prepareStatement(sql);
            stmt.setTimestamp(1, stringToTimestamp(availability));
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){

                // Parse all classroom things and return a classroom Object. Add it to the list.
                Booking book = new Booking().parse(rs);
                booking.add(book);

            }
        } catch ( SQLException e) {
            throw new RuntimeException("Error listing bookings" + e.getMessage());
        }
        return booking;
    }
}