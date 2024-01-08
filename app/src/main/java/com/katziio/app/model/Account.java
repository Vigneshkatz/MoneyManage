package com.katziio.app.model;

import com.katziio.app.dto.AccountDTO;
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
    private Long accountMonthlySpendLimit;
    private Long monthlySpent;
    public Account(AccountDTO other)
    {
        this.id = other.getId()==null?null:other.getId();
        this.user = other.getUser();
        this.accountNumber = other.getAccountNumber();
        this.currentBalance = other.getCurrentBalance();
        this.cardNumber = other.getCardNumber();
        this.cvv = other.getCvv();
        this.bankName = other.getBankName();
        this.ifsc = other.getIfsc();
        this.phoneLinked = other.getPhoneLinked();
        this.isNetBanking = other.getIsNetBanking();
        this.isActive = other.getIsActive();
        this.createdAt = other.getCreatedAt();
        this.accountType = other.getAccountType();
        this.accountMonthlySpendLimit = other.getAccountMonthlySpendLimit();
        this.monthlySpent = other.getMonthlySpent();
    }

    public Account(Account other)
    {
        this.id = other.getId();
        this.user = other.getUser();
        this.accountNumber = other.getAccountNumber();
        this.currentBalance = other.getCurrentBalance();
        this.cardNumber = other.getCardNumber();
        this.cvv = other.getCvv();
        this.bankName = other.getBankName();
        this.ifsc = other.getIfsc();
        this.phoneLinked = other.getPhoneLinked();
        this.isNetBanking = other.getIsNetBanking();
        this.isActive = other.getIsActive();
        this.createdAt = other.getCreatedAt();
        this.accountType = other.getAccountType();
        this.accountMonthlySpendLimit = other.getAccountMonthlySpendLimit();
        this.monthlySpent = other.getMonthlySpent();
    }
}
