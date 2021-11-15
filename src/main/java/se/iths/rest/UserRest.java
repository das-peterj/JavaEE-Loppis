package se.iths.rest;

import se.iths.entity.User;
import se.iths.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserRest {

    @Inject
    UserService userService;

    @Path("new")
    @POST
    public Response createUser(User user) {
        user = userService.createUser(user);
        return Response.ok(user).build();
    }

    @Path("{id}")
    @GET
    public Response getUser(@PathParam("id") Long id) {
        User foundUser = userService.findUserById(id);
        return Response.ok(foundUser).build();
    }



}
