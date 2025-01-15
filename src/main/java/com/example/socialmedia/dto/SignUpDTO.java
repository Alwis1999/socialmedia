package com.example.socialmedia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignUpDTO {
    private String username;
    private String email;
    private String password;
}
