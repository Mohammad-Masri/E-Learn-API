package com.elearn.app.elearnapi.modules.UserFavoriteCourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elearn.app.elearnapi.modules.Course.Course;
import com.elearn.app.elearnapi.modules.User.User;

@Service
public class UserFavoriteCourseService {

    @Autowired
    private UserFavoriteCourseRepository userFavoriteCourseRepository;

    public UserFavoriteCourse save(UserFavoriteCourse userFavoriteCourse) {
        return this.userFavoriteCourseRepository.save(userFavoriteCourse);
    }

    public UserFavoriteCourse create(User user, Course course) {
        UserFavoriteCourse userFavoriteCourse = new UserFavoriteCourse(user, course);
        return this.save(userFavoriteCourse);
    }

    public UserFavoriteCourse delete(UserFavoriteCourse userFavoriteCourse) {
        this.userFavoriteCourseRepository.delete(userFavoriteCourse);
        return userFavoriteCourse;
    }

    public UserFavoriteCourse findByUserIdAndCourseId(User user, Course course) {
        if (user == null || course == null)
            return null;
        return this.userFavoriteCourseRepository.findByUserIdAndCourseId(user.getId(), course.getId());
    }

    public Boolean isCourseFavoriteByUser(User user, Course course) {
        UserFavoriteCourse userFavoriteCourse = this.findByUserIdAndCourseId(user, course);
        if (userFavoriteCourse == null)
            return false;
        return true;
    }

}
