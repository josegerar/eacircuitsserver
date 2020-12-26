/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socks;

/*
 * @author tonyp
 */
import DataStaticBD.Methods;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * The socket in glassfish doesn't give problems without encoders and decoders, but in tomcat it does, or it doesn't work. The serverEndpoint is the socket link, followed by the encoder and decoder.
 */
@ServerEndpoint(
        value = "/ws_sharing",
        encoders = {EncoderMensaje.class},
        decoders = {DecorderMensaje.class}
)
public class Socket {

    /**
     * They are added to a list so that information can be sent to them by scrolling through the list and obtaining each of the connected sessions.
     */
    private static final List<Groups> connected = new ArrayList<>();//sesiones

    /**
     * This method is executed when a connection is made to the web scoket, then it receives as the session, which is added to the list with the other sessions.
     * @param sesion is given by default.
     */
    @OnOpen
    public void open(Session sesion) {
        /**
         * adds the session to the list when it is created
         */
        System.out.println("opnex");
        sesion.setMaxTextMessageBufferSize(10000500);
    }

    /**
     * This method is executed when the connection with the web scoket is interrupted and receives the session of the person who cut off the communication. This method removes the inactive session from the session list.
     * @param sesion is given by default.
     * @throws EncodeException by the encoder.
     * @throws IOException for possible exceptions. 
     */
    @OnClose
    public void exit(Session sesion) throws IOException, EncodeException {
        int row = searchSession(sesion);//se busca la sesión en la lista
        if (row > -1 && row < connected.size()) {
            connected.get(row).removeUser(sesion);
            if (connected.get(row).usersCount() <= 0) {
                connected.remove(row);//quita el grupo de la lista
            }
        }

    }

    /**
     * It is the method that allows the transition of data, it occurs when a message is sent and it is the one that redirects the information to the other sessions.
     * @param ses is the session and is given by default.
     * @param message is the object with the information.
     * @throws EncodeException by the encoder.
     * @throws IOException for possible exceptions.
     */
    @OnMessage
    public void message(Session ses, Message message) throws IOException, EncodeException {
        System.out.println("nuevo message de :" + message.getHeader());
        System.out.println("nuevo message de :" + message.getContent());
        System.out.println("nuevo message de :" + message.getConfig());
        switch (message.getConfig()) {
            case "init": {
                int row = getGroup(message.getHeader());
                if (row != -1 && row < connected.size()) {
                    MeSession me = createMeSession(ses, message.getContent());
                    if (me != null) {
                        connected.get(row).setUsers(me);
                    } else {
                        SendError(ses, "Missing parameters.");
                    }
                } else {
                    Groups gp = new Groups(message.getHeader());
                    MeSession me = createMeSession(ses, message.getContent());
                    if (me != null) {
//                        connected.get(row).setUsers(me);
                        gp.setUsers(me);
                        connected.add(gp);
                    } else {
                        SendError(ses, "Missing parameters.");
                    }
//                    gp.setUsers(me);
//                    connected.add(gp);
                }
            }
            break;
            case "graph": {
                int row = getGroup(message.getHeader());
                if (row != -1 && row < connected.size()) {
                    connected.get(row).shareNoMi(ses, message);
                } else {
                    SendError(ses, "Group not found.");
                }
            }
            break;
            case "codeJson": {
                int row = getGroup(message.getHeader());
                if (row != -1 && row < connected.size()) {
                    connected.get(row).shareNoMi(ses, message);
                } else {
                    SendError(ses, "Group not found.");
                }
            }
            break;
            case "chat": {
                int row = getGroup(message.getHeader());
                if (row != -1 && row < connected.size()) {
                    connected.get(row).shareNoMi(ses, message);
                } else {
                    SendError(ses, "Group not found.");
                }
            }
            break;
            default: {
                /**
                 * here you will find a message recipient
                 */
                SendError(ses, "Undefined action.");
            }
            break;
        }
    }

  /**
     * It occurs when an exception occurs.
     * @param session failed session.
     * @param throwable for the exception.
     */
    public final void onError(Session session, java.lang.Throwable throwable) {
        System.out.println("wsError:" + throwable.getMessage());
    }
   /**
     * You get the index of where the session you are looking for is located.
     * @param mysession the session for which you want to know your position.
     */
    private int searchSession(Session mysession) {
        int resultRow = -1;
        for (int row = 0; row < connected.size(); row++) {
            int index = connected.get(row).returnIndex(mysession);
            if (index != -1) {
                resultRow = row;
            }
        }
        return resultRow;
    } 
    /** Method of obtaining the group.
     * @param identifier String type variable, contains the identifier.
     * @return Return an integer, with the result.
     */
    private int getGroup(String identifier) {
        int resultRow = -1;
        for (int row = 0; row < connected.size(); row++) {
            if (connected.get(row).getGroupID().equals(identifier)) {
                resultRow = row;
            }
        }
        return resultRow;
    }
    /**  Method for sending the error.
     * @param ses is given by default.
     * @param erroDetail Detail of the error.
     */
    private void SendError(Session ses, String erroDetail) {
        Message errmsg = new Message();
        errmsg.setHeader("System");
        errmsg.setConfig("error");
        errmsg.setContent(erroDetail);
        try {
            ses.getBasicRemote().sendObject(errmsg);//se envia el mensaje
        } catch (IOException | EncodeException ex) {
            Logger.getLogger(Socket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /** Method for creating the session.
     * @param ses is given by default.
     * @param content It contains the context.
     * @return Return the session.
     */
    private MeSession createMeSession(Session ses, String content) {
        JsonObject json = Methods.stringToJSON(content);
        MeSession me = null;
        if (json.size() > 0) {
            me = new MeSession(ses);
            me.setEmail(Methods.JsonToString(json, "email", ""));
            me.setImage(Methods.JsonToString(json, "img", ""));
        }
        return me;
    }
}
