package oit.is.b20089.shiftoptimizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ShiftOptimizerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShiftOptimizerApplication.class, args);
	}

}
