package com.org.simulator.service.mapper;


import com.org.simulator.domain.*;
import com.org.simulator.service.dto.EmvDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Emv} and its DTO {@link EmvDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EmvMapper extends EntityMapper<EmvDTO, Emv> {



    default Emv fromId(Long id) {
        if (id == null) {
            return null;
        }
        Emv emv = new Emv();
        emv.setId(id);
        return emv;
    }
}
