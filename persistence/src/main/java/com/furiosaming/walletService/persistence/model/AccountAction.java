package com.furiosaming.walletService.persistence.model;

import com.furiosaming.walletService.persistence.model.enums.ActionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountAction {
    private Long id;
    private ActionType actionType;
    private Date date;
}
