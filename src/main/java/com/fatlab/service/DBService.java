package com.fatlab.service;


import java.util.Arrays;
import java.util.Date;

import com.fatlab.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatlab.repositories.HorarioComecoFimAulaRepository;
import com.fatlab.repositories.LabRepository;
import com.fatlab.repositories.MateriaRepository;
import com.fatlab.repositories.ReservaRepository;
import com.fatlab.repositories.UsuarioRepository;

@Service
public class DBService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private MateriaRepository materiaRepository;
	
	@Autowired
	private ReservaRepository reservaRepository;
	
	@Autowired
	private LabRepository labRepository;
	
	@Autowired
	private HorarioComecoFimAulaRepository horarioComecoFimAulaRepository;
	
	public void  instantiateTestDatabase() {

		RA_TIPO r1 = new RA_TIPO("829829289",Tipo.PROFESSOR);
		RA_TIPO r2 = new RA_TIPO("295467890",Tipo.ALUNO);

		Aluno a1 = new Aluno("Joao","j@g.com","batata",r1);
		
		Professor prof = new Professor("jao","ao@g.com","batata",r2);
		
		Materia materia = new Materia("Algoritmos",prof,"A");
		
		Laboratorio lab = new Laboratorio(301);
		
		HorarioComecoFimAula horarioComecoFimAula = new HorarioComecoFimAula("Diurno",3); 
		
		Reserva reserva = new Reserva(new Date(),lab, horarioComecoFimAula, materia);
		
		usuarioRepository.saveAll(Arrays.asList(a1,prof));

		materia.addReserva(reserva);
		prof.addMateria(materia);
		a1.addMateria(materia);

		
		materiaRepository.save(materia);
		usuarioRepository.saveAll(Arrays.asList(a1,prof));
		horarioComecoFimAulaRepository.save(horarioComecoFimAula);
		labRepository.save(lab);
		reservaRepository.save(reserva);
		

	}
}
