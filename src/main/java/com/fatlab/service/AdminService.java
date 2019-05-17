package com.fatlab.service;

import javax.transaction.Transactional;

import com.fatlab.domain.Admin;
import com.fatlab.domain.Usuario;
import com.fatlab.repositories.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * AdminService
 */
@Service
public class AdminService extends GenericServiceImpl<Admin> {

    @Autowired
    UsuarioService usuarioService;

    @Override
    public Admin save(Admin obj) {
        this.usuarioService.save(obj.getUsuario());
        return super.save(obj);
    }

    public Admin findByUsuario(Usuario usuario) {
        return ((AdminRepository) this.repo).findByUsuario(usuario);
    }

    @Transactional
    public void deleteByUsuario(Usuario usuario) {
        ((AdminRepository) this.repo).deleteByUsuario(usuario);
        usuarioService.delete(usuario);
    }
}