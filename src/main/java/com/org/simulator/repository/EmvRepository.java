package com.org.simulator.repository;

import com.org.simulator.domain.Emv;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Emv entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmvRepository extends JpaRepository<Emv, Long> {

    List<Emv> findAllByBank_Id(Long id);

    Page<Emv> findAllByBank_Id(Long id, Pageable pageable);

}
