package com.elearn.app.elearnapi.modules.Course;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.elearn.app.elearnapi.errors.HTTPServerError;
import com.elearn.app.elearnapi.modules.CourseTopic.CourseTopicService;
import com.elearn.app.elearnapi.modules.Topic.Topic;
import com.elearn.app.elearnapi.modules.Topic.TopicService;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseTopicService courseTopicService;

    @Autowired
    private TopicService topicService;

    public List<Course> getAll() {
        List<Course> courses = new ArrayList<>();
        this.courseRepository.findAll().forEach(courses::add);
        return courses;
    }

    public Course getOneById(String id) {
        Course course = this.courseRepository.findById(id).orElse(null);
        return course;
    }

    public Course checkGetOneById(String id) {
        Course course = this.getOneById(id);
        if (course == null) {
            throw new HTTPServerError(HttpStatus.NOT_FOUND, String.format("course with id %s is not found", id));
        }
        return course;
    }

    public List<Course> getAllInTopic(String topicId) {
        List<Course> courses = this.courseRepository.findByTopics_Id(topicId);
        return courses;
    }

    public Course create(String title, String description, String[] topicIds) {
        Set<Topic> topics = this.topicService.getAllByIds(topicIds);
        Course course = new Course(title, description, topics);
        course = this.courseRepository.save(course);
        return course;
    }

    public Course update(String id, String title, String description) {
        Course course = this.checkGetOneById(id);
        course.setTitle(title);
        course.setDescription(description);

        course = this.courseRepository.save(course);

        return course;
    }

    public Course delete(String id) {
        Course course = this.checkGetOneById(id);
        this.courseTopicService.deleteRelatedCourseTopicForThisCourse(course);

        // empty the topics array so spring can serialize the JSON object
        course.setTopics(new HashSet<>());

        this.courseRepository.delete(course);

        return course;
    }
}
