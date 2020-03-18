package com.org.simulator.service;

import com.org.simulator.service.dto.KeyConfigDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.org.simulator.domain.KeyConfig}.
 */
public interface KeyConfigService {

    /**
     * Save a keyConfig.
     *
     * @param keyConfigDTO the entity to save.
     * @return the persisted entity.
     */
    KeyConfigDTO save(KeyConfigDTO keyConfigDTO);

    /**
     * Get all the keyConfigs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<KeyConfigDTO> findAll(Pageable pageable);

    /**
     * Get the "id" keyConfig.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<KeyConfigDTO> findOne(Long id);

    /**
     * Delete the "id" keyConfig.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
