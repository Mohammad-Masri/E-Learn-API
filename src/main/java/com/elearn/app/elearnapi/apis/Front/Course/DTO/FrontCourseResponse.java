package com.elearn.app.elearnapi.apis.Front.Course.DTO;

import java.util.List;

import com.elearn.app.elearnapi.apis.Front.Topic.DTO.FrontTopicResponse;
import com.elearn.app.elearnapi.modules.Asset.DTO.AssetResponse;
import com.elearn.app.elearnapi.modules.Course.Course;

public class FrontCourseResponse extends FrontCourseInListResponse {

    private List<FrontTopicResponse> topics = null;
    private List<FrontLessonResponse> lessons = null;

    public FrontCourseResponse() {
        super();
    }

    public FrontCourseResponse(Course course, Boolean isFavorite, Boolean isPurchased, List<FrontTopicResponse> topics,
            List<FrontLessonResponse> lessons, AssetResponse asset) {
        super(course, isFavorite, isPurchased, asset);
        this.lessons = lessons;
        this.topics = topics;
    }

    public List<FrontLessonResponse> getLessons() {
        return lessons;
    }

    public void setLessons(List<FrontLessonResponse> lessons) {
        this.lessons = lessons;
    }

    public List<FrontTopicResponse> getTopics() {
        return topics;
    }

    public void setTopics(List<FrontTopicResponse> topics) {
        this.topics = topics;
    }

}
