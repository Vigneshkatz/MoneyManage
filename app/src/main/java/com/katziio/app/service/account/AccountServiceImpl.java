package com.katziio.app.service.account;

import com.katziio.app.dto.AccountDTO;
import com.katziio.app.dto.ErrorDTO;
import com.katziio.app.dto.Request;
import com.katziio.app.dto.Response;
import com.katziio.app.exception.ErrorOnSavingInTable;
import com.katziio.app.exception.InvalidDTOException;
import com.katziio.app.model.Account;
import com.katziio.app.repository.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Response create(Request request) throws Exception {
        ErrorDTO errorDTO = new ErrorDTO();
        if (request != null) {
            if (request.getIsAccountDto()) {
                List<String> isError = isValidAccountDTO(request.getAccountDTO());
                if (isError.isEmpty()) {
                    try {
                        Account account = new Account(request.getAccountDTO());
                        this.accountRepository.save(account);
                    } catch (Exception e) {
                        throw new ErrorOnSavingInTable("on Account Repo" + e.getMessage());
                    }
                } else {
                    errorDTO.setEmptyField(isError);
                }

            } else {
                throw new InvalidDTOException("Request dto does not have Account DTO");
            }

        } else {
            throw new NullPointerException("Request is null");
        }
        return null;
    }

    public List<String> isValidAccountDTO(AccountDTO accountDTO) {
        List<String> errors = new ArrayList<>();

        if (accountDTO.getUser() == null) {
            errors.add("User cannot be null");
        }
        if (accountDTO.getAccountNumber() == null) {
            errors.add("Account number cannot be null");
        }
        if (accountDTO.getCurrentBalance() == null) {
            errors.add("Current balance cannot be null");
        }
        if (accountDTO.getCardNumber() == null) {
            errors.add("Card number cannot be null");
        }
        if (accountDTO.getCvv() == null) {
            errors.add("CVV cannot be null");
        }
        if (accountDTO.getBankName() == null) {
            errors.add("Bank name cannot be null");
        }
        if (accountDTO.getIfsc() == null) {
            errors.add("IFSC code cannot be null");
        }
        if (accountDTO.getPhoneLinked() == null) {
            errors.add("Phone linked cannot be null");
        }
        if (accountDTO.getIsNetBanking() == null) {
            errors.add("Net banking status cannot be null");
        }
        if (accountDTO.getIsActive() == null) {
            errors.add("Active status cannot be null");
        }
        if (accountDTO.getCreatedAt() == null) {
            errors.add("Creation date cannot be null");
        }
        if (accountDTO.getAccountType() == null) {
            errors.add("Account type cannot be null");
        }
        if (accountDTO.getAccountMonthlySpendLimit() == null) {
            errors.add("Monthly spend limit cannot be null");
        }
        if (accountDTO.getMonthlySpent() == null) {
            errors.add("Monthly spent amount cannot be null");
        }

        return errors;
    }

}
