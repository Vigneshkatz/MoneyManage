package com.katziio.app.controller.account;

import com.katziio.app.dto.request.ExpenseDTO;
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
    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(){
        this.expenseService = new ExpenseServiceImpl();
    }
    @PostMapping("/{user-id}/{account-id}/create")
    public ResponseDTO createExpense(@PathVariable Long userId, @PathVariable Long accountId,@RequestBody ExpenseDTO expenseDto)
    {
        try {
            return this.expenseService.createExpense(userId,accountId,expenseDto);
        } catch (ErrorOnSavingInTable e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{user-id}/{account-id}/update")
    public ResponseDTO updateExpense(@PathVariable Long userId, @PathVariable Long accountId, @RequestBody ExpenseDTO expenseDto)
    {
        try {
            return this.expenseService.updateExpense(userId,accountId,expenseDto);
        } catch (ErrorOnSavingInTable e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{user-id}/{account-id}")
    public ResponseDTO deleteExpense(@PathVariable Long userId, @PathVariable Long accountId,@RequestBody ExpenseDTO expenseDto)
    {
        return this.expenseService.deleteExpense(userId,accountId,expenseDto);
    }
}
