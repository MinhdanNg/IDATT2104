package ntnu.minhdn.compiler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CompilerApplication {

	@RequestMapping("/compiler")
	public static void main(String[] args) {
		SpringApplication.run(CompilerApplication.class, args);
	}

}