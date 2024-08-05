package com.nasefa.springfinalproject;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// import com.nasefa.springfinalproject.domain.services.gamma.IGamma;
// import com.nasefa.springfinalproject.domain.repositories.ProductRepository;
// import com.nasefa.springfinalproject.persistence.entities.Gamma;


@SpringBootApplication
public class SpringfinalprojectApplication implements CommandLineRunner{

	// @Autowired
	// private IGamma gammaService;

	public static void main(String[] args) {
		SpringApplication.run(SpringfinalprojectApplication.class, args);
	}
	@Bean
    WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @SuppressWarnings("null")
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/*").allowedOrigins("http://localhost:4200","http://127.0.0.1:5500",
                        "http://localhost:8080").allowedMethods("").allowedHeaders("*");
            }
        };
    }

	@Override
	public void run(String... args) throws Exception {
		// gammaList();
	}
	
	// @Transactional
	// public void gammaList(){
	// 	List<Gamma> gammas = gammaService.findAllWithProducts("a");

	// 	gammas.stream().forEach(gamma -> {
	// 		System.out.println(gamma.getProducts());
	// 		System.out.println(gamma);
	// 	});
	// }

}
