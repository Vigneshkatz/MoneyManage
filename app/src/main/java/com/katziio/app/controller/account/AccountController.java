package com.katziio.app.controller.account;

import com.katziio.app.dto.request.Request;
import com.katziio.app.dto.response.Response;
import com.katziio.app.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    public Response createAccount(@RequestBody Request request)
    {
        try {
            return this.accountService.create(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
