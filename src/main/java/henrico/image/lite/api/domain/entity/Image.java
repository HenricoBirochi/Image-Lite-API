package henrico.image.lite.api.domain.entity;

import henrico.image.lite.api.domain.enums.ImageExtension;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// Lombok Annotations
//@Data // Spring said that this annotation in a JPA Entity can cause several problems in performance // It creates getters, setters, toString, hashCode and equals
@Getter
@Setter
@NoArgsConstructor // It creates a no args constructor
@AllArgsConstructor // It creates a constructor with all attributes of this class
@Builder // It's required to user the 'builder' method, that is very useful to instantiate an object of this class
// JPA Annotations
@Entity
@Table(name = "images")
@EntityListeners(AuditingEntityListener.class) // It is required to save the creation date of the register in the DB
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private String name;

    @Column
    private Long size;

    @Column
    @Enumerated(EnumType.STRING)
    private ImageExtension extension;

    @Column
    @CreatedDate // It is required to save the creation date of the register in the DB
    private LocalDateTime uploadDate;

    @Column
    private String tags;

    @Column
    @Lob // When you are going to save files in DataBase you need this annotation '@Lob'
    private byte[] file;

    public String getFullFileName() {
        return getName().concat(".").concat(getExtension().name());
    }

}
