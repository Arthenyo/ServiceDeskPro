package com.ServiceDeskPro.api.controlleres;

import com.ServiceDeskPro.api.Dtos.SubCategoryDTO;
import com.ServiceDeskPro.api.servicies.SubCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping(value = "/subcategories")
public class SubCategoryController {

    @Autowired
    private SubCategoryService subCategoryService;

    @GetMapping
    public ResponseEntity<List<SubCategoryDTO>>findAll(){
        List<SubCategoryDTO> page = subCategoryService.findAll();
        return ResponseEntity.ok().body(page);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<SubCategoryDTO>findById(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(subCategoryService.findById(id));
    }
    @PostMapping
    public ResponseEntity<SubCategoryDTO>insert(@Valid @RequestBody SubCategoryDTO subCategoryDTO){
        subCategoryDTO = subCategoryService.insert(subCategoryDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(subCategoryDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(subCategoryDTO);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<SubCategoryDTO>update(@PathVariable UUID id,@Valid @RequestBody SubCategoryDTO subCategoryDTO){
        return ResponseEntity.ok().body(subCategoryService.update(id,subCategoryDTO));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void>delete(@PathVariable UUID id){
        subCategoryService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
