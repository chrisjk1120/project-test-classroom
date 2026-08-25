package se.lexicon.presentation;

import se.lexicon.customer.Customer;
import se.lexicon.storage.CustomerDAO;

public class CustomerPresentation {

    static public void  listAllCustomers()
    {
        IO.println("ID|Name\t\t|Email\t\t|Type\t\t");
        CustomerDAO storage = new CustomerDAO();
        storage.FindCustomers("").forEach(customer -> {
            IO.println(customer.getId()+"|"+customer.getCustomer_name()+"\t\t|"+customer.getEmail()+"\t\t|"+customer.getType()+"\t\t");


                }

        ); // As the SQL is a LIKE-query with name as argument. If no name is entered, all results will be shown


    }
}
