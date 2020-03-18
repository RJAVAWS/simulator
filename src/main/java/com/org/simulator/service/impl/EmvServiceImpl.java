package com.org.simulator.service.impl;

import com.org.simulator.service.EmvService;
import com.org.simulator.domain.Emv;
import com.org.simulator.repository.EmvRepository;
import com.org.simulator.service.dto.EmvDTO;
import com.org.simulator.service.mapper.EmvMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Emv}.
 */
@Service
@Transactional
public class EmvServiceImpl implements EmvService {

    private final Logger log = LoggerFactory.getLogger(EmvServiceImpl.class);

    private final EmvRepository emvRepository;

    private final EmvMapper emvMapper;

    public EmvServiceImpl(EmvRepository emvRepository, EmvMapper emvMapper) {
        this.emvRepository = emvRepository;
        this.emvMapper = emvMapper;
    }

    /**
     * Save a emv.
     *
     * @param emvDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EmvDTO save(EmvDTO emvDTO) {
        log.debug("Request to save Emv : {}", emvDTO);
        Emv emv = emvMapper.toEntity(emvDTO);
        emv = emvRepository.save(emv);
        return emvMapper.toDto(emv);
    }

    /**
     * Get all the emvs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EmvDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Emvs");
        return emvRepository.findAll(pageable)
            .map(emvMapper::toDto);
    }

    /**
     * Get one emv by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EmvDTO> findOne(Long id) {
        log.debug("Request to get Emv : {}", id);
        return emvRepository.findById(id)
            .map(emvMapper::toDto);
    }

    /**
     * Delete the emv by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Emv : {}", id);
        emvRepository.deleteById(id);
    }
}
