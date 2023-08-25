package com.practise.auth.services;

import com.practise.auth.dto.ExpenseDTO;
import com.practise.auth.entity.Expense;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ExpenseService
{
    ExpenseDTO createExpense(ExpenseDTO expenseDTO);
    List<Expense> findAllExpenses();
    List<Expense> getExpensesByUserId(Long id);
    ExpenseDTO updateExpense(Long id,ExpenseDTO expenseDTO);
    void deleteExpense(Long id);

    Optional<Expense> getExpenseById(Long id);
}
