package com.org.simulator.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.org.simulator.web.rest.TestUtil;

public class MtiConfigTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MtiConfig.class);
        MtiConfig mtiConfig1 = new MtiConfig();
        mtiConfig1.setId(1L);
        MtiConfig mtiConfig2 = new MtiConfig();
        mtiConfig2.setId(mtiConfig1.getId());
        assertThat(mtiConfig1).isEqualTo(mtiConfig2);
        mtiConfig2.setId(2L);
        assertThat(mtiConfig1).isNotEqualTo(mtiConfig2);
        mtiConfig1.setId(null);
        assertThat(mtiConfig1).isNotEqualTo(mtiConfig2);
    }
}
