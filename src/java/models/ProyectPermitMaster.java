/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author SOLIMAR
 */
public class ProyectPermitMaster implements Serializable{
    
    private String id_pm;
    private String id_pr;
    private String description_permitmaster;
    private String projects_id_pr;
    private String root_permitmaster;
    private String joindate_permitmaster;
    private String name_project;
    private String description_project;
    private String code_project;
    private String creationdate_project;
    private ArrayList<EmailShareProyect> share_users;

    public ProyectPermitMaster() {
    }

    public ProyectPermitMaster(String id_pm, String description_permitmaster, String projects_id_pr, String root_permitmaster, String joindate_permitmaster, String name_project, String description_project, String code_project, String creationdate_project) {
        this.id_pm = id_pm;
        this.description_permitmaster = description_permitmaster;
        this.projects_id_pr = projects_id_pr;
        this.root_permitmaster = root_permitmaster;
        this.joindate_permitmaster = joindate_permitmaster;
        this.name_project = name_project;
        this.description_project = description_project;
        this.code_project = code_project;
        this.creationdate_project = creationdate_project;
    }

    public String getId_pm() {
        return id_pm;
    }

    public void setId_pm(String id_pm) {
        this.id_pm = id_pm;
    }

    public String getId_pr() {
        return id_pr;
    }

    public void setId_pr(String id_pr) {
        this.id_pr = id_pr;
    }

    public String getDescription_permitmaster() {
        return description_permitmaster;
    }

    public void setDescription_permitmaster(String description_permitmaster) {
        this.description_permitmaster = description_permitmaster;
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

    public String getJoindate_permitmaster() {
        return joindate_permitmaster;
    }

    public void setJoindate_permitmaster(String joindate_permitmaster) {
        this.joindate_permitmaster = joindate_permitmaster;
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

    public ArrayList<EmailShareProyect> getShare_users() {
        return share_users;
    }

    public void setShare_users(ArrayList<EmailShareProyect> share_users) {
        this.share_users = share_users;
    }

    @Override
    public String toString() {
        return "ProyectPermitMaster{" + "id_pm=" + id_pm + ", description_permitmaster=" + description_permitmaster + ", projects_id_pr=" + projects_id_pr + ", root_permitmaster=" + root_permitmaster + ", joindate_permitmaster=" + joindate_permitmaster + ", name_project=" + name_project + ", description_project=" + description_project + ", code_project=" + code_project + ", creationdate_project=" + creationdate_project + '}';
    }
    
}
