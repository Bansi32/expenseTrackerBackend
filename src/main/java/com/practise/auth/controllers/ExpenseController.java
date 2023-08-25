package com.practise.auth.controllers;

import com.practise.auth.dto.ExpenseDTO;
import com.practise.auth.entity.Expense;
import com.practise.auth.mapper.ExpenseMapper;
import com.practise.auth.services.ExpenseService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Slf4j
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private ExpenseMapper expenseMapper;
    @GetMapping("/expenses")
    ResponseEntity<Collection<Expense>> expenses()
    {
        try
        {
            Collection<Expense> allExpenses=expenseService.findAllExpenses();
            return new ResponseEntity<>(allExpenses, HttpStatus.ACCEPTED);
        }
        catch(Exception e)
        {
            throw new Error("Something went wrong!");
        }
    }
    @GetMapping("/expenses/{id}")
    ResponseEntity<List<Expense>> getExpense(@PathVariable Long id)
    {
        try{
            List<Expense> expenseList= expenseService.getExpensesByUserId(id);
            return new ResponseEntity<>(expenseList,HttpStatus.ACCEPTED);
        }
        catch(Exception e)
        {
            throw new Error("Something went wrong!");
        }
    }
    @GetMapping("/expense/{id}")
    ResponseEntity<Optional<Expense>> getExpenseById(@PathVariable Long id)
    {
        try{
            Optional<Expense> expense= expenseService.getExpenseById(id);
            return new ResponseEntity<>(expense,HttpStatus.ACCEPTED);
        }
        catch(Exception e)
        {
            throw new Error("Something went wrong!");
        }
    }

    @PostMapping("/expense")
    ResponseEntity<ExpenseDTO> createExpense(@Valid @RequestBody ExpenseDTO expenseDTO) throws URISyntaxException {
        try{
            ExpenseDTO result=expenseService.createExpense(expenseDTO);
            return ResponseEntity.created(new URI("/api/expense" + result.getExpenseId())).body(result);
        }
        catch(Exception e)
        {
            log.info(String.valueOf(e.getMessage()));
            throw new Error("Something went wrong!");
        }
    }
    @PutMapping("/expense/{id}")
    ResponseEntity<ExpenseDTO> updateExpense(@PathVariable Long id, @Valid @RequestBody ExpenseDTO expenseDTO) throws URISyntaxException {
        try{
            ExpenseDTO result=expenseService.updateExpense(id,expenseDTO);
            return ResponseEntity.created(new URI("/api/expense" + result.getExpenseId())).body(result);
        }
        catch(Exception e)
        {
            log.info(String.valueOf(e.getMessage()));
            throw new Error("Something went wrong!");
        }
    }
    @DeleteMapping("expense/{id}")
    public void deleteExpense(@PathVariable Long id)
    {
        try{
            expenseService.deleteExpense(id);
        }
        catch(Exception e)
        {
            throw new Error();
        }
    }
}
