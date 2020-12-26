/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socks;

import DataStaticBD.Methods;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.Reader;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * Class to decode the message sent to the webocket.
 * @author USUARIO
 */
public class DecorderMensaje implements Decoder.TextStream<Message> {

     /**
     * The message received is converted to a Json object, and from it parts are extracted and the object you want to work with is formed.
     * @param reader Reader type variable, receives the information to be read.
     * @return Message Return the message.
     * @throws DecodeException by the decoder.
     * @throws IOException for possible exceptions with jsonObject.
     */
    @Override
    public Message decode(Reader reader) throws DecodeException, IOException {
        Message msg = new Message();
        JsonParser parser = new JsonParser();
            JsonObject json = (JsonObject) parser.parse(reader);
            /**
            * the message parameters are extracted and transformed into a type of Message
            */
            msg.setHeader(Methods.JsonToString(json, "header", ""));
            msg.setContent(Methods.JsonToString(json, "content", ""));
            msg.setConfig(Methods.JsonToString(json, "config", "{}"));

        return msg;
    }

    @Override
    public void init(EndpointConfig config) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroy() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
