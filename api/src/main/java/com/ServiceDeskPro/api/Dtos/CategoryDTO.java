package com.ServiceDeskPro.api.Dtos;

import com.ServiceDeskPro.api.entities.Category;
import com.ServiceDeskPro.api.entities.Subcategory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CategoryDTO {

    private UUID id;
    private String name;
    private List<String> subcategories = new ArrayList<>();

    public CategoryDTO(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
    public CategoryDTO(Category entity) {
        id = entity.getId();
        name = entity.getName();
        for(Subcategory name : entity.getSubcategories()){
            subcategories.add(name.getName());
        }
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getSubcategories() {
        return subcategories;
    }
}
