package com.smartmail.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;



@Configuration

public class SweggerUI {
/*
	@Bean
	public Docket getDoket() {
		
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.smartmail"))
				.paths(PathSelectors.any())
				.build().apiInfo(apiMetaData());
	}
	
	
	private ApiInfo apiMetaData() {
		
		return new ApiInfoBuilder()
				.title("Smart Mail Sender")
				.contact(new Contact("Dev - Team ", "test", "test@gamail.com"))
				.license("Apache 2.0")
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
				.version("1.0.0").build();
				
		
		
	}
	*/
}
