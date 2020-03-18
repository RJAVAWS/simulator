package com.org.simulator.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.org.simulator.web.rest.TestUtil;

public class MtiConfigDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MtiConfigDTO.class);
        MtiConfigDTO mtiConfigDTO1 = new MtiConfigDTO();
        mtiConfigDTO1.setId(1L);
        MtiConfigDTO mtiConfigDTO2 = new MtiConfigDTO();
        assertThat(mtiConfigDTO1).isNotEqualTo(mtiConfigDTO2);
        mtiConfigDTO2.setId(mtiConfigDTO1.getId());
        assertThat(mtiConfigDTO1).isEqualTo(mtiConfigDTO2);
        mtiConfigDTO2.setId(2L);
        assertThat(mtiConfigDTO1).isNotEqualTo(mtiConfigDTO2);
        mtiConfigDTO1.setId(null);
        assertThat(mtiConfigDTO1).isNotEqualTo(mtiConfigDTO2);
    }
}
