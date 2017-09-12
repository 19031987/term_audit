package com.term.ddetails.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.term.ddetails.domain.RejectedReasonType;
import com.term.ddetails.domain.RejectedReasons;

import com.term.ddetails.repository.RejectedReasonsRepository;
import com.term.ddetails.web.rest.util.HeaderUtil;
import com.term.ddetails.web.rest.util.PaginationUtil;
import com.term.ddetails.service.dto.RejectedReasonsDTO;
import com.term.ddetails.service.mapper.RejectedReasonsMapper;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing RejectedReasons.
 */
@RestController
@RequestMapping("/api")
public class RejectedReasonsResource {

    private final Logger log = LoggerFactory.getLogger(RejectedReasonsResource.class);

    private static final String ENTITY_NAME = "rejectedReasons";

    private final RejectedReasonsRepository rejectedReasonsRepository;

    private final RejectedReasonsMapper rejectedReasonsMapper;

    public RejectedReasonsResource(RejectedReasonsRepository rejectedReasonsRepository, RejectedReasonsMapper rejectedReasonsMapper) {
        this.rejectedReasonsRepository = rejectedReasonsRepository;
        this.rejectedReasonsMapper = rejectedReasonsMapper;
    }

    /**
     * POST  /rejected-reasons : Create a new rejectedReasons.
     *
     * @param rejectedReasonsDTO the rejectedReasonsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new rejectedReasonsDTO, or with status 400 (Bad Request) if the rejectedReasons has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/rejected-reasons")
    @Timed
    public ResponseEntity<RejectedReasonsDTO> createRejectedReasons(@RequestBody RejectedReasonsDTO rejectedReasonsDTO) throws URISyntaxException {
        log.debug("REST request to save RejectedReasons : {}", rejectedReasonsDTO);
        if (rejectedReasonsDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new rejectedReasons cannot already have an ID")).body(null);
        }
        rejectedReasonsDTO.setRejectedReasonTypeId(rejectedReasonsDTO.getReasonTypesId());
        RejectedReasons rejectedReasons = rejectedReasonsMapper.toEntity(rejectedReasonsDTO);
        RejectedReasonType reason = rejectedReasons.getRejectedReasonType();
        rejectedReasons.setReasonTypes(reason);
        
        rejectedReasons = rejectedReasonsRepository.save(rejectedReasons);
        RejectedReasonsDTO result = rejectedReasonsMapper.toDto(rejectedReasons);
        return ResponseEntity.created(new URI("/api/rejected-reasons/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /rejected-reasons : Updates an existing rejectedReasons.
     *
     * @param rejectedReasonsDTO the rejectedReasonsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated rejectedReasonsDTO,
     * or with status 400 (Bad Request) if the rejectedReasonsDTO is not valid,
     * or with status 500 (Internal Server Error) if the rejectedReasonsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/rejected-reasons")
    @Timed
    public ResponseEntity<RejectedReasonsDTO> updateRejectedReasons(@RequestBody RejectedReasonsDTO rejectedReasonsDTO) throws URISyntaxException {
        log.debug("REST request to update RejectedReasons : {}", rejectedReasonsDTO);
        if (rejectedReasonsDTO.getId() == null) {
            return createRejectedReasons(rejectedReasonsDTO);
        }
        RejectedReasons rejectedReasons = rejectedReasonsMapper.toEntity(rejectedReasonsDTO);
        rejectedReasons = rejectedReasonsRepository.save(rejectedReasons);
        RejectedReasonsDTO result = rejectedReasonsMapper.toDto(rejectedReasons);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, rejectedReasonsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /rejected-reasons : get all the rejectedReasons.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of rejectedReasons in body
     */
    @GetMapping("/rejected-reasons")
    @Timed
    public ResponseEntity<List<RejectedReasonsDTO>> getAllRejectedReasons(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of RejectedReasons");
        Page<RejectedReasons> page = rejectedReasonsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/rejected-reasons");
        return new ResponseEntity<>(rejectedReasonsMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /rejected-reasons/:id : get the "id" rejectedReasons.
     *
     * @param id the id of the rejectedReasonsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the rejectedReasonsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/rejected-reasons/{id}")
    @Timed
    public ResponseEntity<RejectedReasonsDTO> getRejectedReasons(@PathVariable Long id) {
        log.debug("REST request to get RejectedReasons : {}", id);
        RejectedReasons rejectedReasons = rejectedReasonsRepository.findOne(id);
        RejectedReasonsDTO rejectedReasonsDTO = rejectedReasonsMapper.toDto(rejectedReasons);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(rejectedReasonsDTO));
    }

    /**
     * DELETE  /rejected-reasons/:id : delete the "id" rejectedReasons.
     *
     * @param id the id of the rejectedReasonsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/rejected-reasons/{id}")
    @Timed
    public ResponseEntity<Void> deleteRejectedReasons(@PathVariable Long id) {
        log.debug("REST request to delete RejectedReasons : {}", id);
        rejectedReasonsRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    @PostMapping("/rejected-reasons/getReasons")
    @Timed
    public List<RejectedReasonsDTO> getReasons(@RequestBody Long rejectedReasonsDTO) throws URISyntaxException {
        List<RejectedReasons>rejectedReasons = rejectedReasonsRepository.findByRejectedReasonTypeId(rejectedReasonsDTO);
        RejectedReasonsDTO reason = new RejectedReasonsDTO();
        List<RejectedReasonsDTO> reasonList = new ArrayList<RejectedReasonsDTO>();
        for(RejectedReasons reasons : rejectedReasons){
        	reason = new RejectedReasonsDTO();
        	reason.setReasonTypesId(reasons.getReasonTypes().getId());
        	reason.setRejectReasons(reasons.getRejectReasons());
        	reasonList.add(reason);
        	
        }
        return reasonList;
    }
}
