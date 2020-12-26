/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socks;

/**
 * This class is used to contain the session and the identifier of the person to whom the information will be sent.
 * @author tonyp
 */

import javax.websocket.Session;

public class MeSession {
    
    private String email = "";
    private String image = "";
    private Session sesion;
    
    public MeSession(){}
    
    public MeSession(Session sesion){
        this.sesion = sesion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Session getSesion() {
        return sesion;
    }

    public void setSesion(Session sesion) {
        this.sesion = sesion;
    }
    
}
