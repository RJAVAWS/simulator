package com.org.simulator.service.impl;

import com.org.simulator.service.KeyConfigService;
import com.org.simulator.domain.KeyConfig;
import com.org.simulator.repository.KeyConfigRepository;
import com.org.simulator.service.dto.KeyConfigDTO;
import com.org.simulator.service.mapper.KeyConfigMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link KeyConfig}.
 */
@Service
@Transactional
public class KeyConfigServiceImpl implements KeyConfigService {

    private final Logger log = LoggerFactory.getLogger(KeyConfigServiceImpl.class);

    private final KeyConfigRepository keyConfigRepository;

    private final KeyConfigMapper keyConfigMapper;

    public KeyConfigServiceImpl(KeyConfigRepository keyConfigRepository, KeyConfigMapper keyConfigMapper) {
        this.keyConfigRepository = keyConfigRepository;
        this.keyConfigMapper = keyConfigMapper;
    }

    /**
     * Save a keyConfig.
     *
     * @param keyConfigDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public KeyConfigDTO save(KeyConfigDTO keyConfigDTO) {
        log.debug("Request to save KeyConfig : {}", keyConfigDTO);
        KeyConfig keyConfig = keyConfigMapper.toEntity(keyConfigDTO);
        keyConfig = keyConfigRepository.save(keyConfig);
        return keyConfigMapper.toDto(keyConfig);
    }

    /**
     * Get all the keyConfigs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<KeyConfigDTO> findAll(Pageable pageable) {
        log.debug("Request to get all KeyConfigs");
        return keyConfigRepository.findAll(pageable)
            .map(keyConfigMapper::toDto);
    }

    /**
     * Get one keyConfig by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<KeyConfigDTO> findOne(Long id) {
        log.debug("Request to get KeyConfig : {}", id);
        return keyConfigRepository.findById(id)
            .map(keyConfigMapper::toDto);
    }

    /**
     * Delete the keyConfig by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete KeyConfig : {}", id);
        keyConfigRepository.deleteById(id);
    }
}
