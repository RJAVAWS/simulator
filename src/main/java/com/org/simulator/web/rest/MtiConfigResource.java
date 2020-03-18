package com.org.simulator.web.rest;

import com.org.simulator.service.MtiConfigService;
import com.org.simulator.web.rest.errors.BadRequestAlertException;
import com.org.simulator.service.dto.MtiConfigDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.org.simulator.domain.MtiConfig}.
 */
@RestController
@RequestMapping("/api")
public class MtiConfigResource {

    private final Logger log = LoggerFactory.getLogger(MtiConfigResource.class);

    private static final String ENTITY_NAME = "mtiConfig";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MtiConfigService mtiConfigService;

    public MtiConfigResource(MtiConfigService mtiConfigService) {
        this.mtiConfigService = mtiConfigService;
    }

    /**
     * {@code POST  /mti-configs} : Create a new mtiConfig.
     *
     * @param mtiConfigDTO the mtiConfigDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mtiConfigDTO, or with status {@code 400 (Bad Request)} if the mtiConfig has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/mti-configs")
    public ResponseEntity<MtiConfigDTO> createMtiConfig(@Valid @RequestBody MtiConfigDTO mtiConfigDTO) throws URISyntaxException {
        log.debug("REST request to save MtiConfig : {}", mtiConfigDTO);
        if (mtiConfigDTO.getId() != null) {
            throw new BadRequestAlertException("A new mtiConfig cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MtiConfigDTO result = mtiConfigService.save(mtiConfigDTO);
        return ResponseEntity.created(new URI("/api/mti-configs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /mti-configs} : Updates an existing mtiConfig.
     *
     * @param mtiConfigDTO the mtiConfigDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mtiConfigDTO,
     * or with status {@code 400 (Bad Request)} if the mtiConfigDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mtiConfigDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/mti-configs")
    public ResponseEntity<MtiConfigDTO> updateMtiConfig(@Valid @RequestBody MtiConfigDTO mtiConfigDTO) throws URISyntaxException {
        log.debug("REST request to update MtiConfig : {}", mtiConfigDTO);
        if (mtiConfigDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MtiConfigDTO result = mtiConfigService.save(mtiConfigDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mtiConfigDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /mti-configs} : get all the mtiConfigs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mtiConfigs in body.
     */
    @GetMapping("/mti-configs")
    public ResponseEntity<List<MtiConfigDTO>> getAllMtiConfigs(Pageable pageable) {
        log.debug("REST request to get a page of MtiConfigs");
        Page<MtiConfigDTO> page = mtiConfigService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /mti-configs/:id} : get the "id" mtiConfig.
     *
     * @param id the id of the mtiConfigDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mtiConfigDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/mti-configs/{id}")
    public ResponseEntity<MtiConfigDTO> getMtiConfig(@PathVariable Long id) {
        log.debug("REST request to get MtiConfig : {}", id);
        Optional<MtiConfigDTO> mtiConfigDTO = mtiConfigService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mtiConfigDTO);
    }

    /**
     * {@code DELETE  /mti-configs/:id} : delete the "id" mtiConfig.
     *
     * @param id the id of the mtiConfigDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/mti-configs/{id}")
    public ResponseEntity<Void> deleteMtiConfig(@PathVariable Long id) {
        log.debug("REST request to delete MtiConfig : {}", id);
        mtiConfigService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
