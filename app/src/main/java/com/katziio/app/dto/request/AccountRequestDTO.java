package com.katziio.app.dto.request;

import com.katziio.app.model.User;
import com.katziio.app.util.enums.AccountType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AccountRequestDTO {
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
}
