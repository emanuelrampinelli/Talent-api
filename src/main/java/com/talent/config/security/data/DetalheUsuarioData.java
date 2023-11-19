package com.talent.config.security.data;

import com.talent.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class DetalheUsuarioData implements UserDetails {

    private Usuario usuario;

    public DetalheUsuarioData(UserDetails usuario) {

        this.usuario = (Usuario) usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return usuario.getAuthorities();
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        // Se o usuario for vazio, retorne novo usuario.getSenha()
        return usuario.getPassword();
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        // Se o usuario for vazio, retorne novo usuario.getEmail()
        return usuario.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return !usuario.isEnabled();
    }
}
