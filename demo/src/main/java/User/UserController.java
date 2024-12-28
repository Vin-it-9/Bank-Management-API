package User;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.inject.Inject;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserService userService;


    @POST
    public Response createUser(User user) {
        User savedUser = userService.saveuser(user);
        return Response.status(Response.Status.CREATED).entity(savedUser).build();
    }

//    @GET
//    @Path("/{id}")
//    public Response getUserById(@PathParam("id") Long id) {
//        User user = userService.findUserById(id);
//        if (user == null) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//        return Response.ok(user).build();
//    }


}
