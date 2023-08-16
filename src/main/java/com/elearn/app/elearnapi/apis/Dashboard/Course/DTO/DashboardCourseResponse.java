package com.elearn.app.elearnapi.apis.Dashboard.Course.DTO;

import java.util.List;

import com.elearn.app.elearnapi.apis.Dashboard.Topic.DTO.DashboardTopicResponse;
import com.elearn.app.elearnapi.modules.Course.Course;
import com.elearn.app.elearnapi.modules.Course.DTO.CourseResponse;

public class DashboardCourseResponse extends CourseResponse {

    private List<DashboardTopicResponse> topics = null;

    private Boolean isPublished;

    public DashboardCourseResponse() {
        super();
    }

    public DashboardCourseResponse(Course course, List<DashboardTopicResponse> topics) {
        super(course);
        this.topics = topics;
        this.isPublished = course.getIsPublished();
    }

    public List<DashboardTopicResponse> getTopics() {
        return topics;
    }

    public void setTopics(List<DashboardTopicResponse> topics) {
        this.topics = topics;
    }

    public Boolean getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(Boolean isPublished) {
        this.isPublished = isPublished;
    }

}
