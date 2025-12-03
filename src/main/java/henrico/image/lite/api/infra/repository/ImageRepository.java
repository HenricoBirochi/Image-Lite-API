package henrico.image.lite.api.infra.repository;

import henrico.image.lite.api.domain.entity.Image;
import henrico.image.lite.api.domain.enums.ImageExtension;
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
        Specification<Image> conjunction = (root, q, criteriaBuilder) -> criteriaBuilder.conjunction();
        Specification<Image> spec = Specification.where(conjunction);

        if(extension != null) {
            // AND extension = 'PNG'
            Specification<Image> extensionEqual = (root, q, cb) -> cb.equal(root.get("extension"), extension);
            spec = spec.and(extensionEqual);
        }

        if(StringUtils.hasText(query)) {
            // AND ( name LIKE 'query' OR tags LIKE 'query' )
            // RIVER => %RI%
            Specification<Image> nameLike = (root, q, cb)
                    -> cb.like(cb.upper(root.get("name")), "%" + query.toUpperCase() + "%");
            Specification<Image> tagsLike = (root, q, cb)
                    -> cb.like(cb.upper(root.get("tags")), "%" + query.toUpperCase() + "%");

            Specification<Image> nameOrTagsLike = Specification.anyOf(nameLike, tagsLike);

            spec = spec.and(nameOrTagsLike);
        }

        return findAll();
    }
}
