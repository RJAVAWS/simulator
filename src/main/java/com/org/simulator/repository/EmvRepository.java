package com.org.simulator.repository;

import com.org.simulator.domain.Emv;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Emv entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmvRepository extends JpaRepository<Emv, Long> {
}
