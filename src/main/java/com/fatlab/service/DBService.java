package com.fatlab.service;

import java.util.Arrays;
import java.util.Date;

import com.fatlab.domain.Admin;
import com.fatlab.domain.Aluno;
import com.fatlab.domain.HorarioComecoFimAula;
import com.fatlab.domain.Lab;
import com.fatlab.domain.Materia;
import com.fatlab.domain.Professor;
import com.fatlab.domain.Reserva;
import com.fatlab.repositories.HorarioComecoFimAulaRepository;
import com.fatlab.repositories.LabRepository;
import com.fatlab.repositories.MateriaRepository;
import com.fatlab.repositories.ReservaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DBService {
		
		@Autowired
		ProfessorService profService;
		

		@Autowired 
		AlunoService alunoService;


		@Autowired
		AdminService admService;

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

	public void  instantiateTestDatabase() {

		Aluno a1 = new Aluno(null,"batat","123",this.encoder.encode("123"),true,"62616616126616");

		Professor prof = new Professor(null,"bata","ao@g.com",this.encoder.encode("batata"),true,"327636267");

		Admin adm = new Admin(null,"batatãozinho","b@g.com",this.encoder.encode("123"));
		Materia materia = new Materia("Algoritmos",prof,"A");

		Materia materia123 = new Materia("BataTeste",null,"A");

		Lab lab = new Lab(301,30);

		HorarioComecoFimAula horarioComecoFimAula = horaService.DefinirHorarios( 3,"Diurno" );

		Reserva reserva = new Reserva(new Date(),lab, horarioComecoFimAula, materia);

		alunoService.save(a1);
		profService.save(prof);
		admService.save(adm);




		materia.addReserva(reserva);
		prof.addMateria(materia);

		labRepository.save(lab);


		horarioComecoFimAulaRepository.save(horarioComecoFimAula);
		materiaRepository.saveAll(Arrays.asList(materia,materia123));

		alunoService.save(a1);
		profService.save(prof);
		
		reservaRepository.save(reserva);

	}
}
