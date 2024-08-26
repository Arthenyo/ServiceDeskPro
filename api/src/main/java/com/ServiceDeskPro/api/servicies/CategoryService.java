package com.ServiceDeskPro.api.servicies;

import com.ServiceDeskPro.api.Dtos.CategoryDTO;
import com.ServiceDeskPro.api.entities.Category;
import com.ServiceDeskPro.api.entities.Subcategory;
import com.ServiceDeskPro.api.repositories.CategoryRepository;
import com.ServiceDeskPro.api.servicies.exception.DateBaseException;
import com.ServiceDeskPro.api.servicies.exception.ObjectNotFound;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll(){
        List<Category> page = categoryRepository.findAll();
        return page.stream().map(CategoryDTO::new).toList();
    }
    @Transactional(readOnly = true)
    public CategoryDTO findById(UUID id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFound("Categoria não encontrada" + id));
        return new CategoryDTO(category);
    }

    public CategoryDTO insert(CategoryDTO dto){
        Category entity = new Category();
        entityToDto(entity,dto);
        entity = categoryRepository.save(entity);
        return new CategoryDTO(entity);
    }
    @Transactional
    public CategoryDTO update(UUID id, CategoryDTO categoryDTO){
        try {
            Category entity = categoryRepository.getReferenceById(id);
            entityToDto(entity,categoryDTO);
            entity = categoryRepository.save(entity);
            return new CategoryDTO(entity);
        }catch (EntityNotFoundException e){
            throw new ObjectNotFound("Categoria nao encontrada " + id);
        }
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(UUID id){
        if(!categoryRepository.existsById(id)){
            throw new ObjectNotFound("Categoria nao encontrada " + id);
        }
        try {
            categoryRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DateBaseException("Não foi possivel deletar a Categoria " + id + ", erro de integridade");
        }
    }

    private void entityToDto(Category entity, CategoryDTO dto){
        entity.setName(dto.getName());
        entity.getSubcategories().clear();
        for(String name : dto.getSubcategories()){
            Subcategory subCategoryDTO = new Subcategory();
            subCategoryDTO.setName(name);
            entity.getSubcategories().add(subCategoryDTO);
        }
    }
}
