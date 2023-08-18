package com.elearn.app.elearnapi.modules.Asset.DTO;

import com.elearn.app.elearnapi.config.constants.AssetType;
import com.elearn.app.elearnapi.modules.Asset.Asset;

public class AssetResponse {
    private String id;
    private String URL;
    private AssetType type;

    public AssetResponse() {
    }

    public AssetResponse(Asset asset) {
        this.id = asset.getId() + "";
        this.URL = asset.getURL();
        this.type = asset.getType();
    }

    public String getId() {
        return id;
    }

    public AssetType getType() {
        return type;
    }

    public String getURL() {
        return URL;
    }

}
