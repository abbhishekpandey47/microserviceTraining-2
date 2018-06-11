package com.training.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.training.service.format.request.SumRequest;
import com.training.service.format.response.ResponseFormat;
import com.training.service.service.SampleService;
import com.training.service.utils.ApplicationUtils;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/service")
@EnableScheduling
public class SampleController {

	
	@Autowired
	SampleService sampleSrvc;
	
	@PostMapping("/getSum")
	public String getSum(@ApiParam @RequestBody SumRequest request)
	{
		
		ResponseFormat response = new ResponseFormat();
		
		try
		{
			if(Integer.toString(request.getOperand1()) == "")
				response.setError("Missing Operand 1");
			else if(Integer.toString(request.getOperand2()) == "")
				response.setError("Missing Operand 2");
			else
			{
				int sum = request.getOperand1() + request.getOperand2();
				
				response.setSum(sum);
			}
			
		}
		catch(Exception e)
		{
			response.setError("Exception");
		}
		
		return ApplicationUtils.createJsonFormat(response);
	}
	
	@GetMapping("/callOutService")
	//@Scheduled(initialDelay=5000, fixedRate=10000)
	public String getGreetings()
	{
		ResponseFormat response = new ResponseFormat();
		System.out.println("IN CALL OUT SERVIUCE....");
		
		SumRequest request = new SumRequest();
		request.setName("Training");
		
		try
		{
			if(request.getName() == null || request.getName() == "")
			{
				response.setError("Missing name Attribute or Value");
			}
			else
			{
				response.setMessage(sampleSrvc.httpPostHelperTest(request));
			}
		}
		catch(Exception e)
		{
			response.setError("Exception");
		}
		
		return ApplicationUtils.createJsonFormat(response);
	}
	
	@PostMapping("/getRecords")
	public String getRecords()
	{
		JsonArray j = new JsonArray();
		try
		{
			
				j = sampleSrvc.getRecords();
			
		}
		catch(Exception e)
		{
			//j.addProperty("error", "GONE Wrong");
		}
		
		return ApplicationUtils.createJsonFormat(j);
	}
	
	
}
