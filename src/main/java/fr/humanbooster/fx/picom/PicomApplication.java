package fr.humanbooster.fx.picom;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PicomApplication {

	public static void main(String[] args) {
		SpringApplication.run(PicomApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext applicationContext) {
		return args -> {

			String[] noms = applicationContext.getBeanDefinitionNames();

			for (String nom : noms) {
				System.out.println(nom + " : " + applicationContext.getBean(nom).getClass().getSimpleName());
			}
		};
	}

}
