package com.elearn.app.elearnapi.apis.Dashboard.Course;

import com.elearn.app.elearnapi.utilities.JsonUtilities;

class CreateCourseBody {
    private String title;
    private String description;
    private String[] topicIds = new String[0];

    public CreateCourseBody(
            String title,
            String description,
            String[] topicIds) {
        this.title = title;
        this.description = description;
        this.topicIds = topicIds;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String[] getTopicIds() {
        return topicIds;
    }

    @Override
    public String toString() {
        return JsonUtilities.convertToJson(this);
    }

}

class CreateLessonBody {

    String title;
    String description;
    String url;

    public CreateLessonBody() {
    }

    public CreateLessonBody(
            String title,
            String description,
            String url) {
        this.title = title;
        this.description = description;
        this.url = url;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return JsonUtilities.convertToJson(this);
    }
}