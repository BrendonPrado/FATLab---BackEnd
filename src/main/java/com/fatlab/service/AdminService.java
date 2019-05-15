package com.fatlab.service;

import com.fatlab.domain.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * AdminService
 */
@Service
public class AdminService extends GenericServiceImpl<Admin>{

    @Autowired
    UsuarioService UsuarioService;
    
    @Override
    public Admin save(Admin obj) {
        this.UsuarioService.save(obj.getUsuario());
        return super.save(obj);
    }
}