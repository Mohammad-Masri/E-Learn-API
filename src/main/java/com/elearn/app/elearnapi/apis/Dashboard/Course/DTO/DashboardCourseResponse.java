package com.elearn.app.elearnapi.apis.Dashboard.Course.DTO;

import java.util.List;

import com.elearn.app.elearnapi.apis.Dashboard.Topic.DTO.DashboardTopicResponse;
import com.elearn.app.elearnapi.modules.Course.Course;

public class DashboardCourseResponse extends DashboardCourseInListResponse {

    private List<DashboardTopicResponse> topics = null;
    private List<DashboardLessonResponse> lessons = null;

    public DashboardCourseResponse() {
        super();
    }

    public DashboardCourseResponse(Course course, List<DashboardTopicResponse> topics,
            List<DashboardLessonResponse> lessons) {
        super(course);
        this.topics = topics;
        this.lessons = lessons;
    }

    public List<DashboardTopicResponse> getTopics() {
        return topics;
    }

    public void setTopics(List<DashboardTopicResponse> topics) {
        this.topics = topics;
    }

    public List<DashboardLessonResponse> getLessons() {
        return lessons;
    }

    public void setLessons(List<DashboardLessonResponse> lessons) {
        this.lessons = lessons;
    }

}
