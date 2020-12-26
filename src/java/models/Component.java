/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author tonyp
 * This class refers to the components of IoT system, which are stored in the database.
 */
public class Component {
    
    /**
     * Get and set methods concerning components of IoT System, which are simple methods we use in classes to display (get) or modify (set) the value of an attribute.
     * Each of the get methods returns a string and los métodos set them a void, and the validation will be done on the controllers.
     */
    
    private String id_component = "";
    private String name_component = "";
    private String description_component = "";
    private String active_component = "";
    private String users_id_user = "";
    private String pathimg_component = "";
    private String pathparamports = "";
    private String dateupload_component = "";

    public Component() {
    }

    public String getId_component() {
        return id_component;
    }

    public void setId_component(String id_component) {
        this.id_component = id_component;
    }

    public String getName_component() {
        return name_component;
    }

    public void setName_component(String name_component) {
        this.name_component = name_component;
    }

    public String getDescription_component() {
        return description_component;
    }

    public void setDescription_component(String description_component) {
        this.description_component = description_component;
    }

    public String getActive_component() {
        return active_component;
    }

    public void setActive_component(String active_component) {
        this.active_component = active_component;
    }

    public String getUsers_id_user() {
        return users_id_user;
    }

    public void setUsers_id_user(String users_id_user) {
        this.users_id_user = users_id_user;
    }

    public String getPathimg_component() {
        return pathimg_component;
    }

    public void setPathimg_component(String pathimg_component) {
        this.pathimg_component = pathimg_component;
    }

    public String getPathparamports() {
        return pathparamports;
    }

    public void setPathparamports(String pathparamports) {
        this.pathparamports = pathparamports;
    }

    public String getDateupload_component() {
        return dateupload_component;
    }

    public void setDateupload_component(String dateupload_component) {
        this.dateupload_component = dateupload_component;
    }
    /**This XML is to make updates in the database concerning the components of  IoT system
      * @return Returns a String as XML  
      */
    public String returnXml()
    {
        String xml = String.format(
                "<component>"
                    +"<id_component>%s</id_component>"
                    +"<name_component>%s</name_component>"
                    +"<description_component>%s</description_component>"
                    +"<active_component>%s</active_component>"
                    +"<users_id_user>%s</users_id_user>"
                    +"<pathparamports>%s</pathparamports>"
                    +"<dateupload_component>%s</dateupload_component>"
                +"</component>",
                getId_component(), getName_component(), getDescription_component(),
                getActive_component(), getUsers_id_user(), getPathparamports(),
                getDateupload_component()
                );
        return xml;
    }
    /** This function return the data in xml format for insert in database
     * This XML is to make insertions in the database concerning the components of  IoT system
     * @return xml in string 
     */
    public String returnXmlForInsert()
    {
        String xml = String.format(
                "<component>"
                    +"<name_component>%s</name_component>"
                    +"<description_component>%s</description_component>"
                    +"<active_component>%s</active_component>"
                    +"<users_id_user>%s</users_id_user>"
                    +"<pathimg_component>%s</pathimg_component>"
                    +"<pathparamports>%s</pathparamports>"
                +"</component>",
                getName_component(), getDescription_component(),
                getActive_component(), getUsers_id_user(), 
                getPathimg_component(), getPathparamports()
                );
        System.out.println(xml);
        return xml;
    }
}
