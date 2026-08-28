package se.lexicon.presentation;

import se.lexicon.customer.Customer;
import se.lexicon.customer.CustomerTypes;
import se.lexicon.customer.InvalidCustomerFormat;
import se.lexicon.storage.BookingDAO;
import se.lexicon.storage.ClassroomDAO;
import se.lexicon.storage.CustomerDAO;
import se.lexicon.storage.exceptions.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;


public class CustomerPresentation {
    static public void menuHandler() {
        int selection;
        boolean inLoop = true;
        while (inLoop) {
            menu();
            selection = Integer.parseInt(Presentation.userInput("Selection > "));
            switch (selection) {
                case 1:
                    listAllCustomers();
                    break;
                case 2:
                    Presentation.printText("NOT IMPLEMENTED");
                    break;
                case 3:
                    // List bookings for customer
                    try {
                        CustomerBookings(Integer.parseInt(Presentation.userInput("Enter customerid: ")));
                    } catch (Exception e) {
                        throw new IllegalArgumentException("Invalid user input." + e.getMessage());
                    }
                    break;
                case 4:
                    final String start_date = Presentation.userInput("Start Date:");
                    final String end_date = Presentation.userInput("End date:");


                        int userId = Integer.parseInt(Presentation.userInput("Customer ID"));
                        int roomId = Integer.parseInt(Presentation.userInput("Room ID"));
                        BookingDAO booking = new BookingDAO();
                        CustomerDAO cust = new CustomerDAO();
                        ClassroomDAO room = new ClassroomDAO();
                        if (!cust.FindCustomer(userId)) {
                            throw new NonExistingCustomerException("Customer does not exist");
                        }
                        if (!room.roomExists(roomId)) {
                            throw new NonExistingRoom("Classroom does not exist");
                        }
                        if (room.checkIfBooked(roomId, start_date, end_date)) {
                            throw new RoomBookedException("This room is already booked this time");
                        }

                    Presentation.printText("All validations completed, adding booking");
                    booking.addBooking(userId, roomId, start_date, end_date);
                            break;
                        case 5:
                            Customer newCust = new Customer();
                            newCust.setCustomer_name(Presentation.userInput("Enter name"));
                            newCust.setEmail(Presentation.userInput("Enter email"));
                            try {
                                String custType = Presentation.userInput("Custeomer type? (INDIVIDUAL/COMPANY)").toUpperCase();
                                if (custType.equals("COMPANY")) {
                                    newCust.setType(CustomerTypes.COMPANY);
                                } else if (custType.equals("INDIVIDUAL")) {
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
                            } catch (Exception e) {
                                throw new AddCustomerException(e.getMessage());
                            }
                            Presentation.printText("New customer added");
                            break;
                        default:
                            inLoop = false;
                            break;

                    }

            }
        }

    static private Timestamp stringToTimestamp(String ts)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        try {
            java.util.Date parsedDate = dateFormat.parse(ts);
            java.sql.Timestamp timestamp = new Timestamp(parsedDate.getTime());
            return timestamp;
        } catch (ParseException e)
        {
            throw new IllegalArgumentException("Invalid date format");
        }

    }
    static public void CustomerBookings(int customerId) {
        // Retrieves a CustomerID and shows bookings
        BookingDAO storage = new BookingDAO();
        IO.println("|Booking#\t\t|Booked From\t\t|Booked To\t\t|Customer\t\t\tRoom");
        storage.getBookings(customerId).forEach(item ->
        {
            Presentation.printText("| " + item.getId() + "\t\t\t|" + item.getStart_date() + "\t\t|" + item.getEnd_date() + "\t\t|" + item.getCustomer().getCustomer_name() + " |\t\t\t" + item.getRoom().getName());

        });
    }
    static public void menu()
    {
        IO.println("===CUSTOMER MODULE===");
        IO.println("1) List customers");
        IO.println("2) Edit customer");
        IO.println("3) List bookings for customer");
        IO.println("4) Add booking for customer");
        IO.println("5) Add new customer");
    }
    static public void  listAllCustomers()
    {
        IO.println("ID|Name\t\t|Email\t\t|Type\t\t");
        CustomerDAO storage = new CustomerDAO();
        storage.FindCustomers("").forEach(customer -> {
            IO.println(customer.getId()+"|"+customer.getCustomer_name()+"\t\t|"+customer.getEmail()+"\t\t|"+customer.getType()+"\t\t");


                }

        ); // As the SQL is a LIKE-query with name as argument. If no name is entered, all results will be shown


    }
}
