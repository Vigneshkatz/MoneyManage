package com.katziio.app.service.account;

import com.katziio.app.dto.error.ErrorDTO;
import com.katziio.app.dto.request.AccountRequestDTO;
import com.katziio.app.dto.request.RequestDTO;
import com.katziio.app.dto.response.ResponseDTO;
import com.katziio.app.exception.*;
import com.katziio.app.model.Account;
import com.katziio.app.model.Expense;
import com.katziio.app.repository.account.AccountRepository;
import com.katziio.app.util.CustomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public ResponseDTO createAccount(RequestDTO request) throws Exception {
        ResponseDTO response = new ResponseDTO();
        ErrorDTO errorDTO = new ErrorDTO();
        if (request != null) {
            if (request.getIsAccountDto()) {
                List<String> isError = isValidAccountDTO(request.getAccountDTO());
                if (isError.isEmpty()) {
                    try {
                        Account account =this.accountRepository.save( new Account(request.getAccountDTO()));
                        response.setErrorDTO(errorDTO);
                        response.setContent(account);
                        return response;
                    } catch (Exception e) {
                        throw new ErrorOnSavingInTable("on Account Repo" + e.getMessage());
                    }
                } else {
                    errorDTO.setIsEmptyField(true);
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

    @Override
    public ResponseDTO updateAccount(RequestDTO request) {
        ResponseDTO response = new ResponseDTO();
        ErrorDTO errorDTO = new ErrorDTO();
        if(request!=null)
        {
            if (request.getIsAccountDto()){
                List<String> isError = isValidAccountDTO(request.getAccountDTO());
                if (isError.isEmpty()) {
                    try {
                        Long accountId = request.getAccountDTO().getId();
                        if(accountId!=null)
                        {
                            Optional<Account> optionalAccount = this.accountRepository.findById(accountId);
                            if(optionalAccount.isPresent()) {
                                try {
                                    Account account = new Account(request.getAccountDTO());
                                    this.accountRepository.save(account);
                                }catch (Exception e)
                                {
                                    throw new ErrorOnSavingInTable("on Account Update" + e.getMessage());
                                }

                            }else {
                                throw new NullPointerException("optional is null");
                            }
                        }else {
                            throw new DataAlreadyExists("Account already exists");
                        }
                    } catch (Exception e) {
                        try {
                            throw new ErrorOnSavingInTable("on Account Repo" + e.getMessage());
                        } catch (ErrorOnSavingInTable ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                } else {
                    errorDTO.setEmptyField(isError);
                }
            } else {
                try {
                    throw new InvalidDTOException("Request dto does not have Account DTO");
                } catch (InvalidDTOException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            throw new NullPointerException("Request is null");
        }
        return null;
    }

    @Override
    public ResponseDTO deleteAccount(Long id) {
        if(id<=0)
        {
            try {
                throw new InvalidIDException("enter a valid id");
            } catch (InvalidIDException e) {
                throw new RuntimeException(e);
            }
        }
        Optional<Account> optionalAccount = this.accountRepository.findById(id);
        if(optionalAccount.isEmpty())
        {
            try {
                throw new DataNotFoundException("No data fount for this id");
            } catch (DataNotFoundException e) {
                throw new RuntimeException(e);
            }
        }else {
            try {
                this.accountRepository.delete(optionalAccount.get());
            }catch (Exception e)
            {
                throw new RuntimeException("Error on deleting account"+e.getMessage());
            }
        }
        return null;

    }

    @Override
    public Boolean isValidAccount(Long accountId) {
        if(!CustomUtil.isValidObject(accountId)){
            return false;
        }
        return this.accountRepository.existsById(accountId);
    }

    @Override
    public Account getAccountById(Long accountId) {
        if(!CustomUtil.isValidObject(accountId)){
            return null;
        }
        Optional<Account> optionalAccount =  this.accountRepository.findById(accountId);
        return optionalAccount.orElse(null);
    }

    @Override
    public ResponseDTO getAccountSummary(Long accountId) {
        ResponseDTO responseDTO = new ResponseDTO();
        ErrorDTO errorDTO = new ErrorDTO();
        Account account = this.getAccountById(accountId);
        if(account!=null) {
//            improvement pending
            responseDTO.setContent(account);
        }else {
            errorDTO.setIsError(true);
            errorDTO.setErrorCode(1);
            errorDTO.setErrorMessage("account not found");
        }
        responseDTO.setErrorDTO(errorDTO);
        return responseDTO;
    }

    public List<String> isValidAccountDTO(AccountRequestDTO accountDTO) {
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