package com.appleyk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import com.appleyk.util.OutPutTime;

@SpringBootApplication// same as @Configuration @EnableAutoConfiguration  @ComponentScan	
public class Application extends SpringBootServletInitializer{

	public static void  main(String[] args) {      		
		SpringApplication.run(Application.class, args);
		OutPutTime.outPutTime();
	}	
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
}
