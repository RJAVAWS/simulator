package com.org.simulator.service.mapper;


import com.org.simulator.domain.*;
import com.org.simulator.service.dto.EmvDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Emv} and its DTO {@link EmvDTO}.
 */
@Mapper(componentModel = "spring", uses = {BankMapper.class})
public interface EmvMapper extends EntityMapper<EmvDTO, Emv> {

    @Mapping(source = "bank.id", target = "bankId")
    EmvDTO toDto(Emv emv);

    @Mapping(source = "bankId", target = "bank")
    Emv toEntity(EmvDTO emvDTO);

    default Emv fromId(Long id) {
        if (id == null) {
            return null;
        }
        Emv emv = new Emv();
        emv.setId(id);
        return emv;
    }
}
