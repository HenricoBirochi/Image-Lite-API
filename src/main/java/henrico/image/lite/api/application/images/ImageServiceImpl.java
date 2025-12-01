package henrico.image.lite.api.application.images;

import henrico.image.lite.api.domain.entity.Image;
import henrico.image.lite.api.domain.service.ImageService;
import henrico.image.lite.api.infra.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor // It creates a constructor with all variables with final on their signature
public class ImageServiceImpl implements ImageService {

    private final ImageRepository repository;

    @Override
    @Transactional
    public Image save(Image image) {
        return repository.save(image);
    }
}
