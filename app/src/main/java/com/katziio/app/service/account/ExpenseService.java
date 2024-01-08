package com.katziio.app.service.account;

import com.katziio.app.dto.response.ResponseDTO;

public interface ExpenseService {
    ResponseDTO createExpense(Long userId, Long accountId, ExpenseService expenseService);

    ResponseDTO updateExpense(Long userId,Long accountID,ExpenseService expenseService);

    ResponseDTO deleteExpense(Long userId, Long accountId, ExpenseService expenseService);
}
