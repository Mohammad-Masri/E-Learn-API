package com.elearn.app.elearnapi.apis.Dashboard.Course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elearn.app.elearnapi.modules.Course.CourseService;
import com.elearn.app.elearnapi.modules.Lesson.Lesson;
import com.elearn.app.elearnapi.modules.Lesson.LessonService;
import com.elearn.app.elearnapi.utilities.PrintUtilities;
import com.elearn.app.elearnapi.apis.Dashboard.Course.DTO.DashboardCourseResponse;
import com.elearn.app.elearnapi.apis.Dashboard.Course.DTO.DashboardLessonResponse;
import com.elearn.app.elearnapi.modules.Course.Course;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/dashboard/courses")
public class DashboardCourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private LessonService lessonService;

    @GetMapping
    public List<DashboardCourseResponse> getAllCourses() {
        List<Course> courses = this.courseService.getAll();
        List<DashboardCourseResponse> courseResponses = this.courseService.makeDashboardCoursesResponse(courses);
        return courseResponses;
    }

    @GetMapping("/{id}")
    public DashboardCourseResponse getCrouseDetails(@PathVariable String id) {
        Course course = this.courseService.checkGetOneById(id);
        DashboardCourseResponse courseResponse = this.courseService.makeDashboardCourseResponse(course);
        return courseResponse;
    }

    @PostMapping
    public DashboardCourseResponse createNewCourse(@RequestBody CreateCourseBody body) {
        Course course = this.courseService.create(body.getTitle(), body.getDescription(), body.getTopicIds());
        DashboardCourseResponse courseResponse = this.courseService.makeDashboardCourseResponse(course);

        return courseResponse;
    }

    @PutMapping("/{id}")
    public DashboardCourseResponse updateCourse(@PathVariable String id, @RequestBody CreateCourseBody body) {
        Course course = this.courseService.update(id, body.getTitle(), body.getDescription(), body.getTopicIds());
        DashboardCourseResponse courseResponse = this.courseService.makeDashboardCourseResponse(course);

        return courseResponse;
    }

    @PutMapping("/{id}/is-published")
    public DashboardCourseResponse toggleIsPublished(@PathVariable String id) {
        Course course = this.courseService.toggleIsPublished(id);
        DashboardCourseResponse courseResponse = this.courseService.makeDashboardCourseResponse(course);

        return courseResponse;
    }

    @DeleteMapping("/{id}")
    public DashboardCourseResponse deleteCrouse(@PathVariable String id) {
        Course course = this.courseService.delete(id);
        DashboardCourseResponse courseResponse = this.courseService.makeDashboardCourseResponse(course);

        return courseResponse;
    }

    @PostMapping("/{courseId}/lessons")
    public DashboardLessonResponse createLesson(@PathVariable String courseId, @RequestBody CreateLessonBody body) {
        Lesson lesson = this.lessonService.create(courseId, body.getTitle(), body.getDescription(), body.getUrl());
        DashboardLessonResponse lessonResponse = this.lessonService.makeDashboardLessonResponse(lesson);
        return lessonResponse;
    }

    @GetMapping("/{courseId}/lessons")
    public List<DashboardLessonResponse> getCourseLessons(@PathVariable String courseId) {
        Course course = this.courseService.checkGetOneById(courseId);
        List<DashboardLessonResponse> lessonResponse = this.lessonService
                .makeDashboardLessonsResponse(course.getLessons());
        return lessonResponse;
    }

    @GetMapping("/{courseId}/lessons/{lessonId}")
    public DashboardLessonResponse getLessonDetails(@PathVariable String courseId, @PathVariable String lessonId) {
        Course course = this.courseService.checkGetOneById(courseId);
        Lesson lesson = this.lessonService.checkGetOneByIdInCourse(lessonId, course);
        DashboardLessonResponse lessonResponse = this.lessonService.makeDashboardLessonResponse(lesson);
        return lessonResponse;
    }

    @PutMapping("/{courseId}/lessons/{lessonId}")
    public DashboardLessonResponse updateLesson(@PathVariable String courseId, @PathVariable String lessonId,
            @RequestBody CreateLessonBody body) {
        Course course = this.courseService.checkGetOneById(courseId);
        Lesson lesson = this.lessonService.update(lessonId, course, body.getTitle(), body.getDescription(),
                body.getUrl());
        DashboardLessonResponse lessonResponse = this.lessonService.makeDashboardLessonResponse(lesson);
        return lessonResponse;
    }

    @DeleteMapping("/{courseId}/lessons/{lessonId}")
    public DashboardLessonResponse deleteLesson(@PathVariable String courseId, @PathVariable String lessonId) {
        Course course = this.courseService.checkGetOneById(courseId);
        Lesson lesson = this.lessonService.delete(lessonId, course);
        DashboardLessonResponse lessonResponse = this.lessonService.makeDashboardLessonResponse(lesson);
        return lessonResponse;
    }

}
