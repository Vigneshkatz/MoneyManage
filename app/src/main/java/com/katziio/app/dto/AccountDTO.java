package com.katziio.app.dto;

import com.katziio.app.model.User;
import com.katziio.app.util.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private Long id;
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
}
