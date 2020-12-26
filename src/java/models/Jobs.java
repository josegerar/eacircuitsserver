/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author jorgefaruk
 * This class refers to the jobs or IoT systems, which are stored in the database.
 */
public class Jobs {
    /**
     * Get and set methods concerning jobs or IoT systems, which are simple methods we use in classes to display (get) or modify (set) the value of an attribute.
     * Each of the get methods returns a string and los métodos set them a void, and the validation will be done on the controllers.
     */

    private String id_job = "";
    private String name_job = "";
    private String commit_job = "";
    private String creationdate_job = "";
    private String lastmodification_job = "";
    private String status_job = "";
    private String permitmaster_id_pm = "";
    private String projects_id_pr = "";
    private String flagmodification_job = "";
    private String root_job = "";
    private String filepath_job = "";

    public Jobs() {
    }

    public String getId_job() {
        return id_job;
    }

    public void setId_job(String id_job) {
        this.id_job = id_job;
    }

    public String getName_job() {
        return name_job;
    }

    public void setName_job(String name_job) {
        this.name_job = name_job;
    }

    public String getCommit_job() {
        return commit_job;
    }

    public void setCommit_job(String commit_job) {
        this.commit_job = commit_job;
    }

    public String getCreationdate_job() {
        return creationdate_job;
    }

    public void setCreationdate_job(String creationdate_job) {
        this.creationdate_job = creationdate_job;
    }

    public String getLastmodificacion_job() {
        return lastmodification_job;
    }

    public void setLastmodificacion_job(String lastmodificacion_job) {
        this.lastmodification_job = lastmodificacion_job;
    }

    public String getStatus_job() {
        return status_job;
    }

    public void setStatus_job(String status_job) {
        this.status_job = status_job;
    }

    public String getPermitmaster_id_pm() {
        return permitmaster_id_pm;
    }

    public void setPermitmaster_id_pm(String permitmaster_id_pm) {
        this.permitmaster_id_pm = permitmaster_id_pm;
    }

    public String getProjects_id_pr() {
        return projects_id_pr;
    }

    public void setProjects_id_pr(String projects_id_pr) {
        this.projects_id_pr = projects_id_pr;
    }

    public String getFlagmodification_job() {
        return flagmodification_job;
    }

    public void setFlagmodification_job(String flagmodification_job) {
        this.flagmodification_job = flagmodification_job;
    }

    public String getRoot_job() {
        return root_job;
    }

    public void setRoot_job(String root_job) {
        this.root_job = root_job;
    }

    public String getFilepath_job() {
        return filepath_job;
    }

    public void setFilepath_job(String filepath_job) {
        this.filepath_job = filepath_job;
    }

    /** This function return the data in xml format for insert in database
     * @return xml in string
     */
    public String returnXmlForInsert() {
        String result = String.format(
                "<jobs>"
                + "<name_job>%s</name_job>"
                + "<commit_job>%s</commit_job>"
//                + "<creationdate_job>%s</creationdate_job>"
//                + "<lastmodification_job>%s</lastmodification_job>"
//                + "<status_job>%s</status_job>"
                + "<permitmaster_id_pm>%s</permitmaster_id_pm>"
                + "<projects_id_pr>%s</projects_id_pr>"
//                + "<flagmodification_job>%s</flagmodification_job>"
//                + "<root_job>%s</root_job>"
                + "<filepath_job>%s</filepath_job>"
                + "</jobs>",
                getName_job(), getCommit_job(), 
//                getCreationdate_job(), getLastmodificacion_job(), 
//                getStatus_job(),
                getPermitmaster_id_pm(), getProjects_id_pr()
//                , getFlagmodification_job()
                ,getFilepath_job());
        return result;
    }

    /** This function return the data in xml format for updates in database
     * This XML is to make updates in the database concerning the jobs or IoT systems
     * @return xml in string 
     */
       public String returnXml() {
        String result = String.format(
                "<jobs>"
                + "<id_job>%s</id_job>"        
                + "<name_job>%s</name_job>"
                + "<commit_job>%s</commit_job>"
                + "<creationdate_job>%s</creationdate_job>"
                + "<lastmodification_job>%s</lastmodification_job>"
                + "<status_job>%s</status_job>"
                + "<permitmaster_id_pm>%s</permitmaster_id_pm>"
                + "<projects_id_pr>%s</projects_id_pr>"
                + "<flagmodification_job>%s</flagmodification_job>"
                + "<root_job>%s</root_job>"
                + "<filepath_job>%s</filepath_job>"
                + "</jobs>",
                getId_job(),getName_job(), getCommit_job(), getCreationdate_job(), getLastmodificacion_job(), getStatus_job(),
                getPermitmaster_id_pm(), getProjects_id_pr(), getFlagmodification_job(),getRoot_job(),getFilepath_job());
        return result;
    }
}
