package se.lexicon.presentation;

public class Presentation
{
    public Presentation()
    {

    }
    public void printMenu()
    {
        boolean inLoop=true;
        while(inLoop)
        {
            IO.println("1) List bookings");
            IO.println("2) Update booking");
            IO.println("3) Delete booking");

        }
    }
}
