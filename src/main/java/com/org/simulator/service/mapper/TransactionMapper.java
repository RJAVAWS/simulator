package com.org.simulator.service.mapper;


import com.org.simulator.domain.*;
import com.org.simulator.service.dto.TransactionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Transaction} and its DTO {@link TransactionDTO}.
 */
@Mapper(componentModel = "spring", uses = {BankMapper.class})
public interface TransactionMapper extends EntityMapper<TransactionDTO, Transaction> {

    @Mapping(source = "bank.id", target = "bankId")
    TransactionDTO toDto(Transaction transaction);

    @Mapping(source = "bankId", target = "bank")
    Transaction toEntity(TransactionDTO transactionDTO);

    default Transaction fromId(Long id) {
        if (id == null) {
            return null;
        }
        Transaction transaction = new Transaction();
        transaction.setId(id);
        return transaction;
    }
}
