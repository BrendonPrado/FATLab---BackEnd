package com.fatlab.service;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fatlab.domain.Admin;
import com.fatlab.domain.Aluno;
import com.fatlab.domain.HorarioComecoFimAula;
import com.fatlab.domain.Laboratorio;
import com.fatlab.domain.Materia;
import com.fatlab.domain.Professor;
import com.fatlab.domain.Reserva;
import com.fatlab.dto.UsuarioDTO;
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

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private HoraService horaService;

	@Autowired
	private UsuarioService usuarioService;

	public void  instantiateTestDatabase() {

		Aluno a1 = new Aluno(null,"batat","123",this.encoder.encode("123"),true,"62616616126616");

		Professor prof = new Professor(null,"bata","ao@g.com",this.encoder.encode("batata"),true,"327636267");

		Admin adm = new Admin(null,"batatãozinho","b@g.com",this.encoder.encode("123"));
		Materia materia = new Materia("Algoritmos",prof,"A");

		Laboratorio lab = new Laboratorio("301","30");

		HorarioComecoFimAula horarioComecoFimAula = horaService.DefinirHorarios( 3,"Diurno" );

		Reserva reserva = new Reserva(new Date(),lab, horarioComecoFimAula, materia);

		usuarioRepository.saveAll(Arrays.asList(a1,prof,adm));



		materia.addReserva(reserva);
		prof.addMateria(materia);


		materiaRepository.save(materia);
		usuarioRepository.saveAll(Arrays.asList(a1,prof));
		horarioComecoFimAulaRepository.save(horarioComecoFimAula);
		labRepository.save(lab);
		reservaRepository.save(reserva);

		UsuarioDTO usuarioDTO= new UsuarioDTO("7162616","Professor",false);

		usuarioService.saveFromDTO(usuarioDTO);


	}
}
