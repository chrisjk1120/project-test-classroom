package se.lexicon.presentation;

import se.lexicon.booking.Booking;
import se.lexicon.classroom.Classroom;
import se.lexicon.storage.BookingDAO;
import se.lexicon.storage.ClassroomDAO;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
public class ClassroomPresentation {
    static public void menuHandler() {
        ClassroomDAO storage = new ClassroomDAO();
        int selection;
        while (true) {

        Presentation.printText("1) Print classrooms");
        Presentation.printText("2) Edit Classroom");
        Presentation.printText("3) Search availability");
        Presentation.printText("99) Main menu");
        selection=Integer.parseInt(Presentation.userInput("Choice"));
        switch(selection)
        {
            case 1:
                // Print all classrooms

                storage.list().forEach(ClassroomPresentation::present);

                //cust.
                break;
            case 2:
                // Get one customer object, edit it, update/save it
                int custId=Integer.parseInt(Presentation.userInput("Classroom ID:>"));
                List<Classroom> classroom = storage.list().stream()
                        .filter(p -> p.getRoom_id() == custId)
                        .toList();
                if(classroom.isEmpty())
                {
                    // Meaning that the Stream returned no objects, meaning id not exists
                    throw new InvalidClassroomException("The selected classroom id does not exist");
                } else {
                    Classroom selectedRoom = classroom.get(0);
                    selectedRoom.setName(Presentation.userInput("New name: (Old: " + selectedRoom.getName() + ")"));
                    selectedRoom.setCapacity(Integer.parseInt(Presentation.userInput("Capacity (Old: " + selectedRoom.getCapacity() + ");")));
                    selectedRoom.setEquipment(Presentation.userInput("Equipment: (Old: " + selectedRoom.getEquipment() + "):"));
                    // Update classroom
                    storage.save(selectedRoom);
                }
            case 3:
                //Search for available rooms based on date, time, capacity, equipment,
                //    or accessibility.

                // We have a method for this in the BookingDAO-class. Lets use it.

                String fromDateStr = Presentation.userInput("From date: ");
                String toDateStr = Presentation.userInput("To date: ");

                int capacity = Integer.parseInt(Presentation.userInput("Min. capacity: "));
                String name = Presentation.userInput("Name of classroom:");
                String equipment = Presentation.userInput("Equipment");
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    storage.list(formatter.parse(fromDateStr),formatter.parse(toDateStr),name,capacity,equipment).forEach(room -> present(room));
                } catch (ParseException e) {
                    throw new InvalidClassroomException("Invalid date format entered");
                }

                break;
            case 99:
                return;

        }
        }
    }

    static public void present(se.lexicon.classroom.Classroom classroom){
        IO.println("ID: " + classroom.getRoom_id() + " | Name: " + classroom.getName() + " | Capacity: " + classroom.getCapacity() + " | Equipment: " + classroom.getEquipment());
    }
}
