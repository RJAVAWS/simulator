package com.org.simulator.repository;

import com.org.simulator.domain.TestCase;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TestCase entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TestCaseRepository extends JpaRepository<TestCase, Long> {

}
