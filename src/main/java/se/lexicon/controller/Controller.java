package se.lexicon.controller;

import se.lexicon.customer.Customer;
import se.lexicon.customer.CustomerTypes;
import se.lexicon.customer.InvalidCustomerFormat;
import se.lexicon.presentation.Bookings;
import se.lexicon.presentation.ClassroomPresentation;
import se.lexicon.presentation.CustomerPresentation;
import se.lexicon.presentation.Presentation;

import se.lexicon.booking.Booking;
import se.lexicon.storage.CustomerDAO;
import se.lexicon.storage.exceptions.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Controller {
    // This class will handle all data
    static public void Controller() {
        boolean inLoop = true;

        while (inLoop) {
            int selection=0;
            Presentation.printMenu();
            try {
                selection = Integer.parseInt(Presentation.userInput("Your selection>"));
            } catch (IllegalArgumentException e) {
                IO.println(e.getMessage());
            }
            switch(selection)
            {
                case 1:
                    Bookings.ListBookings(); // List
                    boolean running=true;
                    while(running)
                    {
                        int userInput;
                        Bookings.ShowBookingOperations();
                        userInput=Integer.parseInt(Presentation.userInput("Your selection> "));
                        switch(userInput)
                        {
                            case 0:
                                // Exit bookings module
                                running=false;
                                break;
                            case 1:
                                Booking booking = new Booking();

                                // Exceptions should be thrown if invalid format.
                                String searchDate = Presentation.userInput("Enter startdate");

                                booking.setStart_date(stringToDate(searchDate));
                                booking.setEnd_date(Presentation.userInput(("Enter enddate")));
                                Presentation.printText("Listing current customers");
                                CustomerPresentation.listAllCustomers();
                                Presentation.printText("End of listing");
                                break;


                            case 2:


                            case 3:

                                break;

                        }
                    }
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
                    inLoop=false;
                    break;
                default:
                    Presentation.printText("Invalid selection");
            }
        }
    }
    public static Date stringToDate(String dateString) {
        LocalDate localDate = LocalDate.parse(dateString);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
