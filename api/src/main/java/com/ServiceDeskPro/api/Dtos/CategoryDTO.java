package com.ServiceDeskPro.api.Dtos;

import com.ServiceDeskPro.api.entities.Category;
import com.ServiceDeskPro.api.entities.Subcategory;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

public class CategoryDTO {

    private Long id;
    @NotBlank(message = "Campo Obrigatorio")

    private String name;
    private List<String> subcategories = new ArrayList<>();

    public CategoryDTO(Long id, String name) {
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

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getSubcategories() {
        return subcategories;
    }
}
