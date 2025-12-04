package henrico.image.lite.api.infra.repository.specs;

import org.springframework.data.jpa.domain.Specification;

public class GenericSpecs {
    private GenericSpecs() {}

    // Doing that, I'm saying that the generic value will be passed when using this method
    public static <T> Specification<T> conjunction() {
        return (root, q, criteriaBuilder) -> criteriaBuilder.conjunction();
    }
}
