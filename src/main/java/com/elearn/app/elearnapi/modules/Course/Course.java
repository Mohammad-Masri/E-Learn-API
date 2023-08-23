package com.elearn.app.elearnapi.modules.Course;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.elearn.app.elearnapi.modules.Asset.Asset;
import com.elearn.app.elearnapi.modules.Lesson.Lesson;
import com.elearn.app.elearnapi.modules.Topic.Topic;
import com.elearn.app.elearnapi.modules.UserFavoriteCourse.UserFavoriteCourse;
import com.elearn.app.elearnapi.modules.UserPurchasedCourse.UserPurchasedCourse;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    private Boolean isPublished;
    private Boolean isFeatured;

    private int enrollmentCount;

    @OneToOne
    @JoinColumn(name = "asset_id")
    private Asset image;

    @Column(nullable = false)
    private Double price;

    @ManyToMany
    @JoinTable(name = "course_topic", joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns = @JoinColumn(name = "topic_id"))
    private Set<Topic> topics = new HashSet<>();

    @OneToMany(mappedBy = "course")
    private List<Lesson> lessons = new ArrayList<>();

    @OneToMany(mappedBy = "course")
    private List<UserPurchasedCourse> purchasedByUsers = new ArrayList<>();

    @OneToMany(mappedBy = "course")
    private List<UserFavoriteCourse> likedByUsers = new ArrayList<>();

    public Course() {
    }

    public Course(
            String title,
            String description,
            Double price,
            Asset image,
            Set<Topic> topics) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.topics = topics;
        this.image = image;
        this.isPublished = false;
        this.isFeatured = false;
        this.enrollmentCount = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getEnrollmentCount() {
        return enrollmentCount;
    }

    public void setEnrollmentCount(int enrollmentCount) {
        this.enrollmentCount = enrollmentCount;
    }

    public Set<Topic> getTopics() {
        return topics;
    }

    public Asset getImage() {
        return image;
    }

    public void setImage(Asset image) {
        this.image = image;
    }

    public void setTopics(Set<Topic> topics) {
        this.topics = topics;
    }

    public Boolean getIsPublished() {
        return isPublished;
    }

    public Boolean getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(Boolean isFeatured) {
        this.isFeatured = isFeatured;
    }

    public void setIsPublished(Boolean isPublished) {
        this.isPublished = isPublished;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    @JsonIgnore
    public List<UserPurchasedCourse> getPurchasedByUsers() {
        return purchasedByUsers;
    }

    public void setPurchasedByUsers(List<UserPurchasedCourse> purchasedByUsers) {
        this.purchasedByUsers = purchasedByUsers;
    }

    @JsonIgnore
    public List<UserFavoriteCourse> getLikedByUsers() {
        return likedByUsers;
    }

    public void setLikedByUsers(List<UserFavoriteCourse> likedByUsers) {
        this.likedByUsers = likedByUsers;
    }

    @Override
    public String toString() {
        return "id: " + this.id + ", title: " + this.title;
    }
}
