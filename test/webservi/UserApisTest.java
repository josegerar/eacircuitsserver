/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservi;

import DataStaticBD.Methods;
import com.google.gson.JsonObject;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import javax.json.Json;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
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
public class UserApisTest {

    public UserApisTest() {
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

    public String analizeWebService(String data, String path, String serviceName) {
        WebTarget webTarget;
        Client client;
        String BASE_URI = "http://localhost:8080/eacircuitsserver/webresources";
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path(path);

        Response r = webTarget.path(serviceName).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(data, javax.ws.rs.core.MediaType.APPLICATION_JSON), Response.class);
        String result = r.readEntity(String.class);
        System.out.println(r.getStatus());
        System.out.println(result);
        JsonObject jso = Methods.stringToJSON(result);
        return Methods.JsonToString(jso, "status", "4");
    }
       
    private void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testlogin() throws UnsupportedEncodingException, MalformedURLException {
//        javax.json.JsonObject json = Json.createObjectBuilder()
//                .add("usr", "tonypachay@gmail.com")
//                .add("pwd", "123")
//                .build();
//        String data = json.toString();
//        assertEquals(analizeWebService(data, "userApis", "login"), "2");
    }

    @Test
    public void createuser() throws UnsupportedEncodingException, MalformedURLException {
//        javax.json.JsonObject json = Json.createObjectBuilder()
//                .add("name", "Dúval Ricardo")
//                .add("lastname", "Carvajal Suarez")
//                .add("email", "duval.carvajal2017@uteq.edu.ec")
//                .add("pass", "123")
//                .build();
//        String data = json.toString();
//        assertEquals(analizeWebService(data, "userApis", "createuser"), "2");
    }

    @Test
    public void activeaccount() throws UnsupportedEncodingException, MalformedURLException {
//        javax.json.JsonObject json = Json.createObjectBuilder()
//                .add("token", "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5IiwiaWF0IjoxNTk5MTIzNDY0LCJleHAiOjE1OTkxMjQzNjR9.-segiN6viOZXxWFSpNa_ZubigbnCTezJN7BriU3-hsE")
//                .add("email", "duval.carvajal2017@uteq.edu.ec")
//                .build();
//        String data = json.toString();
//        assertEquals(analizeWebService(data, "userApis", "activeaccount"), "2");
    }

    @Test
    public void resendmeCodeverify() throws UnsupportedEncodingException, MalformedURLException {
//        javax.json.JsonObject json = Json.createObjectBuilder()
//                .add("token", "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5IiwiaWF0IjoxNTk5MTIzNDY0LCJleHAiOjE1OTkxMjQzNjR9.-segiN6viOZXxWFSpNa_ZubigbnCTezJN7BriU3-hsE")
//                .add("email", "duval.carvajal2017@uteq.edu.ec")
//                .build();
//        String data = json.toString();
//        assertEquals(analizeWebService(data, "userApis", "resendmeCodeverify"), "2");
    }

    @Test
    public void getdatasession() throws UnsupportedEncodingException, MalformedURLException {
//        javax.json.JsonObject json = Json.createObjectBuilder()
//                .add("token", "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5IiwiaWF0IjoxNTk5MTIzNDY0LCJleHAiOjE1OTkxMjQzNjR9.-segiN6viOZXxWFSpNa_ZubigbnCTezJN7BriU3-hsE")
//                .build();
//        String data = json.toString();
//        assertEquals(analizeWebService(data, "userApis", "getdatasession"), "2");
    }

    @Test
    public void saveProject() throws UnsupportedEncodingException, MalformedURLException {
//        javax.json.JsonObject json = Json.createObjectBuilder()
//                .add("token", "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5IiwiaWF0IjoxNTk5MTIzNDY0LCJleHAiOjE1OTkxMjQzNjR9.-segiN6viOZXxWFSpNa_ZubigbnCTezJN7BriU3-hsE")
//                .add("nameProject", "NewProject")
//                .add("descriptionProjec", "A new superProject about systems IoT.")
//                .build();
//        String data = json.toString();
//        assertEquals(analizeWebService(data, "projectsApis", "saveProject"), "2");
    }

    @Test
    public void getProjects() throws UnsupportedEncodingException, MalformedURLException {
//        javax.json.JsonObject json = Json.createObjectBuilder()
//                .add("token", "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5IiwiaWF0IjoxNTk5MTIzNDY0LCJleHAiOjE1OTkxMjQzNjR9.-segiN6viOZXxWFSpNa_ZubigbnCTezJN7BriU3-hsE")
//                .build();
//        String data = json.toString();
//        assertEquals(analizeWebService(data, "projectsApis", "getProjects"), "2");
    }

    @Test
    public void getjobs() throws UnsupportedEncodingException, MalformedURLException {
//        javax.json.JsonObject json = Json.createObjectBuilder()
//                .add("token", "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5IiwiaWF0IjoxNTk5MTIzNDY0LCJleHAiOjE1OTkxMjQzNjR9.-segiN6viOZXxWFSpNa_ZubigbnCTezJN7BriU3-hsE")
//                .add("projectId", "1")
//                .build();
//        String data = json.toString();
//        assertEquals(analizeWebService(data, "projectsApis", "getjobs"), "2");
    }

    @Test
    public void loadSystemIOT() throws UnsupportedEncodingException, MalformedURLException {
//        javax.json.JsonObject json = Json.createObjectBuilder()
//                .add("token", "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5IiwiaWF0IjoxNTk5MTIzNDY0LCJleHAiOjE1OTkxMjQzNjR9.-segiN6viOZXxWFSpNa_ZubigbnCTezJN7BriU3-hsE")
//                .add("tokenidjob", "QWUEWEOOPMCDMDNDJDJAISXNZCOWSXKNSCAUIBCIAUSBCASC")
//                .build();
//        String data = json.toString();
//        assertEquals(analizeWebService(data, "projectsApis", "loadSystemIOT"), "2");
    }

    @Test
    public void getComponentesActive() throws UnsupportedEncodingException, MalformedURLException {
//        javax.json.JsonObject json = Json.createObjectBuilder()
//                .add("token", "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5IiwiaWF0IjoxNTk5MTIzNDY0LCJleHAiOjE1OTkxMjQzNjR9.-segiN6viOZXxWFSpNa_ZubigbnCTezJN7BriU3-hsE")
//                .build();
//        String data = json.toString();
//        assertEquals(analizeWebService(data, "components", "getComponentesActive"), "2");
    }

    @Test
    public void getComponents() throws UnsupportedEncodingException, MalformedURLException {
//        javax.json.JsonObject json = Json.createObjectBuilder()
//                .add("token", "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5IiwiaWF0IjoxNTk5MTIzNDY0LCJleHAiOjE1OTkxMjQzNjR9.-segiN6viOZXxWFSpNa_ZubigbnCTezJN7BriU3-hsE")
//                .build();
//        String data = json.toString();
//        assertEquals(analizeWebService(data, "components", "getComponents"), "2");
    }

    @Test
    public void saveComponent() throws UnsupportedEncodingException, MalformedURLException {
//        javax.json.JsonObject json = Json.createObjectBuilder()
//                .add("token", "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5IiwiaWF0IjoxNTk5MTIzNDY0LCJleHAiOjE1OTkxMjQzNjR9.-segiN6viOZXxWFSpNa_ZubigbnCTezJN7BriU3-hsE")
//                .add("nameComponent","Hc-sr04")
//                .add("descriptionComponent", "this component is used to obtain the distance that exists with another object")
//                .add("activeComponent", "true")
//                .add("pathImgComponent", "https://ci5.googleusercontent.com/proxy/CUIpfzAuC5omvSzYuEsePhKeOJUT1oWHSlaZRhBFjn1Hc7ViT4Hzo5HEesxHy0_hRem4Epj_N1tVnfAvANizWLeJWi6cN4lHWuno3pQL39wZBPc5AY0ih-VPEyPspClwRzGehTrSgDyhmR0=s0-d-e1-ft#https://res.cloudinary.com/dhgrt80hq/image/upload/v1600059875/public/Group_15_l0wulg.png")
//                .add("dataPorts", "{}")
//                .build();
//        String data = json.toString();
//        assertEquals(analizeWebService(data, "components", "saveComponent"), "2");
    }
    
}
