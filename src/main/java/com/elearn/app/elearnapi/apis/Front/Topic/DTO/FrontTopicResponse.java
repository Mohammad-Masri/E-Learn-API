package com.elearn.app.elearnapi.apis.Front.Topic.DTO;

import com.elearn.app.elearnapi.modules.Topic.DTO.TopicResponse;

public class FrontTopicResponse extends TopicResponse {

    public FrontTopicResponse() {
        super();
    }

    public FrontTopicResponse(
            String id,
            String title,
            String description) {
        super(id, title, description);
    }

}
