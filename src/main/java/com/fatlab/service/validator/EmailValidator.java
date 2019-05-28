package com.fatlab.service.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.fatlab.domain.Usuario;
import com.fatlab.service.UserService;
import com.fatlab.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;


public class EmailValidator implements ConstraintValidator<EmailValid, String> {


 

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UserService service;


	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		String message =  "";
		
		String email = this.service.authenticated().getUsername();
		Usuario usuario = usuarioService.findByEmail(email);

		if(usuarioService.findByEmail(value) != null && !email.equals(usuario.getEmail())) {
			message = "este email já esta sendo usado";
		}
		
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message)
					.addConstraintViolation();
				return message.equals("");
	}
}
