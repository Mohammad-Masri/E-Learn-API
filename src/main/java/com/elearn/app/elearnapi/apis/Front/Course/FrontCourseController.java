package com.elearn.app.elearnapi.apis.Front.Course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elearn.app.elearnapi.apis.Dashboard.Course.DTO.FrontCourseInListResponse;
import com.elearn.app.elearnapi.modules.BooleanResponse;
import com.elearn.app.elearnapi.modules.Course.Course;
import com.elearn.app.elearnapi.modules.Course.CourseService;
import com.elearn.app.elearnapi.modules.PaymentTransaction.PaymentTransaction;
import com.elearn.app.elearnapi.modules.PaymentTransaction.PaymentTransactionService;
import com.elearn.app.elearnapi.modules.User.User;
import com.elearn.app.elearnapi.modules.User.UserService;
import com.elearn.app.elearnapi.modules.UserFavoriteCourse.UserFavoriteCourse;
import com.elearn.app.elearnapi.modules.UserFavoriteCourse.UserFavoriteCourseService;
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
    @Autowired
    private UserFavoriteCourseService userFavoriteCourseService;

    @GetMapping
    public List<FrontCourseInListResponse> getAllCourses(HttpServletRequest request) {
        String id = (String) request.getAttribute("id");
        User user = this.userService.findById(id);
        List<Course> courses = this.courseService.getAll();
        List<FrontCourseInListResponse> courseResponses = this.courseService
                .makeFrontCoursesInListResponse(user, courses);
        return courseResponses;
    }

    @GetMapping(value = "/mine")
    public List<FrontCourseInListResponse> getMyCourses(HttpServletRequest request) {
        String id = (String) request.getAttribute("id");
        User user = this.userService.checkFindById(id);
        List<Course> courses = this.courseService.getUserCourses(user);
        List<FrontCourseInListResponse> courseResponses = this.courseService
                .makeFrontCoursesInListResponse(user, courses);
        return courseResponses;
    }

    @GetMapping(value = "/favorite")
    public List<FrontCourseInListResponse> getMyFavoriteCourses(HttpServletRequest request) {
        String id = (String) request.getAttribute("id");
        User user = this.userService.checkFindById(id);
        List<Course> courses = this.courseService.getUserFavoriteCourses(user);
        List<FrontCourseInListResponse> courseResponses = this.courseService
                .makeFrontCoursesInListResponse(user, courses);
        return courseResponses;
    }

    @PostMapping(value = "/{courseId}/pay")
    public BooleanResponse payACourse(@PathVariable String courseId, HttpServletRequest request) {

        String id = (String) request.getAttribute("id");
        User user = this.userService.checkFindById(id);

        Course course = this.courseService.checkGetOneById(courseId);

        this.userPurchasedCourseService.checkIsUserAlreadyPayThisCourse(user, course);

        PaymentTransaction transaction = this.paymentTransactionService.create(course);

        this.userPurchasedCourseService.create(user, course, transaction);

        return new BooleanResponse(true);
    }

    @PutMapping(value = "/{courseId}/toggle-favorite")
    public BooleanResponse toggleFavorite(@PathVariable String courseId, HttpServletRequest request) {
        String id = (String) request.getAttribute("id");
        User user = this.userService.checkFindById(id);
        Course course = this.courseService.checkGetOneById(courseId);

        Boolean isFavorite = this.userFavoriteCourseService.isCourseFavoriteByUser(user, course);

        if (isFavorite) {
            UserFavoriteCourse userFavoriteCourse = this.userFavoriteCourseService.findByUserIdAndCourseId(user,
                    course);
            this.userFavoriteCourseService.delete(userFavoriteCourse);
        } else {
            this.userFavoriteCourseService.create(user, course);
        }

        return new BooleanResponse(true);
    }
}
