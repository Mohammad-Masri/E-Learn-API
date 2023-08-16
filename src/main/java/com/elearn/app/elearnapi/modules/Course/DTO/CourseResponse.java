package com.elearn.app.elearnapi.modules.Course.DTO;

import com.elearn.app.elearnapi.modules.Course.Course;

public abstract class CourseResponse {

    private Long id;
    private String title;
    private String description;

    public CourseResponse() {
    }

    public CourseResponse(
            Course course) {
        this.id = course.getId();
        this.title = course.getTitle();
        this.description = course.getDescription();
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

}
