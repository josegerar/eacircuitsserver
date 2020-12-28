/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author SOLIMAR
 */
public class EmailShareProyect {
    
    private String permit;
    private String users_id_user;
    private String projects_id_pr;
    private String email;
    private String code_project;

    public EmailShareProyect() {
    }

    public EmailShareProyect(String permit, String users_id_user, String projects_id_pr, String email) {
        this.permit = permit;
        this.users_id_user = users_id_user;
        this.projects_id_pr = projects_id_pr;
        this.email = email;
    }

    public String getPermit() {
        return permit;
    }

    public void setPermit(String permit) {
        this.permit = permit;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode_project() {
        return code_project;
    }

    public void setCode_project(String code_project) {
        this.code_project = code_project;
    }

    @Override
    public String toString() {
        return "EmailShareProyect{" + "permit=" + permit + ", users_id_user=" + users_id_user + ", projects_id_pr=" + projects_id_pr + ", email=" + email + ", code_project=" + code_project + '}';
    } 
    
}
