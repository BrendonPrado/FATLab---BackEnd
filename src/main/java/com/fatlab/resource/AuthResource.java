package com.fatlab.resource;

import java.util.ArrayList;
import java.util.List;

import com.fatlab.domain.Aluno;
import com.fatlab.domain.Materia;
import com.fatlab.domain.Professor;
import com.fatlab.domain.Usuario;
import com.fatlab.domain.enums.Funcao;
import com.fatlab.service.AlunoService;
import com.fatlab.service.MateriaService;
import com.fatlab.service.ProfessorService;
import com.fatlab.service.UserService;
import com.fatlab.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource{

    @Autowired
    private UserService service;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProfessorService profService;

    @Autowired
    private AlunoService alunoService;
   

    @Secured({"ROLE_ALUNO","ROLE_PROFESSOR","ROLE_ADMIN"})
    @RequestMapping(value = "/me",method = RequestMethod.GET)
    public ResponseEntity<Usuario> me(){
        String email = service.authenticated().getUsername();
        Usuario usuario = usuarioService.findByEmail(email);
        return ResponseEntity.ok().body(usuario);

    }

    @Secured({"ROLE_ALUNO","ROLE_PROFESSOR"})
    @RequestMapping(value = "/me/materias",method = RequestMethod.GET)
    public ResponseEntity<List<Materia>> minhasMaterias(){

        String email = service.authenticated().getUsername();
        Usuario usuario = usuarioService.findByEmail(email);
        List<Materia> materias = new ArrayList<Materia>();
        
        if(usuario.getFuncao().contains(Funcao.Aluno)){
            materias = alunoService.findByUsuario(usuario).getMaterias();
        }else if(usuario.getFuncao().contains(Funcao.Professor)){
            materias = profService.findByUsuario(usuario).getMaterias();
        }
        
        return ResponseEntity.ok().body(materias);
    }
}