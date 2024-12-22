package main;

import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import main.user.User;
import main.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jakarta.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MicronautTest
class DemoTest {

    @Inject
    EmbeddedApplication<?> application;

    @Inject
    UserService userservice;


    @Test
    void testItWorks() {
        Assertions.assertTrue(application.isRunning());
    }

    @Test
    void saveUser() {
        User user = new User();
        user.setName("John");
        user.setEmail("john@example.com");
        user.setPassword("password");
        userservice.save(user);
    }

    @Test
    void getUser() {
        long id = 2;
        userservice.findById(id);
    }

}
