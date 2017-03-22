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
}
