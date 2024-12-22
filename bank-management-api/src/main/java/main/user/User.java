package main.user;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;
import main.account.Account;

import java.util.*;


@Entity
@Serdeable
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int age;

    private  String email;

    private  String password;

    private String phoneNumber;

    private String address;

    @OneToMany(mappedBy = "user")
    private Set<Account> accounts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
