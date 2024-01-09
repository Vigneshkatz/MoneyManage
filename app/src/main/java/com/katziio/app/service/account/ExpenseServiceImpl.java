package com.katziio.app.service.account;

import com.katziio.app.dto.request.ExpenseDTO;
import com.katziio.app.dto.response.ResponseDTO;
import com.katziio.app.model.Account;
import com.katziio.app.model.Expense;
import com.katziio.app.model.User;
import com.katziio.app.repository.account.ExpenseRepository;
import com.katziio.app.service.user.UserService;
import com.katziio.app.util.CustomUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ExpenseServiceImpl implements ExpenseService{
    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ExpenseRepository expenseRepository;
    @Override
    public ResponseDTO createExpense(Long userId, Long accountId, ExpenseDTO expenseRequest) {
       if(!CustomUtil.isValidObject(userId) && !CustomUtil.isValidObject(accountId) && !CustomUtil.isValidObject(expenseService)){
           return null;
       }
       if (!userService.isValidUser(userId)){
           return null;
       }
       if(!accountService.isValidAccount(accountId)) {
           return null;
       }
        Expense expense = this.expenseRepository.save(new Expense(expenseRequest));
        Account account = this.accountService.getAccountById(accountId);
        User user = this.userService.getAccountById(userId);
        expense.setAccount(account);
        account.setUser(user);

    }

    @Override
    public ResponseDTO updateExpense(Long userId, Long accountId,  ExpenseDTO expenseRequest) {
        return null;
    }

    @Override
    public ResponseDTO deleteExpense(Long userId, Long accountId, ExpenseDTO expenseRequest) {
        return null;
    }
}
