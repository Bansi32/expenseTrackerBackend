package com.practise.auth.repo;

import com.practise.auth.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense,Long> {
    Optional<Expense> findById(Long id);
    List<Expense> findExpensesByUserId(@Param("id") Long userId);
}
