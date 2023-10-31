package com.furiosaming.walletService.persistence.model;

import com.furiosaming.walletService.persistence.model.enums.ActionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Класс, описывающий действия, связанные с аккаунтом
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountAction {
    /** Уникальный идентификатор действия в аккаунте */
    private Long id;
    /** Тип действия в аккаунте */
    private ActionType actionType;
    /** Дата действия */
    private LocalDateTime date;
    /** Аккаунт, с которого выполнено действие */
    private Person person;
}
