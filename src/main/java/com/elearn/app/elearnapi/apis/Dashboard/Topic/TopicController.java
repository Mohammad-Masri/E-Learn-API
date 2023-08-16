package com.elearn.app.elearnapi.apis.Dashboard.Topic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elearn.app.elearnapi.apis.Dashboard.Course.DTO.DashboardCourseResponse;
import com.elearn.app.elearnapi.apis.Dashboard.Topic.DTO.DashboardTopicResponse;
import com.elearn.app.elearnapi.modules.Course.Course;
import com.elearn.app.elearnapi.modules.Course.CourseService;
import com.elearn.app.elearnapi.modules.Topic.Topic;
import com.elearn.app.elearnapi.modules.Topic.TopicService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/dashboard/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<DashboardTopicResponse> getAllTopics() {
        List<Topic> topics = this.topicService.getAll();
        List<DashboardTopicResponse> dashboardTopicResponses = this.topicService.makeDashboardTopicsResponse(topics);
        return dashboardTopicResponses;
    }

    @GetMapping("/{id}")
    public DashboardTopicResponse getTopicDetails(@PathVariable String id) {
        Topic topic = this.topicService.checkGetOneById(id);
        DashboardTopicResponse dashboardTopicResponse = this.topicService.makeDashboardTopicResponse(topic);
        return dashboardTopicResponse;
    }

    @GetMapping("/{id}/courses")
    public List<DashboardCourseResponse> getCoursesInTopic(@PathVariable String id) {
        Topic topic = this.topicService.checkGetOneById(id);
        List<Course> courses = this.courseService.getAllInTopic(topic.getId());
        List<DashboardCourseResponse> courseResponses = this.courseService.makeDashboardCoursesResponse(courses);

        return courseResponses;
    }

    @PostMapping
    public DashboardTopicResponse createNewTopic(@RequestBody CreateTopicBody body) {
        Topic topic = this.topicService.create(body.getTitle(), body.getDescription());
        DashboardTopicResponse dashboardTopicResponse = this.topicService.makeDashboardTopicResponse(topic);
        return dashboardTopicResponse;
    }

    @PutMapping("/{id}")
    public DashboardTopicResponse update(@PathVariable String id, @RequestBody CreateTopicBody body) {
        Topic topic = this.topicService.update(id, body.getTitle(), body.getDescription());
        DashboardTopicResponse dashboardTopicResponse = this.topicService.makeDashboardTopicResponse(topic);

        return dashboardTopicResponse;
    }

    @DeleteMapping("/{id}")
    public DashboardTopicResponse delete(@PathVariable String id) {
        Topic topic = this.topicService.delete(id);
        DashboardTopicResponse dashboardTopicResponse = this.topicService.makeDashboardTopicResponse(topic);
        return dashboardTopicResponse;
    }

}
