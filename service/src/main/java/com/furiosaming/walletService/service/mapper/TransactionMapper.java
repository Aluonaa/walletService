package com.furiosaming.walletService.service.mapper;

import com.furiosaming.walletService.persistence.model.Transaction;
import com.furiosaming.walletService.service.dto.TransactionDto;
import org.mapstruct.Mapper;

@Mapper
public interface TransactionMapper {
    TransactionDto transactionalToDto(Transaction transaction);
    Transaction dtoToTransaction(TransactionDto transactionDto);
}
