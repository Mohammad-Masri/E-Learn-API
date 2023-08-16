package com.elearn.app.elearnapi.modules.Lesson;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elearn.app.elearnapi.apis.Dashboard.Course.DTO.DashboardLessonResponse;
import com.elearn.app.elearnapi.modules.Course.Course;
import com.elearn.app.elearnapi.modules.Course.CourseService;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private CourseService courseService;

    public Lesson create(String courseId, String title, String description, String URL) {
        Course course = this.courseService.checkGetOneById(courseId);
        List<Lesson> lessons = course.getLessons();
        int number = lessons.size() + 1;
        Lesson lesson = new Lesson(number, title, description, URL, course);
        lesson = this.lessonRepository.save(lesson);
        return lesson;
    }

    public DashboardLessonResponse makeDashboardLessonResponse(Lesson lesson) {
        return new DashboardLessonResponse(lesson);
    }

    public List<DashboardLessonResponse> makeDashboardLessonsResponse(List<Lesson> lessons) {
        List<DashboardLessonResponse> lessonsResponse = new LinkedList<>();

        for (int i = 0; i < lessons.size(); i++) {
            DashboardLessonResponse dashboardCourseResponse = this.makeDashboardLessonResponse(lessons.get(i));
            lessonsResponse.add(dashboardCourseResponse);
        }

        return lessonsResponse;
    }

}
