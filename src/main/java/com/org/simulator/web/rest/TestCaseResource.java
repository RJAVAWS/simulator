package com.org.simulator.web.rest;

import com.org.simulator.service.TestCaseService;
import com.org.simulator.web.rest.errors.BadRequestAlertException;
import com.org.simulator.service.dto.TestCaseDTO;

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
 * REST controller for managing {@link com.org.simulator.domain.TestCase}.
 */
@RestController
@RequestMapping("/api")
public class TestCaseResource {

    private final Logger log = LoggerFactory.getLogger(TestCaseResource.class);

    private static final String ENTITY_NAME = "testCase";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TestCaseService testCaseService;

    public TestCaseResource(TestCaseService testCaseService) {
        this.testCaseService = testCaseService;
    }

    /**
     * {@code POST  /test-cases} : Create a new testCase.
     *
     * @param testCaseDTO the testCaseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new testCaseDTO, or with status {@code 400 (Bad Request)} if the testCase has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/test-cases")
    public ResponseEntity<TestCaseDTO> createTestCase(@Valid @RequestBody TestCaseDTO testCaseDTO) throws URISyntaxException {
        log.debug("REST request to save TestCase : {}", testCaseDTO);
        if (testCaseDTO.getId() != null) {
            throw new BadRequestAlertException("A new testCase cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TestCaseDTO result = testCaseService.save(testCaseDTO);
        return ResponseEntity.created(new URI("/api/test-cases/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /test-cases} : Updates an existing testCase.
     *
     * @param testCaseDTO the testCaseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated testCaseDTO,
     * or with status {@code 400 (Bad Request)} if the testCaseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the testCaseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/test-cases")
    public ResponseEntity<TestCaseDTO> updateTestCase(@Valid @RequestBody TestCaseDTO testCaseDTO) throws URISyntaxException {
        log.debug("REST request to update TestCase : {}", testCaseDTO);
        if (testCaseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TestCaseDTO result = testCaseService.save(testCaseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, testCaseDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /test-cases} : get all the testCases.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of testCases in body.
     */
    @GetMapping("/test-cases")
    public ResponseEntity<List<TestCaseDTO>> getAllTestCases(Pageable pageable) {
        log.debug("REST request to get a page of TestCases");
        Page<TestCaseDTO> page = testCaseService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /test-cases/:id} : get the "id" testCase.
     *
     * @param id the id of the testCaseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the testCaseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/test-cases/{id}")
    public ResponseEntity<TestCaseDTO> getTestCase(@PathVariable Long id) {
        log.debug("REST request to get TestCase : {}", id);
        Optional<TestCaseDTO> testCaseDTO = testCaseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(testCaseDTO);
    }

    /**
     * {@code DELETE  /test-cases/:id} : delete the "id" testCase.
     *
     * @param id the id of the testCaseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/test-cases/{id}")
    public ResponseEntity<Void> deleteTestCase(@PathVariable Long id) {
        log.debug("REST request to delete TestCase : {}", id);
        testCaseService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
