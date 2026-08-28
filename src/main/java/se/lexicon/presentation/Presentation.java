package se.lexicon.presentation;

public class Presentation
{
    public Presentation()
    {

    }
    static public void printMenu()
    {

            IO.println("1) List upcoming bookings");
            IO.println("2) Manage classrooms");
            IO.println("3) Manage customers");
            IO.println("6) List rooms");


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
