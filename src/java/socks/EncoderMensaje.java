/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socks;

import java.io.IOException;
import java.io.Writer;
import javax.json.Json;
import javax.json.JsonWriter;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 *Class to encode the message sent to the webocket.
 * @author USUARIO
 */
public class EncoderMensaje implements Encoder.TextStream<Message>{
    /** Method to encrypt.
     * @param msg receives the message to be encrypted.
     * @param wrt Writer type variable, to write.
     * @throws EncodeException by the encoder.
     * @throws IOException for possible exceptions with jsonObject.
     */
    @Override
    public void encode(Message msg, Writer wrt) throws EncodeException, IOException {

        javax.json.JsonObject json = Json.createObjectBuilder()
             .add("header", msg.getHeader())
             .add("content", msg.getContent())
             .add("config", msg.getConfig())
                .build();
        /*
        A jsonObject is created using the Gson library and parameters are added:
            header, content, config
        */
        try (JsonWriter jsonWriter = Json.createWriter(wrt)){
            jsonWriter.writeObject(json);
        }catch(Exception e)
        {
            System.out.println("enconding dice:"+e.getMessage());
        }
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
