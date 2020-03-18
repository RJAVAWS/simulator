package com.org.simulator.service.impl;

import com.org.simulator.service.TestCaseService;
import com.org.simulator.domain.TestCase;
import com.org.simulator.repository.TestCaseRepository;
import com.org.simulator.service.dto.TestCaseDTO;
import com.org.simulator.service.mapper.TestCaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TestCase}.
 */
@Service
@Transactional
public class TestCaseServiceImpl implements TestCaseService {

    private final Logger log = LoggerFactory.getLogger(TestCaseServiceImpl.class);

    private final TestCaseRepository testCaseRepository;

    private final TestCaseMapper testCaseMapper;

    public TestCaseServiceImpl(TestCaseRepository testCaseRepository, TestCaseMapper testCaseMapper) {
        this.testCaseRepository = testCaseRepository;
        this.testCaseMapper = testCaseMapper;
    }

    /**
     * Save a testCase.
     *
     * @param testCaseDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TestCaseDTO save(TestCaseDTO testCaseDTO) {
        log.debug("Request to save TestCase : {}", testCaseDTO);
        TestCase testCase = testCaseMapper.toEntity(testCaseDTO);
        testCase = testCaseRepository.save(testCase);
        return testCaseMapper.toDto(testCase);
    }

    /**
     * Get all the testCases.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TestCaseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TestCases");
        return testCaseRepository.findAll(pageable)
            .map(testCaseMapper::toDto);
    }

    /**
     * Get one testCase by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TestCaseDTO> findOne(Long id) {
        log.debug("Request to get TestCase : {}", id);
        return testCaseRepository.findById(id)
            .map(testCaseMapper::toDto);
    }

    /**
     * Delete the testCase by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TestCase : {}", id);
        testCaseRepository.deleteById(id);
    }
}
