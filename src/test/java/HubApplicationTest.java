import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HubApplicationTest {
    private static final String URL = "jdbc:mysql://192.168.8.165:3306/hub";


    @Before
    public void setup() {
    }


    @Test
    public void testConnectionToDbPositiveTest() {
        Assert.assertTrue(connectToDbPositive());
    }

    @Test
    public void testConnectionToDbNegativeTest() {
        Assert.assertFalse(connectToDbNegative());

    }


    public boolean connectToDbPositive() {
        try {

            Connection connection = DriverManager.getConnection(URL, "root", "");
            if (connection.isValid(1)) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error with connection to DB" + e.getMessage());
        }
        return false;
    }

    public boolean connectToDbNegative() {
        try {
            Connection connection = DriverManager.getConnection(URL, "root", "");
            if (connection.isValid(1))
                return false;
        } catch (SQLException e) {
            System.out.println("Error with connection to DB" + e.getMessage());
        }


        return true;
    }
}