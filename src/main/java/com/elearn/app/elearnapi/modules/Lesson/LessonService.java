package com.elearn.app.elearnapi.modules.Lesson;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.elearn.app.elearnapi.apis.Dashboard.Course.DTO.DashboardLessonResponse;
import com.elearn.app.elearnapi.apis.Front.Course.DTO.FrontLessonResponse;
import com.elearn.app.elearnapi.config.constants.AssetType;
import com.elearn.app.elearnapi.errors.HTTPServerError;
import com.elearn.app.elearnapi.modules.Asset.Asset;
import com.elearn.app.elearnapi.modules.Asset.AssetService;
import com.elearn.app.elearnapi.modules.Asset.DTO.AssetResponse;
import com.elearn.app.elearnapi.modules.Course.Course;
import com.elearn.app.elearnapi.modules.Course.CourseService;
import com.elearn.app.elearnapi.modules.User.User;
import com.elearn.app.elearnapi.modules.UserPurchasedCourse.UserPurchasedCourseService;
import com.elearn.app.elearnapi.utilities.ArrayUtilities;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private CourseService courseService;

    @Autowired
    private AssetService assetService;

    @Autowired
    private UserPurchasedCourseService userPurchasedCourseService;

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

    public List<Lesson> getAllLessonsForCourse(Course course) {
        return course.getLessons();
    }

    public List<Lesson> getPublishedLessonsForCourse(Course course) {
        return this.lessonRepository.findPublishedLessonsForCourse(course);
    }

    public List<Lesson> findByAssetVideo(Asset video) {
        return this.lessonRepository.findByVideo(video);
    }

    public Lesson create(String courseId, String title, String description, String assetId) {
        Course course = this.courseService.checkGetOneById(courseId);
        List<Lesson> lessons = course.getLessons();
        int number = lessons.size() + 1;
        Asset video = this.assetService.checkGetOneById(assetId);
        this.assetService.checkTypeOfAsset(video, AssetType.VIDEO);
        Lesson lesson = new Lesson(number, title, description, video, course);
        lesson = this.save(lesson);
        return lesson;
    }

    public Lesson update(String id, Course course,
            String title,
            String description,
            String assetId) {

        Lesson lesson = this.checkGetOneByIdInCourse(id, course);

        Asset video = this.assetService.checkGetOneById(assetId);
        this.assetService.checkTypeOfAsset(video, AssetType.VIDEO);

        lesson.setTitle(title);
        lesson.setDescription(description);
        lesson.setVideo(video);

        lesson = this.save(lesson);

        return lesson;
    }

    public void checkCanBePublished(Lesson lesson) {
        if (lesson.getVideo() == null) {
            throw new HTTPServerError(HttpStatus.BAD_REQUEST,
                    String.format("lesson with id %s can't be published because there is no video for it",
                            lesson.getId() + ""));
        }

    }

    public Lesson toggleIsPublish(String id, Course course) {
        Lesson lesson = this.checkGetOneByIdInCourse(id, course);
        if (!lesson.getIsPublished()) {
            this.checkCanBePublished(lesson);

        }
        lesson.setIsPublished(!lesson.getIsPublished());
        lesson = this.save(lesson);
        return lesson;
    }

    public Lesson toggleIsFree(String id, Course course) {
        Lesson lesson = this.checkGetOneByIdInCourse(id, course);
        lesson.setIsFree(!lesson.getIsFree());
        lesson = this.save(lesson);
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
        AssetResponse video = this.assetService.makeAssetResponse(lesson.getVideo());
        return new DashboardLessonResponse(lesson, video);
    }

    public List<DashboardLessonResponse> makeDashboardLessonsResponse(List<Lesson> lessons) {
        List<DashboardLessonResponse> lessonsResponse = new LinkedList<>();

        for (int i = 0; i < lessons.size(); i++) {
            DashboardLessonResponse dashboardCourseResponse = this.makeDashboardLessonResponse(lessons.get(i));
            lessonsResponse.add(dashboardCourseResponse);
        }

        return lessonsResponse;
    }

    public FrontLessonResponse makeFrontLessonResponse(Lesson lesson, Course course, User user, int number) {
        Boolean isPurchased = false;
        if (user != null) {
            isPurchased = this.userPurchasedCourseService.isCoursePurchasedByUser(user, course);
        }
        AssetResponse video = this.assetService.makeAssetResponse(lesson.getVideo());
        return new FrontLessonResponse(lesson, video, isPurchased, number);
    }

    public List<FrontLessonResponse> makeFrontLessonsResponse(List<Lesson> lessons, Course course, User user) {
        List<FrontLessonResponse> lessonsResponse = new LinkedList<>();

        for (int i = 0; i < lessons.size(); i++) {
            int number = i + 1;
            FrontLessonResponse frontLessonResponse = this.makeFrontLessonResponse(lessons.get(i), course, user,
                    number);
            lessonsResponse.add(frontLessonResponse);
        }

        return lessonsResponse;
    }
}
