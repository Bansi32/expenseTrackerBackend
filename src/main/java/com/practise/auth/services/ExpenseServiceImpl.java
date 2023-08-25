package com.practise.auth.services;

import com.practise.auth.dto.ExpenseDTO;
import com.practise.auth.entity.Expense;
import com.practise.auth.entity.User;
import com.practise.auth.mapper.ExpenseMapper;
import com.practise.auth.mapper.UserMapper;
import com.practise.auth.repo.ExpenseRepo;
import com.practise.auth.repo.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ExpenseServiceImpl implements ExpenseService{
    @Autowired
    private ExpenseMapper expenseMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ExpenseRepo expenseRepo;
    @Autowired
    private UserRepo userRepo;
    @Override
    public ExpenseDTO createExpense(ExpenseDTO expenseDTO) {
        Expense expense=expenseMapper.fromExpenseDTO(expenseDTO);
        User user=userRepo.findById(expenseDTO.getUser().getId()).orElseThrow(()->new EntityNotFoundException("User with ID "+expenseDTO.getUser().getId()+" Not Found"));
        expense.setUser(user);

        Expense savedExpense=expenseRepo.save(expense);

        List<Expense> exp=new ArrayList<>();
        exp.add(expense);
        user.setExpenses(exp);
        return expenseMapper.fromExpenseToExpenseDTO(savedExpense);
    }
    @Override
    public List<Expense> findAllExpenses()
    {
        return expenseRepo.findAll();
    }

    @Override
    public List<Expense> getExpensesByUserId(Long id)
    {
        return expenseRepo.findExpensesByUserId(id);
    }

    @Override
    public ExpenseDTO updateExpense(Long id, ExpenseDTO expenseDTO) {
        Expense getExpense=expenseRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Expense with ID "+expenseDTO.getExpenseId()+" Not Found"));
        getExpense.setExpenseDate(expenseDTO.getExpenseDate());
        getExpense.setAmount(expenseDTO.getAmount());
        getExpense.setCategory(expenseDTO.getCategory());
        getExpense.setTitle(expenseDTO.getTitle());
        getExpense.setLocation(expenseDTO.getLocation());
        getExpense.setDescription(expenseDTO.getDescription());
        Expense updatedExpense=expenseRepo.save(getExpense);
        return expenseMapper.fromExpenseToExpenseDTO(updatedExpense);
    }

    @Override
    public void deleteExpense(Long id) {
        expenseRepo.deleteById(id);
    }

    @Override
    public Optional<Expense> getExpenseById(Long id) {
        return expenseRepo.findById(id);
    }
}
