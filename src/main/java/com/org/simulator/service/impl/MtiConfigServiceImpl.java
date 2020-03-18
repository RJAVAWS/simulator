package com.org.simulator.service.impl;

import com.org.simulator.service.MtiConfigService;
import com.org.simulator.domain.MtiConfig;
import com.org.simulator.repository.MtiConfigRepository;
import com.org.simulator.service.dto.MtiConfigDTO;
import com.org.simulator.service.mapper.MtiConfigMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MtiConfig}.
 */
@Service
@Transactional
public class MtiConfigServiceImpl implements MtiConfigService {

    private final Logger log = LoggerFactory.getLogger(MtiConfigServiceImpl.class);

    private final MtiConfigRepository mtiConfigRepository;

    private final MtiConfigMapper mtiConfigMapper;

    public MtiConfigServiceImpl(MtiConfigRepository mtiConfigRepository, MtiConfigMapper mtiConfigMapper) {
        this.mtiConfigRepository = mtiConfigRepository;
        this.mtiConfigMapper = mtiConfigMapper;
    }

    /**
     * Save a mtiConfig.
     *
     * @param mtiConfigDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public MtiConfigDTO save(MtiConfigDTO mtiConfigDTO) {
        log.debug("Request to save MtiConfig : {}", mtiConfigDTO);
        MtiConfig mtiConfig = mtiConfigMapper.toEntity(mtiConfigDTO);
        mtiConfig = mtiConfigRepository.save(mtiConfig);
        return mtiConfigMapper.toDto(mtiConfig);
    }

    /**
     * Get all the mtiConfigs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MtiConfigDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MtiConfigs");
        return mtiConfigRepository.findAll(pageable)
            .map(mtiConfigMapper::toDto);
    }

    /**
     * Get one mtiConfig by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MtiConfigDTO> findOne(Long id) {
        log.debug("Request to get MtiConfig : {}", id);
        return mtiConfigRepository.findById(id)
            .map(mtiConfigMapper::toDto);
    }

    /**
     * Delete the mtiConfig by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MtiConfig : {}", id);
        mtiConfigRepository.deleteById(id);
    }
}
