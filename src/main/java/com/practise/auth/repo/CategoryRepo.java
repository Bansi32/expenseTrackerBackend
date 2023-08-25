package com.practise.auth.repo;

import com.practise.auth.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {
    Optional<Category> findById(Long id);
    Category findByName(String name);
    List<Category> findCategoriesByUserId(@Param("id") Long userId);
}
