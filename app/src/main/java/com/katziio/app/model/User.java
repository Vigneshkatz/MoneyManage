package com.katziio.app.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    private Long id;
    private String userName;
    private Boolean isPremium;
    private String email;
    private String phone;
    private String password;
    private Boolean isVerified;
}
