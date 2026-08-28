package se.lexicon.storage.exceptions;

public class NonExistingRoom extends RuntimeException {
    public NonExistingRoom(String message) {
        super(message);
    }
}
