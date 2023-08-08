package com.elearn.app.elearnapi.modules.Topic;

import java.util.HashSet;
import java.util.Set;

import com.elearn.app.elearnapi.modules.Course.Course;
import com.elearn.app.elearnapi.utilities.JsonUtilities;

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
    private Set<Course> courses = new HashSet<>();

    public Topic() {
    }

    public Topic(
            String id,
            String title,
            String description) {
        this.id = id;
        this.title = title;
        this.description = description;
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

    @Override
    public String toString() {
        return JsonUtilities.convertToJson(this);
    }
}
