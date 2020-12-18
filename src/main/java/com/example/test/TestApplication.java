package com.example.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

@EnableSwagger2
@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.example.test.controllers"))
				.paths(PathSelectors.any())
				.build()
				.enable(true)
				.apiInfo(apiInfo());
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedMethods("*");
			}
		};
	}

	private ApiInfo apiInfo() {
		//noinspection rawtypes
		List<VendorExtension> vendorExtensions = Collections.singletonList(new StringVendorExtension("", ""));

		Contact contactInfo = new Contact("TLS", "www.TLS.fr", "");

		return new ApiInfo("tls-api", "Api pour le module de gestion des employees", "1.0",
				"www" + ".la-tls" + ".org", contactInfo, "Apache 2.0", "www.apache.org", vendorExtensions);
	}

}
