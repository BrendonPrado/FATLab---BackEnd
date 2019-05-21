package com.fatlab.service;


import com.fatlab.domain.Lab;
import com.fatlab.dto.LaboratorioDTO;
import com.fatlab.repositories.LabRepository;

import org.springframework.stereotype.Service;


@Service
public class LabService extends GenericServiceImpl<Lab>{


    public Lab findByNumero(Integer numero){
        return ((LabRepository) this.repo).findByNumero(numero);
    }

	public Lab fromDTO(LaboratorioDTO laboratorioDTO) {
		return new Lab(laboratorioDTO.getNumero(),laboratorioDTO.getCapacidade());
	}

	public void updateFromDTO(Integer id,LaboratorioDTO laboratorioDTO) {
        Lab lab = find(id);
        setInfosByDTO(lab,laboratorioDTO);
        save(lab);
	}

    private void setInfosByDTO(Lab lab,LaboratorioDTO laboratorioDTO) {
        lab.setCapacidade(laboratorioDTO.getCapacidade());
        lab.setNumero(laboratorioDTO.getNumero());
    }
    
}
