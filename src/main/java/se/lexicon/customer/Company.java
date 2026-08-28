package se.lexicon.customer;

public class Company extends Customer {

    public Company(int id,String name, String email){
        super(id,email,name);
        /*super.setId(id);
        super.setEmail(email);
        super.setCustomer_name(name);
        super.setCustomer_type(CustomerTypes.COMPANY);
        */
        setId(id);

        setCustomer_name(name);
        setEmail(email);
        setType(CustomerTypes.COMPANY);
    }

}
