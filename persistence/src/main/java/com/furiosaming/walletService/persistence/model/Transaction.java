package com.furiosaming.walletService.persistence.model;

import com.furiosaming.walletService.persistence.model.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Класс, описывающий транзакцию, проведенную на банковском счету
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    /** Уникальный идентификатор транзакции */
    private Long id;
    /** Тип транзакции (снятие денежных средств/пополнение счета) */
    private TransactionType transactionType;
    /** Количество денежных средств, которые оказались на счету или были выведены в процессе транзакции */
    private double cashValue;
    /** Дата и время проведения транзакции */
    private Date date;

    /**
     * Метод для получения строкового представления объекта класса.
     * Переопределен для более эффективного использования метода получения всех транзакций по счету
     * @return Строка объекта со всеми полями класса
     */
    @Override
    public String toString() {
        return "Транзакция {" +
                "Идентификатор операции: " + id +
                ", Тип операции: " + transactionType +
                ", Сумма: " + cashValue +
                ", Дата операции: " + date +
                '}';
    }
}
