package com.ServiceDeskPro.api.Dtos;

import com.ServiceDeskPro.api.entities.Subcategory;

import java.util.UUID;

public class SubCategoryDTO {

    private UUID id;
    private String name;
    private String category;

    public SubCategoryDTO(UUID id, String name, String category) {
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

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }
}
