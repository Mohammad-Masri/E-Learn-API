package com.elearn.app.elearnapi.modules.Course;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.elearn.app.elearnapi.apis.Dashboard.Course.DTO.DashboardCourseInListResponse;
import com.elearn.app.elearnapi.apis.Dashboard.Course.DTO.DashboardCourseResponse;
import com.elearn.app.elearnapi.apis.Dashboard.Course.DTO.DashboardLessonResponse;
import com.elearn.app.elearnapi.apis.Dashboard.Topic.DTO.DashboardTopicResponse;
import com.elearn.app.elearnapi.apis.Front.Course.DTO.FrontCourseInListResponse;
import com.elearn.app.elearnapi.apis.Front.Course.DTO.FrontCourseResponse;
import com.elearn.app.elearnapi.apis.Front.Course.DTO.FrontLessonResponse;
import com.elearn.app.elearnapi.apis.Front.Topic.DTO.FrontTopicResponse;
import com.elearn.app.elearnapi.errors.HTTPServerError;
import com.elearn.app.elearnapi.modules.Asset.Asset;
import com.elearn.app.elearnapi.modules.Asset.AssetService;
import com.elearn.app.elearnapi.modules.CourseTopic.CourseTopicService;
import com.elearn.app.elearnapi.modules.Lesson.LessonService;
import com.elearn.app.elearnapi.modules.Topic.Topic;
import com.elearn.app.elearnapi.modules.Topic.TopicService;
import com.elearn.app.elearnapi.modules.User.User;
import com.elearn.app.elearnapi.modules.UserFavoriteCourse.UserFavoriteCourse;
import com.elearn.app.elearnapi.modules.UserFavoriteCourse.UserFavoriteCourseService;
import com.elearn.app.elearnapi.modules.UserPurchasedCourse.UserPurchasedCourse;
import com.elearn.app.elearnapi.modules.UserPurchasedCourse.UserPurchasedCourseService;
import com.elearn.app.elearnapi.utilities.ArrayUtilities;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseTopicService courseTopicService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private LessonService lessonService;
    @Autowired
    private AssetService assetService;

    @Autowired
    private UserPurchasedCourseService userPurchasedCourseService;
    @Autowired
    private UserFavoriteCourseService userFavoriteCourseService;

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

    public List<Course> getAllInTopic(String topicId) {
        List<Course> courses = this.courseRepository.findByTopics_Id(topicId);
        return courses;
    }

    public List<Course> getUserCourses(User user) {
        List<UserPurchasedCourse> userPurchasedCourses = user.getPurchasedCourses();
        List<Course> courses = new ArrayList<>();
        for (UserPurchasedCourse upc : userPurchasedCourses) {
            courses.add(upc.getCourse());
        }
        return courses;
    }

    public List<Course> getUserFavoriteCourses(User user) {
        List<UserFavoriteCourse> userFavoriteCourses = user.getFavoriteCourses();
        List<Course> courses = new ArrayList<>();
        for (UserFavoriteCourse ufc : userFavoriteCourses) {
            courses.add(ufc.getCourse());
        }
        return courses;
    }

    public Course create(String title, String description, Double price, String assetId, String[] topicIds) {
        Set<Topic> topics = this.topicService.getAllByIds(topicIds);
        Asset asset = this.assetService.checkGetOneById(assetId);
        Course course = new Course(title, description, price, asset, topics);
        course = this.courseRepository.save(course);
        return course;
    }

    public Course update(String id, String title, String description, Double price, String assetId, String[] topicIds) {
        Course course = this.checkGetOneById(id);
        Set<Topic> topics = this.topicService.getAllByIds(topicIds);
        Asset asset = this.assetService.checkGetOneById(assetId);
        course.setTitle(title);
        course.setDescription(description);
        course.setPrice(price);
        course.setImage(asset);
        course.setTopics(topics);

        course = this.courseRepository.save(course);

        return course;
    }

    public Course toggleIsPublished(String id) {
        Course course = this.checkGetOneById(id);
        course.setIsPublished(!course.getIsPublished());
        course = this.courseRepository.save(course);
        return course;
    }

    public Course toggleIsFeatured(String id) {
        Course course = this.checkGetOneById(id);
        course.setIsFeatured(!course.getIsFeatured());
        course = this.courseRepository.save(course);
        return course;
    }

    public Course delete(String id) {
        Course course = this.checkGetOneById(id);
        this.courseTopicService.deleteRelatedCourseTopicForThisCourse(course);

        // empty the topics array so spring can serialize the JSON object
        course.setTopics(new HashSet<>());

        this.courseRepository.delete(course);

        return course;
    }

    public FrontCourseInListResponse makeFrontCourseInListResponse(User user, Course course) {
        Boolean isFavorite = this.userFavoriteCourseService.isCourseFavoriteByUser(user, course);
        Boolean isPurchased = this.userPurchasedCourseService.isCoursePurchasedByUser(user, course);
        return new FrontCourseInListResponse(course, isFavorite, isPurchased);
    }

    public List<FrontCourseInListResponse> makeFrontCoursesInListResponse(User user, List<Course> courses) {
        List<FrontCourseInListResponse> coursesResponse = new LinkedList<>();

        for (int i = 0; i < courses.size(); i++) {
            FrontCourseInListResponse dashboardCourseResponse = this
                    .makeFrontCourseInListResponse(user, courses.get(i));
            coursesResponse.add(dashboardCourseResponse);
        }

        return coursesResponse;
    }

    public FrontCourseResponse makeFrontCourseResponse(User user, Course course) {
        Boolean isFavorite = this.userFavoriteCourseService.isCourseFavoriteByUser(user, course);
        Boolean isPurchased = this.userPurchasedCourseService.isCoursePurchasedByUser(user, course);
        List<Topic> topicsList = ArrayUtilities.convertSetToLinkedList(course.getTopics());
        List<FrontTopicResponse> topics = this.topicService.makeFrontTopicsResponse(topicsList);
        List<FrontLessonResponse> lessons = this.lessonService.makeFrontLessonsResponse(course.getLessons(), course,
                user);
        return new FrontCourseResponse(course, isFavorite, isPurchased, topics, lessons);
    }

    public List<FrontCourseResponse> makeFrontCoursesResponse(List<Course> courses, User user) {
        List<FrontCourseResponse> coursesResponse = new LinkedList<>();

        for (int i = 0; i < courses.size(); i++) {
            FrontCourseResponse frontCourseResponse = this
                    .makeFrontCourseResponse(user, courses.get(i));
            coursesResponse.add(frontCourseResponse);
        }

        return coursesResponse;
    }

    public DashboardCourseInListResponse makeDashboardCourseInListResponse(Course course) {
        return new DashboardCourseInListResponse(course);
    }

    public List<DashboardCourseInListResponse> makeDashboardCoursesInListResponse(List<Course> courses) {
        List<DashboardCourseInListResponse> coursesResponse = new LinkedList<>();

        for (int i = 0; i < courses.size(); i++) {
            DashboardCourseInListResponse dashboardCourseResponse = this
                    .makeDashboardCourseInListResponse(courses.get(i));
            coursesResponse.add(dashboardCourseResponse);
        }

        return coursesResponse;
    }

    public DashboardCourseResponse makeDashboardCourseResponse(Course course) {
        List<Topic> topicsList = ArrayUtilities.convertSetToLinkedList(course.getTopics());
        List<DashboardTopicResponse> topicResponses = this.topicService.makeDashboardTopicsResponse(topicsList);

        List<DashboardLessonResponse> lessons = this.lessonService.makeDashboardLessonsResponse(course.getLessons());
        return new DashboardCourseResponse(course, topicResponses, lessons);
    }

    public List<DashboardCourseResponse> makeDashboardCoursesResponse(List<Course> courses) {
        List<DashboardCourseResponse> coursesResponse = new LinkedList<>();

        for (int i = 0; i < courses.size(); i++) {
            DashboardCourseResponse dashboardCourseResponse = this.makeDashboardCourseResponse(courses.get(i));
            coursesResponse.add(dashboardCourseResponse);
        }

        return coursesResponse;
    }
}
