
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author preet
 */

@Path("/hello")
public class Hello {
    
    /**
     *
     * @return
     */
    @GET
    @Produces("application/json")
    public String hello() {
        JsonObject json = Json.createObjectBuilder()
                .add("Hello", Json.createArrayBuilder()
                        .add("Java")
                        .add("Html")
                        .add("Css")
                    )
                .build();

        return json.toString();
    }
}
