package se.lexicon.storage;
import se.lexicon.classroom.Classroom;
import se.lexicon.storage.exceptions.ConnectionException;
import se.lexicon.storage.exceptions.StorageParserException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
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

    }

    public List<Classroom> list(Date start, Date end){

        // If the condition missmatches, b.id will be null.
        // Condition missmatches if there's no booking at the given time for this room
        List<Classroom> classrooms = new ArrayList<>();
        String sql = """
                select
                r.id,
                r.name,
                r.accessibilty
                r.equipment
                from classroom r
                left join bookings b on r.id = b.booked_classroom
                	and b.book_start < ?
                	and b.book_end > ?
                	where b.id is null;
                
                """;
        try {
            PreparedStatement stmt = this.storage.conn.prepareStatement(sql);
            stmt.setDate(1, (java.sql.Date) start);
            stmt.setDate(2, (java.sql.Date) end);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                classrooms.add(this.parse(rs));
            }
        } catch (SQLException e) {
            throw new ConnectionException("Error occured fetching availble rooms");
        }
        return classrooms;
    }

    private Classroom parse(ResultSet rs){
        Classroom room = new Classroom();
        try {
            room.setRoom_id(rs.getInt("id"));
            room.setName(rs.getString("name"));
            room.setCapacity(rs.getInt("capacity"));
            room.setAccessibility(rs.getBoolean("accessibility"));
            room.setEquipment(rs.getString("equipment"));
        } catch (SQLException e)
        {
            throw new StorageParserException("Something went wrong retrieving fields from database" + e.getMessage());
        }
        return room;
        }
    public List<Classroom> list()
    {
        String sql= """
                SELECT * FROM classroom ORDER BY name
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
            // Do something
        }
    }
}
