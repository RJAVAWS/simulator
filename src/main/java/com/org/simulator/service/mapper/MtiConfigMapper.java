package com.org.simulator.service.mapper;


import com.org.simulator.domain.*;
import com.org.simulator.service.dto.MtiConfigDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MtiConfig} and its DTO {@link MtiConfigDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MtiConfigMapper extends EntityMapper<MtiConfigDTO, MtiConfig> {



    default MtiConfig fromId(Long id) {
        if (id == null) {
            return null;
        }
        MtiConfig mtiConfig = new MtiConfig();
        mtiConfig.setId(id);
        return mtiConfig;
    }
}
