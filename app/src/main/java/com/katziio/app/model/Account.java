package com.katziio.app.model;

import com.katziio.app.util.enums.AccountType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "user_account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "account_id")
    private Long id;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private User user;
    private Long accountNumber;
    private Long currentBalance;
    private String cardNumber;
    private Integer cvv;
    private String bankName;
    private String ifsc;
    private String phoneLinked;
    private Boolean isNetBanking;
    private Boolean isActive;
    private Date createdAt;
    private AccountType accountType;
}
