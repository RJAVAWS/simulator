package com.org.simulator.service.mapper;


import com.org.simulator.domain.*;
import com.org.simulator.service.dto.BankDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Bank} and its DTO {@link BankDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BankMapper extends EntityMapper<BankDTO, Bank> {


    @Mapping(target = "cards", ignore = true)
    @Mapping(target = "removeCard", ignore = true)
    @Mapping(target = "keyConfigs", ignore = true)
    @Mapping(target = "removeKeyConfig", ignore = true)
    @Mapping(target = "transactions", ignore = true)
    @Mapping(target = "removeTransaction", ignore = true)
    Bank toEntity(BankDTO bankDTO);

    default Bank fromId(Long id) {
        if (id == null) {
            return null;
        }
        Bank bank = new Bank();
        bank.setId(id);
        return bank;
    }
}
