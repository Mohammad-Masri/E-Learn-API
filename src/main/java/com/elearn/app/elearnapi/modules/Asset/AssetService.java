package com.elearn.app.elearnapi.modules.Asset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.elearn.app.elearnapi.config.constants.AssetType;
import com.elearn.app.elearnapi.errors.HTTPServerError;
import com.elearn.app.elearnapi.modules.Asset.DTO.AssetResponse;

@Service
public class AssetService {

    @Autowired
    private AssetRepository assetRepository;

    public Asset save(Asset asset) {
        return this.assetRepository.save(asset);
    }

    public Asset create(String title, String URL, AssetType type) {
        Asset asset = new Asset(title, URL, type);
        return this.save(asset);
    }

    public Asset getOneById(String id) {
        Asset asset = this.assetRepository.findById(id).orElse(null);
        return asset;
    }

    public Asset checkGetOneById(String id) {
        Asset asset = this.getOneById(id);
        if (asset == null) {
            throw new HTTPServerError(HttpStatus.NOT_FOUND, String.format("asset with id %s is not found", id));
        }
        return asset;
    }

    public AssetResponse makeAssetResponse(Asset asset) {
        return new AssetResponse(asset);
    }

}
