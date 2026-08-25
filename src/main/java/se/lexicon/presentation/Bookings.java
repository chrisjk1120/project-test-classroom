package se.lexicon.presentation;

import se.lexicon.booking.Booking;
import se.lexicon.storage.BookingDAO;

import java.util.ArrayList;
import java.util.List;
public class Bookings {

    static public void ListBookings(ArrayList<Booking> bookings)
    {

    }
    static public void ListBookings()
    {
        BookingDAO storage = new BookingDAO();
        IO.println("|Booking#\t\t|Customer\t\t\tRoom");
        storage.getBookings().forEach(item ->
        {
            Presentation.printText("| "+ item.getId()  +"\t\t\t|" + item.getCustomer().getCustomer_name() + " |\t\t\t" + item.getRoom().getName());

        });







    }

    static public void ShowBookingOperations()
    {
        IO.println("0) Exit");
        IO.println("1) Delete booking");
        IO.println("2) Change booking");
        IO.println("3) Add booking");
    }
}
