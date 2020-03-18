package com.org.simulator.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.org.simulator.web.rest.TestUtil;

public class EmvDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmvDTO.class);
        EmvDTO emvDTO1 = new EmvDTO();
        emvDTO1.setId(1L);
        EmvDTO emvDTO2 = new EmvDTO();
        assertThat(emvDTO1).isNotEqualTo(emvDTO2);
        emvDTO2.setId(emvDTO1.getId());
        assertThat(emvDTO1).isEqualTo(emvDTO2);
        emvDTO2.setId(2L);
        assertThat(emvDTO1).isNotEqualTo(emvDTO2);
        emvDTO1.setId(null);
        assertThat(emvDTO1).isNotEqualTo(emvDTO2);
    }
}
