package se.lexicon.storage;
import com.mysql.cj.PreparedQuery;
import se.lexicon.storage.exceptions.ConnectionException;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.*;

public class StorageDAOImpl implements StorageDao {
        public Connection conn = null;


        public  StorageDAOImpl()
        {
            this.conn=this.connect();
        }
        private Connection connect()
        {
            Connection conn =null;
            try {

                conn=DriverManager.getConnection(URL, user, password);

            } catch(Exception e) {
                IO.println(e.getMessage());
            }
            return conn;
        }
        }
/*        private Connection connect()  {
            try (Connection conn = DriverManager.getConnection(URL, user, password)){
                return conn;
            } catch (SQLException e) {
                throw new ConnectionException("ERR: Something seems wrong with the connection." + e.getMessage());
                }

            }
*/


