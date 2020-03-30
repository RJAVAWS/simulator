package com.org.simulator.web.rest;

import com.org.simulator.SimulatorApp;
import com.org.simulator.domain.Card;
import com.org.simulator.domain.Emv;
import com.org.simulator.repository.CardRepository;
import com.org.simulator.service.CardService;
import com.org.simulator.service.dto.CardDTO;
import com.org.simulator.service.mapper.CardMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.org.simulator.domain.enumeration.CardScheme;
import com.org.simulator.domain.enumeration.CardType;
/**
 * Integration tests for the {@link CardResource} REST controller.
 */
@SpringBootTest(classes = SimulatorApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class CardResourceIT {

    private static final String DEFAULT_CARD_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_CARD_DESCRIPTION = "BBBBBBBBBB";

    private static final CardScheme DEFAULT_SCHEME = CardScheme.MASTER;
    private static final CardScheme UPDATED_SCHEME = CardScheme.VISA;

    private static final CardType DEFAULT_TYPE = CardType.MAGNETIC;
    private static final CardType UPDATED_TYPE = CardType.EMV;

    private static final String DEFAULT_CARD_NUMBER = "AAAAAAAAAAAAAAAA";
    private static final String UPDATED_CARD_NUMBER = "BBBBBBBBBBBBBBBB";

    private static final String DEFAULT_CVV = "AAA";
    private static final String UPDATED_CVV = "BBB";

    private static final String DEFAULT_EXPIRY = "AAAA";
    private static final String UPDATED_EXPIRY = "BBBB";

    private static final String DEFAULT_PIN = "AAAA";
    private static final String UPDATED_PIN = "BBBB";

    private static final String DEFAULT_TRACK_2_DATA = "AAAAAAAAAA";
    private static final String UPDATED_TRACK_2_DATA = "BBBBBBBBBB";

    @Autowired
    private CardRepository cardRepository;

    @Mock
    private CardRepository cardRepositoryMock;

    @Autowired
    private CardMapper cardMapper;

    @Mock
    private CardService cardServiceMock;

    @Autowired
    private CardService cardService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCardMockMvc;

    private Card card;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Card createEntity(EntityManager em) {
        Card card = new Card()
            .cardDescription(DEFAULT_CARD_DESCRIPTION)
            .scheme(DEFAULT_SCHEME)
            .type(DEFAULT_TYPE)
            .cardNumber(DEFAULT_CARD_NUMBER)
            .cvv(DEFAULT_CVV)
            .expiry(DEFAULT_EXPIRY)
            .pin(DEFAULT_PIN)
            .track2data(DEFAULT_TRACK_2_DATA);
        // Add required entity
        Emv emv;
        if (TestUtil.findAll(em, Emv.class).isEmpty()) {
            emv = EmvResourceIT.createEntity(em);
            em.persist(emv);
            em.flush();
        } else {
            emv = TestUtil.findAll(em, Emv.class).get(0);
        }
        card.setEmv(emv);
        return card;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Card createUpdatedEntity(EntityManager em) {
        Card card = new Card()
            .cardDescription(UPDATED_CARD_DESCRIPTION)
            .scheme(UPDATED_SCHEME)
            .type(UPDATED_TYPE)
            .cardNumber(UPDATED_CARD_NUMBER)
            .cvv(UPDATED_CVV)
            .expiry(UPDATED_EXPIRY)
            .pin(UPDATED_PIN)
            .track2data(UPDATED_TRACK_2_DATA);
        // Add required entity
        Emv emv;
        if (TestUtil.findAll(em, Emv.class).isEmpty()) {
            emv = EmvResourceIT.createUpdatedEntity(em);
            em.persist(emv);
            em.flush();
        } else {
            emv = TestUtil.findAll(em, Emv.class).get(0);
        }
        card.setEmv(emv);
        return card;
    }

    @BeforeEach
    public void initTest() {
        card = createEntity(em);
    }

    @Test
    @Transactional
    public void createCard() throws Exception {
        int databaseSizeBeforeCreate = cardRepository.findAll().size();

        // Create the Card
        CardDTO cardDTO = cardMapper.toDto(card);
        restCardMockMvc.perform(post("/api/cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cardDTO)))
            .andExpect(status().isCreated());

        // Validate the Card in the database
        List<Card> cardList = cardRepository.findAll();
        assertThat(cardList).hasSize(databaseSizeBeforeCreate + 1);
        Card testCard = cardList.get(cardList.size() - 1);
        assertThat(testCard.getCardDescription()).isEqualTo(DEFAULT_CARD_DESCRIPTION);
        assertThat(testCard.getScheme()).isEqualTo(DEFAULT_SCHEME);
        assertThat(testCard.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testCard.getCardNumber()).isEqualTo(DEFAULT_CARD_NUMBER);
        assertThat(testCard.getCvv()).isEqualTo(DEFAULT_CVV);
        assertThat(testCard.getExpiry()).isEqualTo(DEFAULT_EXPIRY);
        assertThat(testCard.getPin()).isEqualTo(DEFAULT_PIN);
        assertThat(testCard.getTrack2data()).isEqualTo(DEFAULT_TRACK_2_DATA);
    }

    @Test
    @Transactional
    public void createCardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cardRepository.findAll().size();

        // Create the Card with an existing ID
        card.setId(1L);
        CardDTO cardDTO = cardMapper.toDto(card);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCardMockMvc.perform(post("/api/cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Card in the database
        List<Card> cardList = cardRepository.findAll();
        assertThat(cardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCardDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = cardRepository.findAll().size();
        // set the field null
        card.setCardDescription(null);

        // Create the Card, which fails.
        CardDTO cardDTO = cardMapper.toDto(card);

        restCardMockMvc.perform(post("/api/cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cardDTO)))
            .andExpect(status().isBadRequest());

        List<Card> cardList = cardRepository.findAll();
        assertThat(cardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSchemeIsRequired() throws Exception {
        int databaseSizeBeforeTest = cardRepository.findAll().size();
        // set the field null
        card.setScheme(null);

        // Create the Card, which fails.
        CardDTO cardDTO = cardMapper.toDto(card);

        restCardMockMvc.perform(post("/api/cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cardDTO)))
            .andExpect(status().isBadRequest());

        List<Card> cardList = cardRepository.findAll();
        assertThat(cardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = cardRepository.findAll().size();
        // set the field null
        card.setType(null);

        // Create the Card, which fails.
        CardDTO cardDTO = cardMapper.toDto(card);

        restCardMockMvc.perform(post("/api/cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cardDTO)))
            .andExpect(status().isBadRequest());

        List<Card> cardList = cardRepository.findAll();
        assertThat(cardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCardNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = cardRepository.findAll().size();
        // set the field null
        card.setCardNumber(null);

        // Create the Card, which fails.
        CardDTO cardDTO = cardMapper.toDto(card);

        restCardMockMvc.perform(post("/api/cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cardDTO)))
            .andExpect(status().isBadRequest());

        List<Card> cardList = cardRepository.findAll();
        assertThat(cardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCvvIsRequired() throws Exception {
        int databaseSizeBeforeTest = cardRepository.findAll().size();
        // set the field null
        card.setCvv(null);

        // Create the Card, which fails.
        CardDTO cardDTO = cardMapper.toDto(card);

        restCardMockMvc.perform(post("/api/cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cardDTO)))
            .andExpect(status().isBadRequest());

        List<Card> cardList = cardRepository.findAll();
        assertThat(cardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExpiryIsRequired() throws Exception {
        int databaseSizeBeforeTest = cardRepository.findAll().size();
        // set the field null
        card.setExpiry(null);

        // Create the Card, which fails.
        CardDTO cardDTO = cardMapper.toDto(card);

        restCardMockMvc.perform(post("/api/cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cardDTO)))
            .andExpect(status().isBadRequest());

        List<Card> cardList = cardRepository.findAll();
        assertThat(cardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPinIsRequired() throws Exception {
        int databaseSizeBeforeTest = cardRepository.findAll().size();
        // set the field null
        card.setPin(null);

        // Create the Card, which fails.
        CardDTO cardDTO = cardMapper.toDto(card);

        restCardMockMvc.perform(post("/api/cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cardDTO)))
            .andExpect(status().isBadRequest());

        List<Card> cardList = cardRepository.findAll();
        assertThat(cardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTrack2dataIsRequired() throws Exception {
        int databaseSizeBeforeTest = cardRepository.findAll().size();
        // set the field null
        card.setTrack2data(null);

        // Create the Card, which fails.
        CardDTO cardDTO = cardMapper.toDto(card);

        restCardMockMvc.perform(post("/api/cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cardDTO)))
            .andExpect(status().isBadRequest());

        List<Card> cardList = cardRepository.findAll();
        assertThat(cardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCards() throws Exception {
        // Initialize the database
        cardRepository.saveAndFlush(card);

        // Get all the cardList
        restCardMockMvc.perform(get("/api/cards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(card.getId().intValue())))
            .andExpect(jsonPath("$.[*].cardDescription").value(hasItem(DEFAULT_CARD_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].scheme").value(hasItem(DEFAULT_SCHEME.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].cardNumber").value(hasItem(DEFAULT_CARD_NUMBER)))
            .andExpect(jsonPath("$.[*].cvv").value(hasItem(DEFAULT_CVV)))
            .andExpect(jsonPath("$.[*].expiry").value(hasItem(DEFAULT_EXPIRY)))
            .andExpect(jsonPath("$.[*].pin").value(hasItem(DEFAULT_PIN)))
            .andExpect(jsonPath("$.[*].track2data").value(hasItem(DEFAULT_TRACK_2_DATA)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllCardsWithEagerRelationshipsIsEnabled() throws Exception {
        when(cardServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restCardMockMvc.perform(get("/api/cards?eagerload=true"))
            .andExpect(status().isOk());

        verify(cardServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllCardsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(cardServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restCardMockMvc.perform(get("/api/cards?eagerload=true"))
            .andExpect(status().isOk());

        verify(cardServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getCard() throws Exception {
        // Initialize the database
        cardRepository.saveAndFlush(card);

        // Get the card
        restCardMockMvc.perform(get("/api/cards/{id}", card.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(card.getId().intValue()))
            .andExpect(jsonPath("$.cardDescription").value(DEFAULT_CARD_DESCRIPTION))
            .andExpect(jsonPath("$.scheme").value(DEFAULT_SCHEME.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.cardNumber").value(DEFAULT_CARD_NUMBER))
            .andExpect(jsonPath("$.cvv").value(DEFAULT_CVV))
            .andExpect(jsonPath("$.expiry").value(DEFAULT_EXPIRY))
            .andExpect(jsonPath("$.pin").value(DEFAULT_PIN))
            .andExpect(jsonPath("$.track2data").value(DEFAULT_TRACK_2_DATA));
    }

    @Test
    @Transactional
    public void getNonExistingCard() throws Exception {
        // Get the card
        restCardMockMvc.perform(get("/api/cards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCard() throws Exception {
        // Initialize the database
        cardRepository.saveAndFlush(card);

        int databaseSizeBeforeUpdate = cardRepository.findAll().size();

        // Update the card
        Card updatedCard = cardRepository.findById(card.getId()).get();
        // Disconnect from session so that the updates on updatedCard are not directly saved in db
        em.detach(updatedCard);
        updatedCard
            .cardDescription(UPDATED_CARD_DESCRIPTION)
            .scheme(UPDATED_SCHEME)
            .type(UPDATED_TYPE)
            .cardNumber(UPDATED_CARD_NUMBER)
            .cvv(UPDATED_CVV)
            .expiry(UPDATED_EXPIRY)
            .pin(UPDATED_PIN)
            .track2data(UPDATED_TRACK_2_DATA);
        CardDTO cardDTO = cardMapper.toDto(updatedCard);

        restCardMockMvc.perform(put("/api/cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cardDTO)))
            .andExpect(status().isOk());

        // Validate the Card in the database
        List<Card> cardList = cardRepository.findAll();
        assertThat(cardList).hasSize(databaseSizeBeforeUpdate);
        Card testCard = cardList.get(cardList.size() - 1);
        assertThat(testCard.getCardDescription()).isEqualTo(UPDATED_CARD_DESCRIPTION);
        assertThat(testCard.getScheme()).isEqualTo(UPDATED_SCHEME);
        assertThat(testCard.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testCard.getCardNumber()).isEqualTo(UPDATED_CARD_NUMBER);
        assertThat(testCard.getCvv()).isEqualTo(UPDATED_CVV);
        assertThat(testCard.getExpiry()).isEqualTo(UPDATED_EXPIRY);
        assertThat(testCard.getPin()).isEqualTo(UPDATED_PIN);
        assertThat(testCard.getTrack2data()).isEqualTo(UPDATED_TRACK_2_DATA);
    }

    @Test
    @Transactional
    public void updateNonExistingCard() throws Exception {
        int databaseSizeBeforeUpdate = cardRepository.findAll().size();

        // Create the Card
        CardDTO cardDTO = cardMapper.toDto(card);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCardMockMvc.perform(put("/api/cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Card in the database
        List<Card> cardList = cardRepository.findAll();
        assertThat(cardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCard() throws Exception {
        // Initialize the database
        cardRepository.saveAndFlush(card);

        int databaseSizeBeforeDelete = cardRepository.findAll().size();

        // Delete the card
        restCardMockMvc.perform(delete("/api/cards/{id}", card.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Card> cardList = cardRepository.findAll();
        assertThat(cardList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
