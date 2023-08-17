package com.elearn.app.elearnapi.apis.Front.Topic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elearn.app.elearnapi.apis.Front.Topic.DTO.FrontTopicResponse;
import com.elearn.app.elearnapi.modules.Topic.Topic;
import com.elearn.app.elearnapi.modules.Topic.TopicService;

@RestController
@RequestMapping("/front/topics")
public class FrontTopicController {
    @Autowired
    private TopicService topicService;

    @GetMapping
    public List<FrontTopicResponse> getAllTopics() {
        List<Topic> topics = this.topicService.getAll();
        List<FrontTopicResponse> frontTopicResponses = this.topicService.makeFrontTopicsResponse(topics);
        return frontTopicResponses;
    }
}
