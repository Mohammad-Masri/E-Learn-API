package com.elearn.app.elearnapi.apis.Front.Course.DTO;

import com.elearn.app.elearnapi.modules.Lesson.Lesson;
import com.elearn.app.elearnapi.modules.Lesson.DTO.LessonResponse;

public class FrontLessonResponse extends LessonResponse {

    private Boolean isFree;

    public FrontLessonResponse() {
        super();
    }

    public FrontLessonResponse(Lesson lesson, Boolean isPurchased) {
        super(lesson);
        this.isFree = lesson.getIsFree();
        if (!isPurchased && !lesson.getIsFree()) {
            setURL(null);
        }

    }

    public Boolean getIsFree() {
        return isFree;
    }

    public void setIsFree(Boolean isFree) {
        this.isFree = isFree;
    }

}
