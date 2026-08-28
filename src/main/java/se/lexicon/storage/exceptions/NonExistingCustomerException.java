package se.lexicon.storage.exceptions;

public class NonExistingCustomerException extends RuntimeException {
    public NonExistingCustomerException(String message) {
        super(message);
    }
}
