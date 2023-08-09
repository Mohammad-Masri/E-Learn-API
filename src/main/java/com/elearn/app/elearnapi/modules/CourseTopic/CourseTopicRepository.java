package com.elearn.app.elearnapi.modules.CourseTopic;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CourseTopicRepository extends CrudRepository<CourseTopic, String> {

    @Modifying
    @Query(value = "DELETE FROM course_topic WHERE topic_id = ?1", nativeQuery = true)
    void deleteAllByTopicId(String topicId);

}
