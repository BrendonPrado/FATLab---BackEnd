package com.fatlab.service;

import com.fatlab.domain.RA_TIPO;
import com.fatlab.repositories.RA_TIPORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RA_TIPOService {

    @Autowired
    private RA_TIPORepository repo;


    public RA_TIPO save(RA_TIPO ra){
        return repo.save( ra );
    }

    public RA_TIPO findByRaIs(String ra) {
        return repo.findByRaIs( ra );
    }
}
