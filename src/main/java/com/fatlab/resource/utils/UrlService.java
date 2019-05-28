package com.fatlab.resource.utils;

import java.util.HashSet;
import java.util.Set;

import lombok.NoArgsConstructor;

/**
 * UrlUtil
 */
@NoArgsConstructor
public class UrlService {

    public Set<Integer> decodeIntList(String n){
        String[] vet = n.split( "," );
        return this.converteParaIntList(vet);
    }

    private Set<Integer> converteParaIntList(String[] vet) {
        Set<Integer> lista = new HashSet<Integer>();
        for (String cate : vet) {
            lista.add(Integer.parseInt(cate));
        }
        return lista;
    }
    
}