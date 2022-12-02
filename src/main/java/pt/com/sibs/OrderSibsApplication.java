package pt.com.sibs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@ComponentScan(basePackages = { "pt.com.sibs" })
public class OrderSibsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderSibsApplication.class, args);
	}

}
