package com.hasanozgan.api.endpoints;

import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;

/**
 * This is the client server's redirect end point.  This end point will live in the client's server backend, not in this OAuth service.
 * This is end point is created so that we can see the Authorisation code that is sent to the client as a part of the redirect url.
 */
@Path("/redirect")
public class RedirectUriEndpoint {


    @GET
    public Response authorize(@Context HttpServletRequest request)
        throws URISyntaxException, OAuthSystemException {

        System.out.println("Request Params:" + request.getParameterMap());

        return Response.ok().build();
    }

}
