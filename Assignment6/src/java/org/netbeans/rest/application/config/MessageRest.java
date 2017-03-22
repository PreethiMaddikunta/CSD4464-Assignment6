/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
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
 
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    
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

    @GET
    @Path("{from}/{to}")
    @Produces("application/json")
    public Response getByDate(@PathParam("from") String fromStr, @PathParam("to") String toStr) {
        try {
            return Response.ok(messageController.getByDateJson(sdf.parse(fromStr), sdf.parse(toStr))).build();
        } catch (ParseException ex) {
            return Response.status(Response.Status.BAD_REQUEST).build();
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
