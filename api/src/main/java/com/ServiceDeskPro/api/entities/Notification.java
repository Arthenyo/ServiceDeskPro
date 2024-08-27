package com.ServiceDeskPro.api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;
@Entity
@Table(name = "tb_notification")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "called_id")
    private Called called;
    @Column(columnDefinition = "TEXT")
    private String message;
    private LocalDate shippingDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
