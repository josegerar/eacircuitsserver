/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

//import DAO.UserDAO;
import DataStaticBD.Conection;
import DataStaticBD.Methods;
import com.google.gson.JsonObject;
import javax.swing.table.DefaultTableModel;
import DAO.UserDAO;
import DataStaticBD.CodeDJA;
import models.Users;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author tonyp
 * Respective validations of the User.
 */
public class UserController {

    UserDAO udao;

    public UserController() {
        udao = new UserDAO();
    }
    /** Method for logging 
     * @param email String type variable, contains the user's email.
     * @param pwd String type variable, contains the encrypted password of the user.
     * @return It returns a String-type vector, which contains the state.
     */
    public String[] LogIn(String email, String pwd) {
        if (Methods.comprobeEmail(email)) {
            DefaultTableModel table = udao.LogIn(email);
            String message = "User not found.";
            String status = "4";
            Users usr = new Users();
            if (table.getRowCount() > 0) {
                message = "Incorrect Password.";
                status = "3";
                pwd = encriptPassword(pwd);
                for (int index = 0; index < table.getRowCount(); index++) {
                    if (pwd.equals(table.getValueAt(index, 4).toString())) {
                        usr = udao.setUser(table, index);
                        message = "Access granted.";
                        status = "2";
                    }
                }
            }
            /**
             * The next is is used to verify that the account is confirmated.
             */
            if (usr.getTypeuser_user().equals("sleep")) {
                status = "5";
            }
            return new String[]{status, message, jsonUser(usr)};
        } else {
            return new String[]{"3", "The email is not valid.", "{}"};
        }
    }
    /** Create a json with user data.
     * @param usr User's Modal.
     * @return Returns a string with the json loaded with user data.
     */
    private String jsonUser(Users usr) {
        return udao.userDataJson(usr);
    }
    /** Encrypt the user's password.
     * @param pwd Password for user login.
     * @return a string with the encrypted password.
     */
    private String encriptPassword(String pwd) {
        return DigestUtils.sha256Hex(pwd);
    }
     /** Method for creating a new user.
     * @param name String type variable, contains the name of the user.
     * @param lastname String type variable, contains the user's last name.
     * @param email String type variable, contains the user's mail.
     * @param pass String type variable, contains the user's password.
     * @return It returns a String-type vector, which contains the state.
     */
    public String[] createUser(String name, String lastname, String email, String pass) {
        System.out.println("analizare:" + email + ":" + Methods.comprobeEmail(email));
        System.out.println("analizare:" + name + ":" + !name.trim().equals(""));
        System.out.println("analizare:" + lastname + ":" + !lastname.trim().equals(""));
        System.out.println("analizare:" + pass + ":" + !pass.trim().equals(""));
        if (!name.trim().equals("") && !lastname.trim().equals("") && !pass.trim().equals("")
                && Methods.comprobeEmail(email)) {
            Users usr = new Users();
            usr.setNames_user(name);
            usr.setLastname_user(lastname);
            usr.setEmail_user(email);
            usr.setPassword_user(encriptPassword(pass));

            String imgname = udao.verifiUrl(email);
            usr.setImg_user(imgname);
            // allows to obtain a set of random characters for the validation of the account.
            CodeDJA cod = new CodeDJA();
            usr.setCodeverification_user(cod.getMaxAlea());
            if (udao.comprobeUniqueEmail(usr)) {
                if (udao.userInsert(usr)) {
                    return LogIn(email, pass);
                } else {
                    return new String[]{"4", "Unregistered user.", "{}"};
                }
            } else {
                return new String[]{"3", "The email is already registered.", "{}"};
            }
        } else {
            return new String[]{"4", "The input data does not meet the requirements.", "{}"};
        }
    }
    /** Method for creating a new user for the API
     * @param data Json with loaded user data.
     * @return It returns a String-type vector, which contains the state.
     */
    public String[] createUserFromAPI(String data) {
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {
            String name = Methods.JsonToString(Jso.getAsJsonObject(), "name", "");
            String lastname = Methods.JsonToString(Jso.getAsJsonObject(), "lastname", "");
            String email = Methods.JsonToString(Jso.getAsJsonObject(), "email", "");
            String pass = Methods.JsonToString(Jso.getAsJsonObject(), "pass", "");
            String img = Methods.JsonToString(Jso.getAsJsonObject(), "img", "");
            Users usr = new Users();
            usr.setEmail_user(email);
//            String imgname = udao.verifiUrl(email);
            if (udao.comprobeUniqueEmail(usr)) {
                String[] response = createUserAPI(name, lastname, email, pass, img);
//                if (response[0].equals("2") && !img.equals("")) {
//                    saveImage(img, url + imgname, url);
//                }
                return response;
            } else {
                String[] response = LogIn(email, pass);
                return response;
            }
        } else {
            return new String[]{"4", "Invalid data.", "{}"};
        }
    }
    /** Method to create a new user using the API
    * @param name String type variable, contains the name of the user.
     * @param lastname String type variable, contains the user's last name.
     * @param email String type variable, contains the user's mail.
     * @param pass String type variable, contains the user's password.
     * @param urlimg It contains the url of the image.
     * @return It returns a String-type vector, which contains the state.
     */
    private String[] createUserAPI(String name, String lastname, String email, String pass, String urlimg) {
        if (!name.trim().equals("") && !lastname.trim().equals("") && !pass.trim().equals("")
                && Methods.comprobeEmail(email)) {
            Users usr = new Users();
            usr.setNames_user(name);
            usr.setLastname_user(lastname);
            usr.setEmail_user(email);
            usr.setPassword_user(encriptPassword(pass));

            usr.setImg_user(urlimg);

//            if (udao.comprobeUniqueEmail(usr)) {
            if (udao.userInsertAPI(usr)) {
                return LogIn(email, pass);
            } else {
                return new String[]{"4", "Unregistered user.", "{}"};
            }
//            } else {
//                return new String[]{"3", "The email is already registered.", "{}"};
//            }
        } else {
            return new String[]{"4", "The input data does not meet the requirements.", "{}"};
        }
    }
    /** Method for updating a user's information.
     * @param name String type variable, contains the name of the user.
     * @param lastname String type variable, contains the user's last name.
     * @param email String type variable, contains the user's mail.
     * @param phonenumbre String type variable, contains the user's phone number.
     * @param userID String type variable, contains the user's identifier.
     * @return It returns a String-type vector, which contains the state.
     */
    public String[] updateUser(String name, String lastname, String email, String phonenumbre, String userID, String urlimg) {
        if (!name.trim().equals("") && !lastname.trim().equals("") && Methods.comprobeEmail(email)
                && !urlimg.trim().equals("")) {
            Users usr = new Users();
            usr.setNames_user(name);
            usr.setLastname_user(lastname);
            usr.setEmail_user(email);
            usr.setPhone_user(phonenumbre);
            usr.setId_user(userID);
            usr.setImg_user(urlimg);
            if (udao.comprobeUnusedEmail(usr)) {
                if (udao.userUpdate(usr)) {
                    return new String[]{"2", "Successfully modified data.", jsonUser(usr)};
                } else {
                    return new String[]{"4", "The data could not be modified.", "{}"};
                }
            } else {
                return new String[]{"3", "The email is being used by another account.", "{}"};
            }
        } else {
            return new String[]{"4", "The input data does not meet the requirements.", "{}"};
        }
    }
   /** Method for activating a user's account.
     * @param token String type variable, contains the security token (key) or also known as authentication token.
     * @param email String type variable, contains the user's email address.
     * @return It returns a String-type vector, which contains the state.
     */
    public String[] activeAccount(String token, String email) {
        if (!token.trim().equals("")) {
            Users usr = new Users();
            usr.setCodeverification_user(token);
            usr.setEmail_user(email);
            if (udao.comprobeAccount(usr)) {
                return new String[]{"2", "The account has been successfully activated.", "{}"};
            } else {
                return new String[]{"3", "The code could not be associated with some account.", "{}"};
            }
        } else {
            return new String[]{"4", "The input data does not meet the requirements.", "{}"};
        }
    }

    /**
     * This method sends a code to the user's mail, with which he can reset the
     * password.
     *
     * @param email es the user email
     * @return String[] of the process results into a vector. position [0]
     * indicates status, [1] message and [2]additional information.
     */
    public String[] requestCodeForChangePassword(String email) {
        if (Methods.comprobeEmail(email)) {
            CodeDJA cod = new CodeDJA();
            Users usr = new Users();
            usr.setCodeverification_user(cod.getEmailCode());
            usr.setEmail_user(email);

            if (udao.requestCodeForChangePassword(usr)) {
                return new String[]{"2", "Code sent successfully.", "{}"};
            } else {
                return new String[]{"3", "Application could not be completed.", "{}"};
            }
        } else {
            return new String[]{"4", "The email is not valid.", "{}"};
        }
    }
    /** Method to confirm the password entered by the user for a new account
     * @param email String type variable, contains the user's email address.
     * @param code Variable of type String, contains the code sent to the mail to the user when registering.
     * @param pwd String type variable, contains the account password.
     * @return It returns a String-type vector, which contains the state.
     */
    public String[] confirmPwd(String email, String code, String pwd) {
        if (Methods.comprobeEmail(email) && !code.equals("") && !pwd.equals("")) {
            Users usr = new Users();
            usr.setCodeverification_user(code);
            usr.setEmail_user(email);
            usr.setPassword_user(encriptPassword(pwd));
            if (udao.confirmPwdChange(usr)) {
                return new String[]{"2", "Changes successfully made.", "{}"};
            } else {
                return new String[]{"3", "Application could not be completed.", "{}"};
            }
        } else {
            return new String[]{"4", "The information is not valid.", "{}"};
        }
    }
    /** Method for resubmitting the account verification code.
     * @param email String type variable, contains the user's email address.
     * @return It returns a String-type vector, which contains the state.
     */
    public String[] resendMeCodeVerify(String email) {
        if (Methods.comprobeEmail(email)) {
            if (udao.requestVerifyAccount(email)) {
                return new String[]{"2", "Code sent correctly.", "{}"};
            } else {
                return new String[]{"4", "The information could not be sent.", "{}"};
            }
        } else {
            return new String[]{"4", "The email is not valid.", "{}"};
        }
    }
}
