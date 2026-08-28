package se.lexicon.customer;

import se.lexicon.storage.CustomerDAO;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
public class Customer {

    private CustomerTypes type = null;
    private String customer_name = null;
    private int id = 0;
    private String email = null;
    CustomerDAO storage=new CustomerDAO();
    public Customer(int id, String name, String email) {
        setId(id);
        setCustomer_name(name);
        setEmail(email);
        setType(type);
    }

    private boolean isDuplicate(String name)
    {
     List<Customer> customers = this.storage.FindCustomers("");
     customers.stream()
             .filter(customer -> customer.getCustomer_name().equalsIgnoreCase(name))
             .toList();
            IO.println(customers.size());
            if(customers.isEmpty())
            {
                return false;
            } else {
                return true;
            }
    }

    public Customer() {

    }

    public void update() {

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
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
