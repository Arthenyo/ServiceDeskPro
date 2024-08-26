package com.ServiceDeskPro.api.servicies;

import com.ServiceDeskPro.api.Dtos.SubCategoryDTO;
import com.ServiceDeskPro.api.entities.Category;
import com.ServiceDeskPro.api.entities.Subcategory;
import com.ServiceDeskPro.api.repositories.CategoryRepository;
import com.ServiceDeskPro.api.repositories.SubCategoryRepository;
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
public class SubCategoryService {

    @Autowired
    private SubCategoryRepository subCategoryRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<SubCategoryDTO> findAll(){
        List<Subcategory> page = subCategoryRepository.findAll();
        return page.stream().map(SubCategoryDTO::new).toList();
    }
    @Transactional(readOnly = true)
    public SubCategoryDTO findById(UUID id){
        Subcategory subcategory = subCategoryRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFound("SubCategoria não encontrada" + id));
        return new SubCategoryDTO(subcategory);
    }

    public SubCategoryDTO insert(SubCategoryDTO dto){
        Subcategory entity = new Subcategory();
        entityToDto(entity,dto);
        entity = subCategoryRepository.save(entity);
        return new SubCategoryDTO(entity);
    }
    @Transactional
    public SubCategoryDTO update(UUID id, SubCategoryDTO categoryDTO){
        try {
            Subcategory entity = subCategoryRepository.getReferenceById(id);
            entityToDto(entity,categoryDTO);
            entity = subCategoryRepository.save(entity);
            return new SubCategoryDTO(entity);
        }catch (EntityNotFoundException e){
            throw new ObjectNotFound("SubCategoria nao encontrada " + id);
        }
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(UUID id){
        if(!subCategoryRepository.existsById(id)){
            throw new ObjectNotFound("SubCategoria nao encontrada " + id);
        }
        try {
            subCategoryRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DateBaseException("Não foi possivel deletar a SubCategoria " + id + ", erro de integridade");
        }
    }

    private void entityToDto(Subcategory entity, SubCategoryDTO dto){
        entity.setName(dto.getName());
        entity.setCategory(categoryRepository.findByName(dto.getCategory()));
    }
}
