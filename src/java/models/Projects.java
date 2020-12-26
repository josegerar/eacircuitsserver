/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author jorgefaruk
 * This class refers to the projects that may contain several IoT systems, which are stored in the database.
 */
public class Projects {
    
    /**
     * Get and set methods concerning projects that may contain several IoT systems, which are simple methods we use in classes to display (get) or modify (set) the value of an attribute.
     * Each of the get methods returns a string and los métodos set them a void, and the validation will be done on the controllers.
     */

    private String id_pr = "";
    private String name_project = "";
    private String description_project = "";
    private String code_project = "";
    private String creationdate_project = "";

    public Projects() {
    }

    public String getId_pr() {
        return id_pr;
    }

    public void setId_pr(String id_pr) {
        this.id_pr = id_pr;
    }

    public String getName_project() {
        return name_project;
    }

    public void setName_project(String name_project) {
        this.name_project = name_project;
    }

    public String getDescription_project() {
        return description_project;
    }

    public void setDescription_project(String description_project) {
        this.description_project = description_project;
    }

    public String getCode_project() {
        return code_project;
    }

    public void setCode_project(String code_project) {
        this.code_project = code_project;
    }

    public String getCreationdate_project() {
        return creationdate_project;
    }

    public void setCreationdate_project(String creationdate_project) {
        this.creationdate_project = creationdate_project;
    }

    /**
     * This function return the data in xml format for updates in database
     * This XML is to make updates in the database concerning the projects that may contain several IoT systems
     * @return xml in string 
     */
    public String returnXml() {
        String result = String.format(
                "<projects>"
                + "<id_pr>%s</id_pr>"
                + "<name_project>%s</name_project>"
                + "<description_project>%s</description_project>"
                + "<code_project>%s</code_project>"
                + "<creationdate_project>%s</creationdate_project>"
                + "</projects>",
                getId_pr(), getName_project(), getDescription_project(), getCode_project(), getCreationdate_project());
        return result;
    }

   /**
     * This function return the data in xml format for insert in database 
     * @param user indique the user that are creating this proyect
     * @return xml in string
     */
    public String returnXmlForInsert(String user) {
        String result = String.format(
                "<projects>"
                + "<name_project>%s</name_project>"
                + "<description_project>%s</description_project>"
                + "<code_project>%s</code_project>"
                +"<user_id>%s</user_id>"
                + "</projects>",
                getName_project(), getDescription_project(), getCode_project()
                ,user);
        return result;
    }
    
    /** Method that returns an xml to share.
     * @param emails String type variable, contains the user's email.
     * @return It returns a String, with an XML structure.
     */
    public String returnXmlForshare(String emails) {
        String result = String.format(
                "<projects>"
                +"<id_pr>%s</id_pr>"
                +"<users>%s</users>"
                + "</projects>",
                getId_pr(),emails);
        return result;
    }
    /** return xml for confirm share project
     * @param id String type variable, contains the project identifier.
     * @return It returns a String, with an XML structure.
     */

     public String returnXmlForconfirmShareProject(String id) {
        String result = String.format(
                "<projects>"
                +"<code_project>%s</code_project>"
                +"<user_id>%s</user_id>"
                + "</projects>",
                getCode_project(),id);
        return result;
    }
}