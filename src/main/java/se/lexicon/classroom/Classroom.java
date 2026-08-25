package se.lexicon.classroom;
import se.lexicon.customer.*;
import se.lexicon.storage.StorageDAOImpl;
import se.lexicon.storage.StorageDao;

/* This class will give the metods and fields to handle the classroom-object

*/
public class Classroom {
    private int room_id=0;
    private String room_name = null;
    private int room_capacity =0;
    private boolean room_accessibility = false;
    private String equipment=null;
    public Classroom(int id, String name, int capacity,boolean accessibility)
    {
        setRoom_id(id);
        setName(name);
        setCapacity(capacity);
        setAccessibility(accessibility);

    }
    public Classroom()
    {

    }




    public String getName() {
        return this.room_name;
    }

    public void setName(String name) {
        this.room_name = name;
    }

    public int getCapacity() {
        return this.room_capacity;
    }

    public void setCapacity(int capacity) {
        this.room_capacity = capacity;
    }

    public boolean isAccessibility() {
        return room_accessibility;
    }

    public void setAccessibility(boolean accessibility) {
        this.room_accessibility = accessibility;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }
}
