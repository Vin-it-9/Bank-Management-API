package Users;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.inject.Inject;

import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserService userService;


    @POST
    public Response createUser(User user) {
        User savedUser = userService.updateOrSaveUser(user);
        return Response.status(Response.Status.CREATED).entity(savedUser).build();
    }

    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") Long id) {

        User user = userService.findUserById(id);

        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(user).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUserById(@PathParam("id") Long id) {
        userService.deleteUserById(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @Path("/all")
    @GET
    public Response getAllUsers() {
        List<User> users = userService.findAllUsers();
        return Response.ok(users).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateUserById(@PathParam("id") int id, User updatedUser) {

        updatedUser.setId(id);
        User updated = userService.updateOrSaveUser(updatedUser);

        if (updated == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
        }

        return Response.ok(updated).build();
    }



}
