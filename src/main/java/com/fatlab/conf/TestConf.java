package com.fatlab.conf;

import com.fatlab.resource.utils.UrlService;
import com.fatlab.service.DBService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("test")
public class TestConf {

	@Autowired
	private DBService service;

	    @Bean
	    public boolean instantiateDatabase(){
			service.instantiateTestDatabase();
	        return true;
		}
		
		@Bean
		public UrlService getUrlUtil(){
			return new UrlService();
		}

}
