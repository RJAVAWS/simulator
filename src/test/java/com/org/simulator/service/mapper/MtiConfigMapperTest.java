package com.org.simulator.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MtiConfigMapperTest {

    private MtiConfigMapper mtiConfigMapper;

    @BeforeEach
    public void setUp() {
        mtiConfigMapper = new MtiConfigMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(mtiConfigMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(mtiConfigMapper.fromId(null)).isNull();
    }
}
