package com.carrito.carrito.domain.model;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class User {
    private Long userId;
    private String username;
    private String password;
    private String email;
    private LocalDateTime createdAt;


}