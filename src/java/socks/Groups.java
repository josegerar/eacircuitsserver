/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socks;

import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.websocket.Session;

/**
 * This java class is about groups, it contains the get and set methods.
 * @author tonyp
 */
public class Groups {

    private String groupID = "";
    private List<MeSession> users;

    public Groups() {
        this.users = new ArrayList<>();
    }

    public Groups(String groupID) {
        this.groupID = groupID;
        this.users = new ArrayList<>();
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }
    /** Method for obtaining users.
      * @param user user logging in.
      */
    public void setUsers(MeSession user) {
        this.users.add(user);
        shareNoMi(user.getSesion(), easyMessage("System",user.getEmail(),"join"));
        shareall(easyMessage("System",getAllUsers(),"list"));
    }
    /** Get session users.
     * @param mysession is given by default.
     * @return Returns the status of the session.
     */
    public MeSession getUsers(Session mysession) {
        int row = returnIndex(mysession);
        if (row > -1 && row < users.size()) {
            return users.get(row);
        } else {
            return null;
        }
    }
    /** Method to remove a user.
     * @param mysession is given by default.
     * @return returns a boolean operation that states whether it was executed or not.
     */
    public boolean removeUser(Session mysession){
        int row = returnIndex(mysession);
        boolean flag = false;
        if (row > -1 && row < users.size()) {
            shareNoMi(mysession, easyMessage("System",users.get(row).getEmail(),"close"));
            flag = (users.remove(row)!=null);
            shareall(easyMessage("System",getAllUsers(),"list"));
        } else {
            flag = false;
        }
        return flag;
    }
    /** Method to get all users.
     * @return returns a string with the obtained data.
     */
    
    public String getAllUsers() {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (int index = 0; index < users.size(); index++) {
            builder.add(
                    Json.createObjectBuilder()
                            .add("email", users.get(index).getEmail())
                            .add("image", users.get(index).getImage())
            );
        }
        return builder.build().toString();
    }

    /** Method to obtain the position of the session.
     * @param mysession is given by default.
     * @return returns the position of the session.
     */
    public int returnIndex(Session mysession) {
        int resultRow = -1;
        for (int row = 0; row < users.size(); row++) {
            if (users.get(row).getSesion() == mysession) {
                resultRow = row;
            }
        }
        return resultRow;
    }
    /** Share to all but me.
     * @param ses is given by default
     * @param message It contains the information.
     */
    public void shareNoMi(Session ses, Message message) {
        for (MeSession xsession : users) {
            if (xsession.getSesion() != ses) {
                try {
                    xsession.getSesion().getBasicRemote().sendObject(message);
                } catch (Exception e) {
                    System.err.println("shareNoMi:" + e.getMessage());
                }
            }
        };
    }
    /** Share only me.
     * @param ses is given by default
     * @param message It contains the information.
     */
    public void shareOnlyMi(Session ses, Message message) {
        try {
            ses.getBasicRemote().sendObject(message);
        } catch (Exception e) {
            System.err.println("shareOnlyMi:" + e.getMessage());
        }
    }
   /** Share to all.
     * @param message It contains the information.
     */
    public void shareall(Message message) {
        for (MeSession xsession : users) {
            try {
                xsession.getSesion().getBasicRemote().sendObject(message);
            } catch (Exception e) {
                System.err.println("shareall:" + e.getMessage());
            }
        };
    }
    /** Method for counting users.
     * @return Returns the number of users.
     */
    public int usersCount()
    {
        return users.size();
    }
    /** Method to get a message.
     * @param header It contains the message header.
     * @param contect It contains the body or context of the message.
     * @param config It contains the message settings.
     * @return Return the message.
     */
    public Message easyMessage(String header, String contect, String config)
    {
        Message message = new Message();
        message.setHeader(header);
        message.setContent(contect);
        message.setConfig(config);
        return message;
    }
}
