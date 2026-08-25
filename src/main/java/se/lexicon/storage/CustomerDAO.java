package se.lexicon.storage;

import java.sql.PreparedStatement;
import se.lexicon.booking.Booking;
import se.lexicon.customer.Customer;
import se.lexicon.customer.CustomerTypes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import java.util.ArrayList;
public class CustomerDAO implements StorageDao {
    private StorageDAOImpl storage = null;

    public CustomerDAO()
    {
        this.storage=new StorageDAOImpl();
    }

    public void createCustomer(Customer customer)
    {
        // Id is autoincrement in db, hence we don't have to think about it.
        String sql = "INSERT INTO customers (name,type,email) VALUES(?,?,?)";

        try {
            PreparedStatement stmt = this.storage.conn.prepareStatement(sql);
            stmt.setString(1,customer.getCustomer_name());
            stmt.setString(2,customer.getCustomer_type().toString());
            stmt.setString(3, customer.getEmail());
            stmt.executeUpdate();
        } catch (Exception e)
        {
            IO.println(e.getMessage());
        }
    }

    /*public List<Booking> FindBookingsForCustomer(int id) {
        String sql = "SELECT * FROM customers";
    }*/

    public List<Customer> FindCustomers(String name)
    {
        //
        String sql = "SELECT * FROM customers WHERE name LIKE '%?%'";
        List<Customer> customers = new ArrayList<>();
        String customer_name = null;
        String customer_email = null;
        int customer_id = 0;
        try {
            PreparedStatement stmt = this.storage.conn.prepareStatement(sql);
            stmt.setString(1,name);
            //ResultSet rs = stmt.getResultSet();
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {

                CustomerTypes type=CustomerTypes.valueOf(rs.getString("type"));
                customer_name=rs.getString("name");
                customer_email=rs.getString("email");
                customer_id=rs.getInt("id");

                Customer cust = new Customer(customer_id,customer_name,customer_email);


            }
            return customers;
        } catch (SQLException e){
            //throw new SQLException("Error searching database");
            //As a tempoary solution we print the error here
            IO.println(e.getMessage());
        }


        return customers;
    }
}

