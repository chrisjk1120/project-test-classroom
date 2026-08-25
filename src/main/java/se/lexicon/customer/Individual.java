package se.lexicon.customer;

public class Individual extends Customer {


    public Individual(int id, String name, String email) {
        super(id, name, email);
        super.setId(id);
        super.setEmail(email);
        super.setCustomer_name(name);
        super.setCustomer_type(CustomerTypes.INDIVIDUAL);
    }


    
}
