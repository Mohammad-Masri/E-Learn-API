package com.elearn.app.elearnapi.apis.Dashboard.Asset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elearn.app.elearnapi.modules.Asset.Asset;
import com.elearn.app.elearnapi.modules.Asset.AssetService;
import com.elearn.app.elearnapi.modules.Asset.DTO.AssetResponse;

@RestController
@RequestMapping("/dashboard/assets")
public class DashboardAssetController {

    @Autowired
    private AssetService assetService;

    @PostMapping
    public AssetResponse createNewAsset(@RequestBody CreateAssetBody body) {
        Asset asset = this.assetService.create(body.getTitle(), body.getUrl());
        AssetResponse assetResponse = this.assetService.makeAssetResponse(asset);
        return assetResponse;
    }

    @DeleteMapping(value = "/{id}")
    public AssetResponse deleteAsset(@PathVariable String id) {
        Asset asset = this.assetService.checkGetOneById(id);
        asset = this.assetService.delete(asset);
        AssetResponse assetResponse = this.assetService.makeAssetResponse(asset);
        return assetResponse;
    }

}
