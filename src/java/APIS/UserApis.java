/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APIS;

import Controller.UserController;
import DataStaticBD.Methods;
import com.google.gson.JsonObject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author tonyp
 */
@Path("userApis")
public class UserApis {

    @Context
    private UriInfo context;

    private UserController ucontrol;

    /**
     * Creates a new instance of userApis
     */
    public UserApis() {
        ucontrol = new UserController();
    }
    /** This is the web service that allows you to log in.
     * @param data String type variable, receives a json with the necessary data from the user.
     * @return Returns a json with the results of the processes of the Controller's LogIn function.
     */
    
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response LogIn(String data) {
        String message;
        System.out.println("LogIn()");
//        System.out.println(data);
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {
            String user = Methods.JsonToString(Jso, "usr", "");
        String pwd = Methods.JsonToString(Jso, "pwd", "");
//            System.out.println(user+":"+pwd);
            String[] res = ucontrol.LogIn(user, pwd);
            message = "{\"status\":" + res[0] + ",\"information\":\"" + res[1] + "\",\"data\":" + res[2] + "}";
        } else {
            message = "{\"status\":4,\"information\":\"Data are lacking.\",\"data\":{}}";
        }
        return Response.ok(message)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }

    /** This is the web service that allows you to log in.
     * @param data String type variable, receives a json with the necessary data from the user.
     * @return Returns a json with the results of the processes of the Controller's LogIn function.
     */
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/createuser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(String data) {
        String message;
        System.out.println("createUser()");
        System.out.println(data);
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {
            String name = Methods.JsonToString(Jso, "name", "");
            String lastname = Methods.JsonToString(Jso, "lastname", "");
            String email = Methods.JsonToString(Jso, "email", "");
            String pass = Methods.JsonToString(Jso, "pass", "");
            String[] res = ucontrol.createUser(name, lastname, email, pass);
            message = "{\"status\":" + res[0] + ",\"information\":\"" + res[1] + "\",\"data\":" + res[2] + "}";
        } else {
            message = "{\"status\":4,\"information\":\"Error in the request parameters.\",\"data\":[]}";
        }
        return Response.ok(message)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
    /** This is the web service that allows you to create a new user using the API.
     * @param data String type variable, receives a json with the necessary data from the user.
     * @return Returns a json with the results of the processes of the Controller's createUserFromAPI function.
     */
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/createuserfromapi")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUserFromAPI(String data) {
        String message;
        System.out.println("createUserFromAPI()");
        System.out.println(data);
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {
//            String datas = Methods.JsonToSub(Jso, "data", "");
//            String sessionToken = Methods.JsonToString(Jso, "user_token", "");
//            String[] clains = Methods.getDataToJwt(sessionToken);
//            if (!clains[0].equals("") && !clains[1].equals("sleep")) {
                String[] res = ucontrol.createUserFromAPI(data);
                message = "{\"status\":" + res[0] + ",\"information\":\"" + res[1] + "\",\"data\":" + res[2] + "}";
//            } else {
//                message = "{\"status\":4,\"information\":\"Error in the request parameters.\",\"data\":[]}";
//            }
        } else {
            message = "{\"status\":4,\"information\":\"Error in the request parameters.\",\"data\":[]}";
        }
        return Response.ok(message)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
    /** This is the web service that allows you to update a user's data.
     * @param data String type variable, receives a json with the necessary data from the user.
     * @return Returns a json with the results of the processes of the Controller's updateUser function.
     */
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/updateuser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(String data) {
        String message;
        System.out.println("updateUser()");
        System.out.println(data);
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {
            String name = Methods.JsonToString(Jso, "name", "");
            String lastname = Methods.JsonToString(Jso, "lastname", "");
            String email = Methods.JsonToString(Jso, "email", "");
            String phonenumbre = Methods.JsonToString(Jso, "phonenumbre", "");  
            String urlimg = Methods.JsonToString(Jso, "urlimg", "");  
            String sessionToken = Methods.JsonToString(Jso, "user_token", "");
            
            String[] clains = Methods.getDataToJwt(sessionToken);
            if (!clains[0].equals("") && !clains[1].equals("sleep")) {
                String[] res = ucontrol.updateUser(name, lastname, email, phonenumbre, clains[0], urlimg);
                message = "{\"status\":" + res[0] + ",\"information\":\"" + res[1] + "\",\"data\":" + res[2] + "}";
            } else {
                message = "{\"status\":4,\"information\":\"Error in the request parameters.\",\"data\":[]}";
            }
        } else {
            message = "{\"status\":4,\"information\":\"Error in the request parameters.\",\"data\":[]}";
        }
        return Response.ok(message)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
     /** This is the web service that allows you to activate a user's account.
     * @param data String type variable, receives a json with the necessary data from the user.
     * @return Returns a json with the results of the processes of the Controller's activeAccount function.
     */
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/activeaccount")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response activeAccount(String data) {
        String message;
        System.out.println("activeAccount()");
        System.out.println(data);
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {
            String token = Methods.JsonToString(Jso, "token", "");    
            String email = Methods.JsonToString(Jso, "email", "");    
//            String sessionToken = Methods.JsonToString(Jso, "user_token", "");
//            System.out.println(token+" : "+email+"=>"+Methods.comprobeEmail(email));
//            String[] clains = Methods.getDataToJwt(sessionToken);
            if (!token.equals("") && Methods.comprobeEmail(email)) {
                String[] res = ucontrol.activeAccount(token,email);
                message = "{\"status\":" + res[0] + ",\"information\":\"" + res[1] + "\",\"data\":" + res[2] + "}";
            } else {
                message = "{\"status\":4,\"information\":\"Error in the request parameters.\",\"data\":[]}";
            }
        } else {
            message = "{\"status\":4,\"information\":\"Error in the request parameters.\",\"data\":[]}";
        }
        return Response.ok(message)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
    /** This is the web service that allows you to request the code to activate a new account.
     * @param data String type variable, receives a json with the necessary data from the user.
     * @return Returns a json with the results of the processes of the Controller's requestCodeForChangePassword function.
     */
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/requestcodeforchangepassword")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response requestCodeForChangePassword(String data) {
        String message;
        System.out.println("requestCodeForChangePassword()");
        System.out.println(data);
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {
            String email = Methods.JsonToString(Jso, "email", "");      
//            String sessionToken = Methods.JsonToString(Jso, "user_token", "");
            
//            String[] clains = Methods.getDataToJwt(sessionToken);
//            if (!clains[0].equals("") && !clains[1].equals("sleep")) {
                String[] res = ucontrol.requestCodeForChangePassword(email);
                message = "{\"status\":" + res[0] + ",\"information\":\"" + res[1] + "\",\"data\":" + res[2] + "}";
//            } else {
//                message = "{\"status\":4,\"information\":\"Error in the request parameters.\",\"data\":[]}";
//            }
        } else {
            message = "{\"status\":4,\"information\":\"Error in the request parameters.\",\"data\":[]}";
        }
        return Response.ok(message)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
    /** This is the web service that allows you to request confirmation of the password for the new account.
     * @param data String type variable, receives a json with the necessary data from the user.
     * @return Returns a json with the results of the processes of the Controller's confirmPwd function.
     */
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/confirmpwd")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response confirmPwd(String data) {
        String message;
        System.out.println("confirmpwd()");
        System.out.println(data);
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {
            String email = Methods.JsonToString(Jso, "email", "");
            String code = Methods.JsonToString(Jso, "code", "");
            String pwd = Methods.JsonToString(Jso, "pwd", "");
//            String sessionToken = Methods.JsonToString(Jso, "user_token", "");
            
//            String[] clains = Methods.getDataToJwt(sessionToken);
//            if (!clains[0].equals("") && !clains[1].equals("sleep")) {
                String[] res = ucontrol.confirmPwd(email, code, pwd);
                message = "{\"status\":" + res[0] + ",\"information\":\"" + res[1] + "\",\"data\":" + res[2] + "}";
//            } else {
//                message = "{\"status\":4,\"information\":\"Error in the request parameters.\",\"data\":[]}";
//            }
        } else {
            message = "{\"status\":4,\"information\":\"Error in the request parameters.\",\"data\":[]}";
        }
        return Response.ok(message)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
    /** This is the web service that allows you to resend the account confirmation code.
     * @param data String type variable, receives a json with the necessary data from the user.
     * @return Returns a json with the results of the processes of the Controller's resendMeCodeVerify function.
     */
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/resendmeCodeverify")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response resendMeCodeVerify(String data) {
        String message;
        System.out.println("resendMeCodeVerify()");
        System.out.println(data);
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {
            String email = Methods.JsonToString(Jso, "email", "");
            String sessionToken = Methods.JsonToString(Jso, "user_token", "");
            
            String[] clains = Methods.getDataToJwt(sessionToken);
            if (!clains[0].equals("") && !clains[1].equals("sleep")) {
                String[] res = ucontrol.resendMeCodeVerify(email);
                message = "{\"status\":" + res[0] + ",\"information\":\"" + res[1] + "\",\"data\":" + res[2] + "}";
            } else {
                message = "{\"status\":4,\"information\":\"Error in the request parameters.\",\"data\":[]}";
            }
        } else {
            message = "{\"status\":4,\"information\":\"Error in the request parameters.\",\"data\":[]}";
        }
        return Response.ok(message)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
    
    public Response resendCodeVerify(String data) {
        String message;
        System.out.println("resendCodeVerify()");
        System.out.println(data);
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {
            String email = Methods.JsonToString(Jso, "email", "");
            
                String[] res = ucontrol.resendMeCodeVerify(email);
                message = "{\"status\":" + res[0] + ",\"information\":\"" + res[1] + "\",\"data\":" + res[2] + "}";
            
        } else {
            message = "{\"status\":4,\"information\":\"Error in the request parameters.\",\"data\":[]}";
        }
        return Response.ok(message)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
    /** This is the web service that allows you to obtain the session data.
     * @param data String type variable, receives a json with the necessary data from the user.
     * @return Returns a json with the results of the processes of the Controller's getDataSession function.
     */
    
    
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/getdatasession")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getDataSession(String data) {
        JsonObject Jso = Methods.stringToJSON(data);
        String user_token = Methods.JsonToString(Jso, "user_token", "");
        String[] res = Methods.getDataToJwt(user_token);
        String jsonresponse = "{\"status\":" + (res[0].equals("") ? 4 : (res[1].equals("sleep"))?3:2) + ",\"information\":\"" + (res.equals("") ? "Invalid Token." : "All ok.\"") 
                + ",\"data\":{\"permmit\":\""+res[1]+"\"}}";
        return Response.ok(jsonresponse)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
}
