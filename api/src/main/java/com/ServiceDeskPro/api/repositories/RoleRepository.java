package com.ServiceDeskPro.api.repositories;

import com.ServiceDeskPro.api.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    Role findByAuthority(String authority);
}
