package com.talent.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.talent.enums.UsuarioPerfilEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Entidade que representa um Usuário.
 */
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "USUARIO")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Column
    private UsuarioPerfilEnum perfil;

    @NotNull
    @Column
    private Date dataCadastro;

    @NotNull
    @Column
    private int isBloqueado;

    @Column
    private String email;

    @Column
    @JsonIgnore
    private String senha;

    /**
     * Obtém as autoridades (roles) associadas ao usuário.
     *
     * @return Lista de autoridades do usuário.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.perfil == UsuarioPerfilEnum.ROLE_ADMIN) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_USER")
            );
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    /**
     * Obtém a senha do usuário.
     *
     * @return Senha do usuário.
     */
    @Override
    @JsonIgnore
    public String getPassword() {
        return this.getSenha();
    }

    /**
     * Obtém o nome de usuário (email) do usuário.
     *
     * @return Nome de usuário (email) do usuário.
     */
    @Override
    public String getUsername() {
        return this.getEmail();
    }

    /**
     * Verifica se a conta do usuário não está expirada.
     *
     * @return true se a conta não estiver expirada, false caso contrário.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Verifica se a conta do usuário não está bloqueada.
     *
     * @return true se a conta não estiver bloqueada, false caso contrário.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Verifica se as credenciais do usuário não estão expiradas.
     *
     * @return true se as credenciais não estiverem expiradas, false caso contrário.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Verifica se o usuário está habilitado.
     *
     * @return true se o usuário estiver habilitado, false caso contrário.
     */
    @Override
    public boolean isEnabled() {
        return this.getIsBloqueado() == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Usuario usuario = (Usuario) o;
        return id != null && Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
