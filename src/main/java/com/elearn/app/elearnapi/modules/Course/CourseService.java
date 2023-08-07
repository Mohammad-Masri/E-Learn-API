package com.elearn.app.elearnapi.modules.Course;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.elearn.app.elearnapi.errors.HTTPServerError;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

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

    public Course create(String title, String description) {
        Course course = new Course(title, description);
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
        this.courseRepository.delete(course);
        return course;
    }
}
