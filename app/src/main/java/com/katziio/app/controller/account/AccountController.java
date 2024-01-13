package com.katziio.app.controller.account;

import com.katziio.app.dto.request.RequestDTO;
import com.katziio.app.dto.response.ResponseDTO;
import com.katziio.app.service.account.AccountService;
import com.katziio.app.service.account.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(){
        this.accountService = new AccountServiceImpl();
    }

    @PostMapping("/create")
    public ResponseDTO createAccount(@RequestBody RequestDTO request)
    {
        try {
            return this.accountService.createAccount(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
