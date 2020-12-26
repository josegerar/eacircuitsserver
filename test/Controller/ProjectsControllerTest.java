/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DataStaticBD.Methods;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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
public class ProjectsControllerTest {
    
    public ProjectsControllerTest() {
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
    // @Test
    // public void hello() {}
    
    @Test
    public void CreateProject()
    {
//        ProjectsController instance = new ProjectsController();
//        String[] res = instance.CreateProject("NowProject", "This is description about the project", "23");
//        System.out.println(res[0]);
//        System.out.println(res[1]);
//        System.out.println(res[2]);
//        assertEquals(res[0], "2");
    }
    
    @Test
    public void getProjects()
    {
//        System.out.println("getProjects");
//        ProjectsController instance = new ProjectsController();
//        String[] res = instance.getProjects("2");
//        System.out.println(res[0]);
//        System.out.println(res[1]);
//        System.out.println(res[2]);
//        assertEquals(res[0], "2");
    }
    
    @Test
    public void shareProject()
    {
//        System.out.println("shareProject");
//        ProjectsController instance = new ProjectsController();
//        JsonObject Jso = Methods.stringToJSON("{\"emails\":[{\"email\":\"tonypachay@gmai.com\",\"permit\":\"ADMIN\"},{\"email\":\"tonypachay@gmai.com\",\"permit\":\"ADMIN\"}]}");
//        JsonArray jarr = Methods.JsonToArray(Jso, "emails");
//        
//        String[] res = instance.shareProject(jarr, "2");
//        sleep(15);
//        System.out.println(res[0]);
//        System.out.println(res[1]);
//        System.out.println(res[2]);
//        assertEquals(res[0], "2");
    }
    
    @Test
    public void getLoadShareProjectsForConfirm()
    {
//        System.out.println("getLoadShareProjectsForConfirm");
//        ProjectsController instance = new ProjectsController();        
//        String[] res = instance.getLoadShareProjectsForConfirm("1");
//        System.out.println(res[0]);
//        System.out.println(res[1]);
//        System.out.println(res[2]);
//        assertEquals(res[0], "2");
    }
    
    @Test
    public void getLoadShareProjectsConfirm()
    {
//        System.out.println("getLoadShareProjectsForConfirm");
//        ProjectsController instance = new ProjectsController();        
//        String[] res = instance.getLoadShareProjectsConfirm("1");
//        System.out.println(res[0]);
//        System.out.println(res[1]);
//        System.out.println(res[2]);
//        assertEquals(res[0], "2");
    }
    
    @Test
    public void confirmShareProject()
    {
//        System.out.println("confirmShareProject");
//        ProjectsController instance = new ProjectsController();        
//        String[] res = instance.confirmShareProject("ZSDVEMIJASDJQWJDIIXS","1");
//        System.out.println(res[0]);
//        System.out.println(res[1]);
//        System.out.println(res[2]);
//        assertEquals(res[0], "2");
    }
    
    @Test
    public void saveSystem()
    {
//        System.out.println("saveSystem");
//        ProjectsController instance = new ProjectsController();        
//        String[] res = instance.saveSystem("NewSystem", "Description", "20", "2", "newsystem01.text", "D:\\6_programacion\\repositorio_grupal\\eacircuitsserver");
//        System.out.println(res[0]);
//        System.out.println(res[1]);
//        System.out.println(res[2]);
//        assertEquals(res[0], "2");
    }
    
    @Test
    public void getJobs()
    {
//        System.out.println("getJobs");
//        ProjectsController instance = new ProjectsController();        
//        String[] res = instance.getJobs("1","20");
//        System.out.println(res[0]);
//        System.out.println(res[1]);
//        System.out.println(res[2]);
//        assertEquals(res[0], "2");
    }
    
    @Test
    public void loadSystemIOT()
    {
//        System.out.println("loadSystemIOT");
//        ProjectsController instance = new ProjectsController();        
//        String[] res = instance.loadSystemIOT("2", "D:\\6_programacion\\repositorio_grupal\\eacircuitsserver");
//        System.out.println(res[0]);
//        System.out.println(res[1]);
//        System.out.println(res[2]);
//        assertEquals(res[0], "2");
    }
    
    @Test
    public void saveSystemTotal()
    {
//        System.out.println("saveSystemTotal");
//        ProjectsController instance = new ProjectsController();        
//        String[] res = instance.saveSystemTotal("2", "{}","D:\\6_programacion\\repositorio_grupal\\eacircuitsserver");
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
