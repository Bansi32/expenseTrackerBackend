package com.practise.auth.mapper;

import com.practise.auth.dto.ExpenseDTO;
import com.practise.auth.entity.Expense;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class ExpenseMapper {
    public ExpenseDTO fromExpenseToExpenseDTO(Expense expense)
    {
        ExpenseDTO expenseDTO=new ExpenseDTO();
        BeanUtils.copyProperties(expense,expenseDTO);
        return expenseDTO;
    }
    public Expense fromExpenseDTO(ExpenseDTO expenseDTO)
    {
        Expense expense=new Expense();
        BeanUtils.copyProperties(expenseDTO,expense);
        return expense;
    }

}
