package com.katziio.app.model;

import com.katziio.app.dto.request.AccountRequestDTO;
import com.katziio.app.util.enums.AccountType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "users_account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
    @OneToMany(mappedBy = "account")
    private List<Expense> expensesList;
    public Account(AccountRequestDTO other) {
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

    public Account(Account other) {
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

    public Account(User user, String s, String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10, String s11, String s12) {
    }
}