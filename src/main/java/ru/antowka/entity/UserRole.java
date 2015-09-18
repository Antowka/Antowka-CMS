package ru.antowka.entity;


/**
 * Created by anton on 30.07.15.
 */

public class UserRole {

    private int roleId;
    private String userRole;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getUserRole() {
        return this.userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
