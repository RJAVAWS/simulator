package com.org.simulator.service;

import com.org.simulator.service.dto.MtiConfigDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.org.simulator.domain.MtiConfig}.
 */
public interface MtiConfigService {

    /**
     * Save a mtiConfig.
     *
     * @param mtiConfigDTO the entity to save.
     * @return the persisted entity.
     */
    MtiConfigDTO save(MtiConfigDTO mtiConfigDTO);

    /**
     * Get all the mtiConfigs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MtiConfigDTO> findAll(Pageable pageable);

    /**
     * Get the "id" mtiConfig.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MtiConfigDTO> findOne(Long id);

    /**
     * Delete the "id" mtiConfig.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
