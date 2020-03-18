package com.org.simulator.web.rest;

import com.org.simulator.SimulatorApp;
import com.org.simulator.domain.TestCase;
import com.org.simulator.repository.TestCaseRepository;
import com.org.simulator.service.TestCaseService;
import com.org.simulator.service.dto.TestCaseDTO;
import com.org.simulator.service.mapper.TestCaseMapper;
import com.org.simulator.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.org.simulator.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.org.simulator.domain.enumeration.PsdnType;
import com.org.simulator.domain.enumeration.ReqResType;
/**
 * Integration tests for the {@link TestCaseResource} REST controller.
 */
@SpringBootTest(classes = SimulatorApp.class)
public class TestCaseResourceIT {

    private static final String DEFAULT_CASE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_CASE_DESCRIPTION = "BBBBBBBBBB";

    private static final PsdnType DEFAULT_PSDN_TYPE = PsdnType.PRIMARY;
    private static final PsdnType UPDATED_PSDN_TYPE = PsdnType.SECONDARY;

    private static final ReqResType DEFAULT_REQ_RES_TYPE = ReqResType.REQUEST;
    private static final ReqResType UPDATED_REQ_RES_TYPE = ReqResType.RESPONSE;

    private static final String DEFAULT_MTI = "AAAAAAAAAA";
    private static final String UPDATED_MTI = "BBBBBBBBBB";

    private static final String DEFAULT_DE_001 = "AAAAAAAAAA";
    private static final String UPDATED_DE_001 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_002 = "AAAAAAAAAA";
    private static final String UPDATED_DE_002 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_003 = "AAAAAAAAAA";
    private static final String UPDATED_DE_003 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_004 = "AAAAAAAAAA";
    private static final String UPDATED_DE_004 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_005 = "AAAAAAAAAA";
    private static final String UPDATED_DE_005 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_006 = "AAAAAAAAAA";
    private static final String UPDATED_DE_006 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_007 = "AAAAAAAAAA";
    private static final String UPDATED_DE_007 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_008 = "AAAAAAAAAA";
    private static final String UPDATED_DE_008 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_009 = "AAAAAAAAAA";
    private static final String UPDATED_DE_009 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_010 = "AAAAAAAAAA";
    private static final String UPDATED_DE_010 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_011 = "AAAAAAAAAA";
    private static final String UPDATED_DE_011 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_012 = "AAAAAAAAAA";
    private static final String UPDATED_DE_012 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_013 = "AAAAAAAAAA";
    private static final String UPDATED_DE_013 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_014 = "AAAAAAAAAA";
    private static final String UPDATED_DE_014 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_015 = "AAAAAAAAAA";
    private static final String UPDATED_DE_015 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_016 = "AAAAAAAAAA";
    private static final String UPDATED_DE_016 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_017 = "AAAAAAAAAA";
    private static final String UPDATED_DE_017 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_018 = "AAAAAAAAAA";
    private static final String UPDATED_DE_018 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_019 = "AAAAAAAAAA";
    private static final String UPDATED_DE_019 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_020 = "AAAAAAAAAA";
    private static final String UPDATED_DE_020 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_021 = "AAAAAAAAAA";
    private static final String UPDATED_DE_021 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_022 = "AAAAAAAAAA";
    private static final String UPDATED_DE_022 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_023 = "AAAAAAAAAA";
    private static final String UPDATED_DE_023 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_024 = "AAAAAAAAAA";
    private static final String UPDATED_DE_024 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_025 = "AAAAAAAAAA";
    private static final String UPDATED_DE_025 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_026 = "AAAAAAAAAA";
    private static final String UPDATED_DE_026 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_027 = "AAAAAAAAAA";
    private static final String UPDATED_DE_027 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_028 = "AAAAAAAAAA";
    private static final String UPDATED_DE_028 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_029 = "AAAAAAAAAA";
    private static final String UPDATED_DE_029 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_030 = "AAAAAAAAAA";
    private static final String UPDATED_DE_030 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_031 = "AAAAAAAAAA";
    private static final String UPDATED_DE_031 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_032 = "AAAAAAAAAA";
    private static final String UPDATED_DE_032 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_033 = "AAAAAAAAAA";
    private static final String UPDATED_DE_033 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_034 = "AAAAAAAAAA";
    private static final String UPDATED_DE_034 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_035 = "AAAAAAAAAA";
    private static final String UPDATED_DE_035 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_036 = "AAAAAAAAAA";
    private static final String UPDATED_DE_036 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_037 = "AAAAAAAAAA";
    private static final String UPDATED_DE_037 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_038 = "AAAAAAAAAA";
    private static final String UPDATED_DE_038 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_039 = "AAAAAAAAAA";
    private static final String UPDATED_DE_039 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_040 = "AAAAAAAAAA";
    private static final String UPDATED_DE_040 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_041 = "AAAAAAAAAA";
    private static final String UPDATED_DE_041 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_042 = "AAAAAAAAAA";
    private static final String UPDATED_DE_042 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_043 = "AAAAAAAAAA";
    private static final String UPDATED_DE_043 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_044 = "AAAAAAAAAA";
    private static final String UPDATED_DE_044 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_045 = "AAAAAAAAAA";
    private static final String UPDATED_DE_045 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_046 = "AAAAAAAAAA";
    private static final String UPDATED_DE_046 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_047 = "AAAAAAAAAA";
    private static final String UPDATED_DE_047 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_048 = "AAAAAAAAAA";
    private static final String UPDATED_DE_048 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_049 = "AAAAAAAAAA";
    private static final String UPDATED_DE_049 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_050 = "AAAAAAAAAA";
    private static final String UPDATED_DE_050 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_051 = "AAAAAAAAAA";
    private static final String UPDATED_DE_051 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_052 = "AAAAAAAAAA";
    private static final String UPDATED_DE_052 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_053 = "AAAAAAAAAA";
    private static final String UPDATED_DE_053 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_054 = "AAAAAAAAAA";
    private static final String UPDATED_DE_054 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_055 = "AAAAAAAAAA";
    private static final String UPDATED_DE_055 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_056 = "AAAAAAAAAA";
    private static final String UPDATED_DE_056 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_057 = "AAAAAAAAAA";
    private static final String UPDATED_DE_057 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_058 = "AAAAAAAAAA";
    private static final String UPDATED_DE_058 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_059 = "AAAAAAAAAA";
    private static final String UPDATED_DE_059 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_060 = "AAAAAAAAAA";
    private static final String UPDATED_DE_060 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_061 = "AAAAAAAAAA";
    private static final String UPDATED_DE_061 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_062 = "AAAAAAAAAA";
    private static final String UPDATED_DE_062 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_063 = "AAAAAAAAAA";
    private static final String UPDATED_DE_063 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_064 = "AAAAAAAAAA";
    private static final String UPDATED_DE_064 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_065 = "AAAAAAAAAA";
    private static final String UPDATED_DE_065 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_066 = "AAAAAAAAAA";
    private static final String UPDATED_DE_066 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_067 = "AAAAAAAAAA";
    private static final String UPDATED_DE_067 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_068 = "AAAAAAAAAA";
    private static final String UPDATED_DE_068 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_069 = "AAAAAAAAAA";
    private static final String UPDATED_DE_069 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_070 = "AAAAAAAAAA";
    private static final String UPDATED_DE_070 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_071 = "AAAAAAAAAA";
    private static final String UPDATED_DE_071 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_072 = "AAAAAAAAAA";
    private static final String UPDATED_DE_072 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_073 = "AAAAAAAAAA";
    private static final String UPDATED_DE_073 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_074 = "AAAAAAAAAA";
    private static final String UPDATED_DE_074 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_075 = "AAAAAAAAAA";
    private static final String UPDATED_DE_075 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_076 = "AAAAAAAAAA";
    private static final String UPDATED_DE_076 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_077 = "AAAAAAAAAA";
    private static final String UPDATED_DE_077 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_078 = "AAAAAAAAAA";
    private static final String UPDATED_DE_078 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_079 = "AAAAAAAAAA";
    private static final String UPDATED_DE_079 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_080 = "AAAAAAAAAA";
    private static final String UPDATED_DE_080 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_081 = "AAAAAAAAAA";
    private static final String UPDATED_DE_081 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_082 = "AAAAAAAAAA";
    private static final String UPDATED_DE_082 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_083 = "AAAAAAAAAA";
    private static final String UPDATED_DE_083 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_084 = "AAAAAAAAAA";
    private static final String UPDATED_DE_084 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_085 = "AAAAAAAAAA";
    private static final String UPDATED_DE_085 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_086 = "AAAAAAAAAA";
    private static final String UPDATED_DE_086 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_087 = "AAAAAAAAAA";
    private static final String UPDATED_DE_087 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_088 = "AAAAAAAAAA";
    private static final String UPDATED_DE_088 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_089 = "AAAAAAAAAA";
    private static final String UPDATED_DE_089 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_090 = "AAAAAAAAAA";
    private static final String UPDATED_DE_090 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_091 = "AAAAAAAAAA";
    private static final String UPDATED_DE_091 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_092 = "AAAAAAAAAA";
    private static final String UPDATED_DE_092 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_093 = "AAAAAAAAAA";
    private static final String UPDATED_DE_093 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_094 = "AAAAAAAAAA";
    private static final String UPDATED_DE_094 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_095 = "AAAAAAAAAA";
    private static final String UPDATED_DE_095 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_096 = "AAAAAAAAAA";
    private static final String UPDATED_DE_096 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_097 = "AAAAAAAAAA";
    private static final String UPDATED_DE_097 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_098 = "AAAAAAAAAA";
    private static final String UPDATED_DE_098 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_099 = "AAAAAAAAAA";
    private static final String UPDATED_DE_099 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_100 = "AAAAAAAAAA";
    private static final String UPDATED_DE_100 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_101 = "AAAAAAAAAA";
    private static final String UPDATED_DE_101 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_102 = "AAAAAAAAAA";
    private static final String UPDATED_DE_102 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_103 = "AAAAAAAAAA";
    private static final String UPDATED_DE_103 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_104 = "AAAAAAAAAA";
    private static final String UPDATED_DE_104 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_105 = "AAAAAAAAAA";
    private static final String UPDATED_DE_105 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_106 = "AAAAAAAAAA";
    private static final String UPDATED_DE_106 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_107 = "AAAAAAAAAA";
    private static final String UPDATED_DE_107 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_108 = "AAAAAAAAAA";
    private static final String UPDATED_DE_108 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_109 = "AAAAAAAAAA";
    private static final String UPDATED_DE_109 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_110 = "AAAAAAAAAA";
    private static final String UPDATED_DE_110 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_111 = "AAAAAAAAAA";
    private static final String UPDATED_DE_111 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_112 = "AAAAAAAAAA";
    private static final String UPDATED_DE_112 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_113 = "AAAAAAAAAA";
    private static final String UPDATED_DE_113 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_114 = "AAAAAAAAAA";
    private static final String UPDATED_DE_114 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_115 = "AAAAAAAAAA";
    private static final String UPDATED_DE_115 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_116 = "AAAAAAAAAA";
    private static final String UPDATED_DE_116 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_117 = "AAAAAAAAAA";
    private static final String UPDATED_DE_117 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_118 = "AAAAAAAAAA";
    private static final String UPDATED_DE_118 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_119 = "AAAAAAAAAA";
    private static final String UPDATED_DE_119 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_120 = "AAAAAAAAAA";
    private static final String UPDATED_DE_120 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_121 = "AAAAAAAAAA";
    private static final String UPDATED_DE_121 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_122 = "AAAAAAAAAA";
    private static final String UPDATED_DE_122 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_123 = "AAAAAAAAAA";
    private static final String UPDATED_DE_123 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_124 = "AAAAAAAAAA";
    private static final String UPDATED_DE_124 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_125 = "AAAAAAAAAA";
    private static final String UPDATED_DE_125 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_126 = "AAAAAAAAAA";
    private static final String UPDATED_DE_126 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_127 = "AAAAAAAAAA";
    private static final String UPDATED_DE_127 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_128 = "AAAAAAAAAA";
    private static final String UPDATED_DE_128 = "BBBBBBBBBB";

    @Autowired
    private TestCaseRepository testCaseRepository;

    @Autowired
    private TestCaseMapper testCaseMapper;

    @Autowired
    private TestCaseService testCaseService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restTestCaseMockMvc;

    private TestCase testCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TestCaseResource testCaseResource = new TestCaseResource(testCaseService);
        this.restTestCaseMockMvc = MockMvcBuilders.standaloneSetup(testCaseResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TestCase createEntity(EntityManager em) {
        TestCase testCase = new TestCase()
            .caseDescription(DEFAULT_CASE_DESCRIPTION)
            .psdnType(DEFAULT_PSDN_TYPE)
            .reqResType(DEFAULT_REQ_RES_TYPE)
            .mti(DEFAULT_MTI)
            .de001(DEFAULT_DE_001)
            .de002(DEFAULT_DE_002)
            .de003(DEFAULT_DE_003)
            .de004(DEFAULT_DE_004)
            .de005(DEFAULT_DE_005)
            .de006(DEFAULT_DE_006)
            .de007(DEFAULT_DE_007)
            .de008(DEFAULT_DE_008)
            .de009(DEFAULT_DE_009)
            .de010(DEFAULT_DE_010)
            .de011(DEFAULT_DE_011)
            .de012(DEFAULT_DE_012)
            .de013(DEFAULT_DE_013)
            .de014(DEFAULT_DE_014)
            .de015(DEFAULT_DE_015)
            .de016(DEFAULT_DE_016)
            .de017(DEFAULT_DE_017)
            .de018(DEFAULT_DE_018)
            .de019(DEFAULT_DE_019)
            .de020(DEFAULT_DE_020)
            .de021(DEFAULT_DE_021)
            .de022(DEFAULT_DE_022)
            .de023(DEFAULT_DE_023)
            .de024(DEFAULT_DE_024)
            .de025(DEFAULT_DE_025)
            .de026(DEFAULT_DE_026)
            .de027(DEFAULT_DE_027)
            .de028(DEFAULT_DE_028)
            .de029(DEFAULT_DE_029)
            .de030(DEFAULT_DE_030)
            .de031(DEFAULT_DE_031)
            .de032(DEFAULT_DE_032)
            .de033(DEFAULT_DE_033)
            .de034(DEFAULT_DE_034)
            .de035(DEFAULT_DE_035)
            .de036(DEFAULT_DE_036)
            .de037(DEFAULT_DE_037)
            .de038(DEFAULT_DE_038)
            .de039(DEFAULT_DE_039)
            .de040(DEFAULT_DE_040)
            .de041(DEFAULT_DE_041)
            .de042(DEFAULT_DE_042)
            .de043(DEFAULT_DE_043)
            .de044(DEFAULT_DE_044)
            .de045(DEFAULT_DE_045)
            .de046(DEFAULT_DE_046)
            .de047(DEFAULT_DE_047)
            .de048(DEFAULT_DE_048)
            .de049(DEFAULT_DE_049)
            .de050(DEFAULT_DE_050)
            .de051(DEFAULT_DE_051)
            .de052(DEFAULT_DE_052)
            .de053(DEFAULT_DE_053)
            .de054(DEFAULT_DE_054)
            .de055(DEFAULT_DE_055)
            .de056(DEFAULT_DE_056)
            .de057(DEFAULT_DE_057)
            .de058(DEFAULT_DE_058)
            .de059(DEFAULT_DE_059)
            .de060(DEFAULT_DE_060)
            .de061(DEFAULT_DE_061)
            .de062(DEFAULT_DE_062)
            .de063(DEFAULT_DE_063)
            .de064(DEFAULT_DE_064)
            .de065(DEFAULT_DE_065)
            .de066(DEFAULT_DE_066)
            .de067(DEFAULT_DE_067)
            .de068(DEFAULT_DE_068)
            .de069(DEFAULT_DE_069)
            .de070(DEFAULT_DE_070)
            .de071(DEFAULT_DE_071)
            .de072(DEFAULT_DE_072)
            .de073(DEFAULT_DE_073)
            .de074(DEFAULT_DE_074)
            .de075(DEFAULT_DE_075)
            .de076(DEFAULT_DE_076)
            .de077(DEFAULT_DE_077)
            .de078(DEFAULT_DE_078)
            .de079(DEFAULT_DE_079)
            .de080(DEFAULT_DE_080)
            .de081(DEFAULT_DE_081)
            .de082(DEFAULT_DE_082)
            .de083(DEFAULT_DE_083)
            .de084(DEFAULT_DE_084)
            .de085(DEFAULT_DE_085)
            .de086(DEFAULT_DE_086)
            .de087(DEFAULT_DE_087)
            .de088(DEFAULT_DE_088)
            .de089(DEFAULT_DE_089)
            .de090(DEFAULT_DE_090)
            .de091(DEFAULT_DE_091)
            .de092(DEFAULT_DE_092)
            .de093(DEFAULT_DE_093)
            .de094(DEFAULT_DE_094)
            .de095(DEFAULT_DE_095)
            .de096(DEFAULT_DE_096)
            .de097(DEFAULT_DE_097)
            .de098(DEFAULT_DE_098)
            .de099(DEFAULT_DE_099)
            .de100(DEFAULT_DE_100)
            .de101(DEFAULT_DE_101)
            .de102(DEFAULT_DE_102)
            .de103(DEFAULT_DE_103)
            .de104(DEFAULT_DE_104)
            .de105(DEFAULT_DE_105)
            .de106(DEFAULT_DE_106)
            .de107(DEFAULT_DE_107)
            .de108(DEFAULT_DE_108)
            .de109(DEFAULT_DE_109)
            .de110(DEFAULT_DE_110)
            .de111(DEFAULT_DE_111)
            .de112(DEFAULT_DE_112)
            .de113(DEFAULT_DE_113)
            .de114(DEFAULT_DE_114)
            .de115(DEFAULT_DE_115)
            .de116(DEFAULT_DE_116)
            .de117(DEFAULT_DE_117)
            .de118(DEFAULT_DE_118)
            .de119(DEFAULT_DE_119)
            .de120(DEFAULT_DE_120)
            .de121(DEFAULT_DE_121)
            .de122(DEFAULT_DE_122)
            .de123(DEFAULT_DE_123)
            .de124(DEFAULT_DE_124)
            .de125(DEFAULT_DE_125)
            .de126(DEFAULT_DE_126)
            .de127(DEFAULT_DE_127)
            .de128(DEFAULT_DE_128);
        return testCase;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TestCase createUpdatedEntity(EntityManager em) {
        TestCase testCase = new TestCase()
            .caseDescription(UPDATED_CASE_DESCRIPTION)
            .psdnType(UPDATED_PSDN_TYPE)
            .reqResType(UPDATED_REQ_RES_TYPE)
            .mti(UPDATED_MTI)
            .de001(UPDATED_DE_001)
            .de002(UPDATED_DE_002)
            .de003(UPDATED_DE_003)
            .de004(UPDATED_DE_004)
            .de005(UPDATED_DE_005)
            .de006(UPDATED_DE_006)
            .de007(UPDATED_DE_007)
            .de008(UPDATED_DE_008)
            .de009(UPDATED_DE_009)
            .de010(UPDATED_DE_010)
            .de011(UPDATED_DE_011)
            .de012(UPDATED_DE_012)
            .de013(UPDATED_DE_013)
            .de014(UPDATED_DE_014)
            .de015(UPDATED_DE_015)
            .de016(UPDATED_DE_016)
            .de017(UPDATED_DE_017)
            .de018(UPDATED_DE_018)
            .de019(UPDATED_DE_019)
            .de020(UPDATED_DE_020)
            .de021(UPDATED_DE_021)
            .de022(UPDATED_DE_022)
            .de023(UPDATED_DE_023)
            .de024(UPDATED_DE_024)
            .de025(UPDATED_DE_025)
            .de026(UPDATED_DE_026)
            .de027(UPDATED_DE_027)
            .de028(UPDATED_DE_028)
            .de029(UPDATED_DE_029)
            .de030(UPDATED_DE_030)
            .de031(UPDATED_DE_031)
            .de032(UPDATED_DE_032)
            .de033(UPDATED_DE_033)
            .de034(UPDATED_DE_034)
            .de035(UPDATED_DE_035)
            .de036(UPDATED_DE_036)
            .de037(UPDATED_DE_037)
            .de038(UPDATED_DE_038)
            .de039(UPDATED_DE_039)
            .de040(UPDATED_DE_040)
            .de041(UPDATED_DE_041)
            .de042(UPDATED_DE_042)
            .de043(UPDATED_DE_043)
            .de044(UPDATED_DE_044)
            .de045(UPDATED_DE_045)
            .de046(UPDATED_DE_046)
            .de047(UPDATED_DE_047)
            .de048(UPDATED_DE_048)
            .de049(UPDATED_DE_049)
            .de050(UPDATED_DE_050)
            .de051(UPDATED_DE_051)
            .de052(UPDATED_DE_052)
            .de053(UPDATED_DE_053)
            .de054(UPDATED_DE_054)
            .de055(UPDATED_DE_055)
            .de056(UPDATED_DE_056)
            .de057(UPDATED_DE_057)
            .de058(UPDATED_DE_058)
            .de059(UPDATED_DE_059)
            .de060(UPDATED_DE_060)
            .de061(UPDATED_DE_061)
            .de062(UPDATED_DE_062)
            .de063(UPDATED_DE_063)
            .de064(UPDATED_DE_064)
            .de065(UPDATED_DE_065)
            .de066(UPDATED_DE_066)
            .de067(UPDATED_DE_067)
            .de068(UPDATED_DE_068)
            .de069(UPDATED_DE_069)
            .de070(UPDATED_DE_070)
            .de071(UPDATED_DE_071)
            .de072(UPDATED_DE_072)
            .de073(UPDATED_DE_073)
            .de074(UPDATED_DE_074)
            .de075(UPDATED_DE_075)
            .de076(UPDATED_DE_076)
            .de077(UPDATED_DE_077)
            .de078(UPDATED_DE_078)
            .de079(UPDATED_DE_079)
            .de080(UPDATED_DE_080)
            .de081(UPDATED_DE_081)
            .de082(UPDATED_DE_082)
            .de083(UPDATED_DE_083)
            .de084(UPDATED_DE_084)
            .de085(UPDATED_DE_085)
            .de086(UPDATED_DE_086)
            .de087(UPDATED_DE_087)
            .de088(UPDATED_DE_088)
            .de089(UPDATED_DE_089)
            .de090(UPDATED_DE_090)
            .de091(UPDATED_DE_091)
            .de092(UPDATED_DE_092)
            .de093(UPDATED_DE_093)
            .de094(UPDATED_DE_094)
            .de095(UPDATED_DE_095)
            .de096(UPDATED_DE_096)
            .de097(UPDATED_DE_097)
            .de098(UPDATED_DE_098)
            .de099(UPDATED_DE_099)
            .de100(UPDATED_DE_100)
            .de101(UPDATED_DE_101)
            .de102(UPDATED_DE_102)
            .de103(UPDATED_DE_103)
            .de104(UPDATED_DE_104)
            .de105(UPDATED_DE_105)
            .de106(UPDATED_DE_106)
            .de107(UPDATED_DE_107)
            .de108(UPDATED_DE_108)
            .de109(UPDATED_DE_109)
            .de110(UPDATED_DE_110)
            .de111(UPDATED_DE_111)
            .de112(UPDATED_DE_112)
            .de113(UPDATED_DE_113)
            .de114(UPDATED_DE_114)
            .de115(UPDATED_DE_115)
            .de116(UPDATED_DE_116)
            .de117(UPDATED_DE_117)
            .de118(UPDATED_DE_118)
            .de119(UPDATED_DE_119)
            .de120(UPDATED_DE_120)
            .de121(UPDATED_DE_121)
            .de122(UPDATED_DE_122)
            .de123(UPDATED_DE_123)
            .de124(UPDATED_DE_124)
            .de125(UPDATED_DE_125)
            .de126(UPDATED_DE_126)
            .de127(UPDATED_DE_127)
            .de128(UPDATED_DE_128);
        return testCase;
    }

    @BeforeEach
    public void initTest() {
        testCase = createEntity(em);
    }

    @Test
    @Transactional
    public void createTestCase() throws Exception {
        int databaseSizeBeforeCreate = testCaseRepository.findAll().size();

        // Create the TestCase
        TestCaseDTO testCaseDTO = testCaseMapper.toDto(testCase);
        restTestCaseMockMvc.perform(post("/api/test-cases")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(testCaseDTO)))
            .andExpect(status().isCreated());

        // Validate the TestCase in the database
        List<TestCase> testCaseList = testCaseRepository.findAll();
        assertThat(testCaseList).hasSize(databaseSizeBeforeCreate + 1);
        TestCase testTestCase = testCaseList.get(testCaseList.size() - 1);
        assertThat(testTestCase.getCaseDescription()).isEqualTo(DEFAULT_CASE_DESCRIPTION);
        assertThat(testTestCase.getPsdnType()).isEqualTo(DEFAULT_PSDN_TYPE);
        assertThat(testTestCase.getReqResType()).isEqualTo(DEFAULT_REQ_RES_TYPE);
        assertThat(testTestCase.getMti()).isEqualTo(DEFAULT_MTI);
        assertThat(testTestCase.getDe001()).isEqualTo(DEFAULT_DE_001);
        assertThat(testTestCase.getDe002()).isEqualTo(DEFAULT_DE_002);
        assertThat(testTestCase.getDe003()).isEqualTo(DEFAULT_DE_003);
        assertThat(testTestCase.getDe004()).isEqualTo(DEFAULT_DE_004);
        assertThat(testTestCase.getDe005()).isEqualTo(DEFAULT_DE_005);
        assertThat(testTestCase.getDe006()).isEqualTo(DEFAULT_DE_006);
        assertThat(testTestCase.getDe007()).isEqualTo(DEFAULT_DE_007);
        assertThat(testTestCase.getDe008()).isEqualTo(DEFAULT_DE_008);
        assertThat(testTestCase.getDe009()).isEqualTo(DEFAULT_DE_009);
        assertThat(testTestCase.getDe010()).isEqualTo(DEFAULT_DE_010);
        assertThat(testTestCase.getDe011()).isEqualTo(DEFAULT_DE_011);
        assertThat(testTestCase.getDe012()).isEqualTo(DEFAULT_DE_012);
        assertThat(testTestCase.getDe013()).isEqualTo(DEFAULT_DE_013);
        assertThat(testTestCase.getDe014()).isEqualTo(DEFAULT_DE_014);
        assertThat(testTestCase.getDe015()).isEqualTo(DEFAULT_DE_015);
        assertThat(testTestCase.getDe016()).isEqualTo(DEFAULT_DE_016);
        assertThat(testTestCase.getDe017()).isEqualTo(DEFAULT_DE_017);
        assertThat(testTestCase.getDe018()).isEqualTo(DEFAULT_DE_018);
        assertThat(testTestCase.getDe019()).isEqualTo(DEFAULT_DE_019);
        assertThat(testTestCase.getDe020()).isEqualTo(DEFAULT_DE_020);
        assertThat(testTestCase.getDe021()).isEqualTo(DEFAULT_DE_021);
        assertThat(testTestCase.getDe022()).isEqualTo(DEFAULT_DE_022);
        assertThat(testTestCase.getDe023()).isEqualTo(DEFAULT_DE_023);
        assertThat(testTestCase.getDe024()).isEqualTo(DEFAULT_DE_024);
        assertThat(testTestCase.getDe025()).isEqualTo(DEFAULT_DE_025);
        assertThat(testTestCase.getDe026()).isEqualTo(DEFAULT_DE_026);
        assertThat(testTestCase.getDe027()).isEqualTo(DEFAULT_DE_027);
        assertThat(testTestCase.getDe028()).isEqualTo(DEFAULT_DE_028);
        assertThat(testTestCase.getDe029()).isEqualTo(DEFAULT_DE_029);
        assertThat(testTestCase.getDe030()).isEqualTo(DEFAULT_DE_030);
        assertThat(testTestCase.getDe031()).isEqualTo(DEFAULT_DE_031);
        assertThat(testTestCase.getDe032()).isEqualTo(DEFAULT_DE_032);
        assertThat(testTestCase.getDe033()).isEqualTo(DEFAULT_DE_033);
        assertThat(testTestCase.getDe034()).isEqualTo(DEFAULT_DE_034);
        assertThat(testTestCase.getDe035()).isEqualTo(DEFAULT_DE_035);
        assertThat(testTestCase.getDe036()).isEqualTo(DEFAULT_DE_036);
        assertThat(testTestCase.getDe037()).isEqualTo(DEFAULT_DE_037);
        assertThat(testTestCase.getDe038()).isEqualTo(DEFAULT_DE_038);
        assertThat(testTestCase.getDe039()).isEqualTo(DEFAULT_DE_039);
        assertThat(testTestCase.getDe040()).isEqualTo(DEFAULT_DE_040);
        assertThat(testTestCase.getDe041()).isEqualTo(DEFAULT_DE_041);
        assertThat(testTestCase.getDe042()).isEqualTo(DEFAULT_DE_042);
        assertThat(testTestCase.getDe043()).isEqualTo(DEFAULT_DE_043);
        assertThat(testTestCase.getDe044()).isEqualTo(DEFAULT_DE_044);
        assertThat(testTestCase.getDe045()).isEqualTo(DEFAULT_DE_045);
        assertThat(testTestCase.getDe046()).isEqualTo(DEFAULT_DE_046);
        assertThat(testTestCase.getDe047()).isEqualTo(DEFAULT_DE_047);
        assertThat(testTestCase.getDe048()).isEqualTo(DEFAULT_DE_048);
        assertThat(testTestCase.getDe049()).isEqualTo(DEFAULT_DE_049);
        assertThat(testTestCase.getDe050()).isEqualTo(DEFAULT_DE_050);
        assertThat(testTestCase.getDe051()).isEqualTo(DEFAULT_DE_051);
        assertThat(testTestCase.getDe052()).isEqualTo(DEFAULT_DE_052);
        assertThat(testTestCase.getDe053()).isEqualTo(DEFAULT_DE_053);
        assertThat(testTestCase.getDe054()).isEqualTo(DEFAULT_DE_054);
        assertThat(testTestCase.getDe055()).isEqualTo(DEFAULT_DE_055);
        assertThat(testTestCase.getDe056()).isEqualTo(DEFAULT_DE_056);
        assertThat(testTestCase.getDe057()).isEqualTo(DEFAULT_DE_057);
        assertThat(testTestCase.getDe058()).isEqualTo(DEFAULT_DE_058);
        assertThat(testTestCase.getDe059()).isEqualTo(DEFAULT_DE_059);
        assertThat(testTestCase.getDe060()).isEqualTo(DEFAULT_DE_060);
        assertThat(testTestCase.getDe061()).isEqualTo(DEFAULT_DE_061);
        assertThat(testTestCase.getDe062()).isEqualTo(DEFAULT_DE_062);
        assertThat(testTestCase.getDe063()).isEqualTo(DEFAULT_DE_063);
        assertThat(testTestCase.getDe064()).isEqualTo(DEFAULT_DE_064);
        assertThat(testTestCase.getDe065()).isEqualTo(DEFAULT_DE_065);
        assertThat(testTestCase.getDe066()).isEqualTo(DEFAULT_DE_066);
        assertThat(testTestCase.getDe067()).isEqualTo(DEFAULT_DE_067);
        assertThat(testTestCase.getDe068()).isEqualTo(DEFAULT_DE_068);
        assertThat(testTestCase.getDe069()).isEqualTo(DEFAULT_DE_069);
        assertThat(testTestCase.getDe070()).isEqualTo(DEFAULT_DE_070);
        assertThat(testTestCase.getDe071()).isEqualTo(DEFAULT_DE_071);
        assertThat(testTestCase.getDe072()).isEqualTo(DEFAULT_DE_072);
        assertThat(testTestCase.getDe073()).isEqualTo(DEFAULT_DE_073);
        assertThat(testTestCase.getDe074()).isEqualTo(DEFAULT_DE_074);
        assertThat(testTestCase.getDe075()).isEqualTo(DEFAULT_DE_075);
        assertThat(testTestCase.getDe076()).isEqualTo(DEFAULT_DE_076);
        assertThat(testTestCase.getDe077()).isEqualTo(DEFAULT_DE_077);
        assertThat(testTestCase.getDe078()).isEqualTo(DEFAULT_DE_078);
        assertThat(testTestCase.getDe079()).isEqualTo(DEFAULT_DE_079);
        assertThat(testTestCase.getDe080()).isEqualTo(DEFAULT_DE_080);
        assertThat(testTestCase.getDe081()).isEqualTo(DEFAULT_DE_081);
        assertThat(testTestCase.getDe082()).isEqualTo(DEFAULT_DE_082);
        assertThat(testTestCase.getDe083()).isEqualTo(DEFAULT_DE_083);
        assertThat(testTestCase.getDe084()).isEqualTo(DEFAULT_DE_084);
        assertThat(testTestCase.getDe085()).isEqualTo(DEFAULT_DE_085);
        assertThat(testTestCase.getDe086()).isEqualTo(DEFAULT_DE_086);
        assertThat(testTestCase.getDe087()).isEqualTo(DEFAULT_DE_087);
        assertThat(testTestCase.getDe088()).isEqualTo(DEFAULT_DE_088);
        assertThat(testTestCase.getDe089()).isEqualTo(DEFAULT_DE_089);
        assertThat(testTestCase.getDe090()).isEqualTo(DEFAULT_DE_090);
        assertThat(testTestCase.getDe091()).isEqualTo(DEFAULT_DE_091);
        assertThat(testTestCase.getDe092()).isEqualTo(DEFAULT_DE_092);
        assertThat(testTestCase.getDe093()).isEqualTo(DEFAULT_DE_093);
        assertThat(testTestCase.getDe094()).isEqualTo(DEFAULT_DE_094);
        assertThat(testTestCase.getDe095()).isEqualTo(DEFAULT_DE_095);
        assertThat(testTestCase.getDe096()).isEqualTo(DEFAULT_DE_096);
        assertThat(testTestCase.getDe097()).isEqualTo(DEFAULT_DE_097);
        assertThat(testTestCase.getDe098()).isEqualTo(DEFAULT_DE_098);
        assertThat(testTestCase.getDe099()).isEqualTo(DEFAULT_DE_099);
        assertThat(testTestCase.getDe100()).isEqualTo(DEFAULT_DE_100);
        assertThat(testTestCase.getDe101()).isEqualTo(DEFAULT_DE_101);
        assertThat(testTestCase.getDe102()).isEqualTo(DEFAULT_DE_102);
        assertThat(testTestCase.getDe103()).isEqualTo(DEFAULT_DE_103);
        assertThat(testTestCase.getDe104()).isEqualTo(DEFAULT_DE_104);
        assertThat(testTestCase.getDe105()).isEqualTo(DEFAULT_DE_105);
        assertThat(testTestCase.getDe106()).isEqualTo(DEFAULT_DE_106);
        assertThat(testTestCase.getDe107()).isEqualTo(DEFAULT_DE_107);
        assertThat(testTestCase.getDe108()).isEqualTo(DEFAULT_DE_108);
        assertThat(testTestCase.getDe109()).isEqualTo(DEFAULT_DE_109);
        assertThat(testTestCase.getDe110()).isEqualTo(DEFAULT_DE_110);
        assertThat(testTestCase.getDe111()).isEqualTo(DEFAULT_DE_111);
        assertThat(testTestCase.getDe112()).isEqualTo(DEFAULT_DE_112);
        assertThat(testTestCase.getDe113()).isEqualTo(DEFAULT_DE_113);
        assertThat(testTestCase.getDe114()).isEqualTo(DEFAULT_DE_114);
        assertThat(testTestCase.getDe115()).isEqualTo(DEFAULT_DE_115);
        assertThat(testTestCase.getDe116()).isEqualTo(DEFAULT_DE_116);
        assertThat(testTestCase.getDe117()).isEqualTo(DEFAULT_DE_117);
        assertThat(testTestCase.getDe118()).isEqualTo(DEFAULT_DE_118);
        assertThat(testTestCase.getDe119()).isEqualTo(DEFAULT_DE_119);
        assertThat(testTestCase.getDe120()).isEqualTo(DEFAULT_DE_120);
        assertThat(testTestCase.getDe121()).isEqualTo(DEFAULT_DE_121);
        assertThat(testTestCase.getDe122()).isEqualTo(DEFAULT_DE_122);
        assertThat(testTestCase.getDe123()).isEqualTo(DEFAULT_DE_123);
        assertThat(testTestCase.getDe124()).isEqualTo(DEFAULT_DE_124);
        assertThat(testTestCase.getDe125()).isEqualTo(DEFAULT_DE_125);
        assertThat(testTestCase.getDe126()).isEqualTo(DEFAULT_DE_126);
        assertThat(testTestCase.getDe127()).isEqualTo(DEFAULT_DE_127);
        assertThat(testTestCase.getDe128()).isEqualTo(DEFAULT_DE_128);
    }

    @Test
    @Transactional
    public void createTestCaseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = testCaseRepository.findAll().size();

        // Create the TestCase with an existing ID
        testCase.setId(1L);
        TestCaseDTO testCaseDTO = testCaseMapper.toDto(testCase);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTestCaseMockMvc.perform(post("/api/test-cases")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(testCaseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TestCase in the database
        List<TestCase> testCaseList = testCaseRepository.findAll();
        assertThat(testCaseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCaseDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = testCaseRepository.findAll().size();
        // set the field null
        testCase.setCaseDescription(null);

        // Create the TestCase, which fails.
        TestCaseDTO testCaseDTO = testCaseMapper.toDto(testCase);

        restTestCaseMockMvc.perform(post("/api/test-cases")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(testCaseDTO)))
            .andExpect(status().isBadRequest());

        List<TestCase> testCaseList = testCaseRepository.findAll();
        assertThat(testCaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPsdnTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = testCaseRepository.findAll().size();
        // set the field null
        testCase.setPsdnType(null);

        // Create the TestCase, which fails.
        TestCaseDTO testCaseDTO = testCaseMapper.toDto(testCase);

        restTestCaseMockMvc.perform(post("/api/test-cases")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(testCaseDTO)))
            .andExpect(status().isBadRequest());

        List<TestCase> testCaseList = testCaseRepository.findAll();
        assertThat(testCaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkReqResTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = testCaseRepository.findAll().size();
        // set the field null
        testCase.setReqResType(null);

        // Create the TestCase, which fails.
        TestCaseDTO testCaseDTO = testCaseMapper.toDto(testCase);

        restTestCaseMockMvc.perform(post("/api/test-cases")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(testCaseDTO)))
            .andExpect(status().isBadRequest());

        List<TestCase> testCaseList = testCaseRepository.findAll();
        assertThat(testCaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTestCases() throws Exception {
        // Initialize the database
        testCaseRepository.saveAndFlush(testCase);

        // Get all the testCaseList
        restTestCaseMockMvc.perform(get("/api/test-cases?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(testCase.getId().intValue())))
            .andExpect(jsonPath("$.[*].caseDescription").value(hasItem(DEFAULT_CASE_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].psdnType").value(hasItem(DEFAULT_PSDN_TYPE.toString())))
            .andExpect(jsonPath("$.[*].reqResType").value(hasItem(DEFAULT_REQ_RES_TYPE.toString())))
            .andExpect(jsonPath("$.[*].mti").value(hasItem(DEFAULT_MTI)))
            .andExpect(jsonPath("$.[*].de001").value(hasItem(DEFAULT_DE_001.toString())))
            .andExpect(jsonPath("$.[*].de002").value(hasItem(DEFAULT_DE_002.toString())))
            .andExpect(jsonPath("$.[*].de003").value(hasItem(DEFAULT_DE_003.toString())))
            .andExpect(jsonPath("$.[*].de004").value(hasItem(DEFAULT_DE_004.toString())))
            .andExpect(jsonPath("$.[*].de005").value(hasItem(DEFAULT_DE_005.toString())))
            .andExpect(jsonPath("$.[*].de006").value(hasItem(DEFAULT_DE_006.toString())))
            .andExpect(jsonPath("$.[*].de007").value(hasItem(DEFAULT_DE_007.toString())))
            .andExpect(jsonPath("$.[*].de008").value(hasItem(DEFAULT_DE_008.toString())))
            .andExpect(jsonPath("$.[*].de009").value(hasItem(DEFAULT_DE_009.toString())))
            .andExpect(jsonPath("$.[*].de010").value(hasItem(DEFAULT_DE_010.toString())))
            .andExpect(jsonPath("$.[*].de011").value(hasItem(DEFAULT_DE_011.toString())))
            .andExpect(jsonPath("$.[*].de012").value(hasItem(DEFAULT_DE_012.toString())))
            .andExpect(jsonPath("$.[*].de013").value(hasItem(DEFAULT_DE_013.toString())))
            .andExpect(jsonPath("$.[*].de014").value(hasItem(DEFAULT_DE_014.toString())))
            .andExpect(jsonPath("$.[*].de015").value(hasItem(DEFAULT_DE_015.toString())))
            .andExpect(jsonPath("$.[*].de016").value(hasItem(DEFAULT_DE_016.toString())))
            .andExpect(jsonPath("$.[*].de017").value(hasItem(DEFAULT_DE_017.toString())))
            .andExpect(jsonPath("$.[*].de018").value(hasItem(DEFAULT_DE_018.toString())))
            .andExpect(jsonPath("$.[*].de019").value(hasItem(DEFAULT_DE_019.toString())))
            .andExpect(jsonPath("$.[*].de020").value(hasItem(DEFAULT_DE_020.toString())))
            .andExpect(jsonPath("$.[*].de021").value(hasItem(DEFAULT_DE_021.toString())))
            .andExpect(jsonPath("$.[*].de022").value(hasItem(DEFAULT_DE_022.toString())))
            .andExpect(jsonPath("$.[*].de023").value(hasItem(DEFAULT_DE_023.toString())))
            .andExpect(jsonPath("$.[*].de024").value(hasItem(DEFAULT_DE_024.toString())))
            .andExpect(jsonPath("$.[*].de025").value(hasItem(DEFAULT_DE_025.toString())))
            .andExpect(jsonPath("$.[*].de026").value(hasItem(DEFAULT_DE_026.toString())))
            .andExpect(jsonPath("$.[*].de027").value(hasItem(DEFAULT_DE_027.toString())))
            .andExpect(jsonPath("$.[*].de028").value(hasItem(DEFAULT_DE_028.toString())))
            .andExpect(jsonPath("$.[*].de029").value(hasItem(DEFAULT_DE_029.toString())))
            .andExpect(jsonPath("$.[*].de030").value(hasItem(DEFAULT_DE_030.toString())))
            .andExpect(jsonPath("$.[*].de031").value(hasItem(DEFAULT_DE_031.toString())))
            .andExpect(jsonPath("$.[*].de032").value(hasItem(DEFAULT_DE_032.toString())))
            .andExpect(jsonPath("$.[*].de033").value(hasItem(DEFAULT_DE_033.toString())))
            .andExpect(jsonPath("$.[*].de034").value(hasItem(DEFAULT_DE_034.toString())))
            .andExpect(jsonPath("$.[*].de035").value(hasItem(DEFAULT_DE_035.toString())))
            .andExpect(jsonPath("$.[*].de036").value(hasItem(DEFAULT_DE_036.toString())))
            .andExpect(jsonPath("$.[*].de037").value(hasItem(DEFAULT_DE_037.toString())))
            .andExpect(jsonPath("$.[*].de038").value(hasItem(DEFAULT_DE_038.toString())))
            .andExpect(jsonPath("$.[*].de039").value(hasItem(DEFAULT_DE_039.toString())))
            .andExpect(jsonPath("$.[*].de040").value(hasItem(DEFAULT_DE_040.toString())))
            .andExpect(jsonPath("$.[*].de041").value(hasItem(DEFAULT_DE_041.toString())))
            .andExpect(jsonPath("$.[*].de042").value(hasItem(DEFAULT_DE_042.toString())))
            .andExpect(jsonPath("$.[*].de043").value(hasItem(DEFAULT_DE_043.toString())))
            .andExpect(jsonPath("$.[*].de044").value(hasItem(DEFAULT_DE_044.toString())))
            .andExpect(jsonPath("$.[*].de045").value(hasItem(DEFAULT_DE_045.toString())))
            .andExpect(jsonPath("$.[*].de046").value(hasItem(DEFAULT_DE_046.toString())))
            .andExpect(jsonPath("$.[*].de047").value(hasItem(DEFAULT_DE_047.toString())))
            .andExpect(jsonPath("$.[*].de048").value(hasItem(DEFAULT_DE_048.toString())))
            .andExpect(jsonPath("$.[*].de049").value(hasItem(DEFAULT_DE_049.toString())))
            .andExpect(jsonPath("$.[*].de050").value(hasItem(DEFAULT_DE_050.toString())))
            .andExpect(jsonPath("$.[*].de051").value(hasItem(DEFAULT_DE_051.toString())))
            .andExpect(jsonPath("$.[*].de052").value(hasItem(DEFAULT_DE_052.toString())))
            .andExpect(jsonPath("$.[*].de053").value(hasItem(DEFAULT_DE_053.toString())))
            .andExpect(jsonPath("$.[*].de054").value(hasItem(DEFAULT_DE_054.toString())))
            .andExpect(jsonPath("$.[*].de055").value(hasItem(DEFAULT_DE_055.toString())))
            .andExpect(jsonPath("$.[*].de056").value(hasItem(DEFAULT_DE_056.toString())))
            .andExpect(jsonPath("$.[*].de057").value(hasItem(DEFAULT_DE_057.toString())))
            .andExpect(jsonPath("$.[*].de058").value(hasItem(DEFAULT_DE_058.toString())))
            .andExpect(jsonPath("$.[*].de059").value(hasItem(DEFAULT_DE_059.toString())))
            .andExpect(jsonPath("$.[*].de060").value(hasItem(DEFAULT_DE_060.toString())))
            .andExpect(jsonPath("$.[*].de061").value(hasItem(DEFAULT_DE_061.toString())))
            .andExpect(jsonPath("$.[*].de062").value(hasItem(DEFAULT_DE_062.toString())))
            .andExpect(jsonPath("$.[*].de063").value(hasItem(DEFAULT_DE_063.toString())))
            .andExpect(jsonPath("$.[*].de064").value(hasItem(DEFAULT_DE_064.toString())))
            .andExpect(jsonPath("$.[*].de065").value(hasItem(DEFAULT_DE_065.toString())))
            .andExpect(jsonPath("$.[*].de066").value(hasItem(DEFAULT_DE_066.toString())))
            .andExpect(jsonPath("$.[*].de067").value(hasItem(DEFAULT_DE_067.toString())))
            .andExpect(jsonPath("$.[*].de068").value(hasItem(DEFAULT_DE_068.toString())))
            .andExpect(jsonPath("$.[*].de069").value(hasItem(DEFAULT_DE_069.toString())))
            .andExpect(jsonPath("$.[*].de070").value(hasItem(DEFAULT_DE_070.toString())))
            .andExpect(jsonPath("$.[*].de071").value(hasItem(DEFAULT_DE_071.toString())))
            .andExpect(jsonPath("$.[*].de072").value(hasItem(DEFAULT_DE_072.toString())))
            .andExpect(jsonPath("$.[*].de073").value(hasItem(DEFAULT_DE_073.toString())))
            .andExpect(jsonPath("$.[*].de074").value(hasItem(DEFAULT_DE_074.toString())))
            .andExpect(jsonPath("$.[*].de075").value(hasItem(DEFAULT_DE_075.toString())))
            .andExpect(jsonPath("$.[*].de076").value(hasItem(DEFAULT_DE_076.toString())))
            .andExpect(jsonPath("$.[*].de077").value(hasItem(DEFAULT_DE_077.toString())))
            .andExpect(jsonPath("$.[*].de078").value(hasItem(DEFAULT_DE_078.toString())))
            .andExpect(jsonPath("$.[*].de079").value(hasItem(DEFAULT_DE_079.toString())))
            .andExpect(jsonPath("$.[*].de080").value(hasItem(DEFAULT_DE_080.toString())))
            .andExpect(jsonPath("$.[*].de081").value(hasItem(DEFAULT_DE_081.toString())))
            .andExpect(jsonPath("$.[*].de082").value(hasItem(DEFAULT_DE_082.toString())))
            .andExpect(jsonPath("$.[*].de083").value(hasItem(DEFAULT_DE_083.toString())))
            .andExpect(jsonPath("$.[*].de084").value(hasItem(DEFAULT_DE_084.toString())))
            .andExpect(jsonPath("$.[*].de085").value(hasItem(DEFAULT_DE_085.toString())))
            .andExpect(jsonPath("$.[*].de086").value(hasItem(DEFAULT_DE_086.toString())))
            .andExpect(jsonPath("$.[*].de087").value(hasItem(DEFAULT_DE_087.toString())))
            .andExpect(jsonPath("$.[*].de088").value(hasItem(DEFAULT_DE_088.toString())))
            .andExpect(jsonPath("$.[*].de089").value(hasItem(DEFAULT_DE_089.toString())))
            .andExpect(jsonPath("$.[*].de090").value(hasItem(DEFAULT_DE_090.toString())))
            .andExpect(jsonPath("$.[*].de091").value(hasItem(DEFAULT_DE_091.toString())))
            .andExpect(jsonPath("$.[*].de092").value(hasItem(DEFAULT_DE_092.toString())))
            .andExpect(jsonPath("$.[*].de093").value(hasItem(DEFAULT_DE_093.toString())))
            .andExpect(jsonPath("$.[*].de094").value(hasItem(DEFAULT_DE_094.toString())))
            .andExpect(jsonPath("$.[*].de095").value(hasItem(DEFAULT_DE_095.toString())))
            .andExpect(jsonPath("$.[*].de096").value(hasItem(DEFAULT_DE_096.toString())))
            .andExpect(jsonPath("$.[*].de097").value(hasItem(DEFAULT_DE_097.toString())))
            .andExpect(jsonPath("$.[*].de098").value(hasItem(DEFAULT_DE_098.toString())))
            .andExpect(jsonPath("$.[*].de099").value(hasItem(DEFAULT_DE_099.toString())))
            .andExpect(jsonPath("$.[*].de100").value(hasItem(DEFAULT_DE_100.toString())))
            .andExpect(jsonPath("$.[*].de101").value(hasItem(DEFAULT_DE_101.toString())))
            .andExpect(jsonPath("$.[*].de102").value(hasItem(DEFAULT_DE_102.toString())))
            .andExpect(jsonPath("$.[*].de103").value(hasItem(DEFAULT_DE_103.toString())))
            .andExpect(jsonPath("$.[*].de104").value(hasItem(DEFAULT_DE_104.toString())))
            .andExpect(jsonPath("$.[*].de105").value(hasItem(DEFAULT_DE_105.toString())))
            .andExpect(jsonPath("$.[*].de106").value(hasItem(DEFAULT_DE_106.toString())))
            .andExpect(jsonPath("$.[*].de107").value(hasItem(DEFAULT_DE_107.toString())))
            .andExpect(jsonPath("$.[*].de108").value(hasItem(DEFAULT_DE_108.toString())))
            .andExpect(jsonPath("$.[*].de109").value(hasItem(DEFAULT_DE_109.toString())))
            .andExpect(jsonPath("$.[*].de110").value(hasItem(DEFAULT_DE_110.toString())))
            .andExpect(jsonPath("$.[*].de111").value(hasItem(DEFAULT_DE_111.toString())))
            .andExpect(jsonPath("$.[*].de112").value(hasItem(DEFAULT_DE_112.toString())))
            .andExpect(jsonPath("$.[*].de113").value(hasItem(DEFAULT_DE_113.toString())))
            .andExpect(jsonPath("$.[*].de114").value(hasItem(DEFAULT_DE_114.toString())))
            .andExpect(jsonPath("$.[*].de115").value(hasItem(DEFAULT_DE_115.toString())))
            .andExpect(jsonPath("$.[*].de116").value(hasItem(DEFAULT_DE_116.toString())))
            .andExpect(jsonPath("$.[*].de117").value(hasItem(DEFAULT_DE_117.toString())))
            .andExpect(jsonPath("$.[*].de118").value(hasItem(DEFAULT_DE_118.toString())))
            .andExpect(jsonPath("$.[*].de119").value(hasItem(DEFAULT_DE_119.toString())))
            .andExpect(jsonPath("$.[*].de120").value(hasItem(DEFAULT_DE_120.toString())))
            .andExpect(jsonPath("$.[*].de121").value(hasItem(DEFAULT_DE_121.toString())))
            .andExpect(jsonPath("$.[*].de122").value(hasItem(DEFAULT_DE_122.toString())))
            .andExpect(jsonPath("$.[*].de123").value(hasItem(DEFAULT_DE_123.toString())))
            .andExpect(jsonPath("$.[*].de124").value(hasItem(DEFAULT_DE_124.toString())))
            .andExpect(jsonPath("$.[*].de125").value(hasItem(DEFAULT_DE_125.toString())))
            .andExpect(jsonPath("$.[*].de126").value(hasItem(DEFAULT_DE_126.toString())))
            .andExpect(jsonPath("$.[*].de127").value(hasItem(DEFAULT_DE_127.toString())))
            .andExpect(jsonPath("$.[*].de128").value(hasItem(DEFAULT_DE_128.toString())));
    }
    
    @Test
    @Transactional
    public void getTestCase() throws Exception {
        // Initialize the database
        testCaseRepository.saveAndFlush(testCase);

        // Get the testCase
        restTestCaseMockMvc.perform(get("/api/test-cases/{id}", testCase.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(testCase.getId().intValue()))
            .andExpect(jsonPath("$.caseDescription").value(DEFAULT_CASE_DESCRIPTION))
            .andExpect(jsonPath("$.psdnType").value(DEFAULT_PSDN_TYPE.toString()))
            .andExpect(jsonPath("$.reqResType").value(DEFAULT_REQ_RES_TYPE.toString()))
            .andExpect(jsonPath("$.mti").value(DEFAULT_MTI))
            .andExpect(jsonPath("$.de001").value(DEFAULT_DE_001.toString()))
            .andExpect(jsonPath("$.de002").value(DEFAULT_DE_002.toString()))
            .andExpect(jsonPath("$.de003").value(DEFAULT_DE_003.toString()))
            .andExpect(jsonPath("$.de004").value(DEFAULT_DE_004.toString()))
            .andExpect(jsonPath("$.de005").value(DEFAULT_DE_005.toString()))
            .andExpect(jsonPath("$.de006").value(DEFAULT_DE_006.toString()))
            .andExpect(jsonPath("$.de007").value(DEFAULT_DE_007.toString()))
            .andExpect(jsonPath("$.de008").value(DEFAULT_DE_008.toString()))
            .andExpect(jsonPath("$.de009").value(DEFAULT_DE_009.toString()))
            .andExpect(jsonPath("$.de010").value(DEFAULT_DE_010.toString()))
            .andExpect(jsonPath("$.de011").value(DEFAULT_DE_011.toString()))
            .andExpect(jsonPath("$.de012").value(DEFAULT_DE_012.toString()))
            .andExpect(jsonPath("$.de013").value(DEFAULT_DE_013.toString()))
            .andExpect(jsonPath("$.de014").value(DEFAULT_DE_014.toString()))
            .andExpect(jsonPath("$.de015").value(DEFAULT_DE_015.toString()))
            .andExpect(jsonPath("$.de016").value(DEFAULT_DE_016.toString()))
            .andExpect(jsonPath("$.de017").value(DEFAULT_DE_017.toString()))
            .andExpect(jsonPath("$.de018").value(DEFAULT_DE_018.toString()))
            .andExpect(jsonPath("$.de019").value(DEFAULT_DE_019.toString()))
            .andExpect(jsonPath("$.de020").value(DEFAULT_DE_020.toString()))
            .andExpect(jsonPath("$.de021").value(DEFAULT_DE_021.toString()))
            .andExpect(jsonPath("$.de022").value(DEFAULT_DE_022.toString()))
            .andExpect(jsonPath("$.de023").value(DEFAULT_DE_023.toString()))
            .andExpect(jsonPath("$.de024").value(DEFAULT_DE_024.toString()))
            .andExpect(jsonPath("$.de025").value(DEFAULT_DE_025.toString()))
            .andExpect(jsonPath("$.de026").value(DEFAULT_DE_026.toString()))
            .andExpect(jsonPath("$.de027").value(DEFAULT_DE_027.toString()))
            .andExpect(jsonPath("$.de028").value(DEFAULT_DE_028.toString()))
            .andExpect(jsonPath("$.de029").value(DEFAULT_DE_029.toString()))
            .andExpect(jsonPath("$.de030").value(DEFAULT_DE_030.toString()))
            .andExpect(jsonPath("$.de031").value(DEFAULT_DE_031.toString()))
            .andExpect(jsonPath("$.de032").value(DEFAULT_DE_032.toString()))
            .andExpect(jsonPath("$.de033").value(DEFAULT_DE_033.toString()))
            .andExpect(jsonPath("$.de034").value(DEFAULT_DE_034.toString()))
            .andExpect(jsonPath("$.de035").value(DEFAULT_DE_035.toString()))
            .andExpect(jsonPath("$.de036").value(DEFAULT_DE_036.toString()))
            .andExpect(jsonPath("$.de037").value(DEFAULT_DE_037.toString()))
            .andExpect(jsonPath("$.de038").value(DEFAULT_DE_038.toString()))
            .andExpect(jsonPath("$.de039").value(DEFAULT_DE_039.toString()))
            .andExpect(jsonPath("$.de040").value(DEFAULT_DE_040.toString()))
            .andExpect(jsonPath("$.de041").value(DEFAULT_DE_041.toString()))
            .andExpect(jsonPath("$.de042").value(DEFAULT_DE_042.toString()))
            .andExpect(jsonPath("$.de043").value(DEFAULT_DE_043.toString()))
            .andExpect(jsonPath("$.de044").value(DEFAULT_DE_044.toString()))
            .andExpect(jsonPath("$.de045").value(DEFAULT_DE_045.toString()))
            .andExpect(jsonPath("$.de046").value(DEFAULT_DE_046.toString()))
            .andExpect(jsonPath("$.de047").value(DEFAULT_DE_047.toString()))
            .andExpect(jsonPath("$.de048").value(DEFAULT_DE_048.toString()))
            .andExpect(jsonPath("$.de049").value(DEFAULT_DE_049.toString()))
            .andExpect(jsonPath("$.de050").value(DEFAULT_DE_050.toString()))
            .andExpect(jsonPath("$.de051").value(DEFAULT_DE_051.toString()))
            .andExpect(jsonPath("$.de052").value(DEFAULT_DE_052.toString()))
            .andExpect(jsonPath("$.de053").value(DEFAULT_DE_053.toString()))
            .andExpect(jsonPath("$.de054").value(DEFAULT_DE_054.toString()))
            .andExpect(jsonPath("$.de055").value(DEFAULT_DE_055.toString()))
            .andExpect(jsonPath("$.de056").value(DEFAULT_DE_056.toString()))
            .andExpect(jsonPath("$.de057").value(DEFAULT_DE_057.toString()))
            .andExpect(jsonPath("$.de058").value(DEFAULT_DE_058.toString()))
            .andExpect(jsonPath("$.de059").value(DEFAULT_DE_059.toString()))
            .andExpect(jsonPath("$.de060").value(DEFAULT_DE_060.toString()))
            .andExpect(jsonPath("$.de061").value(DEFAULT_DE_061.toString()))
            .andExpect(jsonPath("$.de062").value(DEFAULT_DE_062.toString()))
            .andExpect(jsonPath("$.de063").value(DEFAULT_DE_063.toString()))
            .andExpect(jsonPath("$.de064").value(DEFAULT_DE_064.toString()))
            .andExpect(jsonPath("$.de065").value(DEFAULT_DE_065.toString()))
            .andExpect(jsonPath("$.de066").value(DEFAULT_DE_066.toString()))
            .andExpect(jsonPath("$.de067").value(DEFAULT_DE_067.toString()))
            .andExpect(jsonPath("$.de068").value(DEFAULT_DE_068.toString()))
            .andExpect(jsonPath("$.de069").value(DEFAULT_DE_069.toString()))
            .andExpect(jsonPath("$.de070").value(DEFAULT_DE_070.toString()))
            .andExpect(jsonPath("$.de071").value(DEFAULT_DE_071.toString()))
            .andExpect(jsonPath("$.de072").value(DEFAULT_DE_072.toString()))
            .andExpect(jsonPath("$.de073").value(DEFAULT_DE_073.toString()))
            .andExpect(jsonPath("$.de074").value(DEFAULT_DE_074.toString()))
            .andExpect(jsonPath("$.de075").value(DEFAULT_DE_075.toString()))
            .andExpect(jsonPath("$.de076").value(DEFAULT_DE_076.toString()))
            .andExpect(jsonPath("$.de077").value(DEFAULT_DE_077.toString()))
            .andExpect(jsonPath("$.de078").value(DEFAULT_DE_078.toString()))
            .andExpect(jsonPath("$.de079").value(DEFAULT_DE_079.toString()))
            .andExpect(jsonPath("$.de080").value(DEFAULT_DE_080.toString()))
            .andExpect(jsonPath("$.de081").value(DEFAULT_DE_081.toString()))
            .andExpect(jsonPath("$.de082").value(DEFAULT_DE_082.toString()))
            .andExpect(jsonPath("$.de083").value(DEFAULT_DE_083.toString()))
            .andExpect(jsonPath("$.de084").value(DEFAULT_DE_084.toString()))
            .andExpect(jsonPath("$.de085").value(DEFAULT_DE_085.toString()))
            .andExpect(jsonPath("$.de086").value(DEFAULT_DE_086.toString()))
            .andExpect(jsonPath("$.de087").value(DEFAULT_DE_087.toString()))
            .andExpect(jsonPath("$.de088").value(DEFAULT_DE_088.toString()))
            .andExpect(jsonPath("$.de089").value(DEFAULT_DE_089.toString()))
            .andExpect(jsonPath("$.de090").value(DEFAULT_DE_090.toString()))
            .andExpect(jsonPath("$.de091").value(DEFAULT_DE_091.toString()))
            .andExpect(jsonPath("$.de092").value(DEFAULT_DE_092.toString()))
            .andExpect(jsonPath("$.de093").value(DEFAULT_DE_093.toString()))
            .andExpect(jsonPath("$.de094").value(DEFAULT_DE_094.toString()))
            .andExpect(jsonPath("$.de095").value(DEFAULT_DE_095.toString()))
            .andExpect(jsonPath("$.de096").value(DEFAULT_DE_096.toString()))
            .andExpect(jsonPath("$.de097").value(DEFAULT_DE_097.toString()))
            .andExpect(jsonPath("$.de098").value(DEFAULT_DE_098.toString()))
            .andExpect(jsonPath("$.de099").value(DEFAULT_DE_099.toString()))
            .andExpect(jsonPath("$.de100").value(DEFAULT_DE_100.toString()))
            .andExpect(jsonPath("$.de101").value(DEFAULT_DE_101.toString()))
            .andExpect(jsonPath("$.de102").value(DEFAULT_DE_102.toString()))
            .andExpect(jsonPath("$.de103").value(DEFAULT_DE_103.toString()))
            .andExpect(jsonPath("$.de104").value(DEFAULT_DE_104.toString()))
            .andExpect(jsonPath("$.de105").value(DEFAULT_DE_105.toString()))
            .andExpect(jsonPath("$.de106").value(DEFAULT_DE_106.toString()))
            .andExpect(jsonPath("$.de107").value(DEFAULT_DE_107.toString()))
            .andExpect(jsonPath("$.de108").value(DEFAULT_DE_108.toString()))
            .andExpect(jsonPath("$.de109").value(DEFAULT_DE_109.toString()))
            .andExpect(jsonPath("$.de110").value(DEFAULT_DE_110.toString()))
            .andExpect(jsonPath("$.de111").value(DEFAULT_DE_111.toString()))
            .andExpect(jsonPath("$.de112").value(DEFAULT_DE_112.toString()))
            .andExpect(jsonPath("$.de113").value(DEFAULT_DE_113.toString()))
            .andExpect(jsonPath("$.de114").value(DEFAULT_DE_114.toString()))
            .andExpect(jsonPath("$.de115").value(DEFAULT_DE_115.toString()))
            .andExpect(jsonPath("$.de116").value(DEFAULT_DE_116.toString()))
            .andExpect(jsonPath("$.de117").value(DEFAULT_DE_117.toString()))
            .andExpect(jsonPath("$.de118").value(DEFAULT_DE_118.toString()))
            .andExpect(jsonPath("$.de119").value(DEFAULT_DE_119.toString()))
            .andExpect(jsonPath("$.de120").value(DEFAULT_DE_120.toString()))
            .andExpect(jsonPath("$.de121").value(DEFAULT_DE_121.toString()))
            .andExpect(jsonPath("$.de122").value(DEFAULT_DE_122.toString()))
            .andExpect(jsonPath("$.de123").value(DEFAULT_DE_123.toString()))
            .andExpect(jsonPath("$.de124").value(DEFAULT_DE_124.toString()))
            .andExpect(jsonPath("$.de125").value(DEFAULT_DE_125.toString()))
            .andExpect(jsonPath("$.de126").value(DEFAULT_DE_126.toString()))
            .andExpect(jsonPath("$.de127").value(DEFAULT_DE_127.toString()))
            .andExpect(jsonPath("$.de128").value(DEFAULT_DE_128.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTestCase() throws Exception {
        // Get the testCase
        restTestCaseMockMvc.perform(get("/api/test-cases/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTestCase() throws Exception {
        // Initialize the database
        testCaseRepository.saveAndFlush(testCase);

        int databaseSizeBeforeUpdate = testCaseRepository.findAll().size();

        // Update the testCase
        TestCase updatedTestCase = testCaseRepository.findById(testCase.getId()).get();
        // Disconnect from session so that the updates on updatedTestCase are not directly saved in db
        em.detach(updatedTestCase);
        updatedTestCase
            .caseDescription(UPDATED_CASE_DESCRIPTION)
            .psdnType(UPDATED_PSDN_TYPE)
            .reqResType(UPDATED_REQ_RES_TYPE)
            .mti(UPDATED_MTI)
            .de001(UPDATED_DE_001)
            .de002(UPDATED_DE_002)
            .de003(UPDATED_DE_003)
            .de004(UPDATED_DE_004)
            .de005(UPDATED_DE_005)
            .de006(UPDATED_DE_006)
            .de007(UPDATED_DE_007)
            .de008(UPDATED_DE_008)
            .de009(UPDATED_DE_009)
            .de010(UPDATED_DE_010)
            .de011(UPDATED_DE_011)
            .de012(UPDATED_DE_012)
            .de013(UPDATED_DE_013)
            .de014(UPDATED_DE_014)
            .de015(UPDATED_DE_015)
            .de016(UPDATED_DE_016)
            .de017(UPDATED_DE_017)
            .de018(UPDATED_DE_018)
            .de019(UPDATED_DE_019)
            .de020(UPDATED_DE_020)
            .de021(UPDATED_DE_021)
            .de022(UPDATED_DE_022)
            .de023(UPDATED_DE_023)
            .de024(UPDATED_DE_024)
            .de025(UPDATED_DE_025)
            .de026(UPDATED_DE_026)
            .de027(UPDATED_DE_027)
            .de028(UPDATED_DE_028)
            .de029(UPDATED_DE_029)
            .de030(UPDATED_DE_030)
            .de031(UPDATED_DE_031)
            .de032(UPDATED_DE_032)
            .de033(UPDATED_DE_033)
            .de034(UPDATED_DE_034)
            .de035(UPDATED_DE_035)
            .de036(UPDATED_DE_036)
            .de037(UPDATED_DE_037)
            .de038(UPDATED_DE_038)
            .de039(UPDATED_DE_039)
            .de040(UPDATED_DE_040)
            .de041(UPDATED_DE_041)
            .de042(UPDATED_DE_042)
            .de043(UPDATED_DE_043)
            .de044(UPDATED_DE_044)
            .de045(UPDATED_DE_045)
            .de046(UPDATED_DE_046)
            .de047(UPDATED_DE_047)
            .de048(UPDATED_DE_048)
            .de049(UPDATED_DE_049)
            .de050(UPDATED_DE_050)
            .de051(UPDATED_DE_051)
            .de052(UPDATED_DE_052)
            .de053(UPDATED_DE_053)
            .de054(UPDATED_DE_054)
            .de055(UPDATED_DE_055)
            .de056(UPDATED_DE_056)
            .de057(UPDATED_DE_057)
            .de058(UPDATED_DE_058)
            .de059(UPDATED_DE_059)
            .de060(UPDATED_DE_060)
            .de061(UPDATED_DE_061)
            .de062(UPDATED_DE_062)
            .de063(UPDATED_DE_063)
            .de064(UPDATED_DE_064)
            .de065(UPDATED_DE_065)
            .de066(UPDATED_DE_066)
            .de067(UPDATED_DE_067)
            .de068(UPDATED_DE_068)
            .de069(UPDATED_DE_069)
            .de070(UPDATED_DE_070)
            .de071(UPDATED_DE_071)
            .de072(UPDATED_DE_072)
            .de073(UPDATED_DE_073)
            .de074(UPDATED_DE_074)
            .de075(UPDATED_DE_075)
            .de076(UPDATED_DE_076)
            .de077(UPDATED_DE_077)
            .de078(UPDATED_DE_078)
            .de079(UPDATED_DE_079)
            .de080(UPDATED_DE_080)
            .de081(UPDATED_DE_081)
            .de082(UPDATED_DE_082)
            .de083(UPDATED_DE_083)
            .de084(UPDATED_DE_084)
            .de085(UPDATED_DE_085)
            .de086(UPDATED_DE_086)
            .de087(UPDATED_DE_087)
            .de088(UPDATED_DE_088)
            .de089(UPDATED_DE_089)
            .de090(UPDATED_DE_090)
            .de091(UPDATED_DE_091)
            .de092(UPDATED_DE_092)
            .de093(UPDATED_DE_093)
            .de094(UPDATED_DE_094)
            .de095(UPDATED_DE_095)
            .de096(UPDATED_DE_096)
            .de097(UPDATED_DE_097)
            .de098(UPDATED_DE_098)
            .de099(UPDATED_DE_099)
            .de100(UPDATED_DE_100)
            .de101(UPDATED_DE_101)
            .de102(UPDATED_DE_102)
            .de103(UPDATED_DE_103)
            .de104(UPDATED_DE_104)
            .de105(UPDATED_DE_105)
            .de106(UPDATED_DE_106)
            .de107(UPDATED_DE_107)
            .de108(UPDATED_DE_108)
            .de109(UPDATED_DE_109)
            .de110(UPDATED_DE_110)
            .de111(UPDATED_DE_111)
            .de112(UPDATED_DE_112)
            .de113(UPDATED_DE_113)
            .de114(UPDATED_DE_114)
            .de115(UPDATED_DE_115)
            .de116(UPDATED_DE_116)
            .de117(UPDATED_DE_117)
            .de118(UPDATED_DE_118)
            .de119(UPDATED_DE_119)
            .de120(UPDATED_DE_120)
            .de121(UPDATED_DE_121)
            .de122(UPDATED_DE_122)
            .de123(UPDATED_DE_123)
            .de124(UPDATED_DE_124)
            .de125(UPDATED_DE_125)
            .de126(UPDATED_DE_126)
            .de127(UPDATED_DE_127)
            .de128(UPDATED_DE_128);
        TestCaseDTO testCaseDTO = testCaseMapper.toDto(updatedTestCase);

        restTestCaseMockMvc.perform(put("/api/test-cases")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(testCaseDTO)))
            .andExpect(status().isOk());

        // Validate the TestCase in the database
        List<TestCase> testCaseList = testCaseRepository.findAll();
        assertThat(testCaseList).hasSize(databaseSizeBeforeUpdate);
        TestCase testTestCase = testCaseList.get(testCaseList.size() - 1);
        assertThat(testTestCase.getCaseDescription()).isEqualTo(UPDATED_CASE_DESCRIPTION);
        assertThat(testTestCase.getPsdnType()).isEqualTo(UPDATED_PSDN_TYPE);
        assertThat(testTestCase.getReqResType()).isEqualTo(UPDATED_REQ_RES_TYPE);
        assertThat(testTestCase.getMti()).isEqualTo(UPDATED_MTI);
        assertThat(testTestCase.getDe001()).isEqualTo(UPDATED_DE_001);
        assertThat(testTestCase.getDe002()).isEqualTo(UPDATED_DE_002);
        assertThat(testTestCase.getDe003()).isEqualTo(UPDATED_DE_003);
        assertThat(testTestCase.getDe004()).isEqualTo(UPDATED_DE_004);
        assertThat(testTestCase.getDe005()).isEqualTo(UPDATED_DE_005);
        assertThat(testTestCase.getDe006()).isEqualTo(UPDATED_DE_006);
        assertThat(testTestCase.getDe007()).isEqualTo(UPDATED_DE_007);
        assertThat(testTestCase.getDe008()).isEqualTo(UPDATED_DE_008);
        assertThat(testTestCase.getDe009()).isEqualTo(UPDATED_DE_009);
        assertThat(testTestCase.getDe010()).isEqualTo(UPDATED_DE_010);
        assertThat(testTestCase.getDe011()).isEqualTo(UPDATED_DE_011);
        assertThat(testTestCase.getDe012()).isEqualTo(UPDATED_DE_012);
        assertThat(testTestCase.getDe013()).isEqualTo(UPDATED_DE_013);
        assertThat(testTestCase.getDe014()).isEqualTo(UPDATED_DE_014);
        assertThat(testTestCase.getDe015()).isEqualTo(UPDATED_DE_015);
        assertThat(testTestCase.getDe016()).isEqualTo(UPDATED_DE_016);
        assertThat(testTestCase.getDe017()).isEqualTo(UPDATED_DE_017);
        assertThat(testTestCase.getDe018()).isEqualTo(UPDATED_DE_018);
        assertThat(testTestCase.getDe019()).isEqualTo(UPDATED_DE_019);
        assertThat(testTestCase.getDe020()).isEqualTo(UPDATED_DE_020);
        assertThat(testTestCase.getDe021()).isEqualTo(UPDATED_DE_021);
        assertThat(testTestCase.getDe022()).isEqualTo(UPDATED_DE_022);
        assertThat(testTestCase.getDe023()).isEqualTo(UPDATED_DE_023);
        assertThat(testTestCase.getDe024()).isEqualTo(UPDATED_DE_024);
        assertThat(testTestCase.getDe025()).isEqualTo(UPDATED_DE_025);
        assertThat(testTestCase.getDe026()).isEqualTo(UPDATED_DE_026);
        assertThat(testTestCase.getDe027()).isEqualTo(UPDATED_DE_027);
        assertThat(testTestCase.getDe028()).isEqualTo(UPDATED_DE_028);
        assertThat(testTestCase.getDe029()).isEqualTo(UPDATED_DE_029);
        assertThat(testTestCase.getDe030()).isEqualTo(UPDATED_DE_030);
        assertThat(testTestCase.getDe031()).isEqualTo(UPDATED_DE_031);
        assertThat(testTestCase.getDe032()).isEqualTo(UPDATED_DE_032);
        assertThat(testTestCase.getDe033()).isEqualTo(UPDATED_DE_033);
        assertThat(testTestCase.getDe034()).isEqualTo(UPDATED_DE_034);
        assertThat(testTestCase.getDe035()).isEqualTo(UPDATED_DE_035);
        assertThat(testTestCase.getDe036()).isEqualTo(UPDATED_DE_036);
        assertThat(testTestCase.getDe037()).isEqualTo(UPDATED_DE_037);
        assertThat(testTestCase.getDe038()).isEqualTo(UPDATED_DE_038);
        assertThat(testTestCase.getDe039()).isEqualTo(UPDATED_DE_039);
        assertThat(testTestCase.getDe040()).isEqualTo(UPDATED_DE_040);
        assertThat(testTestCase.getDe041()).isEqualTo(UPDATED_DE_041);
        assertThat(testTestCase.getDe042()).isEqualTo(UPDATED_DE_042);
        assertThat(testTestCase.getDe043()).isEqualTo(UPDATED_DE_043);
        assertThat(testTestCase.getDe044()).isEqualTo(UPDATED_DE_044);
        assertThat(testTestCase.getDe045()).isEqualTo(UPDATED_DE_045);
        assertThat(testTestCase.getDe046()).isEqualTo(UPDATED_DE_046);
        assertThat(testTestCase.getDe047()).isEqualTo(UPDATED_DE_047);
        assertThat(testTestCase.getDe048()).isEqualTo(UPDATED_DE_048);
        assertThat(testTestCase.getDe049()).isEqualTo(UPDATED_DE_049);
        assertThat(testTestCase.getDe050()).isEqualTo(UPDATED_DE_050);
        assertThat(testTestCase.getDe051()).isEqualTo(UPDATED_DE_051);
        assertThat(testTestCase.getDe052()).isEqualTo(UPDATED_DE_052);
        assertThat(testTestCase.getDe053()).isEqualTo(UPDATED_DE_053);
        assertThat(testTestCase.getDe054()).isEqualTo(UPDATED_DE_054);
        assertThat(testTestCase.getDe055()).isEqualTo(UPDATED_DE_055);
        assertThat(testTestCase.getDe056()).isEqualTo(UPDATED_DE_056);
        assertThat(testTestCase.getDe057()).isEqualTo(UPDATED_DE_057);
        assertThat(testTestCase.getDe058()).isEqualTo(UPDATED_DE_058);
        assertThat(testTestCase.getDe059()).isEqualTo(UPDATED_DE_059);
        assertThat(testTestCase.getDe060()).isEqualTo(UPDATED_DE_060);
        assertThat(testTestCase.getDe061()).isEqualTo(UPDATED_DE_061);
        assertThat(testTestCase.getDe062()).isEqualTo(UPDATED_DE_062);
        assertThat(testTestCase.getDe063()).isEqualTo(UPDATED_DE_063);
        assertThat(testTestCase.getDe064()).isEqualTo(UPDATED_DE_064);
        assertThat(testTestCase.getDe065()).isEqualTo(UPDATED_DE_065);
        assertThat(testTestCase.getDe066()).isEqualTo(UPDATED_DE_066);
        assertThat(testTestCase.getDe067()).isEqualTo(UPDATED_DE_067);
        assertThat(testTestCase.getDe068()).isEqualTo(UPDATED_DE_068);
        assertThat(testTestCase.getDe069()).isEqualTo(UPDATED_DE_069);
        assertThat(testTestCase.getDe070()).isEqualTo(UPDATED_DE_070);
        assertThat(testTestCase.getDe071()).isEqualTo(UPDATED_DE_071);
        assertThat(testTestCase.getDe072()).isEqualTo(UPDATED_DE_072);
        assertThat(testTestCase.getDe073()).isEqualTo(UPDATED_DE_073);
        assertThat(testTestCase.getDe074()).isEqualTo(UPDATED_DE_074);
        assertThat(testTestCase.getDe075()).isEqualTo(UPDATED_DE_075);
        assertThat(testTestCase.getDe076()).isEqualTo(UPDATED_DE_076);
        assertThat(testTestCase.getDe077()).isEqualTo(UPDATED_DE_077);
        assertThat(testTestCase.getDe078()).isEqualTo(UPDATED_DE_078);
        assertThat(testTestCase.getDe079()).isEqualTo(UPDATED_DE_079);
        assertThat(testTestCase.getDe080()).isEqualTo(UPDATED_DE_080);
        assertThat(testTestCase.getDe081()).isEqualTo(UPDATED_DE_081);
        assertThat(testTestCase.getDe082()).isEqualTo(UPDATED_DE_082);
        assertThat(testTestCase.getDe083()).isEqualTo(UPDATED_DE_083);
        assertThat(testTestCase.getDe084()).isEqualTo(UPDATED_DE_084);
        assertThat(testTestCase.getDe085()).isEqualTo(UPDATED_DE_085);
        assertThat(testTestCase.getDe086()).isEqualTo(UPDATED_DE_086);
        assertThat(testTestCase.getDe087()).isEqualTo(UPDATED_DE_087);
        assertThat(testTestCase.getDe088()).isEqualTo(UPDATED_DE_088);
        assertThat(testTestCase.getDe089()).isEqualTo(UPDATED_DE_089);
        assertThat(testTestCase.getDe090()).isEqualTo(UPDATED_DE_090);
        assertThat(testTestCase.getDe091()).isEqualTo(UPDATED_DE_091);
        assertThat(testTestCase.getDe092()).isEqualTo(UPDATED_DE_092);
        assertThat(testTestCase.getDe093()).isEqualTo(UPDATED_DE_093);
        assertThat(testTestCase.getDe094()).isEqualTo(UPDATED_DE_094);
        assertThat(testTestCase.getDe095()).isEqualTo(UPDATED_DE_095);
        assertThat(testTestCase.getDe096()).isEqualTo(UPDATED_DE_096);
        assertThat(testTestCase.getDe097()).isEqualTo(UPDATED_DE_097);
        assertThat(testTestCase.getDe098()).isEqualTo(UPDATED_DE_098);
        assertThat(testTestCase.getDe099()).isEqualTo(UPDATED_DE_099);
        assertThat(testTestCase.getDe100()).isEqualTo(UPDATED_DE_100);
        assertThat(testTestCase.getDe101()).isEqualTo(UPDATED_DE_101);
        assertThat(testTestCase.getDe102()).isEqualTo(UPDATED_DE_102);
        assertThat(testTestCase.getDe103()).isEqualTo(UPDATED_DE_103);
        assertThat(testTestCase.getDe104()).isEqualTo(UPDATED_DE_104);
        assertThat(testTestCase.getDe105()).isEqualTo(UPDATED_DE_105);
        assertThat(testTestCase.getDe106()).isEqualTo(UPDATED_DE_106);
        assertThat(testTestCase.getDe107()).isEqualTo(UPDATED_DE_107);
        assertThat(testTestCase.getDe108()).isEqualTo(UPDATED_DE_108);
        assertThat(testTestCase.getDe109()).isEqualTo(UPDATED_DE_109);
        assertThat(testTestCase.getDe110()).isEqualTo(UPDATED_DE_110);
        assertThat(testTestCase.getDe111()).isEqualTo(UPDATED_DE_111);
        assertThat(testTestCase.getDe112()).isEqualTo(UPDATED_DE_112);
        assertThat(testTestCase.getDe113()).isEqualTo(UPDATED_DE_113);
        assertThat(testTestCase.getDe114()).isEqualTo(UPDATED_DE_114);
        assertThat(testTestCase.getDe115()).isEqualTo(UPDATED_DE_115);
        assertThat(testTestCase.getDe116()).isEqualTo(UPDATED_DE_116);
        assertThat(testTestCase.getDe117()).isEqualTo(UPDATED_DE_117);
        assertThat(testTestCase.getDe118()).isEqualTo(UPDATED_DE_118);
        assertThat(testTestCase.getDe119()).isEqualTo(UPDATED_DE_119);
        assertThat(testTestCase.getDe120()).isEqualTo(UPDATED_DE_120);
        assertThat(testTestCase.getDe121()).isEqualTo(UPDATED_DE_121);
        assertThat(testTestCase.getDe122()).isEqualTo(UPDATED_DE_122);
        assertThat(testTestCase.getDe123()).isEqualTo(UPDATED_DE_123);
        assertThat(testTestCase.getDe124()).isEqualTo(UPDATED_DE_124);
        assertThat(testTestCase.getDe125()).isEqualTo(UPDATED_DE_125);
        assertThat(testTestCase.getDe126()).isEqualTo(UPDATED_DE_126);
        assertThat(testTestCase.getDe127()).isEqualTo(UPDATED_DE_127);
        assertThat(testTestCase.getDe128()).isEqualTo(UPDATED_DE_128);
    }

    @Test
    @Transactional
    public void updateNonExistingTestCase() throws Exception {
        int databaseSizeBeforeUpdate = testCaseRepository.findAll().size();

        // Create the TestCase
        TestCaseDTO testCaseDTO = testCaseMapper.toDto(testCase);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTestCaseMockMvc.perform(put("/api/test-cases")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(testCaseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TestCase in the database
        List<TestCase> testCaseList = testCaseRepository.findAll();
        assertThat(testCaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTestCase() throws Exception {
        // Initialize the database
        testCaseRepository.saveAndFlush(testCase);

        int databaseSizeBeforeDelete = testCaseRepository.findAll().size();

        // Delete the testCase
        restTestCaseMockMvc.perform(delete("/api/test-cases/{id}", testCase.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TestCase> testCaseList = testCaseRepository.findAll();
        assertThat(testCaseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
