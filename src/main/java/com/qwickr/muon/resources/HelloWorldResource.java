package com.qwickr.muon.resources;

import javax.annotation.security.PermitAll;
import io.dropwizard.auth.Auth;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.qwickr.muon.auth.AuthenticatedUser;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {

    public HelloWorldResource() {
    }

    @GET
    @PermitAll
    public String sayHello(@Auth AuthenticatedUser user) {
        return "Hello";
    }
}
