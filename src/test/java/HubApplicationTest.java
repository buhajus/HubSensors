

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import com.pi4j.io.gpio.GpioPinDigitalInput;
import org.hub.sensors.controller.PiGpioController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HubApplicationTest {
    private final String URL = "jdbc:mysql://127.0.0.1:3306/hub";

    @Test
    public void test() {
        assertEquals(4, number());

    }

    @Test
    public void testConnectionToDbPositiveTest() {
        assertTrue(connectToDbPositive());
    }

    @Test
    public void testConnectionToDbNegativeTest() {
        assertFalse(connectToDbNegative());

    }

    public void testGpioIsHigh() {
        PiGpioController gpioController = new PiGpioController();
        GpioPinDigitalInput digitalInput = gpioController.gpioPin();

        assertTrue(digitalInput.isHigh());
    }

    public int number() {
        return 2 + 2;
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


        return true;
    }
}