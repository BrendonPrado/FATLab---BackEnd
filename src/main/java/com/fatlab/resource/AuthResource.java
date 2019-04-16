package com.fatlab.resource;

import com.fatlab.domain.Usuario;
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
   

    @Secured({"ROLE_ALUNO","ROLE_PROFESSOR","ROLE_ADMIN"})
    @RequestMapping(value = "/me",method = RequestMethod.GET)
    public ResponseEntity<Usuario> me(){
        String email = service.authenticated().getUsername();
        Usuario usuario = usuarioService.findByEmail(email);
        return ResponseEntity.ok().body(usuario);

    }

}