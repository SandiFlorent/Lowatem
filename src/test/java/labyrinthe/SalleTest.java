/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package labyrinthe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gothmog
 */
public class SalleTest {
    
    public SalleTest() {
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

    @Test
    public void testAreRoomsAdjacentOnTheSameFloor() {
        Etage etage = new Etage(1); // Creating a first floor
        Etage etage2 = new Etage(2); // Creating a second floor
        
        ISalle salle1 = new Salle(4, 4, ESalle.NORMALE, etage);
        ISalle salle2 = new Salle(3, 4, ESalle.NORMALE, etage); // Left adjency
        ISalle salle3 = new Salle(5, 4, ESalle.NORMALE, etage); // Right adjency
        ISalle salle4 = new Salle(4, 3, ESalle.ENTREE, etage); // Down ajdency
        ISalle salle5 = new Salle(4, 5, ESalle.ENTREE, etage); // Up adjency
        ISalle salle6 = new Salle(4, 4, ESalle.NORMALE, etage2); // Same coord but not the same floor (no adjency)
        ISalle salle7 = new Salle(3,4, ESalle.NORMALE, etage2); // Left adjency but not on the same floor (no adjency)

        assertTrue(salle1.estAdjacente(salle2));
        assertTrue(salle1.estAdjacente(salle3));
        assertTrue(salle1.estAdjacente(salle4));
        assertTrue(salle1.estAdjacente(salle5));
        assertFalse(salle1.estAdjacente(salle6));
        assertFalse(salle1.estAdjacente(salle7));
    }

    @Test
    public void testAreStairsAdjecent() {
        Etage etage1 = new Etage(1); // First floor
        Etage etage2 = new Etage(2); // Second floor
        Etage etage3 = new Etage(3); // Third floor
        
        ISalle salle1 = new Salle(4, 4, ESalle.ESCALIER_MONTANT, etage1); //Adjacent to salle2
        ISalle salle2 = new Salle(4, 4, ESalle.ESCALIER_DESCENDANT, etage2); //Adjecent to salle1
        ISalle salle3 = new Salle(4, 4, ESalle.ESCALIER_DESCENDANT, etage3); // No adjency (1 floor too high for salle1)
        ISalle salle4 = new Salle(4, 4, ESalle.SORTIE, etage2); // Not a stair
        ISalle salle5 = new Salle(4, 59, ESalle.ESCALIER_DESCENDANT, etage2); //Different coords

        assertTrue(salle1.estAdjacente(salle2)); // Superior adjacency
        assertTrue(salle2.estAdjacente(salle1)); // Inferior adjacency
        assertFalse(salle1.estAdjacente(salle3)); // Not the direct superior of inferior floor
        assertFalse(salle2.estAdjacente(salle3)); // No complementary stair type
        assertFalse(salle1.estAdjacente(salle4)); // Not a stair type
        assertFalse(salle1.estAdjacente(salle5)); // Not the same coords
    }
    
}
