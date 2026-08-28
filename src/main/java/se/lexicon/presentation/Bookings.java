package se.lexicon.presentation;

import se.lexicon.booking.Booking;
import se.lexicon.storage.BookingDAO;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class Bookings {

    static public void ListBookings(ArrayList<Booking> bookings)
    {

    }
    static public void ListBookings()
    {
        BookingDAO storage = new BookingDAO();
        IO.println("|Booking#\t\t|Customer\t\t\tRoom");
        String currentDate = LocalDate.now().toString();
        storage.getBookings(currentDate).forEach(item ->
        {
            Presentation.printText("| "+ item.getId()  +"\t\t\t|" + item.getStart_date() + "\t\t|" + item.getEnd_date() + "\t\t|" + item.getCustomer().getCustomer_name() + " |\t\t\t" + item.getRoom().getName());

        });







    }

    public static Date toUtilDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    static public void ShowBookingOperations()
    {
        IO.println("0) Exit");
        IO.println("1) Delete booking *** NOT IMPLEMETED ***");
        IO.println("2) Change booking");
        IO.println("3) Add booking");
    }
}
