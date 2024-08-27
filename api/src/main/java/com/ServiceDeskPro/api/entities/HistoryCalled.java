package com.ServiceDeskPro.api.entities;

import com.ServiceDeskPro.api.entities.enums.StatusCalled;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;
@Entity
@Table(name = "tb_history_called")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HistoryCalled {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "called_id")
    private Called called;
    private LocalDate localDate;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant createdAt;
    private String description;
    private StatusCalled previousStatus;
    private StatusCalled currentStatus;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    public void prePersist(){
        createdAt = Instant.now();
    }
}
