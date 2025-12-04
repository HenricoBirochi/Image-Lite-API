package henrico.image.lite.api.infra.repository;

import henrico.image.lite.api.domain.entity.Image;
import henrico.image.lite.api.domain.enums.ImageExtension;
import henrico.image.lite.api.infra.repository.specs.GenericSpecs;
import henrico.image.lite.api.infra.repository.specs.ImageSpecs;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.util.StringUtils;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, String>, JpaSpecificationExecutor<Image> {

    /*
    * @param extension
    * @param query
    *
    * SELECT * FROM images WHERE 1 = 1 AND extension = 'PNG' AND ( name LIKE 'query' OR tags LIKE 'query' )
    * */

    default List<Image> findByExtensionAndNameOrTagsLike(ImageExtension extension, String query) {
        // SELECT * FROM images WHERE 1 = 1
        Specification<Image> spec = Specification.where(GenericSpecs.conjunction());

        if(extension != null) {
            // AND extension = 'PNG'
            spec = spec.and(ImageSpecs.extensionEqual(extension));
        }

        if(StringUtils.hasText(query)) {
            // AND ( name LIKE 'query' OR tags LIKE 'query' )
            // RIVER => %RI%
            Specification<Image> nameLike = ImageSpecs.nameLike(query);
            Specification<Image> tagsLike = ImageSpecs.tagsLike(query);

            Specification<Image> nameOrTagsLike = Specification.anyOf(nameLike, tagsLike);

            spec = spec.and(nameOrTagsLike);
        }

        return findAll(spec);
    }
}
