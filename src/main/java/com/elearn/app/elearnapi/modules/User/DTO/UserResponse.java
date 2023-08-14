package com.elearn.app.elearnapi.modules.User.DTO;

import com.elearn.app.elearnapi.config.UserRole;
import com.elearn.app.elearnapi.modules.User.User;

public class UserResponse {
    private String id;
    private String name;
    private String email;
    private UserRole role;

    public UserResponse() {
    }

    public UserResponse(
            User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.role = user.getRole();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

}
