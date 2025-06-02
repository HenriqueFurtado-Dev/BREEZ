package br.com.breez.breez;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		exclude = {
				org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class,
				org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration.class,
				org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration.class
		}
)
public class BreezApplication {
	public static void main(String[] args) {
		SpringApplication.run(BreezApplication.class, args);
	}
}
