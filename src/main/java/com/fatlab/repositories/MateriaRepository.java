package com.fatlab.repositories;

import java.util.Date;
import java.util.List;

import com.fatlab.domain.HorarioComecoFimAula;
import com.fatlab.domain.Materia;
import com.fatlab.domain.Usuario;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriaRepository extends GenericRepository<Materia> {

    List<Materia> findByAlunos(Usuario alunos);

    List<Materia> findByProfessor(Usuario professor);

    @Query("select m from Materia m"+
    " inner join m.reservas r where m in ( :materias )"+
    " and r.horarioComecoFimAula = :hora and r.diaMes = :data")
	Materia findMateriaInMateriasAndHora(List<Materia> materias, HorarioComecoFimAula hora, Date data);

}
