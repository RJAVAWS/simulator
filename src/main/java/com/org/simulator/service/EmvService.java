package com.org.simulator.service;

import com.org.simulator.service.dto.EmvDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.org.simulator.domain.Emv}.
 */
public interface EmvService {

    /**
     * Save a emv.
     *
     * @param emvDTO the entity to save.
     * @return the persisted entity.
     */
    EmvDTO save(EmvDTO emvDTO);

    /**
     * Get all the emvs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EmvDTO> findAll(Pageable pageable);

    /**
     * Get the "id" emv.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EmvDTO> findOne(Long id);

    /**
     * Delete the "id" emv.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
