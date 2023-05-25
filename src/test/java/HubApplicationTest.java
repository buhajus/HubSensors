

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HubApplicationTest {
    private static final String URL = "jdbc:mysql://localhost:3306/hub";

    @Test
    public void testMethodOne() {
        assertEquals(4,number());

    }

    @Test
    public void testConnectionToDbPositiveTest() {
        assertTrue(connectToDbPositive());
    }

    @Test
    public void testConnectionToDbNegativeTest() {
        assertFalse(connectToDbNegative());

    }

    public int number(){
        return 2+2;
    }

    public boolean connectToDbPositive() {
        try {
            Connection connection = DriverManager.getConnection(URL, "root", "");
            if (connection.isValid(0)) {
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


        return false;
    }
}