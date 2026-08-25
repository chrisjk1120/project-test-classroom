package se.lexicon.customer;

public class InvalidCustomerFormat extends RuntimeException {
    public InvalidCustomerFormat(String message) {
        super(message);
    }
}
