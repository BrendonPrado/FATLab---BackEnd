package com.fatlab.service;


import java.util.Arrays;
import java.util.Date;

import com.fatlab.domain.*;
import com.fatlab.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


	@Autowired
	private RA_TIPORepository ra_tipoRepository;


	public void  instantiateTestDatabase() {

		RA_TIPO r1 = new RA_TIPO("829829289",Tipo.PROFESSOR);
		RA_TIPO r2 = new RA_TIPO("295467890",Tipo.ALUNO);
		RA_TIPO r3 = new RA_TIPO("123456789",Tipo.ALUNO );

		Aluno a1 = new Aluno("Joao","j@g.com","batata");
		
		Professor prof = new Professor("jao","ao@g.com","batata");



		Materia materia = new Materia("Algoritmos",prof,"A");
		
		Laboratorio lab = new Laboratorio(301);
		
		HorarioComecoFimAula horarioComecoFimAula = new HorarioComecoFimAula("Diurno",3); 
		
		Reserva reserva = new Reserva(new Date(),lab, horarioComecoFimAula, materia);

		usuarioRepository.saveAll(Arrays.asList(a1,prof));
		r1.setUsuario( prof );
		r2.setUsuario( a1 );

		ra_tipoRepository.saveAll( Arrays.asList( r1,r2,r3) );

		prof.setRa( r1 );
		a1.setRa( r2 );
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
