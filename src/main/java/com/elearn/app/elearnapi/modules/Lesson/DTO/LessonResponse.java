package com.elearn.app.elearnapi.modules.Lesson.DTO;

import com.elearn.app.elearnapi.modules.Lesson.Lesson;

public abstract class LessonResponse {

    private Long id;
    private int number;
    private String title;
    private String description;
    private String URL;

    public LessonResponse() {
    }

    public LessonResponse(Lesson lesson) {

        this.id = lesson.getId();
        this.number = lesson.getNumber();
        this.title = lesson.getTitle();
        this.description = lesson.getDescription();
        this.URL = lesson.getURL();
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String uRL) {
        URL = uRL;
    }

}
