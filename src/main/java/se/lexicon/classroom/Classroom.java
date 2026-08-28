package se.lexicon.classroom;
import se.lexicon.presentation.ClassroomPresentation;
import se.lexicon.storage.ClassroomDAO;

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
        //setEquipment(equipment);
        setAccessibility(accessibility);

    }
    public Classroom()
    {

    }

    public void listClassrooms()
    {
        ClassroomDAO storage = new ClassroomDAO();
        storage.list().forEach(ClassroomPresentation::present);
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
