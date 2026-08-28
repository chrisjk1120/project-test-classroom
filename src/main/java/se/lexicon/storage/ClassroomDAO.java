package se.lexicon.storage;
import se.lexicon.classroom.Classroom;
import se.lexicon.storage.exceptions.ConnectionException;
import se.lexicon.storage.exceptions.StorageParserException;

import java.sql.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
public class ClassroomDAO implements StorageDao {
    private StorageDAOImpl storage = null;
    public ClassroomDAO()
    {
        this.storage = new StorageDAOImpl();
    }
    public void save(Classroom classroom)
    {
         String sql="UPDATE classroom SET name=?, capacity=?, equipment=? WHERE id=?";
         try {
             PreparedStatement stmt = storage.conn.prepareStatement(sql);
             stmt.setString(1,classroom.getName());
             stmt.setInt(2,classroom.getCapacity());
             stmt.setString(3,classroom.getEquipment());
             stmt.setInt(4,classroom.getRoom_id());
             stmt.executeUpdate(); // Because we will not retrieve any data.
         } catch(SQLException e) {
            IO.println("SQL error updating classroom: " + e.getMessage());
         }
    }

   /* public List<Classroom> find(Date book_start, String name, int capacity, String equipment) {
        // We have a booking-method in the BookingDAO which will retrieve all nonbooked rooms in the future.
        BookingDAO b = new BookingDAO();
        b.getBookings(book_start).forEach().stream()
                .filter(room -> room.

                )
    }*/

    public List<Classroom> list(java.util.Date start, java.util.Date end,String name, int capacity, String equipment){

        // If the condition missmatches, b.id will be null.
        // Condition missmatches if there's no booking at the given time for this room
        List<Classroom> classrooms = new ArrayList<>();
        String sql = """

                SELECT
    r.id AS room_id,
    r.name AS room_name,
    r.capacity AS room_capacity,
    r.accessibility AS room_accessibility,
    r.equipment AS room_equipment
FROM classroom r
LEFT JOIN bookings b
    ON r.id = b.booked_classroom
    AND b.book_start < ?
    AND b.book_end   > ?
WHERE b.id is NULL
  AND name LIKE ?
  AND equipment LIKE ? 
  AND r.capacity > ?;""";
        IO.println("test");
        try {
            PreparedStatement stmt = this.storage.conn.prepareStatement(sql);
            stmt.setTimestamp(1, new java.sql.Timestamp(end.getTime()));
            stmt.setTimestamp(2, new java.sql.Timestamp(start.getTime()));
            stmt.setString(3,name);
            stmt.setString(4,equipment);
            stmt.setInt(5,capacity);
            ResultSet rs = stmt.executeQuery();
            //IO.println(executedSql);
            while(rs.next())
            {
                classrooms.add(this.parse(rs));
            }
        } catch (SQLException e) {
            throw new ConnectionException("Error occured fetching availble rooms: " + e.getMessage());
        }
        return classrooms;
    }

    private Classroom parse(ResultSet rs){
        Classroom room = new Classroom();
        try {
            room.setRoom_id(rs.getInt("room_id"));
            room.setName(rs.getString("room_name"));
            room.setCapacity(rs.getInt("room_capacity"));
            room.setAccessibility(rs.getBoolean("room_accessibility"));
            room.setEquipment(rs.getString("room_equipment"));
        } catch (SQLException e)
        {
            throw new StorageParserException("Something went wrong retrieving fields from database" + e.getMessage());
        }
        return room;
        }
    public List<Classroom> list()
    {
        String sql= """
                SELECT id as room_id, name as room_name, capacity as room_capacity, accessibility as room_accessibility, equipment as room_equipment FROM classroom ORDER BY name
                """;
        List<Classroom> rooms = new ArrayList<>();
        try(Statement stmt = storage.conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);) {
            while(rs.next())
            {
                rooms.add(this.parse(rs));
            }
        } catch (SQLException e)
        {
            throw new StorageParserException("An error occured trying to fetch data" + e.getMessage());
        }
        return rooms;
    }
    static private Timestamp stringToTimestamp(String ts)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            java.util.Date parsedDate = dateFormat.parse(ts);
            java.sql.Timestamp timestamp = new Timestamp(parsedDate.getTime());
            return timestamp;
        } catch (ParseException e)
        {
            throw new IllegalArgumentException("Invalid date format");
        }

    }
    public boolean checkIfBooked(int roomId, String start, String end){

        List<Classroom> classrooms = new ArrayList<>();
        String sql = """

                SELECT
    r.id AS room_id,
    r.name AS room_name,
    r.capacity AS room_capacity,
    r.accessibility AS room_accessibility,
    r.equipment AS room_equipment
FROM classroom r
LEFT JOIN bookings b
    ON r.id = b.booked_classroom
    AND b.book_start < ?
    AND b.book_end   > ?
WHERE b.id is not NULL
  AND r.id = ?;""";

        try {
            PreparedStatement stmt = this.storage.conn.prepareStatement(sql);
            stmt.setTimestamp(1, stringToTimestamp(start));
            stmt.setTimestamp(2, stringToTimestamp(end));
            stmt.setInt(3,roomId);
            ResultSet rs = stmt.executeQuery();
            //IO.println(executedSql);
            while(rs.next())
            {
                return true;
            }
        } catch (SQLException e) {
            throw new ConnectionException("Error occured fetching availble rooms: " + e.getMessage());
        }
        return false;
    }
    public boolean roomExists(int id)
    {
        // Find customer based on ID.
        String sql = "SELECT * FROM classroom WHERE id = ? LIMIT 1"; // I know that this only should return one row.

        try {
            PreparedStatement stmt = this.storage.conn.prepareStatement(sql);
            stmt.setInt(1,id);
            //ResultSet rs = stmt.getResultSet();
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                return true;
            }
        } catch (SQLException e){
            //throw new SQLException("Error searching database");
            //As a tempoary solution we print the error here
            IO.println("aasd" + e.getMessage());
        }
        return false;
    }
}
