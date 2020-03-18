package com.org.simulator.web.rest;

import com.org.simulator.SimulatorApp;
import com.org.simulator.domain.MtiConfig;
import com.org.simulator.repository.MtiConfigRepository;
import com.org.simulator.service.MtiConfigService;
import com.org.simulator.service.dto.MtiConfigDTO;
import com.org.simulator.service.mapper.MtiConfigMapper;
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
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.org.simulator.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.org.simulator.domain.enumeration.TranType;
import com.org.simulator.domain.enumeration.IsoVersions;
/**
 * Integration tests for the {@link MtiConfigResource} REST controller.
 */
@SpringBootTest(classes = SimulatorApp.class)
public class MtiConfigResourceIT {

    private static final String DEFAULT_MTI_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_MTI_DESCRIPTION = "BBBBBBBBBB";

    private static final TranType DEFAULT_TN_TYPE = TranType.NMM;
    private static final TranType UPDATED_TN_TYPE = TranType.TRANSACTIONS;

    private static final IsoVersions DEFAULT_ISO_TYPE = IsoVersions.GCCISO87;
    private static final IsoVersions UPDATED_ISO_TYPE = IsoVersions.MBIISO93;

    private static final String DEFAULT_MTI = "AAAAAAAAAA";
    private static final String UPDATED_MTI = "BBBBBBBBBB";

    private static final String DEFAULT_REPEAT_MTI = "AAAAAAAAAA";
    private static final String UPDATED_REPEAT_MTI = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_MTI = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_MTI = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_REPEAT_MTI = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_REPEAT_MTI = "BBBBBBBBBB";

    private static final String DEFAULT_NMM_IDENTIFIER_DE = "AAAAAAAAAA";
    private static final String UPDATED_NMM_IDENTIFIER_DE = "BBBBBBBBBB";

    private static final String DEFAULT_NMM_IDENTIFIER_DE_VAL = "AAAAAAAAAA";
    private static final String UPDATED_NMM_IDENTIFIER_DE_VAL = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_IDENTIFIER_DE = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_IDENTIFIER_DE = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_IDENTIFIER_DE_VAL = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_IDENTIFIER_DE_VAL = "BBBBBBBBBB";

    @Autowired
    private MtiConfigRepository mtiConfigRepository;

    @Autowired
    private MtiConfigMapper mtiConfigMapper;

    @Autowired
    private MtiConfigService mtiConfigService;

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

    private MockMvc restMtiConfigMockMvc;

    private MtiConfig mtiConfig;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MtiConfigResource mtiConfigResource = new MtiConfigResource(mtiConfigService);
        this.restMtiConfigMockMvc = MockMvcBuilders.standaloneSetup(mtiConfigResource)
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
    public static MtiConfig createEntity(EntityManager em) {
        MtiConfig mtiConfig = new MtiConfig()
            .mtiDescription(DEFAULT_MTI_DESCRIPTION)
            .tnType(DEFAULT_TN_TYPE)
            .isoType(DEFAULT_ISO_TYPE)
            .mti(DEFAULT_MTI)
            .repeatMti(DEFAULT_REPEAT_MTI)
            .responseMti(DEFAULT_RESPONSE_MTI)
            .responseRepeatMti(DEFAULT_RESPONSE_REPEAT_MTI)
            .nmmIdentifierDe(DEFAULT_NMM_IDENTIFIER_DE)
            .nmmIdentifierDeVal(DEFAULT_NMM_IDENTIFIER_DE_VAL)
            .responseIdentifierDe(DEFAULT_RESPONSE_IDENTIFIER_DE)
            .responseIdentifierDeVal(DEFAULT_RESPONSE_IDENTIFIER_DE_VAL);
        return mtiConfig;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MtiConfig createUpdatedEntity(EntityManager em) {
        MtiConfig mtiConfig = new MtiConfig()
            .mtiDescription(UPDATED_MTI_DESCRIPTION)
            .tnType(UPDATED_TN_TYPE)
            .isoType(UPDATED_ISO_TYPE)
            .mti(UPDATED_MTI)
            .repeatMti(UPDATED_REPEAT_MTI)
            .responseMti(UPDATED_RESPONSE_MTI)
            .responseRepeatMti(UPDATED_RESPONSE_REPEAT_MTI)
            .nmmIdentifierDe(UPDATED_NMM_IDENTIFIER_DE)
            .nmmIdentifierDeVal(UPDATED_NMM_IDENTIFIER_DE_VAL)
            .responseIdentifierDe(UPDATED_RESPONSE_IDENTIFIER_DE)
            .responseIdentifierDeVal(UPDATED_RESPONSE_IDENTIFIER_DE_VAL);
        return mtiConfig;
    }

    @BeforeEach
    public void initTest() {
        mtiConfig = createEntity(em);
    }

    @Test
    @Transactional
    public void createMtiConfig() throws Exception {
        int databaseSizeBeforeCreate = mtiConfigRepository.findAll().size();

        // Create the MtiConfig
        MtiConfigDTO mtiConfigDTO = mtiConfigMapper.toDto(mtiConfig);
        restMtiConfigMockMvc.perform(post("/api/mti-configs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mtiConfigDTO)))
            .andExpect(status().isCreated());

        // Validate the MtiConfig in the database
        List<MtiConfig> mtiConfigList = mtiConfigRepository.findAll();
        assertThat(mtiConfigList).hasSize(databaseSizeBeforeCreate + 1);
        MtiConfig testMtiConfig = mtiConfigList.get(mtiConfigList.size() - 1);
        assertThat(testMtiConfig.getMtiDescription()).isEqualTo(DEFAULT_MTI_DESCRIPTION);
        assertThat(testMtiConfig.getTnType()).isEqualTo(DEFAULT_TN_TYPE);
        assertThat(testMtiConfig.getIsoType()).isEqualTo(DEFAULT_ISO_TYPE);
        assertThat(testMtiConfig.getMti()).isEqualTo(DEFAULT_MTI);
        assertThat(testMtiConfig.getRepeatMti()).isEqualTo(DEFAULT_REPEAT_MTI);
        assertThat(testMtiConfig.getResponseMti()).isEqualTo(DEFAULT_RESPONSE_MTI);
        assertThat(testMtiConfig.getResponseRepeatMti()).isEqualTo(DEFAULT_RESPONSE_REPEAT_MTI);
        assertThat(testMtiConfig.getNmmIdentifierDe()).isEqualTo(DEFAULT_NMM_IDENTIFIER_DE);
        assertThat(testMtiConfig.getNmmIdentifierDeVal()).isEqualTo(DEFAULT_NMM_IDENTIFIER_DE_VAL);
        assertThat(testMtiConfig.getResponseIdentifierDe()).isEqualTo(DEFAULT_RESPONSE_IDENTIFIER_DE);
        assertThat(testMtiConfig.getResponseIdentifierDeVal()).isEqualTo(DEFAULT_RESPONSE_IDENTIFIER_DE_VAL);
    }

    @Test
    @Transactional
    public void createMtiConfigWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mtiConfigRepository.findAll().size();

        // Create the MtiConfig with an existing ID
        mtiConfig.setId(1L);
        MtiConfigDTO mtiConfigDTO = mtiConfigMapper.toDto(mtiConfig);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMtiConfigMockMvc.perform(post("/api/mti-configs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mtiConfigDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MtiConfig in the database
        List<MtiConfig> mtiConfigList = mtiConfigRepository.findAll();
        assertThat(mtiConfigList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMtiDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = mtiConfigRepository.findAll().size();
        // set the field null
        mtiConfig.setMtiDescription(null);

        // Create the MtiConfig, which fails.
        MtiConfigDTO mtiConfigDTO = mtiConfigMapper.toDto(mtiConfig);

        restMtiConfigMockMvc.perform(post("/api/mti-configs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mtiConfigDTO)))
            .andExpect(status().isBadRequest());

        List<MtiConfig> mtiConfigList = mtiConfigRepository.findAll();
        assertThat(mtiConfigList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTnTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mtiConfigRepository.findAll().size();
        // set the field null
        mtiConfig.setTnType(null);

        // Create the MtiConfig, which fails.
        MtiConfigDTO mtiConfigDTO = mtiConfigMapper.toDto(mtiConfig);

        restMtiConfigMockMvc.perform(post("/api/mti-configs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mtiConfigDTO)))
            .andExpect(status().isBadRequest());

        List<MtiConfig> mtiConfigList = mtiConfigRepository.findAll();
        assertThat(mtiConfigList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMtiIsRequired() throws Exception {
        int databaseSizeBeforeTest = mtiConfigRepository.findAll().size();
        // set the field null
        mtiConfig.setMti(null);

        // Create the MtiConfig, which fails.
        MtiConfigDTO mtiConfigDTO = mtiConfigMapper.toDto(mtiConfig);

        restMtiConfigMockMvc.perform(post("/api/mti-configs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mtiConfigDTO)))
            .andExpect(status().isBadRequest());

        List<MtiConfig> mtiConfigList = mtiConfigRepository.findAll();
        assertThat(mtiConfigList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRepeatMtiIsRequired() throws Exception {
        int databaseSizeBeforeTest = mtiConfigRepository.findAll().size();
        // set the field null
        mtiConfig.setRepeatMti(null);

        // Create the MtiConfig, which fails.
        MtiConfigDTO mtiConfigDTO = mtiConfigMapper.toDto(mtiConfig);

        restMtiConfigMockMvc.perform(post("/api/mti-configs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mtiConfigDTO)))
            .andExpect(status().isBadRequest());

        List<MtiConfig> mtiConfigList = mtiConfigRepository.findAll();
        assertThat(mtiConfigList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkResponseMtiIsRequired() throws Exception {
        int databaseSizeBeforeTest = mtiConfigRepository.findAll().size();
        // set the field null
        mtiConfig.setResponseMti(null);

        // Create the MtiConfig, which fails.
        MtiConfigDTO mtiConfigDTO = mtiConfigMapper.toDto(mtiConfig);

        restMtiConfigMockMvc.perform(post("/api/mti-configs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mtiConfigDTO)))
            .andExpect(status().isBadRequest());

        List<MtiConfig> mtiConfigList = mtiConfigRepository.findAll();
        assertThat(mtiConfigList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkResponseRepeatMtiIsRequired() throws Exception {
        int databaseSizeBeforeTest = mtiConfigRepository.findAll().size();
        // set the field null
        mtiConfig.setResponseRepeatMti(null);

        // Create the MtiConfig, which fails.
        MtiConfigDTO mtiConfigDTO = mtiConfigMapper.toDto(mtiConfig);

        restMtiConfigMockMvc.perform(post("/api/mti-configs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mtiConfigDTO)))
            .andExpect(status().isBadRequest());

        List<MtiConfig> mtiConfigList = mtiConfigRepository.findAll();
        assertThat(mtiConfigList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkResponseIdentifierDeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mtiConfigRepository.findAll().size();
        // set the field null
        mtiConfig.setResponseIdentifierDe(null);

        // Create the MtiConfig, which fails.
        MtiConfigDTO mtiConfigDTO = mtiConfigMapper.toDto(mtiConfig);

        restMtiConfigMockMvc.perform(post("/api/mti-configs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mtiConfigDTO)))
            .andExpect(status().isBadRequest());

        List<MtiConfig> mtiConfigList = mtiConfigRepository.findAll();
        assertThat(mtiConfigList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkResponseIdentifierDeValIsRequired() throws Exception {
        int databaseSizeBeforeTest = mtiConfigRepository.findAll().size();
        // set the field null
        mtiConfig.setResponseIdentifierDeVal(null);

        // Create the MtiConfig, which fails.
        MtiConfigDTO mtiConfigDTO = mtiConfigMapper.toDto(mtiConfig);

        restMtiConfigMockMvc.perform(post("/api/mti-configs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mtiConfigDTO)))
            .andExpect(status().isBadRequest());

        List<MtiConfig> mtiConfigList = mtiConfigRepository.findAll();
        assertThat(mtiConfigList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMtiConfigs() throws Exception {
        // Initialize the database
        mtiConfigRepository.saveAndFlush(mtiConfig);

        // Get all the mtiConfigList
        restMtiConfigMockMvc.perform(get("/api/mti-configs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mtiConfig.getId().intValue())))
            .andExpect(jsonPath("$.[*].mtiDescription").value(hasItem(DEFAULT_MTI_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].tnType").value(hasItem(DEFAULT_TN_TYPE.toString())))
            .andExpect(jsonPath("$.[*].isoType").value(hasItem(DEFAULT_ISO_TYPE.toString())))
            .andExpect(jsonPath("$.[*].mti").value(hasItem(DEFAULT_MTI)))
            .andExpect(jsonPath("$.[*].repeatMti").value(hasItem(DEFAULT_REPEAT_MTI)))
            .andExpect(jsonPath("$.[*].responseMti").value(hasItem(DEFAULT_RESPONSE_MTI)))
            .andExpect(jsonPath("$.[*].responseRepeatMti").value(hasItem(DEFAULT_RESPONSE_REPEAT_MTI)))
            .andExpect(jsonPath("$.[*].nmmIdentifierDe").value(hasItem(DEFAULT_NMM_IDENTIFIER_DE)))
            .andExpect(jsonPath("$.[*].nmmIdentifierDeVal").value(hasItem(DEFAULT_NMM_IDENTIFIER_DE_VAL)))
            .andExpect(jsonPath("$.[*].responseIdentifierDe").value(hasItem(DEFAULT_RESPONSE_IDENTIFIER_DE)))
            .andExpect(jsonPath("$.[*].responseIdentifierDeVal").value(hasItem(DEFAULT_RESPONSE_IDENTIFIER_DE_VAL)));
    }
    
    @Test
    @Transactional
    public void getMtiConfig() throws Exception {
        // Initialize the database
        mtiConfigRepository.saveAndFlush(mtiConfig);

        // Get the mtiConfig
        restMtiConfigMockMvc.perform(get("/api/mti-configs/{id}", mtiConfig.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(mtiConfig.getId().intValue()))
            .andExpect(jsonPath("$.mtiDescription").value(DEFAULT_MTI_DESCRIPTION))
            .andExpect(jsonPath("$.tnType").value(DEFAULT_TN_TYPE.toString()))
            .andExpect(jsonPath("$.isoType").value(DEFAULT_ISO_TYPE.toString()))
            .andExpect(jsonPath("$.mti").value(DEFAULT_MTI))
            .andExpect(jsonPath("$.repeatMti").value(DEFAULT_REPEAT_MTI))
            .andExpect(jsonPath("$.responseMti").value(DEFAULT_RESPONSE_MTI))
            .andExpect(jsonPath("$.responseRepeatMti").value(DEFAULT_RESPONSE_REPEAT_MTI))
            .andExpect(jsonPath("$.nmmIdentifierDe").value(DEFAULT_NMM_IDENTIFIER_DE))
            .andExpect(jsonPath("$.nmmIdentifierDeVal").value(DEFAULT_NMM_IDENTIFIER_DE_VAL))
            .andExpect(jsonPath("$.responseIdentifierDe").value(DEFAULT_RESPONSE_IDENTIFIER_DE))
            .andExpect(jsonPath("$.responseIdentifierDeVal").value(DEFAULT_RESPONSE_IDENTIFIER_DE_VAL));
    }

    @Test
    @Transactional
    public void getNonExistingMtiConfig() throws Exception {
        // Get the mtiConfig
        restMtiConfigMockMvc.perform(get("/api/mti-configs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMtiConfig() throws Exception {
        // Initialize the database
        mtiConfigRepository.saveAndFlush(mtiConfig);

        int databaseSizeBeforeUpdate = mtiConfigRepository.findAll().size();

        // Update the mtiConfig
        MtiConfig updatedMtiConfig = mtiConfigRepository.findById(mtiConfig.getId()).get();
        // Disconnect from session so that the updates on updatedMtiConfig are not directly saved in db
        em.detach(updatedMtiConfig);
        updatedMtiConfig
            .mtiDescription(UPDATED_MTI_DESCRIPTION)
            .tnType(UPDATED_TN_TYPE)
            .isoType(UPDATED_ISO_TYPE)
            .mti(UPDATED_MTI)
            .repeatMti(UPDATED_REPEAT_MTI)
            .responseMti(UPDATED_RESPONSE_MTI)
            .responseRepeatMti(UPDATED_RESPONSE_REPEAT_MTI)
            .nmmIdentifierDe(UPDATED_NMM_IDENTIFIER_DE)
            .nmmIdentifierDeVal(UPDATED_NMM_IDENTIFIER_DE_VAL)
            .responseIdentifierDe(UPDATED_RESPONSE_IDENTIFIER_DE)
            .responseIdentifierDeVal(UPDATED_RESPONSE_IDENTIFIER_DE_VAL);
        MtiConfigDTO mtiConfigDTO = mtiConfigMapper.toDto(updatedMtiConfig);

        restMtiConfigMockMvc.perform(put("/api/mti-configs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mtiConfigDTO)))
            .andExpect(status().isOk());

        // Validate the MtiConfig in the database
        List<MtiConfig> mtiConfigList = mtiConfigRepository.findAll();
        assertThat(mtiConfigList).hasSize(databaseSizeBeforeUpdate);
        MtiConfig testMtiConfig = mtiConfigList.get(mtiConfigList.size() - 1);
        assertThat(testMtiConfig.getMtiDescription()).isEqualTo(UPDATED_MTI_DESCRIPTION);
        assertThat(testMtiConfig.getTnType()).isEqualTo(UPDATED_TN_TYPE);
        assertThat(testMtiConfig.getIsoType()).isEqualTo(UPDATED_ISO_TYPE);
        assertThat(testMtiConfig.getMti()).isEqualTo(UPDATED_MTI);
        assertThat(testMtiConfig.getRepeatMti()).isEqualTo(UPDATED_REPEAT_MTI);
        assertThat(testMtiConfig.getResponseMti()).isEqualTo(UPDATED_RESPONSE_MTI);
        assertThat(testMtiConfig.getResponseRepeatMti()).isEqualTo(UPDATED_RESPONSE_REPEAT_MTI);
        assertThat(testMtiConfig.getNmmIdentifierDe()).isEqualTo(UPDATED_NMM_IDENTIFIER_DE);
        assertThat(testMtiConfig.getNmmIdentifierDeVal()).isEqualTo(UPDATED_NMM_IDENTIFIER_DE_VAL);
        assertThat(testMtiConfig.getResponseIdentifierDe()).isEqualTo(UPDATED_RESPONSE_IDENTIFIER_DE);
        assertThat(testMtiConfig.getResponseIdentifierDeVal()).isEqualTo(UPDATED_RESPONSE_IDENTIFIER_DE_VAL);
    }

    @Test
    @Transactional
    public void updateNonExistingMtiConfig() throws Exception {
        int databaseSizeBeforeUpdate = mtiConfigRepository.findAll().size();

        // Create the MtiConfig
        MtiConfigDTO mtiConfigDTO = mtiConfigMapper.toDto(mtiConfig);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMtiConfigMockMvc.perform(put("/api/mti-configs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mtiConfigDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MtiConfig in the database
        List<MtiConfig> mtiConfigList = mtiConfigRepository.findAll();
        assertThat(mtiConfigList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMtiConfig() throws Exception {
        // Initialize the database
        mtiConfigRepository.saveAndFlush(mtiConfig);

        int databaseSizeBeforeDelete = mtiConfigRepository.findAll().size();

        // Delete the mtiConfig
        restMtiConfigMockMvc.perform(delete("/api/mti-configs/{id}", mtiConfig.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MtiConfig> mtiConfigList = mtiConfigRepository.findAll();
        assertThat(mtiConfigList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
