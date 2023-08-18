package com.elearn.app.elearnapi.modules.User;

import java.util.List;
import java.util.ArrayList;

import com.elearn.app.elearnapi.config.constants.UserRole;
import com.elearn.app.elearnapi.modules.UserPurchasedCourse.UserPurchasedCourse;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;

    @Column(nullable = false, unique = true)
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "user")
    private List<UserPurchasedCourse> purchasedCourses = new ArrayList<>();

    public User() {
    }

    public User(
            String name,
            String email,
            String password,
            UserRole role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserPurchasedCourse> getPurchasedCourses() {
        return purchasedCourses;
    }

    public void setPurchasedCourses(List<UserPurchasedCourse> purchasedCourses) {
        this.purchasedCourses = purchasedCourses;
    }

    @Override
    public String toString() {
        return "id: " + this.id + ", name: " + this.name + ", email: " + this.email;
    }

}
