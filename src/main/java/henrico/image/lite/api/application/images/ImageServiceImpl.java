package henrico.image.lite.api.application.images;

import henrico.image.lite.api.domain.entity.Image;
import henrico.image.lite.api.domain.enums.ImageExtension;
import henrico.image.lite.api.domain.service.ImageService;
import henrico.image.lite.api.infra.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // It creates a constructor with all variables with final on their signature
public class ImageServiceImpl implements ImageService {

    private final ImageRepository repository;

    @Override
    @Transactional // Annotation 'Transactional' it's necessary only in writing queries
    public Image save(Image image) {
        return repository.save(image);
    }

    @Override
    public Optional<Image> getById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Image> search(ImageExtension extension, String query) {
        return repository.findByExtensionAndNameOrTagsLike(extension, query);
    }
}
