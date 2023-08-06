package com.elearn.app.elearnapi.apis.Dashboard.Topic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elearn.app.elearnapi.modules.Topic.Topic;
import com.elearn.app.elearnapi.modules.Topic.TopicService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/dashboard/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @GetMapping
    public List<Topic> getAllTopics() {
        return this.topicService.getAll();
    }

    @GetMapping("/{id}")
    public Topic getTopicDetails(@PathVariable String id) {
        Topic topic = this.topicService.checkGetOneById(id);
        return topic;
    }

    @PostMapping
    public Topic createNewTopic(@RequestBody CreateTopicBody body) {
        Topic topic = this.topicService.create(body.getTitle(), body.getDescription());
        return topic;
    }

    @PutMapping("/{id}")
    public Topic update(@PathVariable String id, @RequestBody CreateTopicBody body) {
        Topic topic = this.topicService.update(id, body.getTitle(), body.getDescription());
        return topic;
    }

    @DeleteMapping("/{id}")
    public Topic delete(@PathVariable String id) {
        Topic topic = this.topicService.delete(id);
        return topic;
    }

}
