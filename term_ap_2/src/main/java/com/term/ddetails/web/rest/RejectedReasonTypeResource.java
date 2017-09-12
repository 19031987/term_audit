package com.term.ddetails.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.term.ddetails.domain.RejectedReasonType;

import com.term.ddetails.repository.RejectedReasonTypeRepository;
import com.term.ddetails.web.rest.util.HeaderUtil;
import com.term.ddetails.web.rest.util.PaginationUtil;
import com.term.ddetails.service.dto.RejectedReasonTypeDTO;
import com.term.ddetails.service.mapper.RejectedReasonTypeMapper;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing RejectedReasonType.
 */
@RestController
@RequestMapping("/api")
public class RejectedReasonTypeResource {

    private final Logger log = LoggerFactory.getLogger(RejectedReasonTypeResource.class);

    private static final String ENTITY_NAME = "rejectedReasonType";

    private final RejectedReasonTypeRepository rejectedReasonTypeRepository;

    private final RejectedReasonTypeMapper rejectedReasonTypeMapper;

    public RejectedReasonTypeResource(RejectedReasonTypeRepository rejectedReasonTypeRepository, RejectedReasonTypeMapper rejectedReasonTypeMapper) {
        this.rejectedReasonTypeRepository = rejectedReasonTypeRepository;
        this.rejectedReasonTypeMapper = rejectedReasonTypeMapper;
    }

    /**
     * POST  /rejected-reason-types : Create a new rejectedReasonType.
     *
     * @param rejectedReasonTypeDTO the rejectedReasonTypeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new rejectedReasonTypeDTO, or with status 400 (Bad Request) if the rejectedReasonType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/rejected-reason-types")
    @Timed
    public ResponseEntity<RejectedReasonTypeDTO> createRejectedReasonType(@RequestBody RejectedReasonTypeDTO rejectedReasonTypeDTO) throws URISyntaxException {
        log.debug("REST request to save RejectedReasonType : {}", rejectedReasonTypeDTO);
        if (rejectedReasonTypeDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new rejectedReasonType cannot already have an ID")).body(null);
        }
        RejectedReasonType rejectedReasonType = rejectedReasonTypeMapper.toEntity(rejectedReasonTypeDTO);
        rejectedReasonType = rejectedReasonTypeRepository.save(rejectedReasonType);
        RejectedReasonTypeDTO result = rejectedReasonTypeMapper.toDto(rejectedReasonType);
        return ResponseEntity.created(new URI("/api/rejected-reason-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /rejected-reason-types : Updates an existing rejectedReasonType.
     *
     * @param rejectedReasonTypeDTO the rejectedReasonTypeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated rejectedReasonTypeDTO,
     * or with status 400 (Bad Request) if the rejectedReasonTypeDTO is not valid,
     * or with status 500 (Internal Server Error) if the rejectedReasonTypeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/rejected-reason-types")
    @Timed
    public ResponseEntity<RejectedReasonTypeDTO> updateRejectedReasonType(@RequestBody RejectedReasonTypeDTO rejectedReasonTypeDTO) throws URISyntaxException {
        log.debug("REST request to update RejectedReasonType : {}", rejectedReasonTypeDTO);
        if (rejectedReasonTypeDTO.getId() == null) {
            return createRejectedReasonType(rejectedReasonTypeDTO);
        }
        RejectedReasonType rejectedReasonType = rejectedReasonTypeMapper.toEntity(rejectedReasonTypeDTO);
        rejectedReasonType = rejectedReasonTypeRepository.save(rejectedReasonType);
        RejectedReasonTypeDTO result = rejectedReasonTypeMapper.toDto(rejectedReasonType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, rejectedReasonTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /rejected-reason-types : get all the rejectedReasonTypes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of rejectedReasonTypes in body
     */
    @GetMapping("/rejected-reason-types")
    @Timed
    public ResponseEntity<List<RejectedReasonTypeDTO>> getAllRejectedReasonTypes(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of RejectedReasonTypes");
        Page<RejectedReasonType> page = rejectedReasonTypeRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/rejected-reason-types");
        return new ResponseEntity<>(rejectedReasonTypeMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /rejected-reason-types/:id : get the "id" rejectedReasonType.
     *
     * @param id the id of the rejectedReasonTypeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the rejectedReasonTypeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/rejected-reason-types/{id}")
    @Timed
    public ResponseEntity<RejectedReasonTypeDTO> getRejectedReasonType(@PathVariable Long id) {
        log.debug("REST request to get RejectedReasonType : {}", id);
        RejectedReasonType rejectedReasonType = rejectedReasonTypeRepository.findOne(id);
        RejectedReasonTypeDTO rejectedReasonTypeDTO = rejectedReasonTypeMapper.toDto(rejectedReasonType);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(rejectedReasonTypeDTO));
    }

    /**
     * DELETE  /rejected-reason-types/:id : delete the "id" rejectedReasonType.
     *
     * @param id the id of the rejectedReasonTypeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/rejected-reason-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteRejectedReasonType(@PathVariable Long id) {
        log.debug("REST request to delete RejectedReasonType : {}", id);
        rejectedReasonTypeRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
