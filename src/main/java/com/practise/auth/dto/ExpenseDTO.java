package com.practise.auth.dto;

import lombok.*;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDTO {
    private Long expenseId;
    private String title;
    private String location;
    private Date expenseDate;
    private String description;
    private Double amount;
    private String category;
    private UserDTO user;
}
