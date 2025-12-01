package henrico.image.lite.api.domain.enums;

import lombok.Getter;
import org.springframework.http.MediaType;

import java.util.Arrays;

public enum ImageExtension {
    PNG(MediaType.IMAGE_PNG),
    GIF(MediaType.IMAGE_GIF),
    JPEG(MediaType.IMAGE_JPEG);

    @Getter
    private final MediaType mediaType;

    ImageExtension(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public static ImageExtension convertingToImageExtension(MediaType mediaType) { // IMPORTANT: STREAM EXPLANATION: The 'Stream object' created by Arrays.stream is very useful, because the type 'Stream' has a lot of methods that can help in searching, filtering or other operations you want to do on an array
        return Arrays.stream(values()) // the variable 'values()' represents all Enums in this enum class. All Enums have this variable
                .filter(ie -> ie.mediaType.equals(mediaType))
                .findFirst()
                .orElse(null);
    }
}
