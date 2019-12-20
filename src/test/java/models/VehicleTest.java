package models;

import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VehicleTest {

    @BeforeEach
    void makePanel() {
        JFXPanel panel = new JFXPanel();
    }

    @Test
    void fastCarAct() {
        Vehicle fastCar = new Vehicle(0,0,"fast car");
        fastCar.act();
        assertEquals(fastCar.getX(),-3);
        for (int i = 0; i < 16; i++)
            fastCar.act();
        assertEquals(fastCar.getX(),600);
    }

    @Test
    void slowCarAct() {
        Vehicle slowCar = new Vehicle(0,0,"slow car");
        slowCar.act();
        assertEquals(slowCar.getX(), -1);
        for (int i = 0; i < 50; i++)
            slowCar.act();
        assertEquals(600,slowCar.getX());
    }

    /**
     * Only necessary to check one type of truck because they share the same properties besides width and image
     */
    @Test
    void truckAct() {
        Vehicle truck = new Vehicle(0,0,"long truck");
        truck.act();
        assertEquals(1,truck.getX());
        for (int i = 0; i < 600; i++)
            truck.act();
        assertEquals(-200,truck.getX());
    }
}