/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author jcs
 */
public class CollisionDetectorTest {

    public CollisionDetectorTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }


    /**
     * Test of collides method, of class CollisionDetector.
     */
    @Test
    public void testCollides() {
        System.out.println("collides");
        Entity entity = new Entity();
        Entity entity2 = new Entity();
        entity.add(new PositionPart(1,1,3));
        entity2.add(new PositionPart(1,1,3));
        entity.setRadius(2);
        entity2.setRadius(2);
        CollisionDetector instance = new CollisionDetector();
        Boolean expResult = true;
        Boolean result = instance.collides(entity, entity2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

}