package com.katziio.app.service.account;

import com.katziio.app.dto.request.RequestDTO;
import com.katziio.app.dto.response.ResponseDTO;
import com.katziio.app.exception.InvalidDTOException;
import com.katziio.app.model.Account;

public interface AccountService {
    ResponseDTO createAccount(RequestDTO request) throws InvalidDTOException, Exception;

    ResponseDTO updateAccount(RequestDTO request);

    ResponseDTO deleteAccount(Long id);

    Boolean isValidAccount(Long accountId);

    Account getAccountById(Long accountId);

}