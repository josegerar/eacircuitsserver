/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author tonyp
 * This class refers to the Registered users, which are stored in the database.
 */
public class Users {
    
    /**
     * Get and set methods concerning Users of IoT systems, which are simple methods we use in classes to display (get) or modify (set) the value of an attribute.
     * Each of the get methods returns a string and los métodos set them a void, and the validation will be done on the controllers.
     */
    
    private String id_user = "";
    private String names_user = "";
    private String lastname_user = "";
    private String email_user = "";
    private String password_user = "";
    private String img_user = "";
    private String codeverification_user = "";
    private String dateverification_user = "";
    private String typeuser_user = "";
    private String phone_user = "";

    public Users() {
    }

    public Users(String names_user, String lastname_user, String codeverification_user, String email_user) {
        this.names_user = names_user;
        this.lastname_user = lastname_user;
        this.codeverification_user = codeverification_user;
        this.email_user = email_user;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getNames_user() {
        return names_user;
    }

    public void setNames_user(String names_user) {
        this.names_user = names_user;
    }

    public String getLastname_user() {
        return lastname_user;
    }

    public void setLastname_user(String lastname_user) {
        this.lastname_user = lastname_user;
    }

    public String getEmail_user() {
        return email_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
    }

    public String getPassword_user() {
        return password_user;
    }

    public void setPassword_user(String password_user) {
        this.password_user = password_user;
    }

    public String getImg_user() {
        return img_user;
    }

    public void setImg_user(String img_user) {
        this.img_user = img_user;
    }

    public String getCodeverification_user() {
        return codeverification_user;
    }

    public void setCodeverification_user(String codeverification_user) {
        this.codeverification_user = codeverification_user;
    }

    public String getDateverification_user() {
        return dateverification_user;
    }

    public void setDateverification_user(String dateverification_user) {
        this.dateverification_user = dateverification_user;
    }

    public String getTypeuser_user() {
        return typeuser_user;
    }

    public void setTypeuser_user(String typeuser_user) {
        this.typeuser_user = typeuser_user;
    }

    public String getPhone_user() {
        return phone_user;
    }

    public void setPhone_user(String phone_user) {
        this.phone_user = phone_user;
    }
    /**This function return the data in xml format
     * fro registered users
     * This XML is to make insertions in the database concerning Users of IoT systems
     * @return xml in String
     */
    public String returnXmlForInsert() {
        String result = String.format(
            "<user><names_user>%s</names_user>"
                + "<lastname_user>%s</lastname_user>"
                + "<email_user>%s</email_user>"
                + "<password_user>%s</password_user>"
                + "<codeverification_user>%s</codeverification_user>"
                + "<img_user>%s</img_user>"
            + "</user>",
                getNames_user(), getLastname_user(), getEmail_user(), 
                getPassword_user(), getCodeverification_user(), getImg_user());
        return result;
    }
    
    /**
     * This function return the data in xml format
     * fro registered users from api :3
     * This XML is to make insertions in the database concerning Users of IoT systems
     * @return xml in string
     */
    public String returnXmlForInsertAPI() {
        String result = String.format(
            "<user><names_user>%s</names_user>"
                + "<lastname_user>%s</lastname_user>"
                + "<email_user>%s</email_user>"
                + "<password_user>%s</password_user>"
                + "<img_user>%s</img_user>"
            + "</user>",
                getNames_user(), getLastname_user(), getEmail_user(), 
                getPassword_user(), getImg_user());
        return result;
    }
    
   /**
     * This function return the data in xml format
     * for insert in database :3
     * This XML is to make updates in the database concerning Users of IoT systems
     * @return xml in string
     */
    public String returnXmlForUpdate() {
        String result = String.format(
                "<user>"
                    + "<id_user>%s</id_user>"
                    + "<names_user>%s</names_user>"
                    + "<lastname_user>%s</lastname_user>"
                    + "<email_user>%s</email_user>"
                    + "<phone_user>%s</phone_user>"
                    + "<img_user>%s</img_user>"
                + "</user>",
                getId_user(), getNames_user(), getLastname_user(), getEmail_user(), 
                getPhone_user(), getImg_user());
        return result;
    }
    
   /**
     * This function return the data in xml format
     * for active your account
     * @return xml in string
     */
    public String returnXmlForActiveAccount() {
        String result = String.format(
                "<user>"
                    + "<email_user>%s</email_user>"
                    + "<codeverification_user>%s</codeverification_user>"
                + "</user>",
                getEmail_user(),
                getCodeverification_user());
        System.out.println(result);
        return result;
    }
    
    /**
     * This function return the data in xml format
     * for request other verifyCode
     * @return xml in string
     */
    public String returnXmlForGetVerifiCode() {
        String result = String.format(
                "<user>"
                    + "<codeverification_user>%s</codeverification_user>"
                    +"<email_user>%s</email_user>"
                + "</user>",
                getCodeverification_user(),getEmail_user());
        System.out.println(result);
        return result;
    }
    
    /**
     * This function return the data in xml format
     * for change user password
     * @return xml in string
     */
    public String returnXmlForChangePwd() {
        String result = String.format(
                "<user>"
                    + "<codeverification_user>%s</codeverification_user>"
                    +"<email_user>%s</email_user>"
                    +"<password_user>%s</password_user>"
                + "</user>",
                getCodeverification_user(),getEmail_user(),getPassword_user());
        System.out.println(result);
        return result;
    }
}
