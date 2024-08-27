package com.ServiceDeskPro.api.Dtos;

import com.ServiceDeskPro.api.entities.Subcategory;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class SubCategoryDTO {

    private Long id;
    @NotBlank(message = "Campo Obrigatorio")
    private String name;
    private String category;

    public SubCategoryDTO(Long id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }
    public SubCategoryDTO(Subcategory entity) {
        id = entity.getId();
        name = entity.getName();
        if(entity.getCategory() == null){
            category = null;
        }else {
            category = entity.getCategory().getName();
        }

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }
}
