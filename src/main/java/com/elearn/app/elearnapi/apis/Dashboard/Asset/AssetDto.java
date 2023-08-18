package com.elearn.app.elearnapi.apis.Dashboard.Asset;

class CreateAssetBody {
    private String title;
    private String url;

    public CreateAssetBody() {
    }

    public CreateAssetBody(
            String title,
            String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
