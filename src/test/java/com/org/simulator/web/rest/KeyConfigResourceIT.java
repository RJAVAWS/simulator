package com.org.simulator.web.rest;

import com.org.simulator.SimulatorApp;
import com.org.simulator.domain.KeyConfig;
import com.org.simulator.repository.KeyConfigRepository;
import com.org.simulator.service.KeyConfigService;
import com.org.simulator.service.dto.KeyConfigDTO;
import com.org.simulator.service.mapper.KeyConfigMapper;
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

import com.org.simulator.domain.enumeration.PinMacType;
/**
 * Integration tests for the {@link KeyConfigResource} REST controller.
 */
@SpringBootTest(classes = SimulatorApp.class)
public class KeyConfigResourceIT {

    private static final PinMacType DEFAULT_PM_TYPE = PinMacType.PIN;
    private static final PinMacType UPDATED_PM_TYPE = PinMacType.MAC;

    private static final String DEFAULT_KEY = "AAAAAAAAAA";
    private static final String UPDATED_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_KCV = "AAAAAAAAAA";
    private static final String UPDATED_KCV = "BBBBBBBBBB";

    private static final Boolean DEFAULT_STATUS = false;
    private static final Boolean UPDATED_STATUS = true;

    private static final String DEFAULT_DE_01 = "AAAAAAAAAA";
    private static final String UPDATED_DE_01 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_02 = "AAAAAAAAAA";
    private static final String UPDATED_DE_02 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_03 = "AAAAAAAAAA";
    private static final String UPDATED_DE_03 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_04 = "AAAAAAAAAA";
    private static final String UPDATED_DE_04 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_05 = "AAAAAAAAAA";
    private static final String UPDATED_DE_05 = "BBBBBBBBBB";

    @Autowired
    private KeyConfigRepository keyConfigRepository;

    @Autowired
    private KeyConfigMapper keyConfigMapper;

    @Autowired
    private KeyConfigService keyConfigService;

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

    private MockMvc restKeyConfigMockMvc;

    private KeyConfig keyConfig;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final KeyConfigResource keyConfigResource = new KeyConfigResource(keyConfigService);
        this.restKeyConfigMockMvc = MockMvcBuilders.standaloneSetup(keyConfigResource)
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
    public static KeyConfig createEntity(EntityManager em) {
        KeyConfig keyConfig = new KeyConfig()
            .pmType(DEFAULT_PM_TYPE)
            .key(DEFAULT_KEY)
            .kcv(DEFAULT_KCV)
            .status(DEFAULT_STATUS)
            .de01(DEFAULT_DE_01)
            .de02(DEFAULT_DE_02)
            .de03(DEFAULT_DE_03)
            .de04(DEFAULT_DE_04)
            .de05(DEFAULT_DE_05);
        return keyConfig;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KeyConfig createUpdatedEntity(EntityManager em) {
        KeyConfig keyConfig = new KeyConfig()
            .pmType(UPDATED_PM_TYPE)
            .key(UPDATED_KEY)
            .kcv(UPDATED_KCV)
            .status(UPDATED_STATUS)
            .de01(UPDATED_DE_01)
            .de02(UPDATED_DE_02)
            .de03(UPDATED_DE_03)
            .de04(UPDATED_DE_04)
            .de05(UPDATED_DE_05);
        return keyConfig;
    }

    @BeforeEach
    public void initTest() {
        keyConfig = createEntity(em);
    }

    @Test
    @Transactional
    public void createKeyConfig() throws Exception {
        int databaseSizeBeforeCreate = keyConfigRepository.findAll().size();

        // Create the KeyConfig
        KeyConfigDTO keyConfigDTO = keyConfigMapper.toDto(keyConfig);
        restKeyConfigMockMvc.perform(post("/api/key-configs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(keyConfigDTO)))
            .andExpect(status().isCreated());

        // Validate the KeyConfig in the database
        List<KeyConfig> keyConfigList = keyConfigRepository.findAll();
        assertThat(keyConfigList).hasSize(databaseSizeBeforeCreate + 1);
        KeyConfig testKeyConfig = keyConfigList.get(keyConfigList.size() - 1);
        assertThat(testKeyConfig.getPmType()).isEqualTo(DEFAULT_PM_TYPE);
        assertThat(testKeyConfig.getKey()).isEqualTo(DEFAULT_KEY);
        assertThat(testKeyConfig.getKcv()).isEqualTo(DEFAULT_KCV);
        assertThat(testKeyConfig.isStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testKeyConfig.getDe01()).isEqualTo(DEFAULT_DE_01);
        assertThat(testKeyConfig.getDe02()).isEqualTo(DEFAULT_DE_02);
        assertThat(testKeyConfig.getDe03()).isEqualTo(DEFAULT_DE_03);
        assertThat(testKeyConfig.getDe04()).isEqualTo(DEFAULT_DE_04);
        assertThat(testKeyConfig.getDe05()).isEqualTo(DEFAULT_DE_05);
    }

    @Test
    @Transactional
    public void createKeyConfigWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = keyConfigRepository.findAll().size();

        // Create the KeyConfig with an existing ID
        keyConfig.setId(1L);
        KeyConfigDTO keyConfigDTO = keyConfigMapper.toDto(keyConfig);

        // An entity with an existing ID cannot be created, so this API call must fail
        restKeyConfigMockMvc.perform(post("/api/key-configs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(keyConfigDTO)))
            .andExpect(status().isBadRequest());

        // Validate the KeyConfig in the database
        List<KeyConfig> keyConfigList = keyConfigRepository.findAll();
        assertThat(keyConfigList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkKeyIsRequired() throws Exception {
        int databaseSizeBeforeTest = keyConfigRepository.findAll().size();
        // set the field null
        keyConfig.setKey(null);

        // Create the KeyConfig, which fails.
        KeyConfigDTO keyConfigDTO = keyConfigMapper.toDto(keyConfig);

        restKeyConfigMockMvc.perform(post("/api/key-configs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(keyConfigDTO)))
            .andExpect(status().isBadRequest());

        List<KeyConfig> keyConfigList = keyConfigRepository.findAll();
        assertThat(keyConfigList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkKcvIsRequired() throws Exception {
        int databaseSizeBeforeTest = keyConfigRepository.findAll().size();
        // set the field null
        keyConfig.setKcv(null);

        // Create the KeyConfig, which fails.
        KeyConfigDTO keyConfigDTO = keyConfigMapper.toDto(keyConfig);

        restKeyConfigMockMvc.perform(post("/api/key-configs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(keyConfigDTO)))
            .andExpect(status().isBadRequest());

        List<KeyConfig> keyConfigList = keyConfigRepository.findAll();
        assertThat(keyConfigList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllKeyConfigs() throws Exception {
        // Initialize the database
        keyConfigRepository.saveAndFlush(keyConfig);

        // Get all the keyConfigList
        restKeyConfigMockMvc.perform(get("/api/key-configs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(keyConfig.getId().intValue())))
            .andExpect(jsonPath("$.[*].pmType").value(hasItem(DEFAULT_PM_TYPE.toString())))
            .andExpect(jsonPath("$.[*].key").value(hasItem(DEFAULT_KEY)))
            .andExpect(jsonPath("$.[*].kcv").value(hasItem(DEFAULT_KCV)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.booleanValue())))
            .andExpect(jsonPath("$.[*].de01").value(hasItem(DEFAULT_DE_01)))
            .andExpect(jsonPath("$.[*].de02").value(hasItem(DEFAULT_DE_02)))
            .andExpect(jsonPath("$.[*].de03").value(hasItem(DEFAULT_DE_03)))
            .andExpect(jsonPath("$.[*].de04").value(hasItem(DEFAULT_DE_04)))
            .andExpect(jsonPath("$.[*].de05").value(hasItem(DEFAULT_DE_05)));
    }
    
    @Test
    @Transactional
    public void getKeyConfig() throws Exception {
        // Initialize the database
        keyConfigRepository.saveAndFlush(keyConfig);

        // Get the keyConfig
        restKeyConfigMockMvc.perform(get("/api/key-configs/{id}", keyConfig.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(keyConfig.getId().intValue()))
            .andExpect(jsonPath("$.pmType").value(DEFAULT_PM_TYPE.toString()))
            .andExpect(jsonPath("$.key").value(DEFAULT_KEY))
            .andExpect(jsonPath("$.kcv").value(DEFAULT_KCV))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.booleanValue()))
            .andExpect(jsonPath("$.de01").value(DEFAULT_DE_01))
            .andExpect(jsonPath("$.de02").value(DEFAULT_DE_02))
            .andExpect(jsonPath("$.de03").value(DEFAULT_DE_03))
            .andExpect(jsonPath("$.de04").value(DEFAULT_DE_04))
            .andExpect(jsonPath("$.de05").value(DEFAULT_DE_05));
    }

    @Test
    @Transactional
    public void getNonExistingKeyConfig() throws Exception {
        // Get the keyConfig
        restKeyConfigMockMvc.perform(get("/api/key-configs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKeyConfig() throws Exception {
        // Initialize the database
        keyConfigRepository.saveAndFlush(keyConfig);

        int databaseSizeBeforeUpdate = keyConfigRepository.findAll().size();

        // Update the keyConfig
        KeyConfig updatedKeyConfig = keyConfigRepository.findById(keyConfig.getId()).get();
        // Disconnect from session so that the updates on updatedKeyConfig are not directly saved in db
        em.detach(updatedKeyConfig);
        updatedKeyConfig
            .pmType(UPDATED_PM_TYPE)
            .key(UPDATED_KEY)
            .kcv(UPDATED_KCV)
            .status(UPDATED_STATUS)
            .de01(UPDATED_DE_01)
            .de02(UPDATED_DE_02)
            .de03(UPDATED_DE_03)
            .de04(UPDATED_DE_04)
            .de05(UPDATED_DE_05);
        KeyConfigDTO keyConfigDTO = keyConfigMapper.toDto(updatedKeyConfig);

        restKeyConfigMockMvc.perform(put("/api/key-configs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(keyConfigDTO)))
            .andExpect(status().isOk());

        // Validate the KeyConfig in the database
        List<KeyConfig> keyConfigList = keyConfigRepository.findAll();
        assertThat(keyConfigList).hasSize(databaseSizeBeforeUpdate);
        KeyConfig testKeyConfig = keyConfigList.get(keyConfigList.size() - 1);
        assertThat(testKeyConfig.getPmType()).isEqualTo(UPDATED_PM_TYPE);
        assertThat(testKeyConfig.getKey()).isEqualTo(UPDATED_KEY);
        assertThat(testKeyConfig.getKcv()).isEqualTo(UPDATED_KCV);
        assertThat(testKeyConfig.isStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testKeyConfig.getDe01()).isEqualTo(UPDATED_DE_01);
        assertThat(testKeyConfig.getDe02()).isEqualTo(UPDATED_DE_02);
        assertThat(testKeyConfig.getDe03()).isEqualTo(UPDATED_DE_03);
        assertThat(testKeyConfig.getDe04()).isEqualTo(UPDATED_DE_04);
        assertThat(testKeyConfig.getDe05()).isEqualTo(UPDATED_DE_05);
    }

    @Test
    @Transactional
    public void updateNonExistingKeyConfig() throws Exception {
        int databaseSizeBeforeUpdate = keyConfigRepository.findAll().size();

        // Create the KeyConfig
        KeyConfigDTO keyConfigDTO = keyConfigMapper.toDto(keyConfig);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKeyConfigMockMvc.perform(put("/api/key-configs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(keyConfigDTO)))
            .andExpect(status().isBadRequest());

        // Validate the KeyConfig in the database
        List<KeyConfig> keyConfigList = keyConfigRepository.findAll();
        assertThat(keyConfigList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteKeyConfig() throws Exception {
        // Initialize the database
        keyConfigRepository.saveAndFlush(keyConfig);

        int databaseSizeBeforeDelete = keyConfigRepository.findAll().size();

        // Delete the keyConfig
        restKeyConfigMockMvc.perform(delete("/api/key-configs/{id}", keyConfig.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<KeyConfig> keyConfigList = keyConfigRepository.findAll();
        assertThat(keyConfigList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
