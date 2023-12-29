package com.katziio.app.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "auth")
@Data
public class Authentication {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private User user;
    private Long otp;
}
