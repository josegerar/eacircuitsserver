/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APIS;

import Controller.ProjectsController;
import DataStaticBD.Methods;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.lang.RandomStringUtils;

/**
 * REST Web Service
 *
 * @author tonyp
 */
@Path("projectsApis")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjectsApis {

    @Context
    private UriInfo context;

    @Context
    private HttpServletRequest request;

    private ProjectsController pController;

    /**
     * Creates a new instance of ProjectsApis
     */
    public ProjectsApis() {
//        System.out.println("xxx");
        pController = new ProjectsController();
    }

    /** You get a json.
     * @param x Test variable.
     * @return Returns a json with the results of the processes of the Controller's CreateProject function.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson(@QueryParam("x") String x) {
        //TODO return proper representation object
        return Response.ok("{\"stats\":\"holis" + x + "\"}")
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
    /** This is the web service that allows you to create new projects.
     * @param data String type variable, receives a json with the necessary data (one of them is the session token).
     * @return Returns a json with the results of the processes of the Controller's CreateProject function.
     */
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/saveProject")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response CreateProject(String data) {
        String message;
        System.out.println("CreateProject()");
        System.out.println(data);
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {

            String name_project = Methods.JsonToString(Jso, "nameProject", "");
            String description_project = Methods.JsonToString(Jso, "descriptionProjec", "");
            String sessionToken = Methods.JsonToString(Jso, "user_token", "");

            String[] clains = Methods.getDataToJwt(sessionToken);

            if (!clains[0].equals("") && !clains[1].equals("sleep")) {
                String[] res = pController.CreateProject(name_project, description_project, clains[0]);
                message = "{\"status\":" + res[0] + ",\"information\":\"" + res[1] + "\",\"data\":" + res[2] + "}";
            } else {
                message = "{\"status\":4,\"information\":\"Error in the request parameters.\",\"data\":[]}";
            }
//            System.out.println("x:"+clains[0]);
//            System.out.println("y:"+clains[1]);
//            System.out.println("mensaje:"+message);
        } else {
            message = "{\"status\":4,\"information\":\"Data are lacking.\",\"data\":{}}";
        }
        return Response.ok(message)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
    /** This is the web service that allows you to get the projects.
     * @param data String type variable, receives a json with the necessary data (one of them is the session token).
     * @return Returns a json with the results of the processes of the Controller's getProjects function.
     */
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/getProjects")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getProjects(String data) {
        String message;
        System.out.println("getProjects()");
        System.out.println(data);
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {
            String sessionToken = Methods.JsonToString(Jso, "user_token", "");
            String[] clains = Methods.getDataToJwt(sessionToken);
            if (!clains[0].equals("") && !clains[1].equals("sleep")) {
                String[] res = pController.getProjects(clains[0]);
                message = "{\"status\":" + res[0] + ",\"information\":\"" + res[1] + "\",\"data\":" + res[2] + "}";
            } else {
                message = "{\"status\":4,\"information\":\"Error in the request parameters.\",\"data\":[]}";
            }
        } else {
            message = "{\"status\":4,\"information\":\"Data are lacking.\",\"data\":{}}";
        }
        return Response.ok(message)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
    /** This is the web service that allows you to share your projects.
     * @param data String type variable, receives a json with the necessary data (one of them is the session token).
     * @return Returns a json with the results of the processes of the Controller's shareProject function.
     */
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/shareProject")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response shareProject(String data) {
        String message;
        System.out.println("shareProject()");
        System.out.println(data);
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {

            String projID = Methods.JsonToString(Jso, "idProject", "");
            JsonArray arr = Methods.JsonToArray(Jso, "emails");
            String sessionToken = Methods.JsonToString(Jso, "user_token", "");
            System.out.println(arr.toString());
            String[] clains = Methods.getDataToJwt(sessionToken);
            if (!clains[0].equals("") && !clains[1].equals("sleep")) {
//                System.out.println(arr.get(0));
//                pController.shareProject(arr, projID);
                String[] res = pController.shareProject(arr, projID, clains[0]);
                message = "{\"status\":" + res[0] + ",\"information\":\"" + res[1] + "\",\"data\":" + res[2] + "}";
            } else {
                message = "{\"status\":4,\"information\":\"Error in the request parameters.\",\"data\":[]}";
            }
        } else {
            message = "{\"status\":4,\"information\":\"Data are lacking.\",\"data\":{}}";
        }
        System.out.println(message);
        return Response.ok(message)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
    /** This is the web service that allows you to obtain the remaining projects to be confirmed.
     * @param data String type variable, receives a json with the necessary data (one of them is the session token).
     * @return Returns a json with the results of the processes of the Controller's getLoadShareProjectsForConfirm function.
     */
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/getLoadShareProjectsForConfirm")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getLoadShareProjectsForConfirm(String data) {
        String message;
        System.out.println("getLoadShareProjectsForConfirm()");
        System.out.println(data);
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {

            String sessionToken = Methods.JsonToString(Jso, "user_token", "");

            String[] clains = Methods.getDataToJwt(sessionToken);

            if (!clains[0].equals("") && !clains[1].equals("sleep")) {
                String[] res = pController.getLoadShareProjectsForConfirm(clains[0]);
                System.out.println(res[0] +" "+ res[1]+" "+ res[2]);
                message = "{\"status\":" + res[0] + ",\"information\":\"" + res[1] + "\",\"data\":" + res[2] + "}";
            } else {
                message = "{\"status\":4,\"information\":\"Error in the request parameters.\",\"data\":[]}";
            }
        } else {
            message = "{\"status\":4,\"information\":\"Data are lacking.\",\"data\":{}}";
        }
        return Response.ok(message)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
    /** This is the web service that allows you to get the confirmation of the projects.
     * @param data String type variable, receives a json with the necessary data (one of them is the session token).
     * @return Returns a json with the results of the processes of the Controller's getLoadShareProjectsConfirm function.
     */
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/getLoadShareProjectsConfirm")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getLoadShareProjectsConfirm(String data) {
        String message;
        System.out.println("getLoadShareProjectsConfirm()");
        System.out.println(data);
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {

            String sessionToken = Methods.JsonToString(Jso, "user_token", "");

            String[] clains = Methods.getDataToJwt(sessionToken);

            if (!clains[0].equals("") && !clains[1].equals("sleep")) {
                String[] res = pController.getLoadShareProjectsConfirm(clains[0]);
                message = "{\"status\":" + res[0] + ",\"information\":\"" + res[1] + "\",\"data\":" + res[2] + "}";
            } else {
                message = "{\"status\":4,\"information\":\"Error in the request parameters.\",\"data\":[]}";
            }
        } else {
            message = "{\"status\":4,\"information\":\"Data are lacking.\",\"data\":{}}";
        }
        return Response.ok(message)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
    /** This is the web service that allows you to confirm shared projects.
     * @param data String type variable, receives a json with the necessary data (one of them is the session token).
     * @return Returns a json with the results of the processes of the Controller's confirmShareProject function.
     */
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/confirmShareProject")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response confirmShareProject(String data) {
        String message;
        System.out.println("confirmShareProject()");
        System.out.println(data);
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {
            String sessionToken = Methods.JsonToString(Jso, "user_token", "");
            String codeProj = Methods.JsonToString(Jso, "code_project", "");
            String[] clains = Methods.getDataToJwt(sessionToken);

            if (!clains[0].equals("") && !clains[1].equals("sleep") && !codeProj.equals("")) {
                String[] res = pController.confirmShareProject(codeProj, clains[0]);
                message = "{\"status\":" + res[0] + ",\"information\":\"" + res[1] + "\",\"data\":" + res[2] + "}";
            } else {
                message = "{\"status\":4,\"information\":\"Error in the request parameters.\",\"data\":[]}";
            }
        } else {
            message = "{\"status\":4,\"information\":\"Data are lacking.\",\"data\":{}}";
        }
        return Response.ok(message)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
    /** This is the web service that allows you to save the IoT systems.
     * @param data String type variable, receives a json with the necessary data (one of them is the session token).
     * @return Returns a json with the results of the processes of the Controller's saveSystem function.
     */
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/saveSystem")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveSystem(String data) {
        String message;
        System.out.println("saveSystem()");
        System.out.println(data);
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {
            String sessionToken = Methods.JsonToString(Jso, "user_token", "");

            String nameSystem = Methods.JsonToString(Jso, "systemName", "");
            String descriptionSystem = Methods.JsonToString(Jso, "description", "");
            String pmId = Methods.JsonToString(Jso, "permitMasterId", "");
            String prId = Methods.JsonToString(Jso, "projectId", "");
            String ruta = request.getServletContext().getRealPath("").concat("\\resource\\");

            String superName = pController.verifyuniqueCode(RandomStringUtils.randomAlphanumeric(10)) + ".txt";
            String[] clains = Methods.getDataToJwt(sessionToken);

            if (!clains[0].equals("") && !clains[1].equals("sleep")) {
                String[] res = pController.saveSystem(nameSystem, descriptionSystem, pmId, prId, superName, ruta);
                message = "{\"status\":" + res[0] + ",\"information\":\"" + res[1] + "\",\"data\":" + res[2] + "}";
            } else {
                message = "{\"status\":4,\"information\":\"Error in the request parameters.\",\"data\":[]}";
            }
        } else {
            message = "{\"status\":4,\"information\":\"Data are lacking.\",\"data\":{}}";
        }
        return Response.ok(message)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
    /** This is the web service that allows you to get the job areas or jobs.
     * @param data String type variable, receives a json with the necessary data (one of them is the session token).
     * @return Returns a json with the results of the processes of the Controller's getJobs function.
     */
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/getjobs")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getJobs(String data) {
        String message;
        System.out.println("getJobs()");
        System.out.println(data);
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {
            String sessionToken = Methods.JsonToString(Jso, "user_token", "");
            String prId = Methods.JsonToString(Jso, "projectId", "");

            String[] clains = Methods.getDataToJwt(sessionToken);

            if (!clains[0].equals("") && !clains[1].equals("sleep")) {
                String[] res = pController.getJobs(clains[0], prId);
                message = "{\"status\":" + res[0] + ",\"information\":\"" + res[1] + "\",\"data\":" + res[2] + "}";
            } else {
                message = "{\"status\":4,\"information\":\"Error in the request parameters.\",\"data\":[]}";
            }
        } else {
            message = "{\"status\":4,\"information\":\"Data are lacking.\",\"data\":{}}";
        }
        return Response.ok(message)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
    /** This is the web service that allows you to upload the work areas or jobs.
     * @param data String type variable, receives a json with the necessary data (one of them is the session token).
     * @return Returns a json with the results of the processes of the Controller's loadSystemIOT function.
     */
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/loadSystemIOT")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loadSystemIOT(String data) {
        String message;
        System.out.println("loadSystemIOT()");
        System.out.println(data);
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {
            String sessionToken = Methods.JsonToString(Jso, "user_token", "");
            String idjob = Methods.JsonToString(Jso, "tokenidjob", "");

            String[] clains = Methods.getDataToJwt(sessionToken);
            if (!clains[0].equals("") && !clains[1].equals("sleep")) {
                String ruta = request.getServletContext().getRealPath("").concat("\\resource\\");
                String[] res = pController.loadSystemIOT(idjob, ruta);
                message = "{\"status\":" + res[0] + ",\"information\":\"" + res[1] + "\",\"data\":" + res[2] + "}";
            } else {
                message = "{\"status\":4,\"information\":\"Error in the request parameters.\",\"data\":[]}";
            }
        } else {
            message = "{\"status\":4,\"information\":\"Data are lacking.\",\"data\":{}}";
        }
        return Response.ok(message)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
    /** This is the web service that allows you to save the entire IoT system.
     * @param data String type variable, receives a json with the necessary data (one of them is the session token).
     * @return Returns a json with the results of the processes of the Controller's saveSystemTotal function.
     */
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/saveSystemTotal")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveSystemTotal(String data) {
        String message;
        System.out.println("saveSystemTotal()");
        System.out.println(data);
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {
            //don't use token because the session is bad idea for this
//            String sessionToken = Methods.JsonToString(Jso, "user_token", "");
            String dataModel = Methods.JsonToString(Jso, "dataModel", "");
            String idSystem = Methods.JsonToString(Jso, "idSystem", "");
            String filepath = Methods.JsonToString(Jso, "filepath", "");
//            String[] clains = Methods.getDataToJwt(sessionToken);
//            if (!clains[0].equals("") && !clains[1].equals("sleep")) {
            String ruta = request.getServletContext().getRealPath("").concat("\\resource\\") + "_systemsIOT" + "\\";
            String[] res = pController.saveSystemTotal(idSystem, dataModel, ruta +  filepath);
            message = "{\"status\":" + res[0] + ",\"information\":\"" + res[1] + "\",\"data\":" + res[2] + "}";

//            } else {
//                message = "{\"status\":4,\"information\":\"Error in the request parameters.\",\"data\":[]}";
//            }
        } else {
            message = "{\"status\":4,\"information\":\"Data are lacking.\",\"data\":{}}";
        }
        return Response.ok(message)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
    /** This is the web service that allows you to obtain the file paths.
     * @return Returns a json with the results of the processes of the Controller's getpath function.
     */

    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/getpath")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getpath() {
        System.out.println("ehere!!!");
//        Logger.getLogger("MyLogger").log(Level.WARNING,"getpath()");

        String ruta = request.getServletContext().getRealPath("");
        System.out.println("ehere!!!:" + ruta);
        return Response.ok("{\"path\":\"" + ruta + "\"}")
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
    
    //duval 19-09-2020
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/userJoinProjects")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response userJoinProjects(String data) {
        String message;
        System.out.println("getJobs()");
        System.out.println(data);
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {
            String sessionToken = Methods.JsonToString(Jso, "user_token", "");
            String prId = Methods.JsonToString(Jso, "projectId", "");
            String[] clains = Methods.getDataToJwt(sessionToken);
            if (!clains[0].equals("") && !clains[1].equals("sleep")) {
                String[] res = pController.userJoinProjects(prId);
                message = "{\"status\":" + res[0] + ",\"information\":\"" + res[1] + "\",\"data\":" + res[2] + "}";
            } else {
                message = "{\"status\":4,\"information\":\"Error in the request parameters.\",\"data\":[]}";
            }
        } else {
            message = "{\"status\":4,\"information\":\"Data are lacking.\",\"data\":{}}";
        }
        return Response.ok(message)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
    
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/updatePermit")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePermit(String data) {
        String message;
        System.out.println("getJobs()");
        System.out.println(data);
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {
            String sessionToken = Methods.JsonToString(Jso, "user_token", "");
            String descPM = Methods.JsonToString(Jso, "descriptionPM", "");
            String idPM = Methods.JsonToString(Jso, "idPM", "");
            String[] clains = Methods.getDataToJwt(sessionToken);
            if (!clains[0].equals("") && !clains[1].equals("sleep")) {
                String[] res = pController.updatePermit(descPM, idPM);
                message = "{\"status\":" + res[0] + ",\"information\":\"" + res[1] + "\",\"data\":" + res[2] + "}";
            } else {
                message = "{\"status\":4,\"information\":\"Error in the request parameters.\",\"data\":[]}";
            }
        } else {
            message = "{\"status\":4,\"information\":\"Data are lacking.\",\"data\":{}}";
        }
        return Response.ok(message)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
    
     @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/deleteUserJoin")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteUserJoin(String data) {
        String message;
        System.out.println("getJobs()");
        System.out.println(data);
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {
            String sessionToken = Methods.JsonToString(Jso, "user_token", "");
            String idPM = Methods.JsonToString(Jso, "idPM", "");
            String[] clains = Methods.getDataToJwt(sessionToken);
            if (!clains[0].equals("") && !clains[1].equals("sleep")) {
                String[] res = pController.deleteUserJoin(idPM);
                message = "{\"status\":" + res[0] + ",\"information\":\"" + res[1] + "\",\"data\":" + res[2] + "}";
            } else {
                message = "{\"status\":4,\"information\":\"Error in the request parameters.\",\"data\":[]}";
            }
        } else {
            message = "{\"status\":4,\"information\":\"Data are lacking.\",\"data\":{}}";
        }
        return Response.ok(message)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
    
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/deleteJobsRoot")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteJobsRoot(String data){
        String message;
        System.out.println("getJobs()");
        System.out.println(data);
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {
            String sessionToken = Methods.JsonToString(Jso, "user_token", "");
            String idJobs = Methods.JsonToString(Jso, "idJobs", "");
            String[] clains = Methods.getDataToJwt(sessionToken);
            if (!clains[0].equals("") && !clains[1].equals("sleep")) {
                String[] res = pController.deleteJobsRoot(idJobs);
                message = "{\"status\":" + res[0] + ",\"information\":\"" + res[1] + "\",\"data\":" + res[2] + "}";
            } else {
                message = "{\"status\":4,\"information\":\"Error in the request parameters.\",\"data\":[]}";
            }
        } else {
            message = "{\"status\":4,\"information\":\"Data are lacking.\",\"data\":{}}";
        }
        return Response.ok(message)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
    
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/deleteJobsAdmin")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteJobsAdmin(String data){
        String message;
        System.out.println("getJobs()");
        System.out.println(data);
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {
            String sessionToken = Methods.JsonToString(Jso, "user_token", "");
            String idProject = Methods.JsonToString(Jso, "idProject", "");
            String idJobs = Methods.JsonToString(Jso, "idJobs", "");
            String[] clains = Methods.getDataToJwt(sessionToken);
            if (!clains[0].equals("") && !clains[1].equals("sleep")) {
                String[] res = pController.deleteJobsAdmin(clains[0], idProject, idJobs);
                message = "{\"status\":" + res[0] + ",\"information\":\"" + res[1] + "\",\"data\":" + res[2] + "}";
            } else {
                message = "{\"status\":4,\"information\":\"Error in the request parameters.\",\"data\":[]}";
            }
        } else {
            message = "{\"status\":4,\"information\":\"Data are lacking.\",\"data\":{}}";
        }
        return Response.ok(message)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
    
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/deleteProjects")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteProjects(String data){
        String message;
        System.out.println("getJobs()");
        System.out.println(data);
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {
            String sessionToken = Methods.JsonToString(Jso, "user_token", "");
            String idProject = Methods.JsonToString(Jso, "idProject", "");
            String[] clains = Methods.getDataToJwt(sessionToken);
            if (!clains[0].equals("") && !clains[1].equals("sleep")) {
                String[] res = pController.deleteProjects(idProject);
                message = "{\"status\":" + res[0] + ",\"information\":\"" + res[1] + "\",\"data\":" + res[2] + "}";
            } else {
                message = "{\"status\":4,\"information\":\"Error in the request parameters.\",\"data\":[]}";
            }
        } else {
            message = "{\"status\":4,\"information\":\"Data are lacking.\",\"data\":{}}";
        }
        return Response.ok(message)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
    
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/deleteProjectsShare")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteProjectsShare(String data){
        String message;
        System.out.println("getJobs()");
        System.out.println(data);
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {
            String sessionToken = Methods.JsonToString(Jso, "user_token", "");
            String idPermitMaster = Methods.JsonToString(Jso, "idPermitMaster", "");
            String[] clains = Methods.getDataToJwt(sessionToken);
            if (!clains[0].equals("") && !clains[1].equals("sleep")) {
                String[] res = pController.deleteProjectsShare(idPermitMaster);
                message = "{\"status\":" + res[0] + ",\"information\":\"" + res[1] + "\",\"data\":" + res[2] + "}";
            } else {
                message = "{\"status\":4,\"information\":\"Error in the request parameters.\",\"data\":[]}";
            }
        } else {
            message = "{\"status\":4,\"information\":\"Data are lacking.\",\"data\":{}}";
        }
        return Response.ok(message)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
}
