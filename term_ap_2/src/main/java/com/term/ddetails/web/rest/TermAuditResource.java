package com.term.ddetails.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.term.ddetails.domain.TermAudit;

import com.term.ddetails.repository.TermAuditRepository;
import com.term.ddetails.web.rest.util.HeaderUtil;
import com.term.ddetails.web.rest.util.PaginationUtil;
import com.term.ddetails.service.dto.TermAuditDTO;
import com.term.ddetails.service.mapper.TermAuditMapper;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing TermAudit.
 */
@RestController
@RequestMapping("/api")
public class TermAuditResource {

    private final Logger log = LoggerFactory.getLogger(TermAuditResource.class);

    private static final String ENTITY_NAME = "termAudit";

    private final TermAuditRepository termAuditRepository;

    private final TermAuditMapper termAuditMapper;

    public TermAuditResource(TermAuditRepository termAuditRepository, TermAuditMapper termAuditMapper) {
        this.termAuditRepository = termAuditRepository;
        this.termAuditMapper = termAuditMapper;
    }

    /**
     * POST  /term-audits : Create a new termAudit.
     *
     * @param termAuditDTO the termAuditDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new termAuditDTO, or with status 400 (Bad Request) if the termAudit has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/term-audits")
    @Timed
    public ResponseEntity<TermAuditDTO> createTermAudit(@Valid @RequestBody TermAuditDTO termAuditDTO) throws URISyntaxException {
        log.debug("REST request to save TermAudit : {}", termAuditDTO);
        if (termAuditDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new termAudit cannot already have an ID")).body(null);
        }
        termAuditDTO.setAuditby(getCurrentUserLogin());
        termAuditDTO.setAuditdate(LocalDate.now() );
        TermAudit termAudit = termAuditMapper.toEntity(termAuditDTO);
        termAudit = termAuditRepository.save(termAudit);
        TermAuditDTO result = termAuditMapper.toDto(termAudit);
        return ResponseEntity.created(new URI("/api/term-audits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /term-audits : Updates an existing termAudit.
     *
     * @param termAuditDTO the termAuditDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated termAuditDTO,
     * or with status 400 (Bad Request) if the termAuditDTO is not valid,
     * or with status 500 (Internal Server Error) if the termAuditDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/term-audits")
    @Timed
    public ResponseEntity<TermAuditDTO> updateTermAudit(@Valid @RequestBody TermAuditDTO termAuditDTO) throws URISyntaxException {
        log.debug("REST request to update TermAudit : {}", termAuditDTO);
        if (termAuditDTO.getId() == null) {
            return createTermAudit(termAuditDTO);
        }
        termAuditDTO.setDob(getCurrentUserLogin());
        termAuditDTO.setSecauditdate(LocalDate.now() );
        TermAudit termAudit = termAuditMapper.toEntity(termAuditDTO);
      
        termAudit = termAuditRepository.save(termAudit);
        TermAuditDTO result = termAuditMapper.toDto(termAudit);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, termAuditDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /term-audits : get all the termAudits.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of termAudits in body
     */
    @GetMapping("/term-audits")
    @Timed
    public ResponseEntity<List<TermAuditDTO>> getAllTermAudits(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of TermAudits");
        Page<TermAudit> page = termAuditRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/term-audits");
        return new ResponseEntity<>(termAuditMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /term-audits/:id : get the "id" termAudit.
     *
     * @param id the id of the termAuditDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the termAuditDTO, or with status 404 (Not Found)
     */
    @GetMapping("/term-audits/{id}")
    @Timed
    public ResponseEntity<TermAuditDTO> getTermAudit(@PathVariable Long id) {
        log.debug("REST request to get TermAudit : {}", id);
        TermAudit termAudit = termAuditRepository.findOne(id);
        TermAuditDTO termAuditDTO = termAuditMapper.toDto(termAudit);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(termAuditDTO));
    }

    /**
     * DELETE  /term-audits/:id : delete the "id" termAudit.
     *
     * @param id the id of the termAuditDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/term-audits/{id}")
    @Timed
    public ResponseEntity<Void> deleteTermAudit(@PathVariable Long id) {
        log.debug("REST request to delete TermAudit : {}", id);
        termAuditRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    @PostMapping("/term-audits/{id}")
    @Timed
    public ResponseEntity<TermAuditDTO> getData(@RequestBody Long mobilenumber) {
        log.debug("REST request to get TermAudit : {}", mobilenumber);
        TermAuditDTO dto = new TermAuditDTO();
      TermAudit audit = termAuditRepository.findBymobilenumber(mobilenumber.toString());
      if(audit== null) {
        Object termAudit = termAuditRepository.getData(mobilenumber);
        if(termAudit== null){
        	  termAudit = termAuditRepository.getDataPostpaid(mobilenumber);
        	  

              Object[] termAuditDTo = ( Object[] )termAudit;
              
              if(termAuditDTo!= null) {
               if(termAuditDTo[0]!= null)
               	dto.setMobilenumber((termAuditDTo[0].toString()));
               if(termAuditDTo[1]!= null)
              	dto.setTermno((termAuditDTo[1].toString()));
               if(termAuditDTo[2]!= null)
               	dto.setDoa(termAuditDTo[2].toString());
               if(termAuditDTo[3]!= null)
                 	dto.setActivationsource((termAuditDTo[3].toString()));
               if(termAuditDTo[7]!= null)
              	dto.setAddressproof((String)termAuditDTo[7]);
               if(termAuditDTo[5]!= null)
              	dto.setNameofcustomer((String)termAuditDTo[5]);
               if(termAuditDTo[9]!= null)
                 	dto.setState((String)termAuditDTo[9]);
               if(termAuditDTo[4]!= null)
                  	dto.setConnectiontype((String)termAuditDTo[4]);
               if(termAuditDTo[8]!= null)
                 	dto.setVendor((String)termAuditDTo[8]);
              	dto.setAddress(termAuditDTo[6]+"");
              }else{
            	  return	ResponseEntity.badRequest()
            	            .headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"mobilenumberexits","Please check the mobile number entered"))
            	            .body(null);
              }
        	  
        }else{
        Object[] termAuditDTo = ( Object[] )termAudit;
        
         if(termAuditDTo!= null) {
        	 
       
         if(termAuditDTo[0]!= null)
         	dto.setMobilenumber((termAuditDTo[0].toString()));
         if(termAuditDTo[1]!= null)
        	dto.setTermno((termAuditDTo[1].toString()));
         if(termAuditDTo[2]!= null)
         	dto.setDoa((termAuditDTo[2].toString()));
         if(termAuditDTo[3]!= null)
          	dto.setActivationsource((termAuditDTo[3].toString()));
         if(termAuditDTo[4]!= null)
        	dto.setVendor(termAuditDTo[4].toString());
         if(termAuditDTo[5]!= null)
         	dto.setNameofcustomer(termAuditDTo[5].toString());
         if(termAuditDTo[6]!= null)
        	dto.setFathername((String)termAuditDTo[6]);
         if(termAuditDTo[7]!= null)
        	dto.setAddress((String)termAuditDTo[7]);
         if(termAuditDTo[8]!= null)
         	dto.setIdentityproof((String)termAuditDTo[8]);
                
         if(termAuditDTo[16]!= null) {
        	 dto.setState((String)termAuditDTo[16]);
         }
         	
         if(termAuditDTo[15]!= null)
           	dto.setAoname((String)termAuditDTo[15]);
         dto.setConnectiontype("Prepaid");
        /* if(termAuditDTo[14]!= null)
            	dto.setActivationdate((String)termAuditDTo[14]);
*/          if(termAuditDTo[13]!= null)
            	dto.setBarcode((String)termAuditDTo[13]);
         
         if(termAuditDTo[12]!= null)
         	dto.setConnection(termAuditDTo[12].toString());
         	
         }else{
       	  return	ResponseEntity.badRequest()
       	            .headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"mobilenumberexits","Please check the mobile number entered"))
       	            .body(null);
         }
        }
      }
        else {
        return	ResponseEntity.badRequest()
            .headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"mobilenumberexits","Mobile number is already entered"))
            .body(null);
        }
      
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(dto)) ;
    }
    
    @PostMapping("/term-audits/findByTermNo")
    @Timed
    public ResponseEntity<TermAuditDTO> findByTermNo(@RequestBody String findBymobNo) {
        log.debug("REST request to get TermAudit : {}", findBymobNo);
        TermAudit termAudit = termAuditRepository.findBymobilenumber(findBymobNo);
        if(termAudit!= null) {
        TermAuditDTO termAuditDTO = termAuditMapper.toDto(termAudit);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(termAuditDTO));
        }else {
        	 return	ResponseEntity.badRequest()
        	            .headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"mobilenumberexits","Mobile number is not processed in First level Please check "))
        	            .body(null);
        }
    }
    public String getCurrentUserLogin() {
        org.springframework.security.core.context.SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String login = null;
        if (authentication != null)
            if (authentication.getPrincipal() instanceof UserDetails)
            	login = ((UserDetails) authentication.getPrincipal()).getUsername();
            else if (authentication.getPrincipal() instanceof String)
            	login = (String) authentication.getPrincipal();
        
        return login; 
        

}
}
