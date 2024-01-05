package com.katziio.app.controller.account;

import com.katziio.app.dto.request.RequestDTO;
import com.katziio.app.dto.response.ResponseDTO;
import com.katziio.app.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    public ResponseDTO createAccount(@RequestBody RequestDTO request) {
        try {
            return this.accountService.createAccount(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/update")
    public ResponseDTO updateAccount(@RequestBody RequestDTO request) {
        try {
            return this.accountService.updateAccount(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PutMapping("/delete/{id}")
    public ResponseDTO deleteAccount(@PathVariable Long id) {
        try {
            return this.accountService.deleteAccount(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
