package com.elearn.app.elearnapi.modules.Course;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.elearn.app.elearnapi.modules.Lesson.Lesson;
import com.elearn.app.elearnapi.modules.Topic.Topic;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Boolean isPublished;

    @ManyToMany
    @JoinTable(name = "course_topic", joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns = @JoinColumn(name = "topic_id"))
    private Set<Topic> topics = new HashSet<>();

    @OneToMany(mappedBy = "course")
    private List<Lesson> lessons = new ArrayList<>();

    public Course() {
    }

    public Course(
            String title,
            String description,
            Set<Topic> topics) {
        this.title = title;
        this.description = description;
        this.topics = topics;
        this.isPublished = false;
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

    public Set<Topic> getTopics() {
        return topics;
    }

    public void setTopics(Set<Topic> topics) {
        this.topics = topics;
    }

    public Boolean getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(Boolean isPublished) {
        this.isPublished = isPublished;
    }

    @Override
    public String toString() {
        return "id: " + this.id + ", title: " + this.title;
    }
}
