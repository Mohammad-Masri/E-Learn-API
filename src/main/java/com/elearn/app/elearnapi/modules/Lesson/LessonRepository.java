package com.elearn.app.elearnapi.modules.Lesson;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.elearn.app.elearnapi.modules.Asset.Asset;
import com.elearn.app.elearnapi.modules.Course.Course;

import java.util.List;

public interface LessonRepository extends CrudRepository<Lesson, String> {

    public List<Lesson> findByVideo(Asset video);

    @Query("SELECT l FROM Lesson l WHERE l.course = :course AND l.isPublished = true")
    List<Lesson> findPublishedLessonsForCourse(Course course);
}
