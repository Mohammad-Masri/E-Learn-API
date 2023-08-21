package com.elearn.app.elearnapi.apis.Dashboard.Course.DTO;

import com.elearn.app.elearnapi.modules.Asset.DTO.AssetResponse;
import com.elearn.app.elearnapi.modules.Course.Course;
import com.elearn.app.elearnapi.modules.Course.DTO.CourseResponse;

public class DashboardCourseInListResponse extends CourseResponse {

    private Boolean isPublished;

    public DashboardCourseInListResponse() {
        super();
    }

    public DashboardCourseInListResponse(Course course, AssetResponse asset) {
        super(course, asset);
        this.isPublished = course.getIsPublished();
    }

    public Boolean getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(Boolean isPublished) {
        this.isPublished = isPublished;
    }

}
