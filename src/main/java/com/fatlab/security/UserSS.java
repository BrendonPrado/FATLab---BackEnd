package com.fatlab.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.ElementCollection;
import javax.persistence.FetchType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fatlab.domain.enums.Funcao;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class UserSS implements UserDetails {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String email;
    private String senha;


    private Collection<? extends GrantedAuthority> authorities;  
  


    public UserSS(Integer id, String email, String senha, Set<Funcao> authorities) {
        super();
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.authorities = authorities.stream().map( x ->
                new SimpleGrantedAuthority( x.getDescricao() ) )
                .collect( Collectors.toList());
    }
    public UserSS() {
    }


    public boolean hasRole(Funcao func) {
		return getAuthorities().contains(new SimpleGrantedAuthority(func.getDescricao()));
}

public Integer getId() {
    return id;
}

@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
}

@Override
public String getPassword() {
    return senha;
}

@Override
public String getUsername() {
    return email;
}

@Override
public boolean isAccountNonExpired() {
    return true;
}

@Override
public boolean isAccountNonLocked() {
    return true;
}

@Override
public boolean isCredentialsNonExpired() {
    return true;
}

@Override
public boolean isEnabled() {
    return true;
}


   


}