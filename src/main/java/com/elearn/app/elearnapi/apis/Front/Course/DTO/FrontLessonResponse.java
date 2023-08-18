package com.elearn.app.elearnapi.apis.Front.Course.DTO;

import com.elearn.app.elearnapi.modules.Asset.DTO.AssetResponse;
import com.elearn.app.elearnapi.modules.Lesson.Lesson;
import com.elearn.app.elearnapi.modules.Lesson.DTO.LessonResponse;

public class FrontLessonResponse extends LessonResponse {

    private Boolean isFree;

    public FrontLessonResponse() {
        super();
    }

    public FrontLessonResponse(Lesson lesson, AssetResponse asset, Boolean isPurchased) {
        super(lesson, asset);
        this.isFree = lesson.getIsFree();
        if (!isPurchased && !lesson.getIsFree()) {
            setVideo(null);
        }

    }

    public Boolean getIsFree() {
        return isFree;
    }

    public void setIsFree(Boolean isFree) {
        this.isFree = isFree;
    }

}
