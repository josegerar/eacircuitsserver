/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APIS;

import Controller.ProjectsController;
import DAO.ProjectsDAO;
import DAO.UserDAO;
import DataStaticBD.Conection;
import DataStaticBD.Methods;
import DataStaticBD.TemplateEmail;
import java.util.ArrayList;
import models.ProyectPermitMaster;
import models.Users;

/**
 *
 * @author SOLIMAR
 */
public class prueba {
    public static void main(String[] arg){
//        Conection c = new Conection();
//        String sql = String.format("select pm.id_pm, pm.description_permitmaster, pm.projects_id_pr, pm.root_permitmaster, pm.joindate_permitmaster, \n"
//                + "pr.name_project, pr.description_project, pr.code_project, pr.creationdate_project\n"
//                + " from permitmaster as pm inner join projects as pr on pm.projects_id_pr = pr.id_pr\n"
//                + "where pm.users_id_user=%s and pm.joinactive_permitmaster=true and pm.description_permitmaster = 'ROOT'", 35);
//        ArrayList<ProyectPermitMaster> datos =  c.getObjectDB(sql, ProyectPermitMaster.class, 1);
//        System.out.println(new UserDAO().getUserEmail("eloko-josphep@hotmail.com").toString());
        //TemplateEmail tempe = new TemplateEmail();
          //  tempe.userInsert(new Users("Jose", "Garcia", "CUVqwrcZDNrDGTFGTFOgARGYoqtWiYIFTdZr", "jgarcia24121996@gmail.com"));
//        Users us = new UserDAO().getUserEmail("eloko-josphep@hotmail.com");
//          TemplateEmail tempe = new TemplateEmail();
//            tempe.userInsert(us);
System.out.println(System.getProperty("user.dir"));
          //System.out.println(Methods.readJsonFile(System.getProperty("user.dir") + "\\web\\resource\\_puertos\\5MnspKeiyG.json"));
    }
}
