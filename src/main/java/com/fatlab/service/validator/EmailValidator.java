package com.fatlab.service.validator;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.fatlab.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;


public class EmailValidator implements ConstraintValidator<EmailValid, String> {


 

	@Autowired
	private UsuarioService usuarioService;

	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		String message =  "";
		

		if(usuarioService.findByEmail(value) != null) {
			message = "este email já esta sendo usado";
		}
		
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message)
					.addConstraintViolation();
				return message.equals("");
	}
}
