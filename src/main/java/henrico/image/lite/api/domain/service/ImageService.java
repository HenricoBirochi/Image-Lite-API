package henrico.image.lite.api.domain.service;

import henrico.image.lite.api.domain.entity.Image;
import henrico.image.lite.api.domain.enums.ImageExtension;

import java.util.List;
import java.util.Optional;

public interface ImageService {
    Image save(Image image);

    Optional<Image> getById(String id);

    List<Image> search(ImageExtension extension, String query);
}
