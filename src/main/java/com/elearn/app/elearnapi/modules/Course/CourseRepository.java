package com.elearn.app.elearnapi.modules.Course;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.elearn.app.elearnapi.modules.Asset.Asset;

public interface CourseRepository extends CrudRepository<Course, String> {
    List<Course> findByTopics_Id(String topicId);

    List<Course> findAllByIsPublished(Boolean isPublished);

    List<Course> findByImage(Asset image);
}
