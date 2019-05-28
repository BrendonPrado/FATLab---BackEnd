package com.fatlab.service.validator;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.fatlab.domain.Aluno;
import com.fatlab.domain.Materia;
import com.fatlab.domain.Professor;
import com.fatlab.dto.MatriculaDTO;
import com.fatlab.repositories.MateriaRepository;
import com.fatlab.resource.exception.FieldMessage;
import com.fatlab.service.AlunoService;
import com.fatlab.service.ProfessorService;

import org.springframework.beans.factory.annotation.Autowired;

public class MatriculaDTOValidator implements ConstraintValidator<MatriculaDTOValido, MatriculaDTO> {

    @Autowired
    private ProfessorService profService;


    @Autowired
    private AlunoService alunoService;

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private HttpServletRequest req;

    @Override
    public boolean isValid(MatriculaDTO value, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();
        
        Professor professor = null;
        Aluno aluno = null;


        if(req.getRequestURI().equals("/materias/professor")){
            if(!req.getRequestURI().equals("PUT") && value.getUsuario_id() == null)
                professor = profService.findByUsuarioId(value.getUsuario_id());
        }else  if(req.getRequestURI().equals("/materias/alunos")){
            aluno = alunoService.findByUsuarioId(value.getUsuario_id());
        }

        Materia materia = materiaRepository.findById(value.getMateria_id()).get();

        if(materia == null){
            list.add(new FieldMessage("materia_id","Esta materia não existe!"));
        }

        if(aluno != null && aluno.getMaterias().contains(materia)){
            if(aluno.getMaterias().contains(materia)){
                list.add(new FieldMessage("materia_id","Este usuário já está matriculado nesta materia"));
            }
        }

        if(professor != null && materia.getProfessor() != null){
            if(professor.getMaterias().contains(materia)){
                list.add(new FieldMessage("materia_id","Esta materia já tem professor!"));
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