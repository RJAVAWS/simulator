package com.org.simulator.repository;

import com.org.simulator.domain.MtiConfig;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MtiConfig entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MtiConfigRepository extends JpaRepository<MtiConfig, Long> {

}
