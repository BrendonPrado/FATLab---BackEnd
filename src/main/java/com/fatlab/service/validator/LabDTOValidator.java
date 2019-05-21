package com.fatlab.service.validator;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.fatlab.domain.Lab;
import com.fatlab.dto.LaboratorioDTO;
import com.fatlab.repositories.LabRepository;
import com.fatlab.resource.exception.FieldMessage;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * LabDTOValidoValidator
 */
public class LabDTOValidator implements ConstraintValidator<LabDTOValido, LaboratorioDTO> {


    @Autowired
    private LabRepository labRepo;

    @Autowired
    private HttpServletRequest req;

    @Override
    public boolean isValid(LaboratorioDTO value, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();


        Lab lab = this.labRepo.findByNumero(value.getNumero());
        if(lab != null && !req.getMethod().equals("PUT")){
            list.add(new FieldMessage("numero", "Este já lab já foi cadastrado"));
        }

        Integer capacidade = null;
        try{
        capacidade = value.getCapacidade();
        }
        catch(Exception e){
            list.add(new FieldMessage("capacidade","por favor insira um numero inteiro maior que zero"));
        }

        if( capacidade != null && capacidade <= 0){
            list.add(new FieldMessage("capacidade", "A capacidade deve ser maior que zero!"));
        }
        
        for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
        return list.isEmpty();
    }


    
}