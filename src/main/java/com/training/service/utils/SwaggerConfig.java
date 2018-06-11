package com.training.service.utils;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



/**
 * Created by Abhishek on 5th Mar 2018.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig 
{
	
    @Bean
    public Docket api() 
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(paths())
                .build().apiInfo(metadata());
    }
    
    
    @SuppressWarnings("unchecked")
	private Predicate<String> paths() 
    {        
		return or(
	            regex("/service.*"));
     }
    
    private ApiInfo metadata() 
    {
        Contact contact = new Contact("Abhishek Pandey", "99025424**", "abbhishekpandey47@gmail.com");
        return new ApiInfoBuilder().title("training Service")
                .description("Port : 22021").version("1.0").contact(contact).build();
    }
}
