package com.elearn.app.elearnapi.modules.Lesson;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Lesson lesson = new Lesson(number, title, description, URL);
        lesson = this.lessonRepository.save(lesson);
        return lesson;
    }

}
