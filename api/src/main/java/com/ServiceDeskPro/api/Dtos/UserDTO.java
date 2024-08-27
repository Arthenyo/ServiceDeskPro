package com.ServiceDeskPro.api.Dtos;


import com.ServiceDeskPro.api.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

public class UserDTO {

    private Long id;
    @NotBlank(message = "Campo Obrigatorio")
    @Size(min = 3, max =  15, message = "O campo deve ter no minimo 3 caracteres e no maximo 15 caracteres")
    private String name;
    @Email(message = "Favor Entar Email Valido")
    private String email;
    private String password;
    Set<RoleDTO> roles = new HashSet<>();

    public UserDTO() {
    }

    public UserDTO(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public UserDTO(User entity) {
        id = entity.getId();
        email = entity.getEmail();
        password = entity.getPassword();
        entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }
}
