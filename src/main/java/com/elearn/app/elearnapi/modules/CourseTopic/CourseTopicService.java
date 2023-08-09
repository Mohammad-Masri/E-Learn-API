package com.elearn.app.elearnapi.modules.CourseTopic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elearn.app.elearnapi.modules.Topic.Topic;

import jakarta.transaction.Transactional;

@Service
public class CourseTopicService {

    @Autowired
    private CourseTopicRepository courseTopicRepository;

    @Transactional
    public void deleteRelatedCourseTopicForThisTopic(Topic topic) {
        this.courseTopicRepository.deleteAllByTopicId(topic.getId());
    }
}
