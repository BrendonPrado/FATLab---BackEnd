package com.fatlab.service.validator;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.fatlab.domain.Lab;
import com.fatlab.dto.ReservaDTO;
import com.fatlab.resource.exception.FieldMessage;
import com.fatlab.service.LabService;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * ReservaDTOValidator
 */
public class ReservaDTOValidator implements ConstraintValidator<ReservaDTOValido, ReservaDTO> {

    @Autowired
    private LabService labService;

    @Override
    public boolean isValid(ReservaDTO reservaDTO, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        List<Lab> labs = this.labService.getAllDispLabs(reservaDTO.getDiaMes(),reservaDTO.getNum_aula());
        Lab lab = labService.find(reservaDTO.getLab_id());

        if(!labs.contains(lab)){
            list.add(new FieldMessage("lab_id","Este lab já esta marcado com uma reserva nesta data"));
        }
        
        for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
    }

    
}