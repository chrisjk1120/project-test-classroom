package se.lexicon.presentation;

public class Presentation
{
    public Presentation()
    {

    }
    static public void printMenu()
    {

            IO.println("1) List upcoming bookings");
            IO.println("2) Add booking");
            IO.println("3) List customers");
            IO.println("4) Add customer");
            IO.println("5) List rooms");


    }

    static public void printText(String text)
    {
        IO.println(text);
    }

    static public String userInput(String prompt)
    {
        return IO.readln(prompt);
    }
}
