package com.ServiceDeskPro.api.Dtos;


import com.ServiceDeskPro.api.entities.Role;

import java.util.UUID;

public class RoleDTO {

    private Long id;
    private String authority;

    public RoleDTO(){}

    public RoleDTO(Long id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public RoleDTO(Role entity) {
        id = entity.getId();
        authority = entity.getAuthority();
    }

    public Long getId() {
        return id;
    }

    public String getAuthority() {
        return authority;
    }
}
