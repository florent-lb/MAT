package com.extia;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import io.quarkus.security.identity.SecurityIdentity;

@Path("hello")
public class Hello {
    
    @Inject
    SecurityIdentity identity;

    @GET
    public SecurityIdentity identity()
    {
        return identity;
    }
    
}
