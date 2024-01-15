package com.katziio.app.controller.account;

import com.katziio.app.dto.request.ExpenseRequestDTO;
import com.katziio.app.dto.response.ResponseDTO;
import com.katziio.app.exception.ErrorOnSavingInTable;
import com.katziio.app.service.account.AccountServiceImpl;
import com.katziio.app.service.account.ExpenseService;
import com.katziio.app.service.account.ExpenseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/expense")
public class ExpenseController {
    @Autowired
    private  ExpenseService expenseService;

    @PostMapping("/{user-id}/{account-id}/create")
    public ResponseDTO createExpense(@PathVariable Long userId, @PathVariable Long accountId,@RequestBody ExpenseRequestDTO expenseDto)
    {
        try {
            return this.expenseService.createExpense(userId,accountId,expenseDto);
        } catch (ErrorOnSavingInTable e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{user-id}/{account-id}/update")
    public ResponseDTO updateExpense(@PathVariable Long userId, @PathVariable Long accountId, @RequestBody ExpenseRequestDTO expenseDto)
    {
        try {
            return this.expenseService.updateExpense(userId,accountId,expenseDto);
        } catch (ErrorOnSavingInTable e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{user-id}/{account-id}")
    public ResponseDTO deleteExpense(@PathVariable Long userId, @PathVariable Long accountId,@RequestBody ExpenseRequestDTO expenseDto)
    {
        return this.expenseService.deleteExpense(userId,accountId,expenseDto);
    }
}
