/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MessageWebSocket;

import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.netbeans.rest.application.config.MessageController;

/**
 *
 * @author c0688963
 */
@ServerEndpoint("/socket")
@ApplicationScoped
public class MessageWebSocket {
    
    @Inject
    MessageController messageController;

    private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
            String output = "";
            JsonObject json = Json.createReader(new StringReader(message)).readObject();
            if (json.containsKey("getAll")) {
                output = messageController.getAllJson().toString();
            } else if (json.containsKey("getById")) {
                output = messageController.getByIdJson(json.getInt("getById")).toString();
            
            } else if (json.containsKey("delete")) {
                output = Json.createObjectBuilder()
                        .add("ok", messageController.deleteById(json.getInt("delete")))
                        .build().toString();
            } else {
                output = Json.createObjectBuilder()
                        .add("error", "Request not valid")
                        .add("original", json)
                        .build().toString();
            }
            session.getBasicRemote().sendText(output);
        
        
    }

}
