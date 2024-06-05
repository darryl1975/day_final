package sg.edu.nus.iss.day38_backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sg.edu.nus.iss.day38_backend.service.ImageService;

@SpringBootApplication
public class Day38BackendApplication implements CommandLineRunner {

	@Autowired
	ImageService imgSvc;

	public static void main(String[] args) {
		SpringApplication.run(Day38BackendApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		imgSvc.initDirectory();
	}

}
