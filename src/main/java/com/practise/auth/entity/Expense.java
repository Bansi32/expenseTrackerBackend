package com.practise.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Data
@Builder
@Table(name="expenses")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="expense_id",nullable = false)
    private Long expenseId;

    @NonNull
    @Basic
    @Column(name="title",nullable = false,length=100)
    private String title;
    @Basic
    @Column(name="location")
    private String location;

    @Basic
    @NonNull
    @Column(name="expense_date",nullable = false)
    private Date expenseDate;
    @Basic
    @NonNull
    @Column(name="expense_description",nullable = false,length = 500)
    private String description;
    @NonNull
    @Basic
    @Column(name="amount",nullable = false,length=15)
    private Double amount;

    @NonNull
    @Basic
    @Column(name="category",nullable = false)
    private String category;
    @ToString.Exclude
    @ManyToOne(fetch =FetchType.EAGER)
    @JoinColumn(name="user_id",referencedColumnName = "user_id")
    @JsonIgnore
    @JsonProperty("user_id")
    private User user;
}
