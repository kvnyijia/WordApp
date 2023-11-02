package wordApp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WordAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(WordAppApplication.class, args);
	}

	@Bean 
	public CommandLineRunner commandLineRunner() {
		return runner -> {
			System.out.println(">>> Server running...");
		};
	}
}
