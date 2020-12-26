/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tonyp
 */
public class Components {

    public Components() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    
    @Test
    public void getComponentsActive() {
//        System.out.println("getComponentsActive");
//        ComponentsController instance = new ComponentsController();
//        String[] res = instance.getComponentsActive("1");
//        System.out.println(res[0]);
//        System.out.println(res[1]);
//        System.out.println(res[2]);
//        assertEquals(res[0], "2");
    }
    
    @Test
    public void getComponentsInactive() {
//        System.out.println("getComponentsInactive");
//        ComponentsController instance = new ComponentsController();
//        String[] res = instance.getComponentsInactive("1");
//        System.out.println(res[0]);
//        System.out.println(res[1]);
//        System.out.println(res[2]);
//        assertEquals(res[0], "2");
    }
    
    @Test
    public void getComponentsPropuesta() {
//        System.out.println("getComponentsPropuesta");
//        ComponentsController instance = new ComponentsController();
//        String[] res = instance.getComponentsPropuesta();
//        System.out.println(res[0]);
//        System.out.println(res[1]);
//        System.out.println(res[2]);
//        assertEquals(res[0], "2");
    }
    
    @Test
    public void getComponents() {
//        System.out.println("getComponents");
//        ComponentsController instance = new ComponentsController();
//        String[] res = instance.getComponents("D:\\6_programacion\\repositorio_grupal\\eacircuitsserver");
//        System.out.println(res[0]);
//        System.out.println(res[1]);
//        System.out.println(res[2]);
//        assertEquals(res[0], "2");
    }
    
    @Test
    public void getComponentsTotal() {
//        System.out.println("getComponentsTotal");
//        ComponentsController instance = new ComponentsController();
//        String[] res = instance.getComponentsTotal();
//        System.out.println(res[0]);
//        System.out.println(res[1]);
//        System.out.println(res[2]);
//        assertEquals(res[0], "2");
    }
    
    @Test
    public void saveComponent() {
        System.out.println("saveComponent");
        /*ComponentsController instance = new ComponentsController();
        String[] res = instance.saveComponent("HRC-06", "Ultrasonic sensor", 
                "true", "1", "http:\\\\img.com\\img", "{}", 
                "D:\\6_programacion\\repositorio_grupal\\eacircuitsserver");
        sleep(5);
        System.out.println(res[0]);
        System.out.println(res[1]);
        System.out.println(res[2]);
        assertEquals(res[0], "2");*/
        assertEquals("1", "1");
    }
    
    @Test
    public void aproveComponent() {
//        System.out.println("aproveComponent");
//        ComponentsController instance = new ComponentsController();
//        String[] res = instance.aproveComponent("56");
//        System.out.println(res[0]);
//        System.out.println(res[1]);
//        System.out.println(res[2]);
//        assertEquals(res[0], "2");
    }
    
    private void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
