package com.katziio.app.service.account;

import com.katziio.app.dto.request.ExpenseDTO;
import com.katziio.app.dto.response.ResponseDTO;

public interface ExpenseService {
    ResponseDTO createExpense(Long userId, Long accountId, ExpenseDTO expenseRequest);

    ResponseDTO updateExpense(Long userId, Long accountID, ExpenseDTO expenseRequest);

    ResponseDTO deleteExpense(Long userId, Long accountId, ExpenseDTO expenseRequest);

}
