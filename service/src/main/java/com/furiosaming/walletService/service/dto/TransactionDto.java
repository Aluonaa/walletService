package com.furiosaming.walletService.service.dto;

import com.furiosaming.walletService.persistence.model.BankAccount;
import com.furiosaming.walletService.persistence.model.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    /** Уникальный идентификатор транзакции */
    private Long id;
    /** Тип транзакции (снятие денежных средств/пополнение счета) */
    private TransactionType transactionType;
    /** Количество денежных средств, которые оказались на счету или были выведены в процессе транзакции */
    private Long cashValue;
    /** Дата и время проведения транзакции */
    private LocalDateTime date;
    /** Аккаунт, на котором произошла транзакция */
    private BankAccount bankAccount;
    /** Уникальный код транзакции */
    private Long transactionCode;

}
