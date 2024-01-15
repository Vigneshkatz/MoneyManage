package com.katziio.app.service.account;

import com.katziio.app.dto.request.ExpenseRequestDTO;
import com.katziio.app.dto.response.ResponseDTO;
import com.katziio.app.exception.ErrorOnSavingInTable;
import org.springframework.stereotype.Service;

@Service
public interface ExpenseService {
    ResponseDTO createExpense(Long userId, Long accountId, ExpenseRequestDTO expenseRequest) throws ErrorOnSavingInTable;

    ResponseDTO updateExpense(Long userId, Long accountID, ExpenseRequestDTO expenseRequest) throws ErrorOnSavingInTable;

    ResponseDTO deleteExpense(Long userId, Long accountId, ExpenseRequestDTO expenseRequest);

    ResponseDTO getExpenseById(Long userId, Long accountId, Long expenseId);
}
