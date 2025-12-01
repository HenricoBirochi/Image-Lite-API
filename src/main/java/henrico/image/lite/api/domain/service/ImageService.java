package henrico.image.lite.api.domain.service;

import henrico.image.lite.api.domain.entity.Image;

import java.util.Optional;

public interface ImageService {
    Image save(Image image);

    Optional<Image> getById(String id);
}
