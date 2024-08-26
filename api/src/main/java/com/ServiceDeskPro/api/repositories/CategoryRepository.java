package com.ServiceDeskPro.api.repositories;

import com.ServiceDeskPro.api.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    Category findByName(String name);
}
