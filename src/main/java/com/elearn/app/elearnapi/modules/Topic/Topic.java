package com.elearn.app.elearnapi.modules.Topic;

import java.util.HashSet;
import java.util.Set;

import com.elearn.app.elearnapi.modules.Course.Course;
import com.elearn.app.elearnapi.utilities.JsonUtilities;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Topic {

    @Id
    private String id;
    private String title;
    private String description;

    @ManyToMany(mappedBy = "topics")
    private Set<Course> courses;

    public Topic() {
    }

    public Topic(
            String id,
            String title,
            String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.courses = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    @JsonIgnore
    public Set<Course> getCourses() {
        return courses;
    }

    @Override
    public String toString() {
        return JsonUtilities.convertToJson(this);
    }
}
