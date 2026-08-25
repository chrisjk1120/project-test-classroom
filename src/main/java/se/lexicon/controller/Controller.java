package se.lexicon.controller;

import se.lexicon.customer.Customer;
import se.lexicon.customer.CustomerTypes;
import se.lexicon.customer.InvalidCustomerFormat;
import se.lexicon.presentation.Bookings;
import se.lexicon.presentation.CustomerPresentation;
import se.lexicon.presentation.Presentation;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import se.lexicon.booking.Booking;
import se.lexicon.storage.CustomerDAO;

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
                                booking.setStart_date(Presentation.userInput(("Enter startdate")));
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
                    break;
                case 3:
                    CustomerPresentation.listAllCustomers();
                    break;
                case 4:
                    // Add new customer
                    Customer newCust = new Customer();
                    newCust.setCustomer_name(Presentation.userInput("Enter name"));
                    newCust.setEmail(Presentation.userInput("Enter email"));
                    try {
                        String custType = Presentation.userInput("Custeomer type? (INDIVIDUAL/COMPANY)").toUpperCase();
                        if(custType.equals("COMPANY")) {
                            newCust.setType(CustomerTypes.COMPANY);
                        } else if(custType.equals("INDIVIDUAL")) {
                            newCust.setType(CustomerTypes.INDIVIDUAL);
                        } else {
                            throw new InvalidCustomerFormat("Entered customertype is not supperted");
                        }

                    } catch (Exception e) {
                        // Will handle in our own error handler
                        IO.println("Please enter INDIVIDUAL _OR_ COMPANY" + e.getMessage());

                    }

                    CustomerDAO storage = new CustomerDAO();
                    try {
                        storage.save(newCust);
                    } catch ()
                    Presentation.printText("New customer added");


                    break;
                case 99:
                    inLoop=false;
                    break;
                default:
                    Presentation.printText("Invalid selection");
            }
        }
    }
}
