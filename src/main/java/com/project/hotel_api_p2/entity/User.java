package com.project.hotel_api_p2.entity;

import com.project.hotel_api_p2.entity.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Entidade que representa um usuário do sistema.
 * Implementa a interface UserDetails do Spring Security
 * para permitir autenticação e controle de acesso.
 */
@Entity
@Table(name = "tb_users")
public class User implements UserDetails {

    // Identificador único do usuário (chave primária)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nome de usuário (usado para login)
    private String username;

    // Senha do usuário (armazenada de forma criptografada)
    private String password;

    // E-mail do usuário
    private String email;

    /**
     * Relação de papéis (roles) associados ao usuário.
     * Mapeado como uma coleção de enums armazenados em tabela separada.
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "role")
    private Set<UserRole> roles;

    // Construtor padrão exigido pelo JPA
    public User() {}

    // Construtor completo
    public User(Long id, String username, String password, String email, Set<UserRole> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    // Construtor prático usado ao criar usuário com um único papel
    public User(String username, String password, @Email String email, UserRole role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = Set.of(role);
    }

    // Getters e Setters padrão
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    /**
     * Retorna as permissões (authorities) do usuário
     * com base nos papéis (roles) definidos.
     * Se o usuário for ADMIN, recebe também o papel de USER.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (roles.contains(UserRole.ROLE_ADMIN)) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_USER")
            );
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    /**
     * Métodos obrigatórios da interface UserDetails
     * Todos retornam true, indicando que a conta está ativa e válida.
     */
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
