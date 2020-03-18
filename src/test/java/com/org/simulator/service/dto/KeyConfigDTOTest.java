package com.org.simulator.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.org.simulator.web.rest.TestUtil;

public class KeyConfigDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(KeyConfigDTO.class);
        KeyConfigDTO keyConfigDTO1 = new KeyConfigDTO();
        keyConfigDTO1.setId(1L);
        KeyConfigDTO keyConfigDTO2 = new KeyConfigDTO();
        assertThat(keyConfigDTO1).isNotEqualTo(keyConfigDTO2);
        keyConfigDTO2.setId(keyConfigDTO1.getId());
        assertThat(keyConfigDTO1).isEqualTo(keyConfigDTO2);
        keyConfigDTO2.setId(2L);
        assertThat(keyConfigDTO1).isNotEqualTo(keyConfigDTO2);
        keyConfigDTO1.setId(null);
        assertThat(keyConfigDTO1).isNotEqualTo(keyConfigDTO2);
    }
}
