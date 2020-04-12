package com.org.simulator.service.impl;

import com.org.simulator.domain.TestCase;
import com.org.simulator.service.CardService;
import com.org.simulator.domain.Card;
import com.org.simulator.repository.CardRepository;
import com.org.simulator.service.dto.CardDTO;
import com.org.simulator.service.mapper.CardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Card}.
 */
@Service
@Transactional
public class CardServiceImpl implements CardService {

    private final Logger log = LoggerFactory.getLogger(CardServiceImpl.class);

    private final CardRepository cardRepository;

    private final CardMapper cardMapper;

    public CardServiceImpl(CardRepository cardRepository, CardMapper cardMapper) {
        this.cardRepository = cardRepository;
        this.cardMapper = cardMapper;
    }

    /**
     * Save a card.
     *
     * @param cardDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CardDTO save(CardDTO cardDTO) {
        log.debug("Request to save Card : {}", cardDTO);
        Card card = cardMapper.toEntity(cardDTO);
        card = cardRepository.save(card);
        return cardMapper.toDto(card);
    }

    /**
     * Get all the cards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CardDTO> findAll(Pageable pageable, Long bankId) {
        if (bankId != null) {
            log.debug("Request to get all Cards with bank id - " + bankId);
            return cardRepository.findAllByBank_Id(bankId, pageable)
                .map(cardMapper::toDto);
        } else {
            log.debug("Request to get all Cards without bank id");
            return cardRepository.findAll(pageable)
                .map(cardMapper::toDto);
        }
    }

    /**
     * Get all the cards with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<CardDTO> findAllWithEagerRelationships(Pageable pageable) {
        return cardRepository.findAllWithEagerRelationships(pageable).map(cardMapper::toDto);
    }

    /**
     * Get one card by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CardDTO> findOne(Long id) {
        log.debug("Request to get Card : {}", id);
        return cardRepository.findOneWithEagerRelationships(id)
            .map(cardMapper::toDto);
    }

    /**
     * Get the "id" card's Linked Testcases.
     *
     * @param id the id of the entity.
     * @return the List.
     */
    @Override
    public List<Long> findLinkedTestCases(Long id) {
        log.debug("Request to get Card Linked Transactions: {}", id);
        return cardRepository.findById(id).get().getTestCases().stream().map(TestCase::getId).collect(Collectors.toList());
    }


    /**
     * Delete the card by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Card : {}", id);
        cardRepository.deleteById(id);
    }

    @Override
    public Map<Long, String> getKeyValuePair(Long id) {
        if (id != null) {
            return cardRepository.findAllByBank_Id(id).stream().collect(Collectors.toMap(Card::getId, Card::getCardDescription));
        } else {
            return cardRepository.findAll().stream().collect(Collectors.toMap(Card::getId, Card::getCardDescription));
        }
    }
}
