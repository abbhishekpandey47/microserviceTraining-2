package com.training.service.service;

import static org.apache.http.conn.ssl.SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.training.service.database.SampleDatabase;
import com.training.service.format.request.SumRequest;



@Service
public class SampleService {
	
	@Autowired
	SampleDatabase sDB;
	
	//private Logger logger = LoggerFactory.this.getClass();
	
	public JsonArray getRecords()
	{
		JsonObject result = null;
		JsonArray array = new JsonArray();
		
		try
		{
			SqlRowSet data = sDB.getRecords();
			
			if(data != null)
			{
				while(data.next())
				{
					result = new JsonObject();
					result.addProperty("name", data.getString(1));
					result.addProperty("code", data.getString(2));
					result.addProperty("district", data.getString(3));
					
					array.add(result);
				}
			}
			else
				result.addProperty("error", "NO Data");
			
		}
		catch(Exception e)
		{
			result.addProperty("error", "Exception");
		}
		
		
		
		return array;
	}
	
	
	public void testQuery()
	{
		sDB.testQuery();
	}
	
	
	
	public String httpPostHelperTest(SumRequest request) throws Exception {

		String responseString;
		String targetURL = "http://localhost:22020/awsGreet/getGreetings";
		
		System.out.println(targetURL);
		
		JsonObject inputParams = new JsonObject();
		inputParams.addProperty("name", request.getName());

		
		
		SSLContext sslContext1 = SSLContexts.custom().build();

		SSLConnectionSocketFactory f = new SSLConnectionSocketFactory(sslContext1, new String[] { "TLSv1.2" }, null, BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

		HttpClient httpClient = HttpClients.custom().setMaxConnTotal(400).setMaxConnPerRoute(400)
				.setDefaultRequestConfig(RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(5000).build())
				.setRetryHandler(new DefaultHttpRequestRetryHandler(5, true)).setSSLSocketFactory(f).build();

		HttpPost post = new HttpPost(targetURL);

		System.out.println("Sending Request");
		
		if (inputParams != null) {
			StringEntity params = new StringEntity(inputParams.toString());
			post.setEntity(params);
		}

		//String encoding = java.util.Base64.getEncoder().encodeToString(("userName:Password").getBytes()); //BASIC AUTHENTICATION
		
		post.addHeader("content-type", "application/json");
		post.addHeader("Connection", "close");		
		//post.addHeader("Authorization", "Basic " + encoding);

		HttpResponse response = httpClient.execute(post);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		reader.close();

		responseString = sb.toString();
						
		return responseString;				
	}
	

}
