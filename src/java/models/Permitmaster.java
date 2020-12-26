/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author jorgefaruk
 * This class refers to the master permissions that users have, which are stored in the database.
 */
public class Permitmaster {
    /** Get and set methods concerning User permissions, which are simple methods we use in classes to display (get) or modify (set) the value of an attribute.
     * Each of the get methods returns a string and los métodos set them a void, and the validation will be done on the controllers.
     */
    private String id_pm = "";
    private String description_permitmaster = "";
    private String users_id_user = "";
    private String projects_id_pr = "";
    private String root_permitmaster = "";
    private String joinactive_permitmaster = "";
    private String joindate_permitmaster = "";

    public Permitmaster() {
    }

    public String getId_pm() {
        return id_pm;
    }

    public void setId_pm(String id_pm) {
        this.id_pm = id_pm;
    }

    public String getDescription_permitmaster() {
        return description_permitmaster;
    }

    public void setDescription_permitmaster(String description_permitmaster) {
        this.description_permitmaster = description_permitmaster;
    }

    public String getUsers_id_user() {
        return users_id_user;
    }

    public void setUsers_id_user(String users_id_user) {
        this.users_id_user = users_id_user;
    }

    public String getProjects_id_pr() {
        return projects_id_pr;
    }

    public void setProjects_id_pr(String projects_id_pr) {
        this.projects_id_pr = projects_id_pr;
    }

    public String getRoot_permitmaster() {
        return root_permitmaster;
    }

    public void setRoot_permitmaster(String root_permitmaster) {
        this.root_permitmaster = root_permitmaster;
    }

    public String getJoinactive_permitmaster() {
        return joinactive_permitmaster;
    }

    public void setJoinactive_permitmaster(String joinactive_permitmaster) {
        this.joinactive_permitmaster = joinactive_permitmaster;
    }

    public String getJoindate_permitmaster() {
        return joindate_permitmaster;
    }

    public void setJoindate_permitmaster(String joindate_permitmaster) {
        this.joindate_permitmaster = joindate_permitmaster;
    }

    /** 
     * This function return the data in xml format for update in database
     * @return xml model of information
     */
    public String returnXml() {
        String result = String.format(
                "<permitmaster>"
                + "<id_pm>%s</id_pm>"
                + "<description_permitmaster>%s</description_permitmaster>"
                + "<users_id_user>%s</users_id_user>"
                + "<projects_id_pr>%s</projects_id_pr>"
                + "<root_permitmaster>%s</root_permitmaster>"
                + "<joinactive_permitmaster>%s</joinactive_permitmaster>"
                + "<joindate_permitmaster>%s</joindate_permitmaster>"
                + "</permitmaster>",
                getId_pm(), getDescription_permitmaster(), getUsers_id_user(), getProjects_id_pr(), getRoot_permitmaster(),
                getJoinactive_permitmaster(), getJoindate_permitmaster());
        return result;
    }

    /**
     * This function return the data in xml format for insert in database 
     * @return xml in string
     */
    public String returnXmlForInsert() {
        String result = String.format(
                "<permitmaster>"
                + "<description_permitmaster>%s</description_permitmaster>"
                + "<users_id_user>%s</users_id_user>"
                + "<projects_id_pr>%s</projects_id_pr>"
                + "<root_permitmaster>%s</root_permitmaster>"
                + "<joinactive_permitmaster>%s</joinactive_permitmaster>"
                + "<joindate_permitmaster>%s</joindate_permitmaster>"
                + "</permitmaster>",
                getDescription_permitmaster(), getUsers_id_user(), getProjects_id_pr(), getRoot_permitmaster(),
                getJoinactive_permitmaster(), getJoindate_permitmaster());
        return result;
    }

}
