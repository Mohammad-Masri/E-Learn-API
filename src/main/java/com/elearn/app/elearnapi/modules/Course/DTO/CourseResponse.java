package com.elearn.app.elearnapi.modules.Course.DTO;

import com.elearn.app.elearnapi.modules.Asset.DTO.AssetResponse;
import com.elearn.app.elearnapi.modules.Course.Course;

public abstract class CourseResponse {

    private Long id;
    private String title;
    private String description;
    private Double price;
    private Boolean isFeatured;
    private int enrollmentCount;
    private AssetResponse image;

    public CourseResponse() {
    }

    public CourseResponse(
            Course course,
            AssetResponse image) {
        this.id = course.getId();
        this.title = course.getTitle();
        this.price = course.getPrice();
        this.enrollmentCount = course.getEnrollmentCount();
        this.description = course.getDescription();
        this.isFeatured = course.getIsFeatured();
        this.image = image;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEnrollmentCount() {
        return enrollmentCount;
    }

    public void setEnrollmentCount(int enrollmentCount) {
        this.enrollmentCount = enrollmentCount;
    }

    public Boolean getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(Boolean isFeatured) {
        this.isFeatured = isFeatured;
    }

    public AssetResponse getImage() {
        return image;
    }

    public void setImage(AssetResponse image) {
        this.image = image;
    }

}
