package com.elearn.app.elearnapi.apis.Dashboard.Course.DTO;

import com.elearn.app.elearnapi.modules.Asset.DTO.AssetResponse;
import com.elearn.app.elearnapi.modules.Lesson.Lesson;
import com.elearn.app.elearnapi.modules.Lesson.DTO.LessonResponse;

public class DashboardLessonResponse extends LessonResponse {

    private Boolean isFree;
    private Boolean isPublished;

    public DashboardLessonResponse() {
        super();
    }

    public DashboardLessonResponse(Lesson lesson, AssetResponse asset) {
        super(lesson, asset);
        this.isFree = lesson.getIsFree();
        this.isPublished = lesson.getIsPublished();
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

}
