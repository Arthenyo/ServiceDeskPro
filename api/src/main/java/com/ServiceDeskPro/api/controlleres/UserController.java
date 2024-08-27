package com.ServiceDeskPro.api.controlleres;

import com.ServiceDeskPro.api.Dtos.UserDTO;
import com.ServiceDeskPro.api.Dtos.UserInsertDTO;
import com.ServiceDeskPro.api.Dtos.UserUpdateDTO;
import com.ServiceDeskPro.api.servicies.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<UserDTO>>findAll(Pageable pageable){
        Page<UserDTO> page = userService.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserDTO>findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
    public ResponseEntity<UserDTO>findMe(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findMe());
    }

    @PostMapping
    public ResponseEntity<UserDTO>insert(@Valid @RequestBody UserInsertDTO userDTO){
        UserDTO newDTO = userService.Insert(userDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(newDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(newDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserDTO>update(@PathVariable Long id , @Valid @RequestBody UserUpdateDTO userUpdateDTO){
        UserDTO newDto = userService.update(id,userUpdateDTO);
        return ResponseEntity.ok().body(newDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void>delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
