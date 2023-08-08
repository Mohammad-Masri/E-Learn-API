package com.elearn.app.elearnapi.apis.Dashboard.Course;

class CreateCourseBody {
    private String title;
    private String description;

    public CreateCourseBody(
            String title,
            String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

}
