package com.katziio.app.controller.account;

import com.katziio.app.dto.request.RequestDTO;
import com.katziio.app.dto.response.ResponseDTO;
import com.katziio.app.service.account.AccountService;
import com.katziio.app.service.account.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/account")
public class AccountController {
    @Autowired
    private  AccountService accountService;

    @PostMapping("/create")
    public ResponseDTO createAccount(@RequestBody RequestDTO request)
    {
        try {
            return this.accountService.createAccount(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/getAccountSummary/{accountId}")
    public ResponseDTO getAccountSummary(@PathVariable Long accountId)
    {
        return this.accountService.getAccountSummary(accountId);
    }
}
