package com.etslt.oss.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
//@EnableSwagger2
public class SwaggerConfig {
//    @Bean
//    public Docket weatherApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()                 
//                .apis(RequestHandlerSelectors.basePackage("com.etslt.oss.controller"))
//                .paths(regex("/users.*"))
//                .build();
//
//    }
    
    @Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("OSS").apiInfo(apiInfo()).select()
				.paths(regex("/users.*")).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Weather Service")
				.description("Documentation Generateed Using SWAGGER2 for Weather Serice API")
				.termsOfServiceUrl("www.weather-service.com")
				.license("Java License")
				.licenseUrl("https://www.weather-service.com").version("1.0").build();
	}
}