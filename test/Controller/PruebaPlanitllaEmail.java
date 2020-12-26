/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DataStaticBD.TemplateEmail;
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
public class PruebaPlanitllaEmail {

    public PruebaPlanitllaEmail() {
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
    public void hello() {
        String allemails = "tonypachay@gmail.com cisu-79@hotmail.com jorge.molina2015@uteq.edu.ec 	";
        TemplateEmail templa = new TemplateEmail();
        templa.collaborativeWork(allemails, "ZYEXZSDASD");
        
        sleep(8);
        assertEquals("1", "1");
    }

    /**
     * This method is used to sleep the main thread
     *
     * @param seconds is the numbre of seconds that gets.
     */
    private void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
