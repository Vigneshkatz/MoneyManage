package com.katziio.app.service.account;

import com.katziio.app.dto.error.ErrorDTO;
import com.katziio.app.dto.request.ExpenseRequestDTO;
import com.katziio.app.dto.response.ResponseDTO;
import com.katziio.app.exception.ErrorOnSavingInTable;
import com.katziio.app.model.Expense;
import com.katziio.app.repository.account.ExpenseRepository;
import com.katziio.app.service.user.UserService;
import com.katziio.app.util.CustomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Calendar;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService{
    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ExpenseRepository expenseRepository;
    @Override
    public ResponseDTO createExpense(Long userId, Long accountId, ExpenseRequestDTO expenseRequest) throws ErrorOnSavingInTable {
        ResponseDTO responseDTO = new ResponseDTO();
        ErrorDTO errorDTO = new ErrorDTO();

        if (!CustomUtil.isValidObject(userId) || !CustomUtil.isValidObject(accountId) || !CustomUtil.isValidObject(expenseRequest)) {
            throw new IllegalArgumentException("Invalid input parameters");
        }

        try {
            if (!userService.isValidUser(userId)) {
                throw new AccountNotFoundException("User not found for ID: " + userId);
            }

            if (!accountService.isValidAccount(accountId)) {
                throw new AccountNotFoundException("Account not found for ID: " + accountId);
            }
            Expense expense = new Expense(expenseRequest);
            expense.setAccount(accountService.getAccountById(accountId));
            Expense savedExpense = this.expenseRepository.save(expense);
            responseDTO.setContent(savedExpense);
        } catch (AccountNotFoundException e) {
            errorDTO.setErrorCode(404);
            errorDTO.setErrorMessage(e.getMessage());
        } catch (Exception e) {
            errorDTO.setErrorCode(500);
            errorDTO.setErrorMessage("Error occurred while creating expense: " + e.getMessage());
            throw new ErrorOnSavingInTable(errorDTO.getErrorMessage());
        }
        responseDTO.setErrorDTO(errorDTO);
        return responseDTO;
    }

    @Override
    public ResponseDTO updateExpense(Long userId, Long accountId, ExpenseRequestDTO expenseRequest) throws ErrorOnSavingInTable {
        ResponseDTO responseDTO = new ResponseDTO();
        ErrorDTO errorDTO = new ErrorDTO();

        if (!CustomUtil.isValidObject(userId) || !CustomUtil.isValidObject(accountId) || !CustomUtil.isValidObject(expenseRequest)) {
            errorDTO.setErrorCode(400);
            errorDTO.setErrorMessage("Invalid input parameters");
            responseDTO.setErrorDTO(errorDTO);
            return responseDTO;
        }

        try {
            if (!userService.isValidUser(userId)) {
                errorDTO.setErrorCode(404);
                errorDTO.setErrorMessage("User not found for ID: " + userId);
                responseDTO.setErrorDTO(errorDTO);
                return responseDTO;
            }

            if (!accountService.isValidAccount(accountId)) {
                errorDTO.setErrorCode(404);
                errorDTO.setErrorMessage("Account not found for ID: " + accountId);
                responseDTO.setErrorDTO(errorDTO);
                return responseDTO;
            }

            Long expenseId = expenseRequest.getId();
            if (expenseId != null && this.expenseRepository.existsById(expenseId)) {
                Optional<Expense> optionalExpense = this.expenseRepository.findById(expenseId);
                if (optionalExpense.isPresent()) {
                    Expense existingExpense = optionalExpense.get();
                    existingExpense.setTime(Calendar.getInstance().getTime());
                    existingExpense.setCategory(expenseRequest.getCategory());
                    existingExpense.setSpentOn(expenseRequest.getSpentOn());
                    existingExpense.setAmountSpent(expenseRequest.getAmountSpent());
                    existingExpense.setUpdatedBalance(expenseRequest.getUpdatedBalance());
                    existingExpense.setInitialBalance(expenseRequest.getInitialBalance());
                    Expense updatedExpense = this.expenseRepository.save(existingExpense);
                    responseDTO.setContent(updatedExpense);
                }
            } else {
                errorDTO.setErrorCode(404);
                errorDTO.setErrorMessage("Expense not found for ID: " + expenseId);
                responseDTO.setErrorDTO(errorDTO);
                return responseDTO;
            }
        } catch (Exception e) {
            errorDTO.setErrorCode(500);
            errorDTO.setErrorMessage("Error occurred while updating expense: " + e.getMessage());
            responseDTO.setErrorDTO(errorDTO);
            throw new ErrorOnSavingInTable(errorDTO.getErrorMessage());
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO deleteExpense(Long userId, Long accountId, ExpenseRequestDTO expenseRequest) {
        ResponseDTO responseDTO = new ResponseDTO();
        ErrorDTO errorDTO = new ErrorDTO();

        if (!CustomUtil.isValidObject(userId) || !CustomUtil.isValidObject(accountId) || !CustomUtil.isValidObject(expenseRequest)) {
            errorDTO.setErrorCode(400);
            errorDTO.setErrorMessage("Invalid input parameters");
            responseDTO.setErrorDTO(errorDTO);
            return responseDTO;
        }

        try {
            if (!userService.isValidUser(userId)) {
                errorDTO.setErrorCode(404);
                errorDTO.setErrorMessage("User not found for ID: " + userId);
                responseDTO.setErrorDTO(errorDTO);
                return responseDTO;
            }

            if (!accountService.isValidAccount(accountId)) {
                errorDTO.setErrorCode(404);
                errorDTO.setErrorMessage("Account not found for ID: " + accountId);
                responseDTO.setErrorDTO(errorDTO);
                return responseDTO;
            }

            Long expenseId = expenseRequest.getId();
            if (expenseId != null && this.expenseRepository.existsById(expenseId)) {
                try {
                    this.expenseRepository.deleteById(expenseId);
                    return responseDTO;
                } catch (Exception e) {
                    errorDTO.setErrorCode(500);
                    errorDTO.setErrorMessage("Error occurred while deleting expense: " + e.getMessage());
                    responseDTO.setErrorDTO(errorDTO);
                    return responseDTO;
                }
            } else {
                errorDTO.setErrorCode(404);
                errorDTO.setErrorMessage("Expense not found for ID: " + expenseId);
                responseDTO.setErrorDTO(errorDTO);
                return responseDTO;
            }
        } catch (Exception e) {
            errorDTO.setErrorCode(500);
            errorDTO.setErrorMessage("Unexpected error occurred: " + e.getMessage());
            responseDTO.setErrorDTO(errorDTO);
            return responseDTO;
        }
    }

}
