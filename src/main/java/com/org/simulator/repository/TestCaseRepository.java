package com.org.simulator.repository;

import com.org.simulator.domain.TestCase;

import com.org.simulator.domain.enumeration.PsdnType;
import com.org.simulator.domain.enumeration.ReqResType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TestCase entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TestCaseRepository extends JpaRepository<TestCase, Long> {

    Page<TestCase> findAllByReqResTypeAndPsdnType(ReqResType reqResType, PsdnType psdnType, Pageable pageable);

}
