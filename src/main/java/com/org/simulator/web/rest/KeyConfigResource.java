package com.org.simulator.web.rest;

import com.org.simulator.service.KeyConfigService;
import com.org.simulator.web.rest.errors.BadRequestAlertException;
import com.org.simulator.service.dto.KeyConfigDTO;

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
 * REST controller for managing {@link com.org.simulator.domain.KeyConfig}.
 */
@RestController
@RequestMapping("/api")
public class KeyConfigResource {

    private final Logger log = LoggerFactory.getLogger(KeyConfigResource.class);

    private static final String ENTITY_NAME = "keyConfig";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final KeyConfigService keyConfigService;

    public KeyConfigResource(KeyConfigService keyConfigService) {
        this.keyConfigService = keyConfigService;
    }

    /**
     * {@code POST  /key-configs} : Create a new keyConfig.
     *
     * @param keyConfigDTO the keyConfigDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new keyConfigDTO, or with status {@code 400 (Bad Request)} if the keyConfig has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/key-configs")
    public ResponseEntity<KeyConfigDTO> createKeyConfig(@Valid @RequestBody KeyConfigDTO keyConfigDTO) throws URISyntaxException {
        log.debug("REST request to save KeyConfig : {}", keyConfigDTO);
        if (keyConfigDTO.getId() != null) {
            throw new BadRequestAlertException("A new keyConfig cannot already have an ID", ENTITY_NAME, "idexists");
        }
        KeyConfigDTO result = keyConfigService.save(keyConfigDTO);
        return ResponseEntity.created(new URI("/api/key-configs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /key-configs} : Updates an existing keyConfig.
     *
     * @param keyConfigDTO the keyConfigDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated keyConfigDTO,
     * or with status {@code 400 (Bad Request)} if the keyConfigDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the keyConfigDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/key-configs")
    public ResponseEntity<KeyConfigDTO> updateKeyConfig(@Valid @RequestBody KeyConfigDTO keyConfigDTO) throws URISyntaxException {
        log.debug("REST request to update KeyConfig : {}", keyConfigDTO);
        if (keyConfigDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        KeyConfigDTO result = keyConfigService.save(keyConfigDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, keyConfigDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /key-configs} : get all the keyConfigs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of keyConfigs in body.
     */
    @GetMapping("/key-configs")
    public ResponseEntity<List<KeyConfigDTO>> getAllKeyConfigs(Pageable pageable, @RequestParam(required = false) Long bankId) {
        log.debug("REST request to get a page of KeyConfigs");
        Page<KeyConfigDTO> page = keyConfigService.findAll(pageable, bankId);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /key-configs/:id} : get the "id" keyConfig.
     *
     * @param id the id of the keyConfigDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the keyConfigDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/key-configs/{id}")
    public ResponseEntity<KeyConfigDTO> getKeyConfig(@PathVariable Long id) {
        log.debug("REST request to get KeyConfig : {}", id);
        Optional<KeyConfigDTO> keyConfigDTO = keyConfigService.findOne(id);
        return ResponseUtil.wrapOrNotFound(keyConfigDTO);
    }

    /**
     * {@code DELETE  /key-configs/:id} : delete the "id" keyConfig.
     *
     * @param id the id of the keyConfigDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/key-configs/{id}")
    public ResponseEntity<Void> deleteKeyConfig(@PathVariable Long id) {
        log.debug("REST request to delete KeyConfig : {}", id);
        keyConfigService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
