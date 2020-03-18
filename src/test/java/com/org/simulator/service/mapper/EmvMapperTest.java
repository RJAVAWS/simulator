package com.org.simulator.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EmvMapperTest {

    private EmvMapper emvMapper;

    @BeforeEach
    public void setUp() {
        emvMapper = new EmvMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(emvMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(emvMapper.fromId(null)).isNull();
    }
}
