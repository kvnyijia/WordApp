package wordApp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import wordApp.dao.UserDAO;
import wordApp.entity.User;

@SpringBootApplication
public class WordAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(WordAppApplication.class, args);
	}

	@Bean 
	public CommandLineRunner commandLineRunner(UserDAO theUserDAO) {
		return runner -> {
			System.out.println(">>> Server running...");
			createUser(theUserDAO);
		};
	} 

	private void createUser(UserDAO theUserDAO) {
		User u1 = new User("kevin", "kevin@gmail.com", "0000");
		theUserDAO.save(u1);
	}
}
