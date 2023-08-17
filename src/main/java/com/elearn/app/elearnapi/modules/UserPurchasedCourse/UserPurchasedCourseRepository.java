package com.elearn.app.elearnapi.modules.UserPurchasedCourse;

import org.springframework.data.repository.CrudRepository;

public interface UserPurchasedCourseRepository extends CrudRepository<UserPurchasedCourse, String> {
    public UserPurchasedCourse findByUserIdAndCourseId(String userId, Long courseId);
}
