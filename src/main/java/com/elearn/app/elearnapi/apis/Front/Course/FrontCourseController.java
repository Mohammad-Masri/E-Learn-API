package com.elearn.app.elearnapi.apis.Front.Course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elearn.app.elearnapi.apis.Dashboard.Course.DTO.FrontCourseInListResponse;
import com.elearn.app.elearnapi.modules.Course.Course;
import com.elearn.app.elearnapi.modules.Course.CourseService;
import com.elearn.app.elearnapi.modules.PaymentTransaction.PaymentTransaction;
import com.elearn.app.elearnapi.modules.PaymentTransaction.PaymentTransactionService;
import com.elearn.app.elearnapi.modules.User.User;
import com.elearn.app.elearnapi.modules.User.UserService;
import com.elearn.app.elearnapi.modules.UserPurchasedCourse.UserPurchasedCourse;
import com.elearn.app.elearnapi.modules.UserPurchasedCourse.UserPurchasedCourseService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/front/courses")
public class FrontCourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;
    @Autowired
    private PaymentTransactionService paymentTransactionService;
    @Autowired
    private UserPurchasedCourseService userPurchasedCourseService;

    @GetMapping
    public List<FrontCourseInListResponse> getAllCourses() {
        List<Course> courses = this.courseService.getAll();
        List<FrontCourseInListResponse> courseResponses = this.courseService
                .makeFrontCoursesInListResponse(courses);
        return courseResponses;
    }

    @PostMapping(value = "/{courseId}/pay")
    public Boolean payACourse(@PathVariable String courseId, HttpServletRequest request) {

        String id = (String) request.getAttribute("id");
        User user = this.userService.checkFindById(id);

        Course course = this.courseService.checkGetOneById(courseId);

        this.userPurchasedCourseService.checkIsUserAlreadyPayThisCourse(user, course);

        PaymentTransaction transaction = this.paymentTransactionService.create(course);

        UserPurchasedCourse userPurchasedCourse = this.userPurchasedCourseService.create(user, course, transaction);

        return true;
    }
}
