package com.elearn.app.elearnapi.apis.Dashboard.Topic.DTO;

import com.elearn.app.elearnapi.modules.Topic.DTO.TopicResponse;

public class DashboardTopicResponse extends TopicResponse {

    public DashboardTopicResponse() {
        super();
    }

    public DashboardTopicResponse(
            String id,
            String title,
            String description) {
        super(id, title, description);
    }

}
