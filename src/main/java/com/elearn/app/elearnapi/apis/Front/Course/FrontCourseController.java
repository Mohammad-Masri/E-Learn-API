package com.elearn.app.elearnapi.apis.Front.Course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elearn.app.elearnapi.apis.Dashboard.Course.DTO.FrontCourseInListResponse;
import com.elearn.app.elearnapi.modules.Course.Course;
import com.elearn.app.elearnapi.modules.Course.CourseService;

@RestController
@RequestMapping("/front/courses")
public class FrontCourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<FrontCourseInListResponse> getAllCourses() {
        List<Course> courses = this.courseService.getAll();
        List<FrontCourseInListResponse> courseResponses = this.courseService
                .makeFrontCoursesInListResponse(courses);
        return courseResponses;
    }
}
