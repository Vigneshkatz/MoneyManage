package com.katziio.app.service.account;

import com.katziio.app.dto.request.ExpenseDTO;
import com.katziio.app.dto.response.ResponseDTO;
import com.katziio.app.exception.ErrorOnSavingInTable;

public interface ExpenseService {
    ResponseDTO createExpense(Long userId, Long accountId, ExpenseDTO expenseRequest) throws ErrorOnSavingInTable;

    ResponseDTO updateExpense(Long userId, Long accountID, ExpenseDTO expenseRequest) throws ErrorOnSavingInTable;

    ResponseDTO deleteExpense(Long userId, Long accountId, ExpenseDTO expenseRequest);

}
