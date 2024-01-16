package com.katziio.app.dto.request;

import com.katziio.app.model.User;
import com.katziio.app.util.enums.AccountType;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestDTO {
    @Nullable
    private Long id;
    @Nullable
    private User user;
    private Long userId;
    private Long accountNumber;
    private Long currentBalance;
    @Nullable
    private String cardNumber;
    private Integer cvv;
    private String bankName;
    private String ifsc;
    private String phoneLinked;
    @Nullable
    private Boolean isNetBanking;
    private Boolean isActive;
    @Nullable
    private Date createdAt;
    private String accountType;
    @Nullable
    private Long accountMonthlySpendLimit;
    @Nullable
    private Long monthlySpent;
}
