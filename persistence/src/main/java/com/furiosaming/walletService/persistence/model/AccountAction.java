package com.furiosaming.walletService.persistence.model;

import com.furiosaming.walletService.persistence.model.enums.ActionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Класс, описывающий действия, связанные с аккаунтом
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountAction {
    /** Тип действия в аккаунте */
    private ActionType actionType;
    /** Дата действия */
    private Date date;
}
