package com.elearn.app.elearnapi.apis.Front.Course.DTO;

import com.elearn.app.elearnapi.modules.Asset.DTO.AssetResponse;
import com.elearn.app.elearnapi.modules.Course.Course;
import com.elearn.app.elearnapi.modules.Course.DTO.CourseResponse;

public class FrontCourseInListResponse extends CourseResponse {

    private Boolean isFavorite;
    private Boolean isPurchased;

    public FrontCourseInListResponse() {
        super();
    }

    public FrontCourseInListResponse(Course course, Boolean isFavorite, Boolean isPurchased, AssetResponse asset) {
        super(course, asset);
        this.isFavorite = isFavorite;
        this.isPurchased = isPurchased;
    }

    public Boolean getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(Boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public Boolean getIsPurchased() {
        return isPurchased;
    }

    public void setIsPurchased(Boolean isPurchased) {
        this.isPurchased = isPurchased;
    }

}
