package ru.antowka.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by anton on 30.07.15.
 */

@Entity
@Table(name="role")
public class UserRole {

    private int roleId;
    private int userId;
    private String role;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
