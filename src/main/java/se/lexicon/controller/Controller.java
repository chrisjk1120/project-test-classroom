package se.lexicon.controller;

import se.lexicon.customer.Customer;
import se.lexicon.customer.CustomerTypes;
import se.lexicon.customer.InvalidCustomerFormat;
import se.lexicon.presentation.*;

import se.lexicon.booking.Booking;
import se.lexicon.storage.CustomerDAO;
import se.lexicon.storage.exceptions.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Controller {

        // This class will handle all data
        static public void Controller () {
        boolean inLoop = true;
        try {
        while (inLoop) {
            int selection = 0;
            Presentation.printMenu();
            try {
                selection = Integer.parseInt(Presentation.userInput("Your selection>"));
            } catch (IllegalArgumentException e) {
                IO.println(e.getMessage());
            }
            switch (selection) {
                case 1:
                    Bookings.ListBookings(); // List
                    break;


                case 2:
                    // Class to modify classroom
                    ClassroomPresentation.menuHandler();
                    break;
                case 3:
                    CustomerPresentation.menuHandler();
                    break;
                case 4:
                    // Add new customer
                    IO.println("MOVED TO OWN CONTROLLER");

                    break;
                case 99:
                    inLoop = false;
                    break;
                default:
                    Presentation.printText("Invalid selection");
            }
        }
        } catch (Exception e) {
            Presentation.printText("-ERR: " + e.getMessage());

    }
}
    public static Date stringToDate(String dateString) {
        LocalDate localDate = LocalDate.parse(dateString);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
