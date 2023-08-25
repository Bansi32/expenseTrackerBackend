package com.practise.auth.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private Long categoryId;
    private String name;
    private UserDTO user;
}
