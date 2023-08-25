package com.practise.auth.repo;

import com.practise.auth.entity.Category;
import com.practise.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserRepo extends JpaRepository<User,Long> {
    User findFirstByEmail(String email);
    Optional<User> findById(Long id);

}
