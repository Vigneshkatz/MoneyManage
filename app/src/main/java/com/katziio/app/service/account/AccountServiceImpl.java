package com.katziio.app.service.account;

import com.katziio.app.dto.request.AccountRequestDTO;
import com.katziio.app.dto.request.Request;
import com.katziio.app.dto.response.Response;
import com.katziio.app.model.Account;
import com.katziio.app.repository.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.*;

public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountRepository accountRepository;

    public Response create(Request request) {
        Response response = new Response();

      if(request!=null)
      {
          Account account = new Account();
          List<String> emptyField = isAnyFieldNull(request.getAccountRequestDTO());
          if(emptyField.isEmpty())
          {
              response.setEmptyField(emptyField);
          }else {
              
          }

      }else {
          throw new RuntimeException();
      }
      return null;
    }

    public List<String> isAnyFieldNull(AccountRequestDTO accountRequestDTO) {
        List<String> emptyField = new ArrayList<>();

        if (accountRequestDTO.getUser() == null) {
            emptyField.add("user");
        }
        if (accountRequestDTO.getAccountNumber() == null) {
            emptyField.add("account number");
        }
        if (accountRequestDTO.getCurrentBalance() == null) {
            emptyField.add("current balance");
        }
        if (accountRequestDTO.getCardNumber() == null) {
            emptyField.add("cart number");
        }
        if (accountRequestDTO.getCvv() == null) {
            emptyField.add("cvv");
        }
        if (accountRequestDTO.getBankName() == null) {
            emptyField.add("bank name");
        }
        if (accountRequestDTO.getIfsc() == null) {
            emptyField.add("ifsc");
        }
        if (accountRequestDTO.getPhoneLinked() == null) {
            emptyField.add("phone linked");
        }
        if (accountRequestDTO.getIsNetBanking() == null) {
            emptyField.add("net banking");
        }
        if (accountRequestDTO.getIsActive() == null) {
            emptyField.add("active");
        }
        if (accountRequestDTO.getCreatedAt() == null) {
            emptyField.add("created at");
        }
        if (accountRequestDTO.getAccountType() == null) {
            emptyField.add("account type");
        }

      return emptyField;
    }
}
