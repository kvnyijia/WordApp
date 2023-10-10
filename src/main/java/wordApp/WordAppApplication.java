package wordApp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import wordApp.entity.User;
import wordApp.service.UserService;

@SpringBootApplication
public class WordAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(WordAppApplication.class, args);
	}

	@Bean 
	public CommandLineRunner commandLineRunner(UserService service) {
		return runner -> {
			System.out.println(">>> Server running...");
			createUser(service);
		};
	} 

	private void createUser(UserService service) {
		User u1 = new User("kevin", "kevin@gmail.com", "0000");
		service.save(u1);
	}

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}
