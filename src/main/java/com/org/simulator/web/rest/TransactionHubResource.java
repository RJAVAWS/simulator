package com.org.simulator.web.rest;

import com.org.simulator.service.TransactionService;
import com.org.simulator.service.dto.BankDTO;
import com.org.simulator.service.dto.TransactionDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TransactionHubResource {

    private final Logger log = LoggerFactory.getLogger(TransactionHubResource.class);

    private static final String ENTITY_NAME = "transactionHub";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TransactionService transactionService;

    public TransactionHubResource(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * {@code GET  /transactionhub/:id} : get the "id" transaction.
     *
     * @param bankId the id of the bank to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the transactionHubDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/transactionHubDetails")
    public ResponseEntity<TransactionDTO> getTransactionHubDetails(@RequestParam Long bankId) {
        log.debug("REST request to get TransactionHub : {}", bankId);
        Optional<TransactionDTO> transactionDTO = transactionService.findOne(bankId);
        return ResponseUtil.wrapOrNotFound(transactionDTO);
    }
}
