package com.fatlab.service.validator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.fatlab.domain.Lab;
import com.fatlab.dto.ReservaMesDTO;
import com.fatlab.resource.exception.FieldMessage;
import com.fatlab.service.LabService;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * ReservaMesDTOValidator
 */
public class ReservaMesDTOValidator implements ConstraintValidator<ReservaMesDTOValido, ReservaMesDTO> {

    @Autowired
    private LabService labService;

    @Override
    public boolean isValid(ReservaMesDTO reserva, ConstraintValidatorContext context) {
        List<FieldMessage> lista = new ArrayList<>();

        Set<Lab> labs = labService.verificarDiasDoMesEmAlgumasAulas(reserva.getMes(), reserva.getDiasSemana(),reserva.getNum_aula());        
        Lab lab = labService.find(reserva.getLab_id());
        boolean labInLabs = labs.contains(lab);
        Calendar c = Calendar.getInstance();


        if(lab == null || labInLabs){
            lista.add(new FieldMessage("lab_id","Este lab não existe ou já tem reservas com esses parametros!"));
        }

        if(c.get(Calendar.MONTH) > reserva.getMes() || reserva.getMes() > 11){
            lista.add(new FieldMessage("mes","Este mês não é válido"));
        }
        
        for (FieldMessage e : lista) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}

        return lista.isEmpty();
    }

    
}