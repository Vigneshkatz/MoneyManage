package com.katziio.app.service.account;

import com.katziio.app.dto.response.Response;

public interface ExpenseService {
    Response createExpense(Long userId, Long accountId, ExpenseService expenseService);

    Response updateExpense(Long userId,Long accountID,ExpenseService expenseService);

    Response deleteExpense(Long userId, Long accountId, ExpenseService expenseService);
}
