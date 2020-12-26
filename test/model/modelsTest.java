/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import models.Component;
import models.Jobs;
import models.Permitmaster;
import models.Projects;
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
public class modelsTest {

    public modelsTest() {
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

    /**
     * Test of testReturnXmlForInsert, of class Users. this return only the xml
     * needed to insert
     */
    @Test
    public void Users_ReturnXmlForInsert() {
//        System.out.println("Users.testReturnXmlForInsert()");
//        Users instance = new Users(); //se instancia la clase
//        instance.setId_user("1");
//        instance.setCodeverification_user("xyz230420");
//        instance.setDateverification_user("04-23-2020");
//        instance.setEmail_user("anthony.pachay2017@uteq.edu.ec");
//        instance.setImg_user("userimg.png");
//        instance.setLastname_user("Pachay Espinoza");
//        instance.setNames_user("Anthony Abrahan");
//        instance.setPassword_user("superClaveQueVaEncriptada");
//        instance.setTypeuser_user("Admin");
//        assertEquals(
//                "<user>"
//                + "<names_user>Anthony Abrahan</names_user>"
//                + "<lastname_user>Pachay Espinoza</lastname_user>"
//                + "<email_user>anthony.pachay2017@uteq.edu.ec</email_user>"
//                + "<password_user>superClaveQueVaEncriptada</password_user>"
//                + "<codeverification_user>xyz230420</codeverification_user>"
//                + "<img_user>userimg.png</img_user>"
//                + "</user>",
//                instance.returnXmlForInsert());
    }

    /**
     * Test of returnXmlForActiveAccount, of class Users. this return the
     * necessary xml
     */
    @Test
    public void Users_returnXmlForActiveAccount() {
//        System.out.println("Users.returnXmlForActiveAccount()");
//        Users instance = new Users(); //se instancia la clase
//        instance.setCodeverification_user("xyz230420");
//        instance.setEmail_user("anthony.pachay2017@uteq.edu.ec");
//        System.out.println(instance.returnXmlForInsert());
//        assertEquals(
//                "<user>"
//                + "<email_user>anthony.pachay2017@uteq.edu.ec</email_user>"
//                + "<codeverification_user>xyz230420</codeverification_user>"
//                + "</user>",
//                instance.returnXmlForActiveAccount());
    }

    /**
     * Test of returnXmlForChangePwd, of class Users. this return the necessary
     * xml
     */
    @Test
    public void Users_returnXmlForChangePwd() {
//        System.out.println("Users.returnXmlForChangePwd()");
//        Users instance = new Users(); //se instancia la clase
//        instance.setCodeverification_user("xyz230420");
//        instance.setEmail_user("anthony.pachay2017@uteq.edu.ec");
//        instance.setPassword_user("superClaveQueVaEncriptada");
//        assertEquals(
//                "<user>"
//                + "<codeverification_user>xyz230420</codeverification_user>"
//                + "<email_user>anthony.pachay2017@uteq.edu.ec</email_user>"
//                + "<password_user>superClaveQueVaEncriptada</password_user>"
//                + "</user>",
//                instance.returnXmlForChangePwd());
    }

    /**
     * Test of returnXmlForGetVerifiCode, of class Users. this return only the
     * xml needed to insert
     */
    @Test
    public void Users_returnXmlForGetVerifiCode() {
//        System.out.println("Users.returnXmlForGetVerifiCode()");
//        Users instance = new Users(); //se instancia la clase
//        instance.setEmail_user("anthony.pachay2017@uteq.edu.ec");
//        instance.setPassword_user("superClaveQueVaEncriptada");
//        assertEquals(
//                "<user>"
//                + "<codeverification_user></codeverification_user>"
//                + "<email_user>anthony.pachay2017@uteq.edu.ec</email_user>"
//                + "</user>",
//                instance.returnXmlForGetVerifiCode());
    }

    /**
     * Test of testReturnXmlForInsert, of class Users. this return only the xml
     * needed to insert
     */
    @Test
    public void Users_returnXmlForInsertAPI() {
//        System.out.println("Users.returnXmlForInsertAPI()");
//        Users instance = new Users(); //se instancia la clase
//        instance.setImg_user("userimg.png");
//        instance.setEmail_user("anthony.pachay2017@uteq.edu.ec");
//        instance.setLastname_user("Pachay Espinoza");
//        instance.setNames_user("Anthony Abrahan");
//        instance.setPassword_user("superClaveQueVaEncriptada");
//        System.out.println(instance.returnXmlForInsertAPI());
//        assertEquals(
//                "<user>"
//                + "<names_user>Anthony Abrahan</names_user>"
//                + "<lastname_user>Pachay Espinoza</lastname_user>"
//                + "<email_user>anthony.pachay2017@uteq.edu.ec</email_user>"
//                + "<password_user>superClaveQueVaEncriptada</password_user>"
//                + "<img_user>userimg.png</img_user>"
//                + "</user>",
//                instance.returnXmlForInsertAPI());
    }

    /**
     * Test of testReturnXmlForInsert, of class Users. this return only the xml
     * needed to insert
     */
    @Test
    public void Users_returnXmlForUpdate() {
//        System.out.println("Users.testReturnXmlForInsert()");
//        Users instance = new Users(); //se instancia la clase
//        instance.setId_user("1");
//        instance.setEmail_user("anthony.pachay2017@uteq.edu.ec");
//        instance.setLastname_user("Pachay Espinoza");
//        instance.setNames_user("Anthony Abrahan");
//        instance.setPhone_user("0990218166");
//        assertEquals(
//                "<user>"
//                + "<id_user>1</id_user>"
//                + "<names_user>Anthony Abrahan</names_user>"
//                + "<lastname_user>Pachay Espinoza</lastname_user>"
//                + "<email_user>anthony.pachay2017@uteq.edu.ec</email_user>"
//                + "<phone_user>0990218166</phone_user>"
//                + "</user>",
//                instance.returnXmlForUpdate());
    }

    @Test
    public void Projects_returnXml() {
//        System.out.println("Projects_returnXml()");
//        Projects instance = new Projects();
//        instance.setCode_project("xyzjasdksd");
//        instance.setCreationdate_project("09-12-2020");
//        instance.setDescription_project("min_description");
//        instance.setId_pr("1");
//        instance.setName_project("SuperProj");
//        assertEquals(
//                "<projects>"
//                + "<id_pr>1</id_pr>"
//                + "<name_project>SuperProj</name_project>"
//                + "<description_project>min_description</description_project>"
//                + "<code_project>xyzjasdksd</code_project>"
//                + "<creationdate_project>09-12-2020</creationdate_project>"
//                + "</projects>",
//                 instance.returnXml());
    }

    @Test
    public void Projects_returnXmlForInsert() {
//        System.out.println("Projects_returnXmlForInsert()");
//        Projects instance = new Projects();
//        instance.setCode_project("xyzjasdksd");
//        instance.setCreationdate_project("09-12-2020");
//        instance.setDescription_project("min_description");
//        instance.setId_pr("1");
//        instance.setName_project("SuperProj");
//        assertEquals(
//                "<projects>"
//                + "<name_project>SuperProj</name_project>"
//                + "<description_project>min_description</description_project>"
//                + "<code_project>xyzjasdksd</code_project>"
//                + "<user_id>1</user_id>"
//                + "</projects>",
//                 instance.returnXmlForInsert("1"));
    }

    @Test
    public void Projects_returnXmlForconfirmShareProject() {
//        System.out.println("Projects_returnXmlForconfirmShareProject()");
//        Projects instance = new Projects();
//        instance.setCode_project("xyzjasdksd");
//        instance.setId_pr("1");
//        assertEquals(
//                "<projects>"
//                + "<code_project>xyzjasdksd</code_project>"
//                + "<user_id>1</user_id>"
//                + "</projects>",
//                 instance.returnXmlForconfirmShareProject("1"));
    }

    @Test
    public void Projects_returnXmlForshare() {
//        System.out.println("Projects_returnXmlForshare()");
//        Projects instance = new Projects();
//        instance.setId_pr("20");
//        assertEquals(
//                "<projects><id_pr>20</id_pr><users>1</users></projects>",
//                 instance.returnXmlForshare("1"));
    }

    @Test
    public void Permitmaster_returnXml() {
//        System.out.println("Permitmaster_returnXml()");
//        Permitmaster instance = new Permitmaster();
//        instance.setDescription_permitmaster("Permmit description");
//        instance.setId_pm("50");
//        instance.setJoinactive_permitmaster("true");
//        instance.setJoindate_permitmaster("09-15-2020");
//        instance.setProjects_id_pr("20");
//        instance.setRoot_permitmaster("true");
//        instance.setUsers_id_user("1");
////        System.out.println(instance.returnXml());
//        assertEquals(
//                "<permitmaster>"
//                + "<id_pm>50</id_pm>"
//                + "<description_permitmaster>Permmit description</description_permitmaster>"
//                + "<users_id_user>1</users_id_user>"
//                + "<projects_id_pr>20</projects_id_pr>"
//                + "<root_permitmaster>true</root_permitmaster>"
//                + "<joinactive_permitmaster>true</joinactive_permitmaster>"
//                + "<joindate_permitmaster>09-15-2020</joindate_permitmaster>"
//                + "</permitmaster>",
//                 instance.returnXml());
    }

    @Test
    public void Permitmaster_returnXmlForInsert() {
//        System.out.println("Permitmaster_returnXmlForInsert()");
//        Permitmaster instance = new Permitmaster();
//        instance.setDescription_permitmaster("Permmit description");
//        instance.setJoinactive_permitmaster("true");
//        instance.setJoindate_permitmaster("09-15-2020");
//        instance.setProjects_id_pr("20");
//        instance.setRoot_permitmaster("true");
//        instance.setUsers_id_user("1");
//        System.out.println(instance.returnXmlForInsert());
//        assertEquals(
//                "<permitmaster>"
//                + "<description_permitmaster>Permmit description</description_permitmaster>"
//                + "<users_id_user>1</users_id_user>"
//                + "<projects_id_pr>20</projects_id_pr>"
//                + "<root_permitmaster>true</root_permitmaster>"
//                + "<joinactive_permitmaster>true</joinactive_permitmaster>"
//                + "<joindate_permitmaster>09-15-2020</joindate_permitmaster>"
//                + "</permitmaster>",
//                 instance.returnXmlForInsert());
    }

    @Test
    public void Jobs_returnXmlForInsert() {
//        System.out.println("Jobs_returnXmlForInsert()");
//        Jobs instance = new Jobs();
//        instance.setCommit_job("this is a good project :3");
//        instance.setFilepath_job("archivito.txt");
//        instance.setName_job("SuperProj");
//        instance.setPermitmaster_id_pm("1");
//        instance.setProjects_id_pr("1");
//        assertEquals(
//                "<jobs>"
//                + "<name_job>SuperProj</name_job>"
//                + "<commit_job>this is a good project :3</commit_job>"
//                + "<permitmaster_id_pm>1</permitmaster_id_pm>"
//                + "<projects_id_pr>1</projects_id_pr>"
//                + "<filepath_job>archivito.txt</filepath_job>"
//                + "</jobs>",
//                 instance.returnXmlForInsert());
    }

    @Test
    public void Jobs_returnXml() {
//        System.out.println("Jobs_returnXml()");
//        Jobs instance = new Jobs();
//        instance.setCommit_job("this is a good project :3");
//        instance.setCreationdate_job("09-15-2020");
//        instance.setFilepath_job("archivito.txt");
//        instance.setFlagmodification_job("true");
//        instance.setId_job("1");
//        instance.setLastmodificacion_job("09-15-2020");
//        instance.setName_job("SuperProj");
//        instance.setPermitmaster_id_pm("1");
//        instance.setProjects_id_pr("1");
//        instance.setRoot_job("true");
//        instance.setStatus_job("true");
//        assertEquals(
//                "<jobs>"
//                + "<id_job>1</id_job>"
//                + "<name_job>SuperProj</name_job>"
//                + "<commit_job>this is a good project :3</commit_job>"
//                + "<creationdate_job>09-15-2020</creationdate_job>"
//                + "<lastmodification_job>09-15-2020</lastmodification_job>"
//                + "<status_job>true</status_job><"
//                + "permitmaster_id_pm>1</permitmaster_id_pm>"
//                + "<projects_id_pr>1</projects_id_pr>"
//                + "<flagmodification_job>true</flagmodification_job>"
//                + "<root_job>true</root_job>"
//                + "<filepath_job>archivito.txt</filepath_job>"
//                + "</jobs>",
//                 instance.returnXml());
    }

    @Test
    public void Component_returnXml() {
//        System.out.println("Component_returnXml()");
//        Component instance = new Component();
//        instance.setActive_component("true");
//        instance.setDateupload_component("09-15-2020");
//        instance.setDescription_component("this component is used to obtain the distance that exists with another object");
//        instance.setId_component("1");
//        instance.setName_component("Hc-sr04");
//        instance.setPathimg_component("urlimage");
//        instance.setPathparamports("{\"puerto\":[]}");
//        instance.setUsers_id_user("1");
//        assertEquals(
//                "<component>"
//                + "<id_component>1</id_component>"
//                + "<name_component>Hc-sr04</name_component>"
//                + "<description_component>this component is used to obtain the distance that exists with another object</description_component>"
//                + "<active_component>true</active_component>"
//                + "<users_id_user>1</users_id_user>"
//                + "<pathparamports>{\"puerto\":[]}</pathparamports>"
//                + "<dateupload_component>09-15-2020</dateupload_component>"
//                + "</component>",
//                 instance.returnXml());
    }

    @Test
    public void Component_returnXmlForInsert() {
//        System.out.println("Component_returnXmlForInsert()");
//        Component instance = new Component();
//        instance.setActive_component("true");
//        instance.setDateupload_component("09-15-2020");
//        instance.setDescription_component("this component is used to obtain the distance that exists with another object");
//        instance.setId_component("1");
//        instance.setName_component("Hc-sr04");
//        instance.setPathimg_component("urlimage");
//        instance.setPathparamports("{\"puerto\":[]}");
//        instance.setUsers_id_user("1");
//        assertEquals(
//                "<component>"
//                + "<name_component>Hc-sr04</name_component>"
//                + "<description_component>this component is used to obtain the distance that exists with another object</description_component>"
//                + "<active_component>true</active_component>"
//                + "<users_id_user>1</users_id_user>"
//                + "<pathimg_component>urlimage</pathimg_component>"
//                + "<pathparamports>{\"puerto\":[]}</pathparamports>"
//                + "</component>",
//                 instance.returnXmlForInsert());
    }

    
}
