package com.furiosaming.walletService.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {
    private String uuid;
    private String passport;
    private String login;
    private String password;
}
