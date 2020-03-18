package com.org.simulator.service.mapper;


import com.org.simulator.domain.*;
import com.org.simulator.service.dto.KeyConfigDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link KeyConfig} and its DTO {@link KeyConfigDTO}.
 */
@Mapper(componentModel = "spring", uses = {BankMapper.class})
public interface KeyConfigMapper extends EntityMapper<KeyConfigDTO, KeyConfig> {

    @Mapping(source = "bank.id", target = "bankId")
    KeyConfigDTO toDto(KeyConfig keyConfig);

    @Mapping(source = "bankId", target = "bank")
    KeyConfig toEntity(KeyConfigDTO keyConfigDTO);

    default KeyConfig fromId(Long id) {
        if (id == null) {
            return null;
        }
        KeyConfig keyConfig = new KeyConfig();
        keyConfig.setId(id);
        return keyConfig;
    }
}
