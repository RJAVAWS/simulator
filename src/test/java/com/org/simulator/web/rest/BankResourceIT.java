package com.org.simulator.web.rest;

import com.org.simulator.SimulatorApp;
import com.org.simulator.domain.Bank;
import com.org.simulator.domain.Card;
import com.org.simulator.domain.KeyConfig;
import com.org.simulator.domain.Transaction;
import com.org.simulator.repository.BankRepository;
import com.org.simulator.service.BankService;
import com.org.simulator.service.dto.BankDTO;
import com.org.simulator.service.mapper.BankMapper;
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

import com.org.simulator.domain.enumeration.IsoVersions;
/**
 * Integration tests for the {@link BankResource} REST controller.
 */
@SpringBootTest(classes = SimulatorApp.class)
public class BankResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_CODE = 3;
    private static final Integer UPDATED_CODE = 4;

    private static final String DEFAULT_LOGO = "AAAAAAAAAA";
    private static final String UPDATED_LOGO = "BBBBBBBBBB";

    private static final String DEFAULT_IP = "254.74.3.07";
    private static final String UPDATED_IP = "12.204.051.244";

    private static final String DEFAULT_PORT = "AAAAAAAAAA";
    private static final String UPDATED_PORT = "BBBBBBBBBB";

    private static final IsoVersions DEFAULT_ISO_TYPE = IsoVersions.GCCISO87;
    private static final IsoVersions UPDATED_ISO_TYPE = IsoVersions.MBIISO93;

    private static final Boolean DEFAULT_SIGN_ON_OFF_FLAG = false;
    private static final Boolean UPDATED_SIGN_ON_OFF_FLAG = true;

    private static final Boolean DEFAULT_PIN_EXCHANGE_FLAG = false;
    private static final Boolean UPDATED_PIN_EXCHANGE_FLAG = true;

    private static final Boolean DEFAULT_MAC_EXCHANGE_FLAG = false;
    private static final Boolean UPDATED_MAC_EXCHANGE_FLAG = true;

    private static final Boolean DEFAULT_ECHO_FLAG = false;
    private static final Boolean UPDATED_ECHO_FLAG = true;

    private static final Boolean DEFAULT_CUTOVER_FLAG = false;
    private static final Boolean UPDATED_CUTOVER_FLAG = true;

    private static final String DEFAULT_MASTER_KEY = "AAAAAAAAAA";
    private static final String UPDATED_MASTER_KEY = "BBBBBBBBBB";

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private BankMapper bankMapper;

    @Autowired
    private BankService bankService;

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

    private MockMvc restBankMockMvc;

    private Bank bank;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BankResource bankResource = new BankResource(bankService);
        this.restBankMockMvc = MockMvcBuilders.standaloneSetup(bankResource)
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
    public static Bank createEntity(EntityManager em) {
        Bank bank = new Bank()
            .name(DEFAULT_NAME)
            .code(DEFAULT_CODE)
            .logo(DEFAULT_LOGO)
            .ip(DEFAULT_IP)
            .port(DEFAULT_PORT)
            .isoType(DEFAULT_ISO_TYPE)
            .signOnOffFlag(DEFAULT_SIGN_ON_OFF_FLAG)
            .pinExchangeFlag(DEFAULT_PIN_EXCHANGE_FLAG)
            .macExchangeFlag(DEFAULT_MAC_EXCHANGE_FLAG)
            .echoFlag(DEFAULT_ECHO_FLAG)
            .cutoverFlag(DEFAULT_CUTOVER_FLAG)
            .masterKey(DEFAULT_MASTER_KEY);
        // Add required entity
        Card card;
        if (TestUtil.findAll(em, Card.class).isEmpty()) {
            card = CardResourceIT.createEntity(em);
            em.persist(card);
            em.flush();
        } else {
            card = TestUtil.findAll(em, Card.class).get(0);
        }
        bank.getCards().add(card);
        // Add required entity
        KeyConfig keyConfig;
        if (TestUtil.findAll(em, KeyConfig.class).isEmpty()) {
            keyConfig = KeyConfigResourceIT.createEntity(em);
            em.persist(keyConfig);
            em.flush();
        } else {
            keyConfig = TestUtil.findAll(em, KeyConfig.class).get(0);
        }
        bank.getKeyConfigs().add(keyConfig);
        // Add required entity
        Transaction transaction;
        if (TestUtil.findAll(em, Transaction.class).isEmpty()) {
            transaction = TransactionResourceIT.createEntity(em);
            em.persist(transaction);
            em.flush();
        } else {
            transaction = TestUtil.findAll(em, Transaction.class).get(0);
        }
        bank.getTransactions().add(transaction);
        return bank;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bank createUpdatedEntity(EntityManager em) {
        Bank bank = new Bank()
            .name(UPDATED_NAME)
            .code(UPDATED_CODE)
            .logo(UPDATED_LOGO)
            .ip(UPDATED_IP)
            .port(UPDATED_PORT)
            .isoType(UPDATED_ISO_TYPE)
            .signOnOffFlag(UPDATED_SIGN_ON_OFF_FLAG)
            .pinExchangeFlag(UPDATED_PIN_EXCHANGE_FLAG)
            .macExchangeFlag(UPDATED_MAC_EXCHANGE_FLAG)
            .echoFlag(UPDATED_ECHO_FLAG)
            .cutoverFlag(UPDATED_CUTOVER_FLAG)
            .masterKey(UPDATED_MASTER_KEY);
        // Add required entity
        Card card;
        if (TestUtil.findAll(em, Card.class).isEmpty()) {
            card = CardResourceIT.createUpdatedEntity(em);
            em.persist(card);
            em.flush();
        } else {
            card = TestUtil.findAll(em, Card.class).get(0);
        }
        bank.getCards().add(card);
        // Add required entity
        KeyConfig keyConfig;
        if (TestUtil.findAll(em, KeyConfig.class).isEmpty()) {
            keyConfig = KeyConfigResourceIT.createUpdatedEntity(em);
            em.persist(keyConfig);
            em.flush();
        } else {
            keyConfig = TestUtil.findAll(em, KeyConfig.class).get(0);
        }
        bank.getKeyConfigs().add(keyConfig);
        // Add required entity
        Transaction transaction;
        if (TestUtil.findAll(em, Transaction.class).isEmpty()) {
            transaction = TransactionResourceIT.createUpdatedEntity(em);
            em.persist(transaction);
            em.flush();
        } else {
            transaction = TestUtil.findAll(em, Transaction.class).get(0);
        }
        bank.getTransactions().add(transaction);
        return bank;
    }

    @BeforeEach
    public void initTest() {
        bank = createEntity(em);
    }

    @Test
    @Transactional
    public void createBank() throws Exception {
        int databaseSizeBeforeCreate = bankRepository.findAll().size();

        // Create the Bank
        BankDTO bankDTO = bankMapper.toDto(bank);
        restBankMockMvc.perform(post("/api/banks")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bankDTO)))
            .andExpect(status().isCreated());

        // Validate the Bank in the database
        List<Bank> bankList = bankRepository.findAll();
        assertThat(bankList).hasSize(databaseSizeBeforeCreate + 1);
        Bank testBank = bankList.get(bankList.size() - 1);
        assertThat(testBank.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testBank.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testBank.getLogo()).isEqualTo(DEFAULT_LOGO);
        assertThat(testBank.getIp()).isEqualTo(DEFAULT_IP);
        assertThat(testBank.getPort()).isEqualTo(DEFAULT_PORT);
        assertThat(testBank.getIsoType()).isEqualTo(DEFAULT_ISO_TYPE);
        assertThat(testBank.isSignOnOffFlag()).isEqualTo(DEFAULT_SIGN_ON_OFF_FLAG);
        assertThat(testBank.isPinExchangeFlag()).isEqualTo(DEFAULT_PIN_EXCHANGE_FLAG);
        assertThat(testBank.isMacExchangeFlag()).isEqualTo(DEFAULT_MAC_EXCHANGE_FLAG);
        assertThat(testBank.isEchoFlag()).isEqualTo(DEFAULT_ECHO_FLAG);
        assertThat(testBank.isCutoverFlag()).isEqualTo(DEFAULT_CUTOVER_FLAG);
        assertThat(testBank.getMasterKey()).isEqualTo(DEFAULT_MASTER_KEY);
    }

    @Test
    @Transactional
    public void createBankWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bankRepository.findAll().size();

        // Create the Bank with an existing ID
        bank.setId(1L);
        BankDTO bankDTO = bankMapper.toDto(bank);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBankMockMvc.perform(post("/api/banks")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bankDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Bank in the database
        List<Bank> bankList = bankRepository.findAll();
        assertThat(bankList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = bankRepository.findAll().size();
        // set the field null
        bank.setName(null);

        // Create the Bank, which fails.
        BankDTO bankDTO = bankMapper.toDto(bank);

        restBankMockMvc.perform(post("/api/banks")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bankDTO)))
            .andExpect(status().isBadRequest());

        List<Bank> bankList = bankRepository.findAll();
        assertThat(bankList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = bankRepository.findAll().size();
        // set the field null
        bank.setCode(null);

        // Create the Bank, which fails.
        BankDTO bankDTO = bankMapper.toDto(bank);

        restBankMockMvc.perform(post("/api/banks")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bankDTO)))
            .andExpect(status().isBadRequest());

        List<Bank> bankList = bankRepository.findAll();
        assertThat(bankList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIpIsRequired() throws Exception {
        int databaseSizeBeforeTest = bankRepository.findAll().size();
        // set the field null
        bank.setIp(null);

        // Create the Bank, which fails.
        BankDTO bankDTO = bankMapper.toDto(bank);

        restBankMockMvc.perform(post("/api/banks")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bankDTO)))
            .andExpect(status().isBadRequest());

        List<Bank> bankList = bankRepository.findAll();
        assertThat(bankList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPortIsRequired() throws Exception {
        int databaseSizeBeforeTest = bankRepository.findAll().size();
        // set the field null
        bank.setPort(null);

        // Create the Bank, which fails.
        BankDTO bankDTO = bankMapper.toDto(bank);

        restBankMockMvc.perform(post("/api/banks")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bankDTO)))
            .andExpect(status().isBadRequest());

        List<Bank> bankList = bankRepository.findAll();
        assertThat(bankList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsoTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = bankRepository.findAll().size();
        // set the field null
        bank.setIsoType(null);

        // Create the Bank, which fails.
        BankDTO bankDTO = bankMapper.toDto(bank);

        restBankMockMvc.perform(post("/api/banks")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bankDTO)))
            .andExpect(status().isBadRequest());

        List<Bank> bankList = bankRepository.findAll();
        assertThat(bankList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSignOnOffFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = bankRepository.findAll().size();
        // set the field null
        bank.setSignOnOffFlag(null);

        // Create the Bank, which fails.
        BankDTO bankDTO = bankMapper.toDto(bank);

        restBankMockMvc.perform(post("/api/banks")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bankDTO)))
            .andExpect(status().isBadRequest());

        List<Bank> bankList = bankRepository.findAll();
        assertThat(bankList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPinExchangeFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = bankRepository.findAll().size();
        // set the field null
        bank.setPinExchangeFlag(null);

        // Create the Bank, which fails.
        BankDTO bankDTO = bankMapper.toDto(bank);

        restBankMockMvc.perform(post("/api/banks")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bankDTO)))
            .andExpect(status().isBadRequest());

        List<Bank> bankList = bankRepository.findAll();
        assertThat(bankList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMacExchangeFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = bankRepository.findAll().size();
        // set the field null
        bank.setMacExchangeFlag(null);

        // Create the Bank, which fails.
        BankDTO bankDTO = bankMapper.toDto(bank);

        restBankMockMvc.perform(post("/api/banks")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bankDTO)))
            .andExpect(status().isBadRequest());

        List<Bank> bankList = bankRepository.findAll();
        assertThat(bankList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEchoFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = bankRepository.findAll().size();
        // set the field null
        bank.setEchoFlag(null);

        // Create the Bank, which fails.
        BankDTO bankDTO = bankMapper.toDto(bank);

        restBankMockMvc.perform(post("/api/banks")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bankDTO)))
            .andExpect(status().isBadRequest());

        List<Bank> bankList = bankRepository.findAll();
        assertThat(bankList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCutoverFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = bankRepository.findAll().size();
        // set the field null
        bank.setCutoverFlag(null);

        // Create the Bank, which fails.
        BankDTO bankDTO = bankMapper.toDto(bank);

        restBankMockMvc.perform(post("/api/banks")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bankDTO)))
            .andExpect(status().isBadRequest());

        List<Bank> bankList = bankRepository.findAll();
        assertThat(bankList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMasterKeyIsRequired() throws Exception {
        int databaseSizeBeforeTest = bankRepository.findAll().size();
        // set the field null
        bank.setMasterKey(null);

        // Create the Bank, which fails.
        BankDTO bankDTO = bankMapper.toDto(bank);

        restBankMockMvc.perform(post("/api/banks")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bankDTO)))
            .andExpect(status().isBadRequest());

        List<Bank> bankList = bankRepository.findAll();
        assertThat(bankList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBanks() throws Exception {
        // Initialize the database
        bankRepository.saveAndFlush(bank);

        // Get all the bankList
        restBankMockMvc.perform(get("/api/banks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bank.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].logo").value(hasItem(DEFAULT_LOGO)))
            .andExpect(jsonPath("$.[*].ip").value(hasItem(DEFAULT_IP)))
            .andExpect(jsonPath("$.[*].port").value(hasItem(DEFAULT_PORT)))
            .andExpect(jsonPath("$.[*].isoType").value(hasItem(DEFAULT_ISO_TYPE.toString())))
            .andExpect(jsonPath("$.[*].signOnOffFlag").value(hasItem(DEFAULT_SIGN_ON_OFF_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].pinExchangeFlag").value(hasItem(DEFAULT_PIN_EXCHANGE_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].macExchangeFlag").value(hasItem(DEFAULT_MAC_EXCHANGE_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].echoFlag").value(hasItem(DEFAULT_ECHO_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].cutoverFlag").value(hasItem(DEFAULT_CUTOVER_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].masterKey").value(hasItem(DEFAULT_MASTER_KEY)));
    }
    
    @Test
    @Transactional
    public void getBank() throws Exception {
        // Initialize the database
        bankRepository.saveAndFlush(bank);

        // Get the bank
        restBankMockMvc.perform(get("/api/banks/{id}", bank.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bank.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.logo").value(DEFAULT_LOGO))
            .andExpect(jsonPath("$.ip").value(DEFAULT_IP))
            .andExpect(jsonPath("$.port").value(DEFAULT_PORT))
            .andExpect(jsonPath("$.isoType").value(DEFAULT_ISO_TYPE.toString()))
            .andExpect(jsonPath("$.signOnOffFlag").value(DEFAULT_SIGN_ON_OFF_FLAG.booleanValue()))
            .andExpect(jsonPath("$.pinExchangeFlag").value(DEFAULT_PIN_EXCHANGE_FLAG.booleanValue()))
            .andExpect(jsonPath("$.macExchangeFlag").value(DEFAULT_MAC_EXCHANGE_FLAG.booleanValue()))
            .andExpect(jsonPath("$.echoFlag").value(DEFAULT_ECHO_FLAG.booleanValue()))
            .andExpect(jsonPath("$.cutoverFlag").value(DEFAULT_CUTOVER_FLAG.booleanValue()))
            .andExpect(jsonPath("$.masterKey").value(DEFAULT_MASTER_KEY));
    }

    @Test
    @Transactional
    public void getNonExistingBank() throws Exception {
        // Get the bank
        restBankMockMvc.perform(get("/api/banks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBank() throws Exception {
        // Initialize the database
        bankRepository.saveAndFlush(bank);

        int databaseSizeBeforeUpdate = bankRepository.findAll().size();

        // Update the bank
        Bank updatedBank = bankRepository.findById(bank.getId()).get();
        // Disconnect from session so that the updates on updatedBank are not directly saved in db
        em.detach(updatedBank);
        updatedBank
            .name(UPDATED_NAME)
            .code(UPDATED_CODE)
            .logo(UPDATED_LOGO)
            .ip(UPDATED_IP)
            .port(UPDATED_PORT)
            .isoType(UPDATED_ISO_TYPE)
            .signOnOffFlag(UPDATED_SIGN_ON_OFF_FLAG)
            .pinExchangeFlag(UPDATED_PIN_EXCHANGE_FLAG)
            .macExchangeFlag(UPDATED_MAC_EXCHANGE_FLAG)
            .echoFlag(UPDATED_ECHO_FLAG)
            .cutoverFlag(UPDATED_CUTOVER_FLAG)
            .masterKey(UPDATED_MASTER_KEY);
        BankDTO bankDTO = bankMapper.toDto(updatedBank);

        restBankMockMvc.perform(put("/api/banks")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bankDTO)))
            .andExpect(status().isOk());

        // Validate the Bank in the database
        List<Bank> bankList = bankRepository.findAll();
        assertThat(bankList).hasSize(databaseSizeBeforeUpdate);
        Bank testBank = bankList.get(bankList.size() - 1);
        assertThat(testBank.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBank.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testBank.getLogo()).isEqualTo(UPDATED_LOGO);
        assertThat(testBank.getIp()).isEqualTo(UPDATED_IP);
        assertThat(testBank.getPort()).isEqualTo(UPDATED_PORT);
        assertThat(testBank.getIsoType()).isEqualTo(UPDATED_ISO_TYPE);
        assertThat(testBank.isSignOnOffFlag()).isEqualTo(UPDATED_SIGN_ON_OFF_FLAG);
        assertThat(testBank.isPinExchangeFlag()).isEqualTo(UPDATED_PIN_EXCHANGE_FLAG);
        assertThat(testBank.isMacExchangeFlag()).isEqualTo(UPDATED_MAC_EXCHANGE_FLAG);
        assertThat(testBank.isEchoFlag()).isEqualTo(UPDATED_ECHO_FLAG);
        assertThat(testBank.isCutoverFlag()).isEqualTo(UPDATED_CUTOVER_FLAG);
        assertThat(testBank.getMasterKey()).isEqualTo(UPDATED_MASTER_KEY);
    }

    @Test
    @Transactional
    public void updateNonExistingBank() throws Exception {
        int databaseSizeBeforeUpdate = bankRepository.findAll().size();

        // Create the Bank
        BankDTO bankDTO = bankMapper.toDto(bank);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBankMockMvc.perform(put("/api/banks")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bankDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Bank in the database
        List<Bank> bankList = bankRepository.findAll();
        assertThat(bankList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBank() throws Exception {
        // Initialize the database
        bankRepository.saveAndFlush(bank);

        int databaseSizeBeforeDelete = bankRepository.findAll().size();

        // Delete the bank
        restBankMockMvc.perform(delete("/api/banks/{id}", bank.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Bank> bankList = bankRepository.findAll();
        assertThat(bankList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
