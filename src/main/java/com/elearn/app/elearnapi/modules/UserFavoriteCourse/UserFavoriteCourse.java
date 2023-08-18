package com.elearn.app.elearnapi.modules.UserFavoriteCourse;

import java.time.LocalTime;
import java.time.ZoneOffset;

import com.elearn.app.elearnapi.modules.Course.Course;
import com.elearn.app.elearnapi.modules.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class UserFavoriteCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private LocalTime likedAt;

    public UserFavoriteCourse() {
        this.likedAt = LocalTime.now(ZoneOffset.UTC);
    }

    public UserFavoriteCourse(User user, Course course) {
        this.course = course;
        this.user = user;
        this.likedAt = LocalTime.now(ZoneOffset.UTC);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonIgnore
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalTime getLikedAt() {
        return likedAt;
    }

    public void setLikedAt(LocalTime likedAt) {
        this.likedAt = likedAt;
    }

}
