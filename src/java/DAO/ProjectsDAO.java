/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DataStaticBD.CodeDJA;
import DataStaticBD.Conection;
import DataStaticBD.FileAccess;
import DataStaticBD.Methods;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import models.EmailShareProyect;
import models.Jobs;
import models.Projects;
import models.ProyectPermitMaster;

/**
 * Class about the Data Access Object of Project.
 *
 * @author tonyp
 */
public class ProjectsDAO {

    Conection conex;

    public ProjectsDAO() {
        this.conex = new Conection();
    }

    /**
     * This method has uder for create a new proyect.
     *
     * @param pro has a Proyecto Object type. Contains information how name,
     * description and code for join the project
     * @param usr contaons the user Id
     * @return returns boolean value about the result of creation process.
     */
    public boolean CreateProject(Projects pro, String usr) {
        String sentency = String.format("select * from insertprojects('%s')", pro.returnXmlForInsert(usr));
        //result Contains the proyect ID if this be has created.
        String result = conex.fillString(sentency);
        System.out.println(sentency);
        if (!result.equals("") && !result.equals("0")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets the projects stored in the database. Operations in the database.
     *
     * @param usr String type variable, contains the user's identifier.
     * @return It reports a string, inside it contains a json with data about
     * the projects.
     */
    public String getProjects(String usr) {
        String sentency = String.format("select pm.id_pm, pm.description_permitmaster, pm.projects_id_pr, pm.root_permitmaster, pm.joindate_permitmaster, \n"
                + "pr.name_project, pr.description_project, pr.code_project, pr.creationdate_project\n"
                + " from permitmaster as pm inner join projects as pr on pm.projects_id_pr = pr.id_pr\n"
                + "where pm.users_id_user=%s and pm.joinactive_permitmaster=true and pm.description_permitmaster = 'ROOT'", usr);
//        System.out.println(sentency);
        ArrayList<ProyectPermitMaster> datos = conex.getObjectDB(sentency, ProyectPermitMaster.class, 1);
        datos.forEach(proyecto -> {
            proyecto.setShare_users(getEmailsToProyectUserRoot(proyecto.getProjects_id_pr()));
        });
        return Methods.objectToJsonString(datos);
    }

    /**
     * Method that returns the emails to proyects the user root.
     *
     * @param proyectID is id proyecto to query
     * @return list emails proyecto t user root
     */
    public ArrayList<EmailShareProyect> getEmailsToProyectUserRoot(String proyectID) {
        String sentency = String.format("select pm.description_permitmaster as permit, pm.users_id_user, pm.projects_id_pr, u.email_user as email\n"
                + "from permitmaster pm inner join users u on pm.users_id_user = u.id_user\n"
                + "where pm.projects_id_pr = %s and pm.root_permitmaster = false;", proyectID);
        return conex.getObjectDB(sentency, EmailShareProyect.class, 1);
    }

    /**
     * Method that returns the project code.
     *
     * @param idproj String type variable, contains the project identifier.
     * @return It returns a string, with the information obtained after the
     * consultation.
     */
    public String returnCodeProject(String idproj) {
        return conex.fillString("SELECT code_project FROM public.projects where id_pr=" + idproj);
    }

    /**
     * This method serves to search for specific projects. Operations in the
     * database.
     *
     * @param proj Projects's Moda
     * @param emails String type variable, contains the user's mail.
     * @return It returns a String-type vector, which contains the state.
     */
    public String[] shareProject(Projects proj, String emails, String userID) {
        System.out.println("datosXML");
        System.out.println(proj.returnXmlForshare(emails, userID));
        String sentency = String.format("select * from shareprojects('%s');", proj.returnXmlForshare(emails, userID));
        DefaultTableModel table = conex.returnRecord(sentency);
        if (table.getRowCount() > 0) {
            return new String[]{
                table.getValueAt(0, 0).toString(),
                table.getValueAt(0, 1).toString(),
                table.getValueAt(0, 2).toString()
            };
        } else {
            return new String[]{"4", "Error in the params.", "[]"};
        }
    }

    /**
     * Method that looks for projects to confirm. Operations in the database.
     *
     * @param id String type variable, contains the user's identifier.
     * @return It reports a string, inside it contains a json with data about
     * the projects.
     */
    public String getLoadShareProjectsForConfirm(String idUser) {
        String sentency = String.format("select * from permitmaster as pm inner join projects as pr on pm.projects_id_pr = pr.id_pr where pm.users_id_user = %s and pm.joinactive_permitmaster = false and pm.root_permitmaster = false;", idUser);
        String result = conex.getRecordsInJson(sentency);
        return result;
    }

    /**
     * Method that looks for confirmed projects. Operations in the database.
     *
     * @param id String type variable, contains the user's identifier.
     * @return It reports a string, inside it contains a json with data about
     * the projects.
     */
    public String getLoadShareProjectsConfirm(String id) {
        String sentency = String.format("select pm.id_pm, pr.id_pr, pm.description_permitmaster, pm.projects_id_pr, \n"
                + "pm.root_permitmaster, pm.joindate_permitmaster, pr.name_project, \n"
                + "pr.description_project, pr.code_project, pr.creationdate_project\n"
                + "from permitmaster as pm inner join projects as pr on pm.projects_id_pr = pr.id_pr \n"
                + "where pm.users_id_user = %s and pm.joinactive_permitmaster = true and pm.root_permitmaster = false;", id);
        ArrayList<ProyectPermitMaster> datos = conex.getObjectDB(sentency, ProyectPermitMaster.class, 1);
        datos.forEach(proyecto -> {
            proyecto.setShare_users(getEmailsToProyectUserAdmin(proyecto.getProjects_id_pr(), id));
        });
        return Methods.objectToJsonString(datos);
    }

    /**
     * Method that returns the emails to proyects the user root.
     *
     * @param proyectID is id proyecto to query
     * @param userID is user admin nedd view oter email shared
     * @return list emails proyecto t user root
     */
    public ArrayList<EmailShareProyect> getEmailsToProyectUserAdmin(String proyectID, String userID) {
        String sentency = String.format("select pm.description_permitmaster as permit, pm.users_id_user, pm.projects_id_pr, u.email_user as email\n"
                + "from permitmaster pm inner join users u on pm.users_id_user = u.id_user\n"
                + "where pm.projects_id_pr = %s and pm.root_permitmaster = false and pm.users_id_user != %s and description_permitmaster = 'ADMIN';", proyectID, userID);
        return conex.getObjectDB(sentency, EmailShareProyect.class, 1);
    }

    /**
     * Method that shows confirmed projects. Operations in the database.
     *
     * @param pro Projects's Moda
     * @param UserID this param contais the userid used to link the proyect with
     * the user.
     * @return a Boolean, verifying whether the operation was performed in the
     * database or not.
     */
    public boolean confirmShareProject(Projects pro, String UserID) {
        String sentency = String.format("select * from updatepermitmaster('%s')", pro.returnXmlForconfirmShareProject(UserID));
        System.out.println(sentency);
        return conex.modifyBD(sentency);
    }

    /**
     * Get the job done on the IoT system. Operations in the database.
     *
     * @param userID String type variable, contains the user's identifier.
     * @param idproject String type variable, contains the project identifier.
     * @return It reports a string, inside it contains a json with data about
     * the Jobs.
     */
    public String getJobs(String userID, String idproject) {

        String sentency = String.format("select jb.id_job, jb.name_job, jb.commit_job, jb.creationdate_job, jb.lastmodification_job,\n"
                + "		jb.permitmaster_id_pm,jb.filepath_job,pm.description_permitmaster as permit, pm.root_permitmaster\n"
                + "		from jobs as jb \n"
                + "	inner join projects as pj on jb.projects_id_pr=pj.id_pr\n"
                + "	inner join permitmaster as pm  on pm.projects_id_pr=pj.id_pr\n"
                + " where jb.projects_id_pr=%s and pm.users_id_user=%s and status_job = true", idproject, userID);

        System.out.println(sentency);
        DefaultTableModel table = conex.returnRecord(sentency);
        CodeDJA cod = new CodeDJA();
        int colum = table.getColumnCount();
        table.addColumn("code_job");
        for (int index = 0; index < table.getRowCount(); index++) {
            table.setValueAt(cod.encodeDJA(table.getValueAt(index, 0).toString()), index, colum);

        }
        return Methods.tableToJson(table);
    }

    /**
     * Method for loading the IoT system. Operations in the database.
     *
     * @param idjob String type variable, it contains the job identifier, on the
     * IoT system.
     * @param ruta String type variable, it contains the path of the IoT System.
     * @return It returns a String-type vector, which contains the state.
     */
    public String loadSystemIOT(String idjob, String ruta) {
        String sentency = "select * from selectjob(" + idjob + ")";
        Conection con = new Conection();
        DefaultTableModel tb = con.returnRecord(sentency);
        if (tb.getRowCount() > 0 && tb.getColumnCount() == 3) {
            FileAccess fa = new FileAccess();
//            System.out.println("LaRUTAX::"+ruta + "\\" + tb.getValueAt(0, 2).toString());
            String uri = ruta + "\\" + tb.getValueAt(0, 2).toString();
            String datax = fa.readFileText(uri);
            return String.format("{\"job\":%s,\"persons\":%s, \"system\":%s}", tb.getValueAt(0, 0), tb.getValueAt(0, 1), datax);
        } else {
            return "{}";
        }
    }

    /**
     * Method to save the entire IoT system. Operations in the database.
     *
     * @param jbs Job's Moda
     * @return It returns a String-type vector, which contains the state.
     */
    public boolean saveSystem(Jobs jbs) {
        String sentency = String.format("select * from insertjobs('%s')", jbs.returnXmlForInsert());
//        System.out.println(sentency);
        String response = conex.fillString(sentency);
        return (!response.equals("") && !response.equals("0"));
    }

    /**
     * Method to save the entire IoT system. Operations in the database.
     *
     * @param IDSystem String type variable, it contains the system identifier
     * IoT.
     * @return a Boolean value.
     */
    public boolean saveSystemTotal(String IDSystem) {
        String query = String.format("update jobs set lastmodification_job = now() where id_job = %s", IDSystem);
        return (conex.updateDB(query) == 1);
    }

    /*
     * Method to verify the unique code. Operations in the database.
     *
     * @param url this parameter contains a string that is expected to be analyzed to verify its uniqueness
     * @return this function returns a string stating that it is unique
     */
    public String verifiUrl(String url) {
        DefaultTableModel table = conex.returnRecord("select filepath_job from jobs where filepath_job ilike '" + url + "%'");
        String result = "";
        if (table.getRowCount() > 0) {
            int i = 0, count = 0;
            for (i = 0; i < table.getRowCount(); i++) {
                if ((url + (count == 0 ? "" : count) + ".txt").equals(table.getValueAt(i, 0).toString())) {
                    count++;
                    i = 0;
                } else {
                    result = url + (count == 0 ? "" : count);
                }
            }
        } else {
            result = url;
        }
        return result;
    }

    /*
     * Method to get users attached to a project
     *
     * @param idProject This parameter will allow users who have access to that project
     * @return This function returns user data in json format
     */
    public String userJoinProjects(String idProject) {
        String sentency = "select pm.id_pm, pm.projects_id_pr, us.id_user, us.name_user, us.lastname_user, us.email_user, pm.description_permitmaster\n"
                + "from permitmaster as pm inner join users as us on pm.users_id_user = us.id_user  \n"
                + "where pm.projects_id_pr = " + idProject + " and pm.joinactive_permitmaster = true";
        return conex.getRecordsInJson(sentency);
    }

    public int updatePermit(String descriptionPermit, String idPermitMaster) {
        String sentency = "update permitmaster set description_permitmaster = '" + descriptionPermit + "' where id_pm = " + idPermitMaster + "";
        return conex.updateDB(sentency);
    }

    public int deleteUserJoin(String idPermitMaster) {
        String sentency = "update permitmaster set joinactive_permitmaster = false where id_pm = " + idPermitMaster + "";
        return conex.updateDB(sentency);
    }

    public int deleteJobsRoot(String idjobs) {
        String sentency = "update jobs set status_job = false where id_job = " + idjobs + "";
        System.out.println(sentency);
        return conex.updateDB(sentency);
    }

    public String deleteJobsAdmin(String idUser, String idProject, String idJobs) {
        String sentency = "select delete_system_admin(" + idUser + "," + idProject + "," + idJobs + ")";
        return conex.fillString(sentency);
    }

    public int deleteProjects(String idProject) {
        String sentency = "update permitmaster set joinactive_permitmaster = false where projects_id_pr = " + idProject + "";
        return conex.updateDB(sentency);
    }

    public int deleteProjectsShare(String idPermiMaster) {
        String sentency = "update permitmaster set joinactive_permitmaster = false where id_pm = " + idPermiMaster + "";
        //System.out.println(sentency);
        return conex.updateDB(sentency);
    }

    /**
     *getEmailsForShare is methos for list emails for share
     * @param idproj is code proyect
     * @return list object EmailShareProyect
     */
    public ArrayList<EmailShareProyect> getEmailsForShare(String idproj) {
        String sentency = String.format("select pm.description_permitmaster as permit, pm.users_id_user, pm.projects_id_pr,\n"
                + "u.email_user as email, pj.code_project\n"
                + "from permitmaster pm inner join users u on pm.users_id_user=u.id_user\n"
                + "inner join projects pj on pj.id_pr = pm.projects_id_pr \n"
                + "where pm.projects_id_pr = %s  and pm.root_permitmaster = false and pm.joinactive_permitmaster = false;", idproj);
        return conex.getObjectDB(sentency, EmailShareProyect.class, 1);
    }
}
