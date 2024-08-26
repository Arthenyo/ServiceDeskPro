package com.ServiceDeskPro.api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.*;

@Entity
@Table(name = "tb_category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant createdAt;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant updateAt;
    @OneToMany(mappedBy = "category")
    private Set<Subcategory> subcategories = new HashSet<>();
    @PrePersist
    public void prePersist(){
        createdAt = Instant.now();
    }
    @PreUpdate
    public void preUpdate(){
        updateAt = Instant.now();
    }

}
