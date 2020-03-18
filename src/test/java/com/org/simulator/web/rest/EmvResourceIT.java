package com.org.simulator.web.rest;

import com.org.simulator.SimulatorApp;
import com.org.simulator.domain.Emv;
import com.org.simulator.repository.EmvRepository;
import com.org.simulator.service.EmvService;
import com.org.simulator.service.dto.EmvDTO;
import com.org.simulator.service.mapper.EmvMapper;
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

/**
 * Integration tests for the {@link EmvResource} REST controller.
 */
@SpringBootTest(classes = SimulatorApp.class)
public class EmvResourceIT {

    private static final String DEFAULT_EMV_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_EMV_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_DE_5_F_2_A = "AAAAAAAAAA";
    private static final String UPDATED_DE_5_F_2_A = "BBBBBBBBBB";

    private static final String DEFAULT_DE_82 = "AAAAAAAAAA";
    private static final String UPDATED_DE_82 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_84 = "AAAAAAAAAA";
    private static final String UPDATED_DE_84 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_95 = "AAAAAAAAAA";
    private static final String UPDATED_DE_95 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_9_A = "AAAAAAAAAA";
    private static final String UPDATED_DE_9_A = "BBBBBBBBBB";

    private static final String DEFAULT_DE_9_C = "AAAAAAAAAA";
    private static final String UPDATED_DE_9_C = "BBBBBBBBBB";

    private static final String DEFAULT_DE_9_F_02 = "AAAAAAAAAA";
    private static final String UPDATED_DE_9_F_02 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_9_F_03 = "AAAAAAAAAA";
    private static final String UPDATED_DE_9_F_03 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_9_F_09 = "AAAAAAAAAA";
    private static final String UPDATED_DE_9_F_09 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_9_F_10 = "AAAAAAAAAA";
    private static final String UPDATED_DE_9_F_10 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_9_F_1_A = "AAAAAAAAAA";
    private static final String UPDATED_DE_9_F_1_A = "BBBBBBBBBB";

    private static final String DEFAULT_DE_9_F_1_E = "AAAAAAAAAA";
    private static final String UPDATED_DE_9_F_1_E = "BBBBBBBBBB";

    private static final String DEFAULT_DE_9_F_26 = "AAAAAAAAAA";
    private static final String UPDATED_DE_9_F_26 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_9_F_27 = "AAAAAAAAAA";
    private static final String UPDATED_DE_9_F_27 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_9_F_33 = "AAAAAAAAAA";
    private static final String UPDATED_DE_9_F_33 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_9_F_34 = "AAAAAAAAAA";
    private static final String UPDATED_DE_9_F_34 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_9_F_35 = "AAAAAAAAAA";
    private static final String UPDATED_DE_9_F_35 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_9_F_36 = "AAAAAAAAAA";
    private static final String UPDATED_DE_9_F_36 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_9_F_37 = "AAAAAAAAAA";
    private static final String UPDATED_DE_9_F_37 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_9_F_41 = "AAAAAAAAAA";
    private static final String UPDATED_DE_9_F_41 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_9_F_53 = "AAAAAAAAAA";
    private static final String UPDATED_DE_9_F_53 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_8_A = "AAAAAAAAAA";
    private static final String UPDATED_DE_8_A = "BBBBBBBBBB";

    private static final String DEFAULT_DE_71 = "AAAAAAAAAA";
    private static final String UPDATED_DE_71 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_72 = "AAAAAAAAAA";
    private static final String UPDATED_DE_72 = "BBBBBBBBBB";

    private static final String DEFAULT_DE_91 = "AAAAAAAAAA";
    private static final String UPDATED_DE_91 = "BBBBBBBBBB";

    private static final String DEFAULT_OTHERS = "AAAAAAAAAA";
    private static final String UPDATED_OTHERS = "BBBBBBBBBB";

    @Autowired
    private EmvRepository emvRepository;

    @Autowired
    private EmvMapper emvMapper;

    @Autowired
    private EmvService emvService;

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

    private MockMvc restEmvMockMvc;

    private Emv emv;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EmvResource emvResource = new EmvResource(emvService);
        this.restEmvMockMvc = MockMvcBuilders.standaloneSetup(emvResource)
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
    public static Emv createEntity(EntityManager em) {
        Emv emv = new Emv()
            .emvDescription(DEFAULT_EMV_DESCRIPTION)
            .de5F2A(DEFAULT_DE_5_F_2_A)
            .de82(DEFAULT_DE_82)
            .de84(DEFAULT_DE_84)
            .de95(DEFAULT_DE_95)
            .de9A(DEFAULT_DE_9_A)
            .de9C(DEFAULT_DE_9_C)
            .de9F02(DEFAULT_DE_9_F_02)
            .de9F03(DEFAULT_DE_9_F_03)
            .de9F09(DEFAULT_DE_9_F_09)
            .de9F10(DEFAULT_DE_9_F_10)
            .de9F1A(DEFAULT_DE_9_F_1_A)
            .de9F1E(DEFAULT_DE_9_F_1_E)
            .de9F26(DEFAULT_DE_9_F_26)
            .de9F27(DEFAULT_DE_9_F_27)
            .de9F33(DEFAULT_DE_9_F_33)
            .de9F34(DEFAULT_DE_9_F_34)
            .de9F35(DEFAULT_DE_9_F_35)
            .de9F36(DEFAULT_DE_9_F_36)
            .de9F37(DEFAULT_DE_9_F_37)
            .de9F41(DEFAULT_DE_9_F_41)
            .de9F53(DEFAULT_DE_9_F_53)
            .de8A(DEFAULT_DE_8_A)
            .de71(DEFAULT_DE_71)
            .de72(DEFAULT_DE_72)
            .de91(DEFAULT_DE_91)
            .others(DEFAULT_OTHERS);
        return emv;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Emv createUpdatedEntity(EntityManager em) {
        Emv emv = new Emv()
            .emvDescription(UPDATED_EMV_DESCRIPTION)
            .de5F2A(UPDATED_DE_5_F_2_A)
            .de82(UPDATED_DE_82)
            .de84(UPDATED_DE_84)
            .de95(UPDATED_DE_95)
            .de9A(UPDATED_DE_9_A)
            .de9C(UPDATED_DE_9_C)
            .de9F02(UPDATED_DE_9_F_02)
            .de9F03(UPDATED_DE_9_F_03)
            .de9F09(UPDATED_DE_9_F_09)
            .de9F10(UPDATED_DE_9_F_10)
            .de9F1A(UPDATED_DE_9_F_1_A)
            .de9F1E(UPDATED_DE_9_F_1_E)
            .de9F26(UPDATED_DE_9_F_26)
            .de9F27(UPDATED_DE_9_F_27)
            .de9F33(UPDATED_DE_9_F_33)
            .de9F34(UPDATED_DE_9_F_34)
            .de9F35(UPDATED_DE_9_F_35)
            .de9F36(UPDATED_DE_9_F_36)
            .de9F37(UPDATED_DE_9_F_37)
            .de9F41(UPDATED_DE_9_F_41)
            .de9F53(UPDATED_DE_9_F_53)
            .de8A(UPDATED_DE_8_A)
            .de71(UPDATED_DE_71)
            .de72(UPDATED_DE_72)
            .de91(UPDATED_DE_91)
            .others(UPDATED_OTHERS);
        return emv;
    }

    @BeforeEach
    public void initTest() {
        emv = createEntity(em);
    }

    @Test
    @Transactional
    public void createEmv() throws Exception {
        int databaseSizeBeforeCreate = emvRepository.findAll().size();

        // Create the Emv
        EmvDTO emvDTO = emvMapper.toDto(emv);
        restEmvMockMvc.perform(post("/api/emvs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emvDTO)))
            .andExpect(status().isCreated());

        // Validate the Emv in the database
        List<Emv> emvList = emvRepository.findAll();
        assertThat(emvList).hasSize(databaseSizeBeforeCreate + 1);
        Emv testEmv = emvList.get(emvList.size() - 1);
        assertThat(testEmv.getEmvDescription()).isEqualTo(DEFAULT_EMV_DESCRIPTION);
        assertThat(testEmv.getDe5F2A()).isEqualTo(DEFAULT_DE_5_F_2_A);
        assertThat(testEmv.getDe82()).isEqualTo(DEFAULT_DE_82);
        assertThat(testEmv.getDe84()).isEqualTo(DEFAULT_DE_84);
        assertThat(testEmv.getDe95()).isEqualTo(DEFAULT_DE_95);
        assertThat(testEmv.getDe9A()).isEqualTo(DEFAULT_DE_9_A);
        assertThat(testEmv.getDe9C()).isEqualTo(DEFAULT_DE_9_C);
        assertThat(testEmv.getDe9F02()).isEqualTo(DEFAULT_DE_9_F_02);
        assertThat(testEmv.getDe9F03()).isEqualTo(DEFAULT_DE_9_F_03);
        assertThat(testEmv.getDe9F09()).isEqualTo(DEFAULT_DE_9_F_09);
        assertThat(testEmv.getDe9F10()).isEqualTo(DEFAULT_DE_9_F_10);
        assertThat(testEmv.getDe9F1A()).isEqualTo(DEFAULT_DE_9_F_1_A);
        assertThat(testEmv.getDe9F1E()).isEqualTo(DEFAULT_DE_9_F_1_E);
        assertThat(testEmv.getDe9F26()).isEqualTo(DEFAULT_DE_9_F_26);
        assertThat(testEmv.getDe9F27()).isEqualTo(DEFAULT_DE_9_F_27);
        assertThat(testEmv.getDe9F33()).isEqualTo(DEFAULT_DE_9_F_33);
        assertThat(testEmv.getDe9F34()).isEqualTo(DEFAULT_DE_9_F_34);
        assertThat(testEmv.getDe9F35()).isEqualTo(DEFAULT_DE_9_F_35);
        assertThat(testEmv.getDe9F36()).isEqualTo(DEFAULT_DE_9_F_36);
        assertThat(testEmv.getDe9F37()).isEqualTo(DEFAULT_DE_9_F_37);
        assertThat(testEmv.getDe9F41()).isEqualTo(DEFAULT_DE_9_F_41);
        assertThat(testEmv.getDe9F53()).isEqualTo(DEFAULT_DE_9_F_53);
        assertThat(testEmv.getDe8A()).isEqualTo(DEFAULT_DE_8_A);
        assertThat(testEmv.getDe71()).isEqualTo(DEFAULT_DE_71);
        assertThat(testEmv.getDe72()).isEqualTo(DEFAULT_DE_72);
        assertThat(testEmv.getDe91()).isEqualTo(DEFAULT_DE_91);
        assertThat(testEmv.getOthers()).isEqualTo(DEFAULT_OTHERS);
    }

    @Test
    @Transactional
    public void createEmvWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = emvRepository.findAll().size();

        // Create the Emv with an existing ID
        emv.setId(1L);
        EmvDTO emvDTO = emvMapper.toDto(emv);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmvMockMvc.perform(post("/api/emvs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emvDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Emv in the database
        List<Emv> emvList = emvRepository.findAll();
        assertThat(emvList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkEmvDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = emvRepository.findAll().size();
        // set the field null
        emv.setEmvDescription(null);

        // Create the Emv, which fails.
        EmvDTO emvDTO = emvMapper.toDto(emv);

        restEmvMockMvc.perform(post("/api/emvs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emvDTO)))
            .andExpect(status().isBadRequest());

        List<Emv> emvList = emvRepository.findAll();
        assertThat(emvList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEmvs() throws Exception {
        // Initialize the database
        emvRepository.saveAndFlush(emv);

        // Get all the emvList
        restEmvMockMvc.perform(get("/api/emvs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(emv.getId().intValue())))
            .andExpect(jsonPath("$.[*].emvDescription").value(hasItem(DEFAULT_EMV_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].de5F2A").value(hasItem(DEFAULT_DE_5_F_2_A.toString())))
            .andExpect(jsonPath("$.[*].de82").value(hasItem(DEFAULT_DE_82.toString())))
            .andExpect(jsonPath("$.[*].de84").value(hasItem(DEFAULT_DE_84.toString())))
            .andExpect(jsonPath("$.[*].de95").value(hasItem(DEFAULT_DE_95.toString())))
            .andExpect(jsonPath("$.[*].de9A").value(hasItem(DEFAULT_DE_9_A.toString())))
            .andExpect(jsonPath("$.[*].de9C").value(hasItem(DEFAULT_DE_9_C.toString())))
            .andExpect(jsonPath("$.[*].de9F02").value(hasItem(DEFAULT_DE_9_F_02.toString())))
            .andExpect(jsonPath("$.[*].de9F03").value(hasItem(DEFAULT_DE_9_F_03.toString())))
            .andExpect(jsonPath("$.[*].de9F09").value(hasItem(DEFAULT_DE_9_F_09.toString())))
            .andExpect(jsonPath("$.[*].de9F10").value(hasItem(DEFAULT_DE_9_F_10.toString())))
            .andExpect(jsonPath("$.[*].de9F1A").value(hasItem(DEFAULT_DE_9_F_1_A.toString())))
            .andExpect(jsonPath("$.[*].de9F1E").value(hasItem(DEFAULT_DE_9_F_1_E.toString())))
            .andExpect(jsonPath("$.[*].de9F26").value(hasItem(DEFAULT_DE_9_F_26.toString())))
            .andExpect(jsonPath("$.[*].de9F27").value(hasItem(DEFAULT_DE_9_F_27.toString())))
            .andExpect(jsonPath("$.[*].de9F33").value(hasItem(DEFAULT_DE_9_F_33.toString())))
            .andExpect(jsonPath("$.[*].de9F34").value(hasItem(DEFAULT_DE_9_F_34.toString())))
            .andExpect(jsonPath("$.[*].de9F35").value(hasItem(DEFAULT_DE_9_F_35.toString())))
            .andExpect(jsonPath("$.[*].de9F36").value(hasItem(DEFAULT_DE_9_F_36.toString())))
            .andExpect(jsonPath("$.[*].de9F37").value(hasItem(DEFAULT_DE_9_F_37.toString())))
            .andExpect(jsonPath("$.[*].de9F41").value(hasItem(DEFAULT_DE_9_F_41.toString())))
            .andExpect(jsonPath("$.[*].de9F53").value(hasItem(DEFAULT_DE_9_F_53.toString())))
            .andExpect(jsonPath("$.[*].de8A").value(hasItem(DEFAULT_DE_8_A.toString())))
            .andExpect(jsonPath("$.[*].de71").value(hasItem(DEFAULT_DE_71.toString())))
            .andExpect(jsonPath("$.[*].de72").value(hasItem(DEFAULT_DE_72.toString())))
            .andExpect(jsonPath("$.[*].de91").value(hasItem(DEFAULT_DE_91.toString())))
            .andExpect(jsonPath("$.[*].others").value(hasItem(DEFAULT_OTHERS.toString())));
    }
    
    @Test
    @Transactional
    public void getEmv() throws Exception {
        // Initialize the database
        emvRepository.saveAndFlush(emv);

        // Get the emv
        restEmvMockMvc.perform(get("/api/emvs/{id}", emv.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(emv.getId().intValue()))
            .andExpect(jsonPath("$.emvDescription").value(DEFAULT_EMV_DESCRIPTION))
            .andExpect(jsonPath("$.de5F2A").value(DEFAULT_DE_5_F_2_A.toString()))
            .andExpect(jsonPath("$.de82").value(DEFAULT_DE_82.toString()))
            .andExpect(jsonPath("$.de84").value(DEFAULT_DE_84.toString()))
            .andExpect(jsonPath("$.de95").value(DEFAULT_DE_95.toString()))
            .andExpect(jsonPath("$.de9A").value(DEFAULT_DE_9_A.toString()))
            .andExpect(jsonPath("$.de9C").value(DEFAULT_DE_9_C.toString()))
            .andExpect(jsonPath("$.de9F02").value(DEFAULT_DE_9_F_02.toString()))
            .andExpect(jsonPath("$.de9F03").value(DEFAULT_DE_9_F_03.toString()))
            .andExpect(jsonPath("$.de9F09").value(DEFAULT_DE_9_F_09.toString()))
            .andExpect(jsonPath("$.de9F10").value(DEFAULT_DE_9_F_10.toString()))
            .andExpect(jsonPath("$.de9F1A").value(DEFAULT_DE_9_F_1_A.toString()))
            .andExpect(jsonPath("$.de9F1E").value(DEFAULT_DE_9_F_1_E.toString()))
            .andExpect(jsonPath("$.de9F26").value(DEFAULT_DE_9_F_26.toString()))
            .andExpect(jsonPath("$.de9F27").value(DEFAULT_DE_9_F_27.toString()))
            .andExpect(jsonPath("$.de9F33").value(DEFAULT_DE_9_F_33.toString()))
            .andExpect(jsonPath("$.de9F34").value(DEFAULT_DE_9_F_34.toString()))
            .andExpect(jsonPath("$.de9F35").value(DEFAULT_DE_9_F_35.toString()))
            .andExpect(jsonPath("$.de9F36").value(DEFAULT_DE_9_F_36.toString()))
            .andExpect(jsonPath("$.de9F37").value(DEFAULT_DE_9_F_37.toString()))
            .andExpect(jsonPath("$.de9F41").value(DEFAULT_DE_9_F_41.toString()))
            .andExpect(jsonPath("$.de9F53").value(DEFAULT_DE_9_F_53.toString()))
            .andExpect(jsonPath("$.de8A").value(DEFAULT_DE_8_A.toString()))
            .andExpect(jsonPath("$.de71").value(DEFAULT_DE_71.toString()))
            .andExpect(jsonPath("$.de72").value(DEFAULT_DE_72.toString()))
            .andExpect(jsonPath("$.de91").value(DEFAULT_DE_91.toString()))
            .andExpect(jsonPath("$.others").value(DEFAULT_OTHERS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEmv() throws Exception {
        // Get the emv
        restEmvMockMvc.perform(get("/api/emvs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEmv() throws Exception {
        // Initialize the database
        emvRepository.saveAndFlush(emv);

        int databaseSizeBeforeUpdate = emvRepository.findAll().size();

        // Update the emv
        Emv updatedEmv = emvRepository.findById(emv.getId()).get();
        // Disconnect from session so that the updates on updatedEmv are not directly saved in db
        em.detach(updatedEmv);
        updatedEmv
            .emvDescription(UPDATED_EMV_DESCRIPTION)
            .de5F2A(UPDATED_DE_5_F_2_A)
            .de82(UPDATED_DE_82)
            .de84(UPDATED_DE_84)
            .de95(UPDATED_DE_95)
            .de9A(UPDATED_DE_9_A)
            .de9C(UPDATED_DE_9_C)
            .de9F02(UPDATED_DE_9_F_02)
            .de9F03(UPDATED_DE_9_F_03)
            .de9F09(UPDATED_DE_9_F_09)
            .de9F10(UPDATED_DE_9_F_10)
            .de9F1A(UPDATED_DE_9_F_1_A)
            .de9F1E(UPDATED_DE_9_F_1_E)
            .de9F26(UPDATED_DE_9_F_26)
            .de9F27(UPDATED_DE_9_F_27)
            .de9F33(UPDATED_DE_9_F_33)
            .de9F34(UPDATED_DE_9_F_34)
            .de9F35(UPDATED_DE_9_F_35)
            .de9F36(UPDATED_DE_9_F_36)
            .de9F37(UPDATED_DE_9_F_37)
            .de9F41(UPDATED_DE_9_F_41)
            .de9F53(UPDATED_DE_9_F_53)
            .de8A(UPDATED_DE_8_A)
            .de71(UPDATED_DE_71)
            .de72(UPDATED_DE_72)
            .de91(UPDATED_DE_91)
            .others(UPDATED_OTHERS);
        EmvDTO emvDTO = emvMapper.toDto(updatedEmv);

        restEmvMockMvc.perform(put("/api/emvs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emvDTO)))
            .andExpect(status().isOk());

        // Validate the Emv in the database
        List<Emv> emvList = emvRepository.findAll();
        assertThat(emvList).hasSize(databaseSizeBeforeUpdate);
        Emv testEmv = emvList.get(emvList.size() - 1);
        assertThat(testEmv.getEmvDescription()).isEqualTo(UPDATED_EMV_DESCRIPTION);
        assertThat(testEmv.getDe5F2A()).isEqualTo(UPDATED_DE_5_F_2_A);
        assertThat(testEmv.getDe82()).isEqualTo(UPDATED_DE_82);
        assertThat(testEmv.getDe84()).isEqualTo(UPDATED_DE_84);
        assertThat(testEmv.getDe95()).isEqualTo(UPDATED_DE_95);
        assertThat(testEmv.getDe9A()).isEqualTo(UPDATED_DE_9_A);
        assertThat(testEmv.getDe9C()).isEqualTo(UPDATED_DE_9_C);
        assertThat(testEmv.getDe9F02()).isEqualTo(UPDATED_DE_9_F_02);
        assertThat(testEmv.getDe9F03()).isEqualTo(UPDATED_DE_9_F_03);
        assertThat(testEmv.getDe9F09()).isEqualTo(UPDATED_DE_9_F_09);
        assertThat(testEmv.getDe9F10()).isEqualTo(UPDATED_DE_9_F_10);
        assertThat(testEmv.getDe9F1A()).isEqualTo(UPDATED_DE_9_F_1_A);
        assertThat(testEmv.getDe9F1E()).isEqualTo(UPDATED_DE_9_F_1_E);
        assertThat(testEmv.getDe9F26()).isEqualTo(UPDATED_DE_9_F_26);
        assertThat(testEmv.getDe9F27()).isEqualTo(UPDATED_DE_9_F_27);
        assertThat(testEmv.getDe9F33()).isEqualTo(UPDATED_DE_9_F_33);
        assertThat(testEmv.getDe9F34()).isEqualTo(UPDATED_DE_9_F_34);
        assertThat(testEmv.getDe9F35()).isEqualTo(UPDATED_DE_9_F_35);
        assertThat(testEmv.getDe9F36()).isEqualTo(UPDATED_DE_9_F_36);
        assertThat(testEmv.getDe9F37()).isEqualTo(UPDATED_DE_9_F_37);
        assertThat(testEmv.getDe9F41()).isEqualTo(UPDATED_DE_9_F_41);
        assertThat(testEmv.getDe9F53()).isEqualTo(UPDATED_DE_9_F_53);
        assertThat(testEmv.getDe8A()).isEqualTo(UPDATED_DE_8_A);
        assertThat(testEmv.getDe71()).isEqualTo(UPDATED_DE_71);
        assertThat(testEmv.getDe72()).isEqualTo(UPDATED_DE_72);
        assertThat(testEmv.getDe91()).isEqualTo(UPDATED_DE_91);
        assertThat(testEmv.getOthers()).isEqualTo(UPDATED_OTHERS);
    }

    @Test
    @Transactional
    public void updateNonExistingEmv() throws Exception {
        int databaseSizeBeforeUpdate = emvRepository.findAll().size();

        // Create the Emv
        EmvDTO emvDTO = emvMapper.toDto(emv);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmvMockMvc.perform(put("/api/emvs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emvDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Emv in the database
        List<Emv> emvList = emvRepository.findAll();
        assertThat(emvList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEmv() throws Exception {
        // Initialize the database
        emvRepository.saveAndFlush(emv);

        int databaseSizeBeforeDelete = emvRepository.findAll().size();

        // Delete the emv
        restEmvMockMvc.perform(delete("/api/emvs/{id}", emv.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Emv> emvList = emvRepository.findAll();
        assertThat(emvList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
