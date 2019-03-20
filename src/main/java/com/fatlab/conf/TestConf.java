package com.fatlab.conf;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.fatlab.service.DBService;


@Configuration
@Profile("test")
public class TestConf {
	@Autowired
	DBService service;

	    @Bean
	    public boolean instantiateDatabase() throws ParseException {
	        service.instantiateTestDatabase();
	        return true;
	    }

}
