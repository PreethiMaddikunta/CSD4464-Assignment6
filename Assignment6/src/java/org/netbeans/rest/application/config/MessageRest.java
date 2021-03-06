/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author c0688963
 */
@Path("/messages")
@ApplicationScoped
public class MessageRest {
    
    @Inject
    private MessageController messageController;
 
    private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    
     @GET
    @Produces("application/json")
    public Response getAll() {
        return Response.ok(messageController.getAllJson()).build();
    }
    
    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response getById(@PathParam("id") int id) {
        JsonObject json = messageController.getByIdJson(id);
        if (json != null) {
            return Response.ok(json).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    
    
    @DELETE
    @Path("{id}")
    public Response del(@PathParam("id") int id) {
        if (messageController.deleteById(id)) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
