/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package labyrinthe;

import java.io.IOException;
import static junit.framework.Assert.assertFalse;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author mflorent
 */
public class EtageTest {

    public EtageTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    public void testAFloor(Etage floorToTest) {
        ISalle salle;
        ISalle salle2;

        for (int i = 0; i < floorToTest.size(); i++) {

            salle = floorToTest.get(i);
            for (int j = 0; i < floorToTest.size(); i++) {
                if (i != j) {
                    salle2 = floorToTest.get(j);
                    assertFalse(salle.equals(salle2));
                }
                j++;
            }
            assertFalse(salle.getY() > floorToTest.getHauteur());
            assertFalse(salle.getX() > floorToTest.getLargeur());
            assertFalse(salle.getY() < 0);
            assertFalse(salle.getX() < 0);
            i++;
        }
    }

    @Test
    public void testDoubleRooms() throws IOException {
        Etage etage = new Etage();
        Etage etage2 = new Etage();
        Etage etage3 = new Etage();
        Etage etage4 = new Etage();
        etage.charger("etages/etage1N.txt");
        etage2.charger("etages/etage2N.txt");
        etage3.charger("etages/etageInvalide1N.txt");
        etage4.charger("etages/etageInvalide2N.txt");

        // Tests for a valid floor
        testAFloor(etage);

        // Tests for a second valid floor
        testAFloor(etage2);

        // Tests for a not valid floor
        testAFloor(etage3);
        // Tests for a second not valid floor
        testAFloor(etage4);

    }

}
