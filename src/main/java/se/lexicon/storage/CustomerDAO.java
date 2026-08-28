package se.lexicon.storage;

import java.sql.PreparedStatement;
import se.lexicon.booking.Booking;
import se.lexicon.customer.Company;
import se.lexicon.customer.Customer;
import se.lexicon.customer.CustomerTypes;
import se.lexicon.customer.Individual;
import se.lexicon.storage.exceptions.SaveCustomerException;

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



@Override
    public void save(Customer customer)
    {
        String sql="INSERT INTO customers (name,email,type) VALUES(?,?,?)";
        try {
            PreparedStatement stmt = storage.conn.prepareStatement(sql);
            try {
                stmt.setString(1, customer.getCustomer_name());
                stmt.setString(2, customer.getEmail());
                stmt.setString(3, customer.getType().name()); // We have a method return string instead. Convert to a string.
                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new SaveCustomerException("SQL exception thrown:" + e.getMessage());
            } catch (NullPointerException e) {
                throw new SaveCustomerException("Conversion failed");
            }
        } catch(SQLException e) {
                 throw new SaveCustomerException("Something is wrong with this sql:" + e.getMessage());
    }

    }


    public boolean FindCustomer(int id)
    {
        // Find customer based on ID.
        String sql = "SELECT * FROM customers WHERE id = ? LIMIT 1"; // I know that this only should return one row.

        try {
            PreparedStatement stmt = this.storage.conn.prepareStatement(sql);
            stmt.setInt(1,id);
            //ResultSet rs = stmt.getResultSet();
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                return true;
            }
        } catch (SQLException e){
            //throw new SQLException("Error searching database");
            //As a tempoary solution we print the error here
            IO.println("aasd" + e.getMessage());
        }
        return false;
    }

    public List<Customer> FindCustomers(String name)
    {
        //
        String sql = "SELECT * FROM customers WHERE name LIKE ?";
        List<Customer> customers = new ArrayList<>();
        String customer_name = null;
        String customer_email = null;
        int customer_id = 0;
        try {
            PreparedStatement stmt = this.storage.conn.prepareStatement(sql);
            stmt.setString(1,"%"+name+"%");
            //ResultSet rs = stmt.getResultSet();
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {

                CustomerTypes type=CustomerTypes.valueOf(rs.getString("type"));
                customer_name=rs.getString("name");
                customer_email=rs.getString("email");
                customer_id=rs.getInt("id");
                Customer cust;
                if(type==CustomerTypes.INDIVIDUAL) {
                    cust = new Individual(customer_id, customer_name, customer_email);
                } else {
                    cust = new Company(customer_id,customer_name,customer_email);
                }
                customers.add(cust);

            }
            return customers;
        } catch (SQLException e){
            //throw new SQLException("Error searching database");
            //As a tempoary solution we print the error here
            IO.println("aasd" + e.getMessage());
        }


        return customers;
    }
}

