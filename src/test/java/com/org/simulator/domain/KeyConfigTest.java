package com.org.simulator.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.org.simulator.web.rest.TestUtil;

public class KeyConfigTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(KeyConfig.class);
        KeyConfig keyConfig1 = new KeyConfig();
        keyConfig1.setId(1L);
        KeyConfig keyConfig2 = new KeyConfig();
        keyConfig2.setId(keyConfig1.getId());
        assertThat(keyConfig1).isEqualTo(keyConfig2);
        keyConfig2.setId(2L);
        assertThat(keyConfig1).isNotEqualTo(keyConfig2);
        keyConfig1.setId(null);
        assertThat(keyConfig1).isNotEqualTo(keyConfig2);
    }
}
