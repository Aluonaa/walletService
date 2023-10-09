package com.furiosaming.walletService.service.errors;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProgramError {
    CommonErrorTypes type;
    String message;
    String code;
}
