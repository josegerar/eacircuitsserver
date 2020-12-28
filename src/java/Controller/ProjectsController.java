/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ProjectsDAO;
import DataStaticBD.CodeDJA;
import DataStaticBD.FileAccess;
import DataStaticBD.Methods;
import DataStaticBD.TemplateEmail;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import models.EmailShareProyect;
import models.Jobs;
import models.Projects;
import org.apache.commons.lang.RandomStringUtils;

/**
 *
 * @author tonyp
 * Respective validations of the projects.
 */
public class ProjectsController {

//    private Conection conex;
    private ProjectsDAO pDao;

    public ProjectsController() {
        pDao = new ProjectsDAO();
    }
    /** Create a new project 
     * @param name_project String type variable, which the name of the project.
     * @param description_project String type variable, which the description of the project.
     * @param userId String type variable, which contains the user ID.
     * @return It returns a String-type vector, which contains the state.
     */
    public String[] CreateProject(String name_project, String description_project, String userId) {
//        System.out.println(name_project+":"+description_project+":"+userId);
        if (!name_project.equals("")
                && !description_project.equals("")
                && !userId.equals("")) {
            name_project = name_project.replace("'", "''");
            description_project = description_project.replace("'", "''");
            userId = userId.replace("'", "''");
            String codeProject = RandomStringUtils.randomAlphanumeric(10);

            Projects pro = new Projects();
            pro.setName_project(name_project);
            pro.setDescription_project(description_project);
            pro.setCode_project(codeProject);
//            System.out.println("here"+userId);
            if (pDao.CreateProject(pro, userId)) {
                return new String[]{"2", "Creating project was succesful.", "{}"};
            } else {
                return new String[]{"4", "Error in the params.", "{}"};
            }
        } else {
            return new String[]{"3", "Error in the params.", "{}"};
        }
    }
    /** Get the project by user
     * @param userId String type variable, which contains the user ID.
     * @return It returns a String-type vector, which contains the state.
     */
    public String[] getProjects(String userId) {
        if (!userId.equals("")) {
            userId = userId.replace("'", "''");
            String result = pDao.getProjects(userId);
            if (!result.equals("[]")) {
                return new String[]{"2", "Load project was succesful.", result};
            } else {
                return new String[]{"3", "You don't have proyects", "[]"};
            }
        } else {
            return new String[]{"3", "Error in the params.", "[]"};
        }
    }
    /** Project search method 
     * @param arr Json containing information about the project.
     * @param idproj String type variable, contains the project identifier.
     * @return It returns a String-type vector, which contains the state.
     */
    public String[] shareProject(JsonArray arr, String idproj) {
        String total = "", allemails = "";
        for (JsonElement item : arr) {
            JsonObject jso = Methods.JsonElementToJSO(item);
            String email = Methods.JsonToString(jso, "email", "");
            String permmit = Methods.JsonToString(jso, "permit", "");
            String res = String.format(
                    "<user>"
                    + "<email>%s</email>"
                    + "<permmit>%s</permmit>"
                    + "</user>",
                    email, permmit);
            if (Methods.comprobeEmail(email)) {
                total += res;
                allemails += email + ",";
            }
        }
        Projects proj = new Projects();
        proj.setId_pr(idproj);
        String[] res = pDao.shareProject(proj, total);
        ArrayList<EmailShareProyect> emails = pDao.getEmailsForShare(idproj);
        if (res[0].equals("2") || res[0].equals("3")) {
            String[] emailfails = res[2].trim().split(" ");
            for (int index = 0; index < emailfails.length; index++) {
                allemails = allemails.replace(emailfails[index], "");
            }
            res[2] = "[\"" + res[2].trim().replace(" ", "\",\"") + "\"]";

            allemails = allemails.replace(" ", "");
            allemails = allemails.replace(",", " ");

            String code = pDao.returnCodeProject(idproj);
            TemplateEmail templa = new TemplateEmail();
            templa.collaborativeWork(allemails, code);
            //templa.collaborativeWork(emails);
        }
        return res;
    }
    
    /** Method that looks for projects to confirm.
     * @param id String type variable, contains the project identifier.
     * @return It returns a String-type vector, which contains the state.
     */
    public String[] getLoadShareProjectsForConfirm(String id) {
        String data = pDao.getLoadShareProjectsForConfirm(id);
        if (!data.equals("[]")) {
            return new String[]{"2", "Data charge.", data};
        } else {
            return new String[]{"3", "No data.", "{}"};
        }
    }
    /** Method that looks for confirmed projects.
     * @param id String type variable, contains the project identifier.
     * @return It returns a String-type vector, which contains the state.
     */
    public String[] getLoadShareProjectsConfirm(String id) {
        String data = pDao.getLoadShareProjectsConfirm(id);
        System.out.println(data);
        if (!data.equals("[]")) {
            
            return new String[]{"2", "Data charge.", data};
        } else {
            return new String[]{"3", "No data.", "{}"};
        }
    }
    /** Method that shows confirmed projects.
     * @param codeProject String type variable, contains the code sent to the creator of the project.
     * @param id String type variable, contains the project identifier.
     * @return It returns a String-type vector, which contains the state.
     */
    public String[] confirmShareProject(String codeProject, String id) {
        Projects pro = new Projects();
        pro.setCode_project(codeProject);
        if (pDao.confirmShareProject(pro, id)) {
            return new String[]{"2", "Data change.", "{}"};
        } else {
            return new String[]{"3", "Error in the params.", "{}"};
        }
    }
    /** Method for saving an IoT system.
     * @param nameSystem String type variable, it contains the name of the IoT system.
     * @param descriptionSystem String type variable, it contains the description of the IoT system.
     * @param pmId String type variable, it contains the user's permission to the IoT system.
     * @param prId String type variable, it contains an identifier for the IoT system.
     * @param superName String type variable, contains the job or IoT system.
     * @param ruta String type variable, it contains the path of the IoT System.
     * @return It returns a String-type vector, which contains the state.
     */
    public String[] saveSystem(String nameSystem, String descriptionSystem, String pmId, String prId, String superName, String ruta) {
        String res[];
        if (!nameSystem.equals("")
                && !descriptionSystem.equals("")
                && !pmId.equals("")
                && !prId.equals("")
                && !superName.equals("")
                && !ruta.equals("")) {
            Jobs jbs = new Jobs();
            jbs.setName_job(nameSystem);
            jbs.setCommit_job(descriptionSystem);
            jbs.setProjects_id_pr(prId);
            jbs.setPermitmaster_id_pm(pmId);
            jbs.setFilepath_job(superName);

            if (pDao.saveSystem(jbs)) {
                Thread th = new Thread(() -> {
                    FileAccess fa = new FileAccess();
                    fa.writeFileText(ruta + "_systemsIOT" + "\\" + superName, "[{\n"
                            + "	\"modelSystem\":{},\n"
                            + "	\"modelCode\":[]\n"
                            + "}]");
                });
                th.start();
                res = new String[]{"2", "System Saved.", "[]"};
            } else {
                res = new String[]{"4", "The system could not be saved.", "[]"};
            }
        } else {
            res = new String[]{"3", "Error in input parameters.", "[]"};
        }
        return res;
    }
    /**  Get the job done on the IoT system.
     * @param userID String type variable, contains the user's identifier.
     * @param idproject String type variable, contains the project identifier.
     * @return It returns a String-type vector, which contains the state.
     */
    public String[] getJobs(String userID, String idproject) {
        if (!(userID.equals("") || userID.contains("'"))
                && !(idproject.equals("") || idproject.contains("'"))) {
            String result = pDao.getJobs(userID, idproject);
            if (!result.equals("[]")) {
                return new String[]{"2", "Data charge.", result};
            } else {
                return new String[]{"3", "No data :c.", "{}"};
            }
        } else {
            return new String[]{"3", "Error in the parameters.", "{}"};
        }
    }
    /** Method for loading the IoT system.
     * @param idjob String type variable, it contains the job identifier, on the IoT system.
     * @param ruta String type variable, it contains the path of the IoT System.
     * @return It returns a String-type vector, which contains the state.
     */
    public String[] loadSystemIOT(String idjob, String ruta) {
        CodeDJA code = new CodeDJA();
        idjob = code.decodeDJA(idjob);
        idjob = Methods.StringToIntegerString(idjob);
//        System.out.println("decode:" + idjob);
        if (!idjob.equals("") && !idjob.contains("'") && !idjob.contains("-1")) {
            String res = pDao.loadSystemIOT(idjob, ruta + "_systemsIOT");
            if (!res.equals("{}")) {
                return new String[]{"2", "Data obtained.", res};
            } else {
                return new String[]{"4", "Data not found.", res};
            }
        } else {
            return new String[]{"3", "Error in the parameters.", "{}"};
        }
    }
    /** Method to save the entire IoT system.
     * @param IDSystem String type variable, it contains the system identifier IoT.
     * @param dataModelTotal String type variable, contains data about the model.
     * @param fileSystem String type variable, contains information that will be written in a file.
     * @return It returns a String-type vector, which contains the state.
     */
    public String[] saveSystemTotal(String IDSystem, String dataModelTotal, String fileSystem) {
        if (!IDSystem.equals("") && !IDSystem.equals("")) {
            if (pDao.saveSystemTotal(IDSystem)) {
                Thread th = new Thread(() -> {
                    FileAccess fa = new FileAccess();
                    System.out.println("ruta save system controllerProjec:" + fileSystem);
                    fa.writeFileText(fileSystem, dataModelTotal);
                });
                th.start();
                return new String[]{"2", "information found.", "[]"};
            } else {
                return new String[]{"4", "Data not found.", "[]"};
            }
        } else {
            return new String[]{"3", "Error int he params.", "[]"};
        }
    }
    /** Method to verify the unique code.
     * @param code String type variable, contains code to be verified.
     * @return a string over the verification.
     */
    public String verifyuniqueCode(String code) {
        return pDao.verifiUrl(code);
    }
    
    public String[] userJoinProjects (String idProject){
        if(!idProject.equals("")){
            String data = pDao.userJoinProjects(idProject);
            if(!data.equals("[]")){
                return new String[]{"2", "Information found.", data};
            }else{
                return new String[]{"4", "Data not found.", "[]"};
            }
        }else{
            return new String[]{"3","Error in the params","[]"};
        }
    }
    
    public String[] updatePermit(String descriptionpermitMaster, String idPermitMaster){
        if(!idPermitMaster.equals("") && !idPermitMaster.equals("")){
            int data = pDao.updatePermit(descriptionpermitMaster, idPermitMaster);
            if(data != 0){
                return new String[]{"2", "Information found.", "[]"};
            }else{
                return new String[]{"4", "Data not found.", "[]"};
            }
        }else{
            return new String[]{"3","Error in the params","[]"};
        }
    }
    
    public String[] deleteUserJoin( String idPermitMaster){
        if(!idPermitMaster.equals("") && !idPermitMaster.equals("")){
            int data = pDao.deleteUserJoin(idPermitMaster);
            if(data != 0){
                return new String[]{"2", "Information found.", "[]"};
            }else{
                return new String[]{"4", "Data not found.", "[]"};
            }
        }else{
            return new String[]{"3","Error in the params","[]"};
        }
    }
    
    public String[] deleteJobsRoot(String idjobs) {
        if (!idjobs.equals("")) {
            int data = pDao.deleteJobsRoot(idjobs);
            if (data != 0) {
                return new String[]{"2", "Information found.", "[]"};
            } else {
                return new String[]{"4", "Data not found.", "[]"};
            }
        } else {
            return new String[]{"3", "Error in the params", "[]"};
        }
    }

    public String[] deleteJobsAdmin(String idUser, String idProject, String idJobs) {
        if (!idUser.equals("") && !idProject.equals("") && !idJobs.equals("")) {
            String data = pDao.deleteJobsAdmin(idUser, idProject, idJobs);
            if (!data.equals("") && !data.equals("You cannot eliminate this system because you are not the creator of it.")) {
                return new String[]{"2", data, "[]"};
            } else {
                return new String[]{"4", data.length() <= 0 ? "Data not found." : data, "[]"};
            }
        } else {
            return new String[]{"3", "Error in the params", "[]"};
        }
    }
    
    public String [] deleteProjects(String idProjects){
        if (!idProjects.equals("")) {
            int data = pDao.deleteProjects(idProjects);
            if (data != 0) {
                return new String[]{"2", "Transaction executed correctly.", "[]"};
            } else {
                return new String[]{"4", "Data not found.", "[]"};
            }
        } else {
            return new String[]{"3", "Error in the params", "[]"};
        }
    }
    
    public String[]deleteProjectsShare(String idPermitMaster){
        if (!idPermitMaster.equals("")) {
            int data = pDao.deleteProjectsShare(idPermitMaster);
            if (data != 0) {
                return new String[]{"2", "Transaction executed correctly.", "[]"};
            } else {
                return new String[]{"4", "Data not found.", "[]"};
            }
        } else {
            return new String[]{"3", "Error in the params", "[]"};
        }
    }
    
}
