/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cm.restimpl.helloworld;

/**
 *
 * @author tamere
 */
import com.tamere.rest.api.RestServicesInterface;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/helloworld")
public class HelloWorldResource implements RestServicesInterface{

    @GET 
    @Produces("text/plain")
    
    public String getClichedMessage() {
        return "Hello World";
    }
}