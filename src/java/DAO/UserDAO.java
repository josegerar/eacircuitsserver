/** To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DataStaticBD.Conection;
import DataStaticBD.Email;
import DataStaticBD.Methods;
import DataStaticBD.TemplateEmail;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.ArrayList;
import java.util.Date;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.swing.table.DefaultTableModel;
import models.Users;

/**
 * This class acts as a mediator between the controller and the models. It is in
 * charge of performing the transactions with the database.
 *
 * @author tonyp
 */
public class UserDAO {

    Conection conex;

    public UserDAO() {
        conex = new Conection();
    }

    /**
     * userInsert is for insert user
     *
     * @param usr contais the user for intert
     * @return return the id for user insert
     */
    public boolean userInsert(Users usr) {
        String sentency = String.format("select insertusers('%s');", usr.returnXmlForInsert());
        System.out.println(sentency);
        boolean flag = conex.modifyBD(sentency);
        if (flag) {
            TemplateEmail tempe = new TemplateEmail();
            tempe.userInsert(usr);
        }
        return flag;
    }

    /**
     * userInsertAPI is a function call in the database, which will perform the
     * insert for registered users from the api.
     *
     * @param usr with the data, in order to obtain the xml of it.
     * @return returns a bolean about insert
     */
    public boolean userInsertAPI(Users usr) {
        String sentency = String.format("select insertusersapi('%s');", usr.returnXmlForInsertAPI());
        boolean flag = conex.modifyBD(sentency);
        return flag;
    }

    /**
     * userInsert is for insert user userUpdateUnabled
     *
     * @param usr contais the user for intert
     * @return return the id for user insert
     */
    public boolean userUpdate(Users usr) {
        String sentency = String.format("select * from updateuser('%s');", usr.returnXmlForUpdate());
        System.out.println(sentency);
        return conex.modifyBD(sentency);
    }

    /**
     * userUpdateUnabled is for update user unabled
     *
     * @param usr contais the user for intert
     * @return return the id for user insert
     */
    public boolean userUpdateUnabled(Users usr) {
        String sentency = String.format("select * from updateuserunabled('%s');", usr.returnXmlForUpdateUnabled());
        System.out.println(sentency);
        boolean flag = conex.modifyBD(sentency);
        if (flag) {
            TemplateEmail tempe = new TemplateEmail();
            tempe.userInsert(usr);
        }
        return flag;
    }

    /**
     * This function allows to obtain the data of a user according to an
     * identifier.
     *
     * @param id user identifier, format String
     * @return Returns an Users Object.
     */
    public Users userSelect(String id) {
        DefaultTableModel table = conex.returnRecord("select * from users where id_user=" + id);
        //id_user, names_user, lastname_user, email_user, password_user, phonenumbre_user
        Users usr = new Users();
        if (table.getRowCount() > 0) {
            usr.setId_user(table.getValueAt(0, 0).toString());
            usr.setNames_user(table.getValueAt(0, 1).toString());
            usr.setLastname_user(table.getValueAt(0, 2).toString());
            usr.setEmail_user(table.getValueAt(0, 3).toString());
            usr.setPassword_user(table.getValueAt(0, 4).toString());
            usr.setImg_user(table.getValueAt(0, 5).toString());
            usr.setCodeverification_user(table.getValueAt(0, 6).toString());
            usr.setTypeuser_user(table.getValueAt(0, 8).toString());
        }
        return usr;
    }

    /**
     * This function allows to obtain the data of a user according to an email.
     *
     * @param email user email, format String
     * @return Return an Users Object.
     */
    public Users userEmailSelect(String email) {
        DefaultTableModel table = conex.returnRecord("select * from users where email_user='" + email+"'");
        //id_user, names_user, lastname_user, email_user, password_user, phonenumbre_user
        Users usr = new Users();
        if (table.getRowCount() > 0) {
            usr.setId_user(table.getValueAt(0, 0).toString());
            usr.setNames_user(table.getValueAt(0, 1).toString());
            usr.setLastname_user(table.getValueAt(0, 2).toString());
            usr.setEmail_user(table.getValueAt(0, 3).toString());
            usr.setPassword_user(table.getValueAt(0, 4).toString());
            usr.setImg_user(table.getValueAt(0, 5).toString());
            usr.setCodeverification_user(table.getValueAt(0, 6).toString());
            usr.setTypeuser_user(table.getValueAt(0, 8).toString());
        }
        return usr;
    }

    /**
     * This function receives a table to obtain a Users type entity according to
     * the established index.
     *
     * @param table - table to obtain the data.
     * @param index - index to get the data.
     * @return Users - returns a users type object.
     */
    public Users setUser(DefaultTableModel table, int index) {
        Users usr = new Users();
        if (-1 < index && index < table.getRowCount()) {
            usr.setId_user(table.getValueAt(0, 0).toString());
            usr.setNames_user(table.getValueAt(0, 1).toString());
            usr.setLastname_user(table.getValueAt(0, 2).toString());
            usr.setEmail_user(table.getValueAt(0, 3).toString());
            usr.setPassword_user(table.getValueAt(0, 4).toString());
            usr.setImg_user(table.getValueAt(0, 5).toString());
            usr.setTypeuser_user(table.getValueAt(0, 8).toString());
            usr.setPhone_user(table.getValueAt(index, 9).toString());
        }
        return usr;
    }

    /**
     * This function receives a Users type object and returns important
     * parameters for the session. It also returns the jwt token to validate the
     * session.
     *
     * @param usr the object to extract the user's data.
     * @return Return the json with the data
     */
    public String userDataJson(Users usr) {
        String key = "digiclave";
        long tiempo = System.currentTimeMillis();
//        System.out.println(new Date(tiempo) +"-" + new Date(tiempo+900000));
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, key)
                .setSubject("-1")
                .claim("user", usr.getId_user())
                .claim("permit", usr.getTypeuser_user())
                .setIssuedAt(new Date(tiempo))
                .setExpiration(new Date(tiempo + 60 * 60 * 1000))
                .compact();
        JsonObjectBuilder jsoB = Json.createObjectBuilder();
        jsoB.add("email_user", usr.getEmail_user());
        jsoB.add("lastname_user", usr.getLastname_user());
        jsoB.add("names_user", usr.getNames_user());
        jsoB.add("type_user", usr.getTypeuser_user());
        jsoB.add("img_user", usr.getImg_user());
        jsoB.add("phonenumbre_user", usr.getPhone_user());
        jsoB.add("user_token", jwt);
        javax.json.JsonObject jsonObj = jsoB.build();
        return jsonObj.toString();
    }

    /**
     * This function checks that the email to be inserted is unique within the
     * program's database.
     *
     * @param usr User object containing the email to verify the information.
     * @return returns a Boolean value from the result of the transaction.
     */
    public boolean comprobeUniqueEmail(Users usr) {
        String sentency = String.format("select * from users where email_user ilike '%s';", usr.getEmail_user());
        return ((conex.returnRecord(sentency)).getRowCount() <= 0);
    }

    /**
     * Check that the email is not in use.
     *
     * @param usr User object containing the email to verify the information.
     * @return returns a Boolean value from the result of the transaction.
     */
    public boolean comprobeUnusedEmail(Users usr) {
        String sentency = String.format("select * from users where id_user!=%s and email_user ilike '%s';", usr.getId_user(), usr.getEmail_user());
        return ((conex.returnRecord(sentency)).getRowCount() <= 0);
    }

    /**
     * Check that the email is not enabled.
     *
     * @param email
     * @return returns a Boolean value from the result of the transaction.
     */
    public boolean comprobeUnableEmail(String email) {
        String sentency = String.format("select * from users \n"
                + "where email_user = '%s' AND typeuser_user != 'none';", email);
        return ((conex.returnRecord(sentency)).getRowCount() <= 0);
    }

    /**
     * Method to verify the url
     *
     * @param url Contains the url address
     * @return Return the url
     */
    public String verifiUrl(String url) {
        DefaultTableModel table = conex.returnRecord("select img_user from users where img_user ilike '" + url + "%'");
        String result = "";
        if (table.getRowCount() > 0) {
            int i = 0, count = 0;
            for (i = 0; i < table.getRowCount(); i++) {
                if ((url + (count == 0 ? "" : count)).equals(table.getValueAt(i, 0).toString())) {
                    count++;
                    i = 0;
                }
                result = url + (count == 0 ? "" : count);
            }
        } else {
            result = url;
        }
        return result;
    }

    /**
     * Method for approving the user account
     *
     * @param usr User object containing the email to verify the information.
     * @return returns a Boolean value from the result of the transaction.
     */
    public boolean comprobeAccount(Users usr) {
        System.out.println("x");
        String sentency = String.format("select * from activeuseraccount('%s')", usr.returnXmlForActiveAccount());
        String res = conex.fillString(sentency);
        System.out.println("xx" + res);
        return (!res.equals("0") && !res.equals("1"));
    }

    /**
     * method to request the code to change the password
     *
     * @param usr User object containing the email to verify the information.
     * @return returns a Boolean value from the result of the transaction.
     */
    public boolean requestCodeForChangePassword(Users usr) {
        String sentency = String.format("select * from getverifycode('%s')", usr.returnXmlForGetVerifiCode());
        String result = conex.fillString(sentency);
        boolean flag = (!result.equals("") && !result.equals("0"));
        if (flag) {
            TemplateEmail tempe = new TemplateEmail();
            tempe.requestCodeForChangePassword(usr);
        }
        return flag;
    }

    /**
     * method to change the account password.
     *
     * @param usr User object containing the email to verify the information.
     * @return returns a Boolean value from the result of the transaction.
     */
    public boolean confirmPwdChange(Users usr) {
        String sentency = String.format("select * from confirmpwdchange('%s')", usr.returnXmlForChangePwd());
        String result = conex.fillString(sentency);
        return (!result.equals("") && !result.equals("0"));
    }

    /**
     * This method is used to solicit to resend verificatión code @_@
     *
     * @return boolean to know the result
     * @param email is the user email for rec
     */
    public boolean requestVerifyAccount(String email) {
        if (Methods.comprobeEmail(email)) {
            Users usr = userEmailSelect(email);
            TemplateEmail tempe = new TemplateEmail();
            if (usr.getTypeuser_user().equals("sleep")) {
                //tempe.userInsert(usr);
                tempe.requestCodeForConfirmAccount(usr);
            } /*else {
                tempe.requestCodeForChangePassword(usr);
            }*/
            return true;
        } else {
            return false;
        }
    }

    /**
     * method for logging.
     *
     * @param email contains the user's email.
     * @return a table model with data loaded from the database.
     */
    public DefaultTableModel LogIn(String email) {
        return conex.returnRecord("select * from users where email_user='" + email + "'");
    }

    /**
     * getUserEmail is for obtain data for email.
     *
     * @param email contains the user's email.
     * @return object Users cotained data obtaining for database.
     */
    public Users getUserEmail(String email) {
        String sql = String.format("select * from users where email_user = '%s'", email);
        ArrayList<Users> usuario = conex.getObjectDB(sql, Users.class, 1);
        if (usuario.size() > 0) {
            return usuario.get(0);
        } else {
            return new Users();
        }
    }
}
