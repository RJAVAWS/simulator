package com.org.simulator.service;

import com.org.simulator.service.dto.TestCaseDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.org.simulator.domain.TestCase}.
 */
public interface TestCaseService {

    /**
     * Save a testCase.
     *
     * @param testCaseDTO the entity to save.
     * @return the persisted entity.
     */
    TestCaseDTO save(TestCaseDTO testCaseDTO);

    /**
     * Get all the testCases.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TestCaseDTO> findAll(Pageable pageable);

    /**
     * Get the "id" testCase.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TestCaseDTO> findOne(Long id);

    /**
     * Delete the "id" testCase.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
