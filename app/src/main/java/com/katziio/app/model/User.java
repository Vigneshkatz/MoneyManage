package com.katziio.app.model;

import com.katziio.app.util.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private List<Role> roleList;
    @OneToMany(mappedBy = "user")
    private List<Account> accountList;
    public User(Long id,String userName,String isPremium,String email,String phone,String password
    ,String isVerified)
    {
        this.id = id;
        this.userName = userName;
        this.isPremium = isPremium.equalsIgnoreCase("true");
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.isVerified = isVerified.equalsIgnoreCase("true");

    }
}
