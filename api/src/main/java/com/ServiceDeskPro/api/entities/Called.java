package com.ServiceDeskPro.api.entities;

import com.ServiceDeskPro.api.entities.enums.PriorityCalled;
import com.ServiceDeskPro.api.entities.enums.StatusCalled;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "tb_called")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Called {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant createdAt;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant updateAt;
    @Enumerated(EnumType.STRING)
    private StatusCalled status;
    @Enumerated(EnumType.STRING)
    private PriorityCalled priority;
    private LocalDateTime sla;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "technical_id")
    private User technical;
    @ManyToOne
    @JoinColumn(name = "subcategory_id")
    private Subcategory subcategory;
    @OneToMany(mappedBy = "called")
    private List<HistoryCalled> historyCalleds = new ArrayList<>();

    @PrePersist
    public void prePersist(){
        createdAt = Instant.now();
    }
    @PreUpdate
    public void preUpdate(){
        updateAt = Instant.now();
    }
}
