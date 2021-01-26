/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APIS;

import Controller.ComponentsController;
import DataStaticBD.Methods;
import com.google.gson.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author tonyp
 */
@Path("components")
public class ComponentsApis {

    @Context
    private UriInfo context;

    private ComponentsController cControl;
    
    @Context
    private HttpServletRequest request;

    /**
     * Creates a new instance of ComponentsApis
     */
    public ComponentsApis() {
        cControl = new ComponentsController();
    }

    /** This is the web service that allows you to obtain the components that are active.
     * @param data String type variable, receives a json with the necessary data (one of them is the session token).
     * @return Returns a json with the results of the processes of the Controller's getComponentsActive function
     */
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/getComponentesActive")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getComponentesActive(String data) {
        String message;
        System.out.println("getComponentesActive()");
        System.out.println(data);
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {
            String sessionToken = Methods.JsonToString(Jso, "user_token", "");
            String[] clains = Methods.getDataToJwt(sessionToken);
            if (!clains[0].equals("") && !clains[1].equals("sleep")) {
                String[] res = cControl.getComponentsActive(clains[0]);
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
    /** This is the web service that allows you to obtain all the components..
     * @param data String type variable, receives a json with the necessary data (one of them is the session token).
     * @return Returns a json with the results of the processes of the function getComponentsTotal of the Controller
     */
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/getComponentsTotal")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getComponentsTotal(String data) {
        String message;
        System.out.println("getComponentsTotal()");
        System.out.println(data);
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {
            String sessionToken = Methods.JsonToString(Jso, "user_token", "");
            String[] clains = Methods.getDataToJwt(sessionToken);
            if (!clains[0].equals("") && !clains[1].equals("sleep")) {
                String[] res = cControl.getComponentsTotal();
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
    /** This is the web service that allows you to obtain components.
     * @param data String type variable, receives a json with the necessary data (one of them is the session token).
     * @return Returns a json with the results of the processes of the Controller's getComponents function
     */

    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/getComponents")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getComponents(String data) {
        String message;
        System.out.println("getComponentsTotal()");
//        System.out.println(data);
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {
            String sessionToken = Methods.JsonToString(Jso, "user_token", "");
            String[] clains = Methods.getDataToJwt(sessionToken);
            if (!clains[0].equals("") && !clains[1].equals("sleep")) {
                String ruta = request.getServletContext().getRealPath("");
                String[] res = cControl.getComponents(ruta.concat("\\resource\\_puertos\\"));
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
    /** This is the web service that allows you to save the components.
     * @param data String type variable, receives a json with the necessary data (one of them is the session token).
     * @return Returns a json with the results of the processes of the Controller's saveComponent function.
     */

    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/saveComponent")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveComponent(String data) {
        String message;
        System.out.println("getComponentesActive()");
        System.out.println(data);
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {
            String sessionToken = Methods.JsonToString(Jso, "user_token", "");
            String[] clains = Methods.getDataToJwt(sessionToken);
            
            String nameComponent = Methods.JsonToString(Jso, "nameComponent", "");
            String descriptionComponennt = Methods.JsonToString(Jso, "descriptionComponent", "");
            String status = Methods.JsonToString(Jso, "activeComponent", "");
            String pathimg_component = Methods.JsonToString(Jso, "pathImgComponent", "");
            String dataParamPorts = Methods.JsonToString(Jso, "dataPorts", "");
            
            String ruta = request.getServletContext().getRealPath("").concat("\\resource\\_puertos\\");
            
            if (!clains[0].equals("") && !clains[1].equals("sleep")) {
                String[] res = cControl.saveComponent(nameComponent, descriptionComponennt, status, clains[0], pathimg_component, dataParamPorts, ruta);
                message = "{\"status\":" + res[0] + ",\"information\":\"" + res[1] + "\",\"data\":" + res[2] + "}";

            } else {
                message = "{\"status\":4,\"information\":\"Error in the request parameters.\",\"data\":[]}";
            }
        } else {
            message = "{\"status\":3,\"information\":\"Data are lacking.\",\"data\":{}}";
        }
        return Response.ok(message)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
    /** This is the web service that allows you to obtain the inactive components.
     * @param data String type variable, receives a json with the necessary data (one of them is the session token).
     * @return Returns a json with the results of the processes of the Controller's getComponentsInactive function.
     */
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/getComponentsInactive")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getComponentsInactive(String data) {
        String message;
        System.out.println("getComponentsInactive()");
        System.out.println(data);
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {
            String sessionToken = Methods.JsonToString(Jso, "user_token", "");
            String[] clains = Methods.getDataToJwt(sessionToken);
            if (!clains[0].equals("") && !clains[1].equals("sleep")) {
                String[] res = cControl.getComponentsInactive(clains[0]);
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
    /** This is the web service that allows you to obtain the components that were proposed.
     * @param data String type variable, receives a json with the necessary data (one of them is the session token).
     * @return Returns a json with the results of the processes of the function getComponentsPropuesta del Controller.
     */
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/getComponentsPropuesta")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getComponentsPropuesta(String data) {
        String message;
        System.out.println("getComponentsPropuesta()");
        System.out.println(data);
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {
            String sessionToken = Methods.JsonToString(Jso, "user_token", "");
            String[] clains = Methods.getDataToJwt(sessionToken);
            if (!clains[0].equals("") && !clains[1].equals("sleep")) {
                String[] res = cControl.getComponentsPropuesta();
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
    /** This is the web service that allows you to obtain the components that were approved.
     * @param data String type variable, receives a json with the necessary data (one of them is the session token).
     * @return  It returns a json with the results of the processes of the function aproveComponent of the Controller.
     */
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/aproveComponent")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response aproveComponent(String data) {
        String message;
        System.out.println("aproveComponent()");
        System.out.println(data);
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {
            String sessionToken = Methods.JsonToString(Jso, "user_token", "");
            String idComponent = Methods.JsonToString(Jso, "idComponent", "");
            String[] clains = Methods.getDataToJwt(sessionToken);
            if (!clains[0].equals("") && !clains[1].equals("sleep")) {
                String[] res = cControl.aproveComponent(idComponent);
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
