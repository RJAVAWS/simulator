package com.org.simulator.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestCaseMapperTest {

    private TestCaseMapper testCaseMapper;

    @BeforeEach
    public void setUp() {
        testCaseMapper = new TestCaseMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(testCaseMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(testCaseMapper.fromId(null)).isNull();
    }
}
