package com.practise.auth.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String profilePic;
    private Double totalIncome;

}
