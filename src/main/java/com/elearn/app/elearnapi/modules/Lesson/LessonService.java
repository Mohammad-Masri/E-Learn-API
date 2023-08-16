package com.elearn.app.elearnapi.modules.Lesson;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.elearn.app.elearnapi.apis.Dashboard.Course.DTO.DashboardLessonResponse;
import com.elearn.app.elearnapi.errors.HTTPServerError;
import com.elearn.app.elearnapi.modules.Course.Course;
import com.elearn.app.elearnapi.modules.Course.CourseService;
import com.elearn.app.elearnapi.utilities.ArrayUtilities;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private CourseService courseService;

    public Lesson save(Lesson lesson) {
        return this.lessonRepository.save(lesson);
    }

    public Lesson getOneById(String id) {
        Lesson lesson = this.lessonRepository.findById(id).orElse(null);
        return lesson;
    }

    public Lesson checkGetOneById(String id) {
        Lesson lesson = this.getOneById(id);
        if (lesson == null) {
            throw new HTTPServerError(HttpStatus.NOT_FOUND, String.format("lesson with id %s is not found", id));
        }
        return lesson;
    }

    public Lesson getOneByIdInCourse(String id, Course course) {

        Lesson lesson = ArrayUtilities.findOne(course.getLessons(), "getId", id);
        return lesson;
    }

    public Lesson checkGetOneByIdInCourse(String id, Course course) {
        Lesson lesson = this.getOneByIdInCourse(id, course);
        if (lesson == null) {
            throw new HTTPServerError(HttpStatus.NOT_FOUND,
                    String.format("lesson with id %s is not found in course with id %s", id, course.getId()));
        }
        return lesson;
    }

    public Lesson create(String courseId, String title, String description, String URL) {
        Course course = this.courseService.checkGetOneById(courseId);
        List<Lesson> lessons = course.getLessons();
        int number = lessons.size() + 1;
        Lesson lesson = new Lesson(number, title, description, URL, course);
        lesson = this.save(lesson);
        return lesson;
    }

    public Lesson update(String id, Course course,
            String title,
            String description,
            String url) {

        Lesson lesson = this.checkGetOneByIdInCourse(id, course);

        lesson.setTitle(title);
        lesson.setDescription(description);
        lesson.setURL(url);

        lesson = this.lessonRepository.save(lesson);

        return lesson;
    }

    public Lesson delete(String id, Course course) {
        Lesson lesson = this.checkGetOneByIdInCourse(id, course);
        this.lessonRepository.delete(lesson);

        // reordering the number of lessons in this course
        List<Lesson> lessons = course.getLessons();
        for (Lesson l : lessons) {
            if (l.getNumber() > lesson.getNumber()) {
                l.setNumber(l.getNumber() - 1);
                this.save(l);
            }
        }
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
