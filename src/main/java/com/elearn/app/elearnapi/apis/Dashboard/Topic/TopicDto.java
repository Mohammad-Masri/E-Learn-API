package com.elearn.app.elearnapi.apis.Dashboard.Topic;

class CreateTopicBody {
    private String title;
    private String description;

    public CreateTopicBody(
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
