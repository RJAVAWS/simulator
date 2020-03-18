package com.org.simulator.repository;

import com.org.simulator.domain.KeyConfig;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the KeyConfig entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KeyConfigRepository extends JpaRepository<KeyConfig, Long> {

}
