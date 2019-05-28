package com.fatlab.service.validator;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.fatlab.domain.Aluno;
import com.fatlab.domain.Professor;
import com.fatlab.domain.Usuario;
import com.fatlab.dto.UsuarioNewDTO;
import com.fatlab.repositories.AlunoRepository;
import com.fatlab.repositories.ProfessorRepository;
import com.fatlab.resource.exception.FieldMessage;
import com.fatlab.service.UserService;
import com.fatlab.service.UsuarioService;
import com.fatlab.service.excetions.AuthorizationException;

import org.springframework.beans.factory.annotation.Autowired;

public class UsuarioNewDTOValidator implements ConstraintValidator<UsuarioNewDTOValido ,UsuarioNewDTO > {

	
	@Autowired
	private AlunoRepository alunoRep;

	@Autowired
	private ProfessorRepository profRep;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private UserService userService;

	@Autowired
	private UsuarioService usuarioService;


	@Override
	public boolean isValid(UsuarioNewDTO value, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if(request.getMethod().equals("POST")){
			validatedPost(value, list);
		} else {
			Usuario usuario = this.usuarioService.findByEmail(this.userService.authenticated().getUsername());
			String idPath = request.getRequestURL().substring(request.getRequestURL().toString().lastIndexOf('/')+1,request.getRequestURL().toString().length());
			if(usuario.getId() != Integer.parseInt(idPath)){
				list.add(new FieldMessage("nome", "Somente este usuário pode atualizar essas informaçoes!"));
				throw new AuthorizationException("Acesso negado");
			}
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

	private void validatedPost(UsuarioNewDTO value, List<FieldMessage> list) {
		if (value.getMatricula() == null || value.getMatricula() == "") {
			list.add(new FieldMessage("matricula", "Por Favor insira uma matricula válida"));
		}
		Aluno aluno = alunoRep.findAlunoByRa(value.getMatricula());
		Professor prof = profRep.findProfessorByMatricula(value.getMatricula());

		boolean usuarioPreenchido = (aluno != null && aluno.getUsuario().getNome() != null)
				|| (prof != null && prof.getUsuario().getNome() != null);

		if (usuarioPreenchido) {
			list.add(new FieldMessage("matricula", "este usuário já possui cadastro, insira um ra válido!"));
		}
	}

}
