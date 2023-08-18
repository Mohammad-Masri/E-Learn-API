package com.elearn.app.elearnapi.modules.Lesson;

import com.elearn.app.elearnapi.modules.Asset.Asset;
import com.elearn.app.elearnapi.modules.Course.Course;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int number;
    private String title;
    private String description;
    private Boolean isFree;
    private Boolean isPublished;

    @OneToOne
    @JoinColumn(name = "asset_id")
    private Asset video;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public Lesson() {
    }

    public Lesson(
            int number,
            String title,
            String description,
            Asset video,
            Course course) {

        this.number = number;
        this.title = title;
        this.description = description;
        this.video = video;
        this.course = course;
        this.isFree = false;
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

    public Boolean getIsFree() {
        return isFree;
    }

    public void setIsFree(Boolean isFree) {
        this.isFree = isFree;
    }

    public Boolean getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(Boolean isPublished) {
        this.isPublished = isPublished;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Asset getVideo() {
        return video;
    }

    public void setVideo(Asset video) {
        this.video = video;
    }
}
