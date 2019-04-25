package com.fatlab.service;

import com.fatlab.domain.Laboratorio;
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

    public Laboratorio findByNumero(String numero){
        return repo.findByNumero( numero );
    }

    public Laboratorio find(Integer id){
        Optional<Laboratorio> lab = repo.findById( id );
        return lab.orElseThrow( () -> new RuntimeException( "não foi possível encontrar esse lab" ) );
    }

    public Laboratorio save(Laboratorio lab) {
        return repo.save( lab );
    }

	public List<Laboratorio> findAll() {
		return repo.findAll();
    }

	public Laboratorio fromDTO(LaboratorioDTO laboratorioDTO) {
		return new Laboratorio(laboratorioDTO.getNumero(),laboratorioDTO.getCapacidade());
	}
    
}
