/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cm.restimpl.helloworld;

import com.tamere.rest.api.RestServicesInterface;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author tamere
 */
@Path("/byebyeworld")
public class ByeByeResource implements RestServicesInterface {

    @GET
    @Produces("text/plain")
    public String getClichedMessage() {
        return "Bye Bye";
    }
}
