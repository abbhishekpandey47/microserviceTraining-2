package com.training.service.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DBConfig {

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	@Autowired
	protected NamedParameterJdbcTemplate namedJdbcTemplate;
	
}
