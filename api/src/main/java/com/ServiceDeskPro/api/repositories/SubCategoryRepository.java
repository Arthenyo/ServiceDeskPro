package com.ServiceDeskPro.api.repositories;

import com.ServiceDeskPro.api.entities.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubCategoryRepository extends JpaRepository<Subcategory, UUID> {
}
