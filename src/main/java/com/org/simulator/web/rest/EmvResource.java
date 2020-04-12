package com.org.simulator.web.rest;

import com.org.simulator.service.EmvService;
import com.org.simulator.web.rest.errors.BadRequestAlertException;
import com.org.simulator.service.dto.EmvDTO;

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
import java.util.Map;
import java.util.Optional;

/**
 * REST controller for managing {@link com.org.simulator.domain.Emv}.
 */
@RestController
@RequestMapping("/api")
public class EmvResource {

    private final Logger log = LoggerFactory.getLogger(EmvResource.class);

    private static final String ENTITY_NAME = "emv";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EmvService emvService;

    public EmvResource(EmvService emvService) {
        this.emvService = emvService;
    }

    /**
     * {@code POST  /emvs} : Create a new emv.
     *
     * @param emvDTO the emvDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new emvDTO, or with status {@code 400 (Bad Request)} if the emv has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/emvs")
    public ResponseEntity<EmvDTO> createEmv(@Valid @RequestBody EmvDTO emvDTO) throws URISyntaxException {
        log.debug("REST request to save Emv : {}", emvDTO);
        if (emvDTO.getId() != null) {
            throw new BadRequestAlertException("A new emv cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EmvDTO result = emvService.save(emvDTO);
        return ResponseEntity.created(new URI("/api/emvs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /emvs} : Updates an existing emv.
     *
     * @param emvDTO the emvDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated emvDTO,
     * or with status {@code 400 (Bad Request)} if the emvDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the emvDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/emvs")
    public ResponseEntity<EmvDTO> updateEmv(@Valid @RequestBody EmvDTO emvDTO) throws URISyntaxException {
        log.debug("REST request to update Emv : {}", emvDTO);
        if (emvDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EmvDTO result = emvService.save(emvDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, emvDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /emvs} : get all the emvs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of emvs in body.
     */
    @GetMapping("/emvs")
    public ResponseEntity<List<EmvDTO>> getAllEmvs(Pageable pageable, @RequestParam(required = false) Long bankId) {
        log.debug("REST request to get a page of Emvs");
        Page<EmvDTO> page = emvService.findAll(pageable, bankId);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /banks/kvpairs} : get all the env id and name pairs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of emv id and names in body.
     */
    @GetMapping("/emvs/kvpairs")
    public ResponseEntity<Map<Long, String>> getKvpairs(@RequestParam(required = false) Long id) {
        log.debug("REST request to get all pairs of emv id and name");
        return ResponseEntity.ok().body(emvService.getKeyValuePair(id));
    }

    /**
     * {@code GET  /emvs/:id} : get the "id" emv.
     *
     * @param id the id of the emvDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the emvDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/emvs/{id}")
    public ResponseEntity<EmvDTO> getEmv(@PathVariable Long id) {
        log.debug("REST request to get Emv : {}", id);
        Optional<EmvDTO> emvDTO = emvService.findOne(id);
        return ResponseUtil.wrapOrNotFound(emvDTO);
    }

    /**
     * {@code DELETE  /emvs/:id} : delete the "id" emv.
     *
     * @param id the id of the emvDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/emvs/{id}")
    public ResponseEntity<Void> deleteEmv(@PathVariable Long id) {
        log.debug("REST request to delete Emv : {}", id);
        emvService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
