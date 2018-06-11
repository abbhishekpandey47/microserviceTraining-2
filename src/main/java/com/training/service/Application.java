package com.training.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import com.training.service.service.SampleService;

@SpringBootApplication
@PropertySource("file:${DB_CONNECT}")
public class Application implements CommandLineRunner{
	
	@Autowired
	SampleService svc;
	
	public static void main(String[] args)
	{		
		SpringApplication.run(Application.class, args);
	}
	
	
	public void run(String... arg0)
	{
		System.out.println("Hello Welcome.. Lets connect to DB before our applications Starts.... ");
		
		svc.testQuery();
		
		System.out.println("Application Deployed");
	}

}
