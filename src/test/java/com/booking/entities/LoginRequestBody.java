package com.booking.entities;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestBody {
    private String username;
    private String password;
}
