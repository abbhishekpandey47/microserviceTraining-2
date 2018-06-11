package com.training.service.database;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.google.gson.JsonObject;

@Repository
public class SampleDatabase extends DBConfig {
	
	
	public SqlRowSet getRecords()
	{
		JsonObject result = new JsonObject();
		
		SqlRowSet srs = null;
		
		String sql = "Select * from dummycity";
		
		srs = jdbcTemplate.queryForRowSet(sql);		
		
		return srs;
	}
	
	
	
	public void testQuery()
	{
		jdbcTemplate.queryForRowSet("select * from dual");
		
		System.out.println("Connected...");
	}
	

}
