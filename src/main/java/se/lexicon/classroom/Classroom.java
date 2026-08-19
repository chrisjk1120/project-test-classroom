package se.lexicon.classroom;
import se.lexicon.customer.*;
/* This class will give the metods and fields to handle the classroom-object

*/
public class Classroom {
    private String name = null;
    private int capacity =0;
    private boolean accessibility = false;

    public void Classroom(String name, int capacity,boolean accessibility)
    {

    }

    private void masterdata()
    {
        // Populate the database with 20 different classrooms.

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isAccessibility() {
        return accessibility;
    }

    public void setAccessibility(boolean accessibility) {
        this.accessibility = accessibility;
    }
}
