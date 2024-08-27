package com.ServiceDeskPro.api.controlleres;

import com.ServiceDeskPro.api.Dtos.CategoryDTO;
import com.ServiceDeskPro.api.servicies.CategoryService;
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
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>>findAll(){
        List<CategoryDTO> page = categoryService.findAll();
        return ResponseEntity.ok().body(page);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO>findById(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findById(id));
    }
    @PostMapping
    public ResponseEntity<CategoryDTO>insert(@Valid @RequestBody CategoryDTO categoryDTO){
        categoryDTO = categoryService.insert(categoryDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(categoryDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(categoryDTO);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO>update(@PathVariable UUID id,@Valid @RequestBody CategoryDTO categoryDTO){
        return ResponseEntity.ok().body(categoryService.update(id,categoryDTO));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void>delete(@PathVariable UUID id){
        categoryService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
