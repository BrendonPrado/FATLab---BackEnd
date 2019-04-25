package com.fatlab.service.validator;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.fatlab.domain.Admin;
import com.fatlab.domain.Aluno;
import com.fatlab.domain.Materia;
import com.fatlab.domain.Professor;
import com.fatlab.domain.Usuario;
import com.fatlab.dto.MatriculaDTO;
import com.fatlab.repositories.MateriaRepository;
import com.fatlab.repositories.UsuarioRepository;
import com.fatlab.resource.exception.FieldMessage;

import org.springframework.beans.factory.annotation.Autowired;

public class MatriculaDTOValidator implements ConstraintValidator<MatriculaDTOValido, MatriculaDTO> {


    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private MateriaRepository materiaRepository;
    
    @Override
    public boolean isValid(MatriculaDTO value, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();
        
        Usuario usuario = usuarioRepo.findById(value.getUsuario_id()).get();
        Materia materia = materiaRepository.findById(value.getMateria_id()).get();

    

        if(usuario == null || usuario instanceof Admin){
            list.add(new FieldMessage("usuario_id","Este usuario não existe ou não pode matricular em materias!"));
        }

        if(materia == null){
            list.add(new FieldMessage("materia_id","Esta materia não existe!"));
        }

        if(usuario instanceof Aluno){
            if(((Aluno)usuario).getMaterias().contains(materia)){
                list.add(new FieldMessage("materia_id","Este usuário já está matriculado nesta materia"));
            }
        }

        if(usuario instanceof Professor){
            if(((Professor)usuario).getMaterias().contains(materia)){
                list.add(new FieldMessage("materia_id","Este usuário já está matriculado nesta materia"));
            }
        }

        


        for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
        return list.isEmpty();    
    }

    
}