package com.practise.auth.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Builder
@Table(name="users")
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;
    @NonNull
    @Basic
    @Column(nullable = false,name="name")
    private String name;
    @NonNull
    @Basic
    @Column(unique = true,nullable = false,name="email")
    private String email;
    @NonNull
    @Basic
    @Column(nullable = false,name="password")
    private String password;

    @Basic
    @Column(name="phone")
    private String phone;
    @Basic
    @Column(name="profile_pic")
    private String profilePic;
    @Basic
    @Column(name="income")
    private Double totalIncome;
    @ToString.Exclude
    @NonNull
    @OneToMany(mappedBy = "user",fetch=FetchType.EAGER)
    private List<Expense> expenses=new ArrayList<>();
    @OneToMany(mappedBy="user",fetch=FetchType.EAGER)
    private Set<Category> categories=new HashSet<>();

}
