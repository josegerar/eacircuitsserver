/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.UserController;
import DataStaticBD.Methods;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import javax.json.Json;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import models.Users;
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
public class UserControllerTest {

    public UserControllerTest() {
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
    public void comprobeEmail() {
        
        assertTrue(Methods.comprobeEmail("tonypachay@gmail.com"));
    }
    /**
     * This method serves to test the effective login of a user using jUnit
     */
    @Test
    public void testLogIn() {
//        System.out.println("testLogIn");
//        UserController instance = new UserController(); //se instancia la clase
//        String[] res = instance.LogIn("tonypachay@gmail.com", "123");
//        System.out.println(res[0]);
//        System.out.println(res[1]);
//        System.out.println(res[2]);
//        assertEquals(res[0], "2");
    }

    /**
     * This method is used to test the create user method of a user using jUnit
     */
    @Test
    public void testCreateUser() {
//        System.out.println("testCreateUser");
//        UserController instance = new UserController(); //se instancia la clase
//        String[] res = instance.createUser("Vivian", "Zamora", "vivian.zamora2017@uteq.edu.ec", "123");
//        sleep(10);
//        System.out.println(res[0]);
//        System.out.println(res[1]);
//        System.out.println(res[2]);
//        assertEquals(res[0], "5");//for redirect to confirm page
    }

    /**
     * This method is used to test the create user from api method of a user
     * using jUnit
     */
    @Test
    public void createUserFromAPI() {
//        System.out.println("createUserFromAPI");
//        UserController instance = new UserController(); //se instancia la clase
//        String data = String.format("{\"name\":\"%s\","
//                + "\"lastname\":\"%s\","
//                + "\"email\":\"%s\","
//                + "\"pass\":\"%s\","
//                + "\"img\":\"%s\"}"
//                ,"María Angelica", "Ricardez", "mricardez@gmail.com", "123","");
//        String[] res = instance.createUserFromAPI(data);
//        
//        System.out.println(res[0]);
//        System.out.println(res[1]);
//        System.out.println(res[2]);
//        assertEquals(res[0], "2");
    }

    /**
     * This method is used to test the update user method of a user using jUnit
     */
    @Test
    public void updateUser() {
//        System.out.println("updateUser");
//        UserController instance = new UserController(); //se instancia la clase
//        
//        String[] res = instance.updateUser("Anthony Abrahan", "Pachay Espinoza", "apachay@uteq.edu.ec", "593990218166", "3");
//        
//        System.out.println(res[0]);
//        System.out.println(res[1]);
//        System.out.println(res[2]);
//        assertEquals(res[0], "2");
    }

    /**
     * This method is used to test the activeAccount user method of a user using jUnit
     * This method performs the verification of an account.
     */
    @Test
    public void activeAccount() {
//        System.out.println("activeAccount");
//        UserController instance = new UserController(); //se instancia la clase
//        String[] res = instance.activeAccount("cZDcZDytWOgAtasoqtoTcMTRfhdYHQONAIFT", "anthony.pachay2017@uteq.edu.ec");
//        System.out.println(res[0]);
//        System.out.println(res[1]);
//        System.out.println(res[2]);
//        assertEquals(res[0], "2");
    }
    /**
     * This method is used to test the requestCodeForChangePassword user method of a user using jUnit
     * This method sends a code to the user's mail, with which he can reset the password.
     */
    @Test
    public void requestCodeForChangePassword() {
//        System.out.println("requestCodeForChangePassword");
//        UserController instance = new UserController(); //se instancia la clase
//
//        String[] res = instance.requestCodeForChangePassword("vivian.zamora2017@uteq.edu.ec");
//        sleep(15);
//        System.out.println(res[0]);
//        System.out.println(res[1]);
//        System.out.println(res[2]);
//        assertEquals(res[0], "2");
    }
    @Test
    public void confirmPwd() {
//        System.out.println("confirmPwd");
//        UserController instance = new UserController(); //se instancia la clase
//
//        String[] res = instance.confirmPwd("vivian.zamora2017@uteq.edu.ec","ZQHDSACASOCASCASC","123");
//        sleep(15);
//        System.out.println(res[0]);
//        System.out.println(res[1]);
//        System.out.println(res[2]);
//        assertEquals(res[0], "2");
    }
    @Test
    public void resendMeCodeVerify() {
//        System.out.println("resendMeCodeVerify");
//        UserController instance = new UserController(); //se instancia la clase
//
//        String[] res = instance.resendMeCodeVerify("vivian.zamora2017@uteq.edu.ec");
//        sleep(15);
//        System.out.println(res[0]);
//        System.out.println(res[1]);
//        System.out.println(res[2]);
//        assertEquals(res[0], "2");
    }
    
    
    

    /**This method is used to sleep the main thread
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
