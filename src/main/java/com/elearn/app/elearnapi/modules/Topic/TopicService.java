package com.elearn.app.elearnapi.modules.Topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.elearn.app.elearnapi.errors.HTTPServerError;
import com.elearn.app.elearnapi.utilities.ArrayUtilities;
import com.elearn.app.elearnapi.utilities.StringUtilities;

@Service
public class TopicService {
    List<Topic> topics = new ArrayList<Topic>(Arrays.asList(
            new Topic("java", "Java", "Java programming language"),
            new Topic("spring-boot", "Spring Boot", "Spring boot framework"),
            new Topic("javascript", "Javascript", "Javascript scripting language")));

    public List<Topic> getAll() {
        return this.topics;
    }

    public Topic getOneById(String id) {
        Topic topic = ArrayUtilities.findOne(this.topics, "getId", id);
        return topic;
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
        this.topics.add(topic);
        return topic;
    }

    public Topic update(String id, String title, String description) {
        Topic topic = this.checkGetOneById(id);

        int index = this.topics.indexOf(topic);

        topic.setTitle(title);
        topic.setDescription(description);

        this.topics.set(index, topic);

        return topic;
    }

    public Topic delete(String id) {
        Topic topic = this.checkGetOneById(id);

        int index = this.topics.indexOf(topic);

        this.topics.remove(index);

        return topic;
    }
}
