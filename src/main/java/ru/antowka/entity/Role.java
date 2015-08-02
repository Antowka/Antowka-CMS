package ru.antowka.entity;


import java.util.Set;

/**
 * Created by anton on 30.07.15.
 */

public class Role {

    private int roleId;
    private String role;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
