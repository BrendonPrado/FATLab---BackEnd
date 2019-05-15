package com.fatlab.service.validator;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.fatlab.domain.Aluno;
import com.fatlab.domain.Materia;
import com.fatlab.domain.Professor;
import com.fatlab.dto.MatriculaDTO;
import com.fatlab.repositories.MateriaRepository;
import com.fatlab.resource.exception.FieldMessage;
import com.fatlab.service.GenericService;

import org.springframework.beans.factory.annotation.Autowired;

public class MatriculaDTOValidator implements ConstraintValidator<MatriculaDTOValido, MatriculaDTO> {

    @Autowired
    GenericService<Professor> profService;


    @Autowired
    GenericService<Aluno> alunoService;

    
    @Autowired
    private MateriaRepository materiaRepository;
    
    @Override
    public boolean isValid(MatriculaDTO value, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();
        
        Aluno aluno = alunoService.find(value.getUsuario_id());
        Professor professor = profService.find(value.getUsuario_id());

        Materia materia = materiaRepository.findById(value.getMateria_id()).get();

    

        if(aluno == null && aluno == null ){
            list.add(new FieldMessage("usuario_id","Este usuario não existe ou não pode matricular em materias!"));
        }

        if(materia == null){
            list.add(new FieldMessage("materia_id","Esta materia não existe!"));
        }

        if(aluno.getMaterias().contains(materia)){
            if(aluno.getMaterias().contains(materia)){
                list.add(new FieldMessage("materia_id","Este usuário já está matriculado nesta materia"));
            }
        }

        if(aluno.getMaterias().contains(materia)){
            if(professor.getMaterias().contains(materia)){
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