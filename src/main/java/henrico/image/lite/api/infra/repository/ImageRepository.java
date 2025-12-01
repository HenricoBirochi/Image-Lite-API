package henrico.image.lite.api.infra.repository;

import henrico.image.lite.api.domain.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, String> {
}
