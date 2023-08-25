package com.practise.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@RequiredArgsConstructor
@Table(name="Categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id", nullable = false)
    private Long categoryId;
    @NonNull
    @Basic
    @Column(unique = true)
    private String name;
    @ToString.Exclude
    @ManyToOne(fetch =FetchType.EAGER)
    @JoinColumn(name="user_id",referencedColumnName = "user_id")
    @JsonIgnore
    @JsonProperty("user_id")
    private User user;
}








