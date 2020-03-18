package com.org.simulator.service.mapper;


import com.org.simulator.domain.*;
import com.org.simulator.service.dto.TestCaseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TestCase} and its DTO {@link TestCaseDTO}.
 */
@Mapper(componentModel = "spring", uses = {CardMapper.class})
public interface TestCaseMapper extends EntityMapper<TestCaseDTO, TestCase> {

    @Mapping(source = "card.id", target = "cardId")
    TestCaseDTO toDto(TestCase testCase);

    @Mapping(source = "cardId", target = "card")
    TestCase toEntity(TestCaseDTO testCaseDTO);

    default TestCase fromId(Long id) {
        if (id == null) {
            return null;
        }
        TestCase testCase = new TestCase();
        testCase.setId(id);
        return testCase;
    }
}
