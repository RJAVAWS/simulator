package com.org.simulator.service;

import com.org.simulator.service.dto.CardDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.org.simulator.domain.Card}.
 */
public interface CardService extends GenericService {

    /**
     * Save a card.
     *
     * @param cardDTO the entity to save.
     * @return the persisted entity.
     */
    CardDTO save(CardDTO cardDTO);

    /**
     * Get all the cards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CardDTO> findAll(Pageable pageable, Long bankId);

    /**
     * Get all the cards with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<CardDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" card.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CardDTO> findOne(Long id);

    /**
     * Get the "id" card's Linked Testcases.
     *
     * @param id the id of the entity.
     * @return the List.
     */
    List<Long> findLinkedTestCases(Long id);

    /**
     * Delete the "id" card.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
