package com.org.simulator.service.mapper;


import com.org.simulator.domain.*;
import com.org.simulator.service.dto.CardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Card} and its DTO {@link CardDTO}.
 */
@Mapper(componentModel = "spring", uses = {EmvMapper.class, TestCaseMapper.class, BankMapper.class})
public interface CardMapper extends EntityMapper<CardDTO, Card> {

    @Mapping(source = "emv.id", target = "emvId")
    @Mapping(source = "bank.id", target = "bankId")
    CardDTO toDto(Card card);

    @Mapping(source = "emvId", target = "emv")
    @Mapping(target = "removeTestCase", ignore = true)
    @Mapping(source = "bankId", target = "bank")
    @Mapping(source = "useCase", target = "useCase")
    Card toEntity(CardDTO cardDTO);

    default Card fromId(Long id) {
        if (id == null) {
            return null;
        }
        Card card = new Card();
        card.setId(id);
        return card;
    }
}
