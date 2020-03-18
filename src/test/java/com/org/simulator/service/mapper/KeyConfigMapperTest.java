package com.org.simulator.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class KeyConfigMapperTest {

    private KeyConfigMapper keyConfigMapper;

    @BeforeEach
    public void setUp() {
        keyConfigMapper = new KeyConfigMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(keyConfigMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(keyConfigMapper.fromId(null)).isNull();
    }
}
