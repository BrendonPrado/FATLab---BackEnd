package com.fatlab.service;

import javax.transaction.Transactional;

import com.fatlab.domain.Aluno;
import com.fatlab.domain.Usuario;
import com.fatlab.repositories.AlunoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * AlunoService
 */
@Service
public class AlunoService extends GenericServiceImpl<Aluno> {

  @Autowired
  UsuarioService usuarioService;

  @Override
  public Aluno save(Aluno obj) {
    this.usuarioService.save(obj.getUsuario());
    return super.save(obj);
  }

  public Aluno findByUsuario(Usuario usuario) {
    return ((AlunoRepository) this.repo).findByUsuario(usuario);
  }

  public Aluno findByUsuarioId(Integer usuario) {
    return ((AlunoRepository) this.repo).findByUsuarioId(usuario);
  }

  @Transactional
  public void deleteByUsuario(Usuario usuario) {
    ((AlunoRepository) this.repo).deleteByUsuario(usuario);
    usuarioService.delete(usuario);
  }

  public Aluno findAlunoByRa(String matricula) {
    return null;
  }

  @Transactional
  public void atualizarRA(Usuario usuario, String ra) {
    Aluno aluno = findByUsuario(usuario);
    if (raEstaModificado(aluno, ra)) {
      aluno.setRa(ra);
      save(aluno);
    }
  }

  private boolean raEstaModificado(Aluno aluno, String ra) {
    return !aluno.getRa().equals(ra);
  }
}