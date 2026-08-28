package se.lexicon.presentation;

public class ErrorHandler extends RuntimeException {
    public void ErrorHandler(Exception e)
    {
        Presentation.printText("ERR: " + e.getMessage());
    }
}
