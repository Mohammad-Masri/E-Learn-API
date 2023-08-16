package com.elearn.app.elearnapi.apis.Dashboard.Course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elearn.app.elearnapi.modules.Course.CourseService;
import com.elearn.app.elearnapi.modules.Course.Course;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/dashboard/courses")
public class DashboardCourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<Course> getAllCourses() {
        return this.courseService.getAll();
    }

    @GetMapping("/{id}")
    public Course getCrouseDetails(@PathVariable String id) {
        Course topic = this.courseService.checkGetOneById(id);
        return topic;
    }

    @PostMapping
    public Course createNewCourse(@RequestBody CreateCourseBody body) {
        Course topic = this.courseService.create(body.getTitle(), body.getDescription(), body.getTopicIds());
        return topic;
    }

    @PutMapping("/{id}")
    public Course update(@PathVariable String id, @RequestBody CreateCourseBody body) {
        Course topic = this.courseService.update(id, body.getTitle(), body.getDescription(), body.getTopicIds());
        return topic;
    }

    @PutMapping("/{id}/is-published")
    public Course toggleIsPublished(@PathVariable String id) {
        Course topic = this.courseService.toggleIsPublished(id);
        return topic;
    }

    @DeleteMapping("/{id}")
    public Course delete(@PathVariable String id) {
        Course topic = this.courseService.delete(id);
        return topic;
    }

}
