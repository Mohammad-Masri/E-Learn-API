package com.elearn.app.elearnapi.modules.Lesson;

import org.springframework.data.repository.CrudRepository;
import com.elearn.app.elearnapi.modules.Asset.Asset;
import java.util.List;

public interface LessonRepository extends CrudRepository<Lesson, String> {

    public List<Lesson> findByVideo(Asset video);
}
