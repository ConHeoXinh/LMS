package longND.fpt.home;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = "longND.fpt")
@EnableScheduling
public class DoAnV1Application {

	public static void main(String[] args) {
		SpringApplication.run(DoAnV1Application.class, args);
	}

}
