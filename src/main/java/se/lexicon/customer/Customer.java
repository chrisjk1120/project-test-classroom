package se.lexicon.customer;

import se.lexicon.storage.CustomerDAO;
import se.lexicon.storage.StorageDao;

public class Customer {

    private CustomerTypes type = null;
    private String customer_name = null;
    private int id = 0;
    private String email = null;
    private CustomerTypes customer_type; // Really do
    //void parseCustomer(int id,String name, CustomerTypes type);
    CustomerDAO storage=new CustomerDAO();
    public Customer(int id, String name, String email) {
        setId(id);
        setCustomer_name(name);
        setEmail(email);
        setCustomer_type(type);
    }

    public Customer() {

    }

    public void update() {

    }
    public Customer SaveCustomer(String name, String email, CustomerTypes type) {
        // Set the fields and look for formatting errors, throw an exception BEFORE saving.
        this.setCustomer_name(name);
        this.setEmail(email);
        this.setCustomer_type(type);

        storage.createCustomer(this);
    return this;
    }

    public CustomerTypes getType() {
        return type;
    }

    public void setType(CustomerTypes type) {
        this.type = type;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CustomerTypes getCustomer_type() {
        return customer_type;
    }

    public void setCustomer_type(CustomerTypes customer_type) {
        this.customer_type = customer_type;
    }
}
