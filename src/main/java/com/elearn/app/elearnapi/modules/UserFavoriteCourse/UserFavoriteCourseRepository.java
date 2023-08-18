package com.elearn.app.elearnapi.modules.UserFavoriteCourse;

import org.springframework.data.repository.CrudRepository;

public interface UserFavoriteCourseRepository extends CrudRepository<UserFavoriteCourse, String> {
    public UserFavoriteCourse findByUserIdAndCourseId(String userId, Long courseId);
}
