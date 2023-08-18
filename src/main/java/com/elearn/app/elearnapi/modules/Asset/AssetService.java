package com.elearn.app.elearnapi.modules.Asset;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.elearn.app.elearnapi.config.constants.AssetType;
import com.elearn.app.elearnapi.utilities.EnumUtilities;
import com.elearn.app.elearnapi.utilities.FileUtilities;
import com.elearn.app.elearnapi.config.constants.FileType;
import com.elearn.app.elearnapi.errors.HTTPServerError;
import com.elearn.app.elearnapi.modules.Asset.DTO.AssetResponse;
import com.elearn.app.elearnapi.modules.Course.Course;
import com.elearn.app.elearnapi.modules.Course.CourseService;
import com.elearn.app.elearnapi.modules.Lesson.Lesson;
import com.elearn.app.elearnapi.modules.Lesson.LessonService;

@Service
public class AssetService {

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private LessonService lessonService;
    @Autowired
    private CourseService courseService;

    public Asset save(Asset asset) {
        return this.assetRepository.save(asset);
    }

    public AssetType checkAssetTypeIsAccepted(String URL) {
        FileType type = FileUtilities.getFileTypeByExtension(URL);

        if (!type.toString().equals(AssetType.IMAGE.toString())
                && !type.toString().equals(AssetType.VIDEO.toString())) {
            throw new HTTPServerError(HttpStatus.BAD_REQUEST,
                    String.format("asset file type is not accepted, it's should be a %s",
                            EnumUtilities.convertEnumValuesToString(AssetType.class)));
        }

        return AssetType.valueOf(type.toString());
    }

    public Asset create(String title, String URL) {
        AssetType type = this.checkAssetTypeIsAccepted(URL);
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

    public Asset delete(Asset asset) {
        // delete the asset from all associated lessons and courses

        List<Lesson> lessons = this.lessonService.findByAssetVideo(asset);
        for (Lesson lesson : lessons) {
            lesson.setVideo(null);
            // set the lesson to not published after delete the asset
            lesson.setIsPublished(false);
            this.lessonService.save(lesson);
        }

        List<Course> courses = this.courseService.findByAssetImage(asset);
        for (Course course : courses) {
            course.setImage(null);
            this.courseService.save(course);
        }

        this.assetRepository.delete(asset);
        return asset;
    }

}
