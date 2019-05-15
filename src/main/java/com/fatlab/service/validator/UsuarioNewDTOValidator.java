package com.fatlab.service.validator;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.fatlab.domain.Aluno;
import com.fatlab.domain.Professor;
import com.fatlab.dto.UsuarioNewDTO;
import com.fatlab.repositories.AlunoRepository;
import com.fatlab.repositories.ProfessorRepository;
import com.fatlab.resource.exception.FieldMessage;

public class UsuarioNewDTOValidator implements ConstraintValidator<UsuarioNewDTOValido ,UsuarioNewDTO > {

	
	@Autowired
	private AlunoRepository alunoRep;

	@Autowired
	private ProfessorRepository profRep;

	@Override
	public boolean isValid(UsuarioNewDTO value, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		

	
		
		Aluno aluno = alunoRep.findAlunoByRa(value.getMatricula());
		Professor prof = profRep.findProfessorByMatricula(value.getMatricula());
		
		boolean usuarioPreenchido = (aluno != null && aluno.getUsuario().getNome() != null) 
		|| (prof != null && prof.getUsuario().getNome() != null);
		if(usuarioPreenchido ) {
			list.add(new FieldMessage("matricula","este usuário já possui cadastro, insira um ra válido!"));
		}
			
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}
