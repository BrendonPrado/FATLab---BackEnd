package com.fatlab.service;

import com.fatlab.domain.Lab;
import com.fatlab.dto.LaboratorioDTO;
import com.fatlab.repositories.LabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class LabService {

    @Autowired
    private LabRepository repo;

    public Lab findByNumero(String numero){
        return repo.findByNumero( numero );
    }

    public Lab find(Integer id){
        Optional<Lab> lab = repo.findById( id );
        return lab.orElseThrow( () -> new RuntimeException( "não foi possível encontrar esse lab" ) );
    }

    public Lab save(Lab lab) {
        return repo.save( lab );
    }

	public List<Lab> findAll() {
		return repo.findAll();
    }

	public Lab fromDTO(LaboratorioDTO laboratorioDTO) {
		return new Lab(laboratorioDTO.getNumero(),laboratorioDTO.getCapacidade());
	}
    
}
