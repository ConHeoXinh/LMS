package longND.fpt.home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "longND.fpt")
public class DoAnV1Application {

	public static void main(String[] args) {
		SpringApplication.run(DoAnV1Application.class, args);
	}

}
