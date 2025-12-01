package henrico.image.lite.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // It is required to save the creation date of the register in the DB
public class Application {

//    @Bean // It is required so Spring can recognize this method
//    // This 'CommandLineRunner' it is used to run something in the command line together with the application
//    public CommandLineRunner commandLineRunner(@Autowired ImageRepository repository) {
//        return args -> {
//            Image image = Image.builder()
//                    .extension(ImageExtension.PNG)
//                    .name("myimage")
//                    .tags("teste")
//                    .size(1000L)
//                    .build();
//
//            repository.save(image);
//        };
//    }

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
