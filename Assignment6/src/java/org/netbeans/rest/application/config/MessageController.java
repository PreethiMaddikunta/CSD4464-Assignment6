/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

/**
 *
 * @author c0688963
 */
@ApplicationScoped
public class MessageController {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    private List<Message> messages;

    public MessageController() {
        retrieveAllMessages();
    }
    
    public void retrieveAllMessages() {
         
            if (messages == null) {
                messages = new ArrayList<>();
            }
            messages.clear();
         
    }
    
    public JsonArray getAllJson() {
        JsonArrayBuilder json = Json.createArrayBuilder();
        for (Message mess : messages) {
            json.add(mess.toJson());
        }
        return json.build();
    }
    
    public Message getById(int id) {
        for (Message mess : messages) {
            if (mess.getId() == id) {
                return mess;
            }
        }
        return null;
    }
    
    public JsonObject getByIdJson(int id) {
        Message mess = getById(id);
            return getById(id).toJson();
        }
    
     public JsonObject addJson(JsonObject json) {
        Message mess = new Message(json);
        messages.add(mess);
        return mess.toJson();
    }
     
     public boolean deleteById(int id) {
        Message mess = getById(id);
        if (mess != null) {
            messages.remove(mess);
            return true;
        } else {
            return false;
        }
    }
}
