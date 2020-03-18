package com.org.simulator.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.org.simulator.web.rest.TestUtil;

public class TestCaseDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TestCaseDTO.class);
        TestCaseDTO testCaseDTO1 = new TestCaseDTO();
        testCaseDTO1.setId(1L);
        TestCaseDTO testCaseDTO2 = new TestCaseDTO();
        assertThat(testCaseDTO1).isNotEqualTo(testCaseDTO2);
        testCaseDTO2.setId(testCaseDTO1.getId());
        assertThat(testCaseDTO1).isEqualTo(testCaseDTO2);
        testCaseDTO2.setId(2L);
        assertThat(testCaseDTO1).isNotEqualTo(testCaseDTO2);
        testCaseDTO1.setId(null);
        assertThat(testCaseDTO1).isNotEqualTo(testCaseDTO2);
    }
}
