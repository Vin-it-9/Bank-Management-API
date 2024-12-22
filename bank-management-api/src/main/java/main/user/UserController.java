package main.user;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import java.util.List;

@Controller("/users")
public class UserController {

    @Inject
    private UserService service;

    @Post
    public User createUser(@Body User user) {
        return service.save(user);
    }

    @Get("/all")
    public List<User> getAllUsers() {
        return service.findAll();
    }


    @Get("/{id}")
    public User getUserById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Delete("/{id}")
    public HttpResponse<?> deleteUser(@PathVariable Long id) {

        boolean isDeleted = service.delete(id);

        if (isDeleted) {
            return HttpResponse.noContent();
        } else {
            return HttpResponse.notFound("User not found");
        }

    }

    @Put("/update/{id}")
    public HttpResponse<User> updateUser(@PathVariable Long id, @Body User user) {
        User updatedUser = service.update(id, user);

        if (updatedUser != null) {
            return HttpResponse.ok(updatedUser);
        } else {
            return HttpResponse.notFound();
        }
    }

    @Post("/saveall")
    public HttpResponse<List<User>> postUser(@Body List<User> users) {
        return HttpResponse.ok(service.saveall(users));
    }

    @Put("/change/{id}")
    public HttpResponse<User> changepassword(@PathVariable Long id, @Body String password) {
            return HttpResponse.ok(service.changepassword(id, password));
    }

    @Get("/email/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return service.findbyemail(email);
    }

}
