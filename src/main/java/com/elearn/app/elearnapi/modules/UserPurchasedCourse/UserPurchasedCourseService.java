package com.elearn.app.elearnapi.modules.UserPurchasedCourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.elearn.app.elearnapi.errors.HTTPServerError;
import com.elearn.app.elearnapi.modules.Course.Course;
import com.elearn.app.elearnapi.modules.PaymentTransaction.PaymentTransaction;
import com.elearn.app.elearnapi.modules.User.User;

@Service
public class UserPurchasedCourseService {

    @Autowired
    private UserPurchasedCourseRepository userPurchasedCourseRepository;

    public UserPurchasedCourse save(UserPurchasedCourse userPurchasedCourse) {
        return this.userPurchasedCourseRepository.save(userPurchasedCourse);
    }

    public UserPurchasedCourse create(User user, Course course, PaymentTransaction transaction) {
        UserPurchasedCourse userPurchasedCourse = new UserPurchasedCourse(user, course, transaction);
        return this.save(userPurchasedCourse);
    }

    public UserPurchasedCourse findByUserIdAndCourseId(User user, Course course) {
        if (user == null || course == null)
            return null;
        return this.userPurchasedCourseRepository.findByUserIdAndCourseId(user.getId(), course.getId());
    }

    public Boolean isCoursePurchasedByUser(User user, Course course) {
        UserPurchasedCourse userPurchasedCourse = this.findByUserIdAndCourseId(user, course);
        if (userPurchasedCourse == null)
            return false;
        return true;
    }

    public UserPurchasedCourse checkIsUserAlreadyPayThisCourse(User user, Course course) {
        UserPurchasedCourse userPurchasedCourse = this.findByUserIdAndCourseId(user, course);
        if (userPurchasedCourse != null) {
            throw new HTTPServerError(HttpStatus.BAD_REQUEST,
                    String.format("you already pay this course with id ( %s )", course.getId()));
        }
        return userPurchasedCourse;
    }

}
