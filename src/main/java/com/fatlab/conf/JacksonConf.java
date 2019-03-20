package com.fatlab.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fatlab.domain.Aluno;
import com.fatlab.domain.Professor;

public class JacksonConf{
	  @Bean
	    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
	        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
	            public void configure(ObjectMapper objectMapper) {
	                objectMapper.registerSubtypes( Professor.class);
	                objectMapper.registerSubtypes( Aluno.class);
	                super.configure(objectMapper);
	            };
	        };
	        return builder;
	    }
}
