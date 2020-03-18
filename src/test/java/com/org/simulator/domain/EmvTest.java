package com.org.simulator.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.org.simulator.web.rest.TestUtil;

public class EmvTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Emv.class);
        Emv emv1 = new Emv();
        emv1.setId(1L);
        Emv emv2 = new Emv();
        emv2.setId(emv1.getId());
        assertThat(emv1).isEqualTo(emv2);
        emv2.setId(2L);
        assertThat(emv1).isNotEqualTo(emv2);
        emv1.setId(null);
        assertThat(emv1).isNotEqualTo(emv2);
    }
}
