package com.fatlab.service;


import java.util.Arrays;
import java.util.Date;

import com.fatlab.domain.*;
import com.fatlab.domain.enums.Tipo;
import com.fatlab.dto.UsuarioDTO;
import com.fatlab.dto.UsuarioNewDTO;
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
	private HoraService horaService;

	@Autowired
	private UsuarioService usuarioService;

	public void  instantiateTestDatabase() {

		Aluno a1 = new Aluno(null,"Joao","j@g.com","batata",false,"62616616126616");

		Professor prof = new Professor(null,"jao","ao@g.com","batata",true,"327636267");



		Materia materia = new Materia("Algoritmos",prof,"A");

		Laboratorio lab = new Laboratorio("301");

		HorarioComecoFimAula horarioComecoFimAula = horaService.DefinirHorarios( 3,"Diurno" );

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

		UsuarioDTO usuarioDTO= new UsuarioDTO("7162616","Aluno",true);
		UsuarioNewDTO usuarioNewDTO = new UsuarioNewDTO("Jão","jao@g.com","batata","7162616");

		usuarioService.saveFromDTO(usuarioDTO);
		usuarioService.saveFromNewDTO(usuarioNewDTO);


	}
}
