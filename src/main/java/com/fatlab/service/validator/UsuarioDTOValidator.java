package com.fatlab.service.validator;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.fatlab.domain.Aluno;
import com.fatlab.domain.Professor;
import com.fatlab.dto.UsuarioDTO;
import com.fatlab.repositories.AlunoRepository;
import com.fatlab.repositories.ProfessorRepository;
import com.fatlab.resource.exception.FieldMessage;

public class UsuarioDTOValidator implements ConstraintValidator<UsuarioDTOValido,UsuarioDTO > {

	@Autowired
	private AlunoRepository alunoRep;

	@Autowired
	private ProfessorRepository profRep;

	@Autowired
	private HttpServletRequest request;
	
	@Override
	public boolean isValid(UsuarioDTO value, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		Aluno aluno = alunoRep.findAlunoByRa(value.getMatricula());
		Professor prof = profRep.findProfessorByMatricula(value.getMatricula());
		
		boolean usuarioPreenchido = (aluno != null ) || (prof != null);

		System.out.println(this.request.getMethod());
		if(usuarioPreenchido &&  !this.request.getMethod().equals("PUT")) {
			list.add(new FieldMessage("matricula","RA ou matricula já cadastrada!"));
		}
		
		if(!value.getTipo().equals("Aluno") && !value.getTipo().equals("Professor")) {
			list.add(new FieldMessage("tipo","Por favor cadastre um tipo válido de usuario"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}
