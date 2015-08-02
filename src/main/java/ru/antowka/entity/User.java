package ru.antowka.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by anton on 25.07.15.
 */

public class User {

    private Integer userId;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String email;
    private Set<Role> role = new HashSet<Role>(0);
    private boolean enable;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public  Set<Role> getRole() {
        return this.role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}