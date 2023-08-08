package com.elearn.app.elearnapi.modules.Topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.elearn.app.elearnapi.errors.HTTPServerError;
import com.elearn.app.elearnapi.utilities.StringUtilities;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public List<Topic> getAll() {
        List<Topic> topics = new ArrayList<>();
        this.topicRepository.findAll().forEach(topics::add);
        return topics;
    }

    public Topic getOneById(String id) {
        Topic topic = this.topicRepository.findById(id).orElse(null);
        return topic;
    }

    public Set<Topic> getAllByIds(String[] ids) {
        Set<Topic> topics = new HashSet<>();
        this.topicRepository.findAllById(Arrays.asList(ids)).forEach(topics::add);
        return topics;
    }

    public Topic checkGetOneById(String id) {
        Topic topic = this.getOneById(id);
        if (topic == null) {
            throw new HTTPServerError(HttpStatus.NOT_FOUND, String.format("topic with id %s is not found", id));
        }
        return topic;
    }

    public Topic create(String title, String description) {
        String id = StringUtilities.normalize(title);
        Topic oldTopic = this.getOneById(id);
        if (oldTopic != null) {
            throw new HTTPServerError(HttpStatus.BAD_REQUEST, String.format("topic with id %s is already found", id));
        }
        Topic topic = new Topic(id, title, description);
        topic = this.topicRepository.save(topic);
        return topic;
    }

    public Topic update(String id, String title, String description) {
        Topic topic = this.checkGetOneById(id);
        topic.setTitle(title);
        topic.setDescription(description);

        topic = this.topicRepository.save(topic);

        return topic;
    }

    public Topic delete(String id) {
        Topic topic = this.checkGetOneById(id);

        // Remove the topic from associated courses
        topic.getCourses().forEach(course -> course.getTopics().remove(topic));
        // Clear the association from the topic side
        topic.getCourses().clear();

        this.topicRepository.delete(topic);
        return topic;
    }
}
