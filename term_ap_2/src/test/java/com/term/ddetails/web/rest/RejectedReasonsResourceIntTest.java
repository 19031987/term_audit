package com.term.ddetails.web.rest;

import com.term.ddetails.TermAuditAoApp;

import com.term.ddetails.domain.RejectedReasons;
import com.term.ddetails.repository.RejectedReasonsRepository;
import com.term.ddetails.service.dto.RejectedReasonsDTO;
import com.term.ddetails.service.mapper.RejectedReasonsMapper;
import com.term.ddetails.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the RejectedReasonsResource REST controller.
 *
 * @see RejectedReasonsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TermAuditAoApp.class)
public class RejectedReasonsResourceIntTest {

    private static final Integer DEFAULT_FKREASON_TYPE = 1;
    private static final Integer UPDATED_FKREASON_TYPE = 2;

    private static final String DEFAULT_REJECT_REASONS = "AAAAAAAAAA";
    private static final String UPDATED_REJECT_REASONS = "BBBBBBBBBB";

    @Autowired
    private RejectedReasonsRepository rejectedReasonsRepository;

    @Autowired
    private RejectedReasonsMapper rejectedReasonsMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRejectedReasonsMockMvc;

    private RejectedReasons rejectedReasons;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        RejectedReasonsResource rejectedReasonsResource = new RejectedReasonsResource(rejectedReasonsRepository, rejectedReasonsMapper);
        this.restRejectedReasonsMockMvc = MockMvcBuilders.standaloneSetup(rejectedReasonsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RejectedReasons createEntity(EntityManager em) {
        RejectedReasons rejectedReasons = new RejectedReasons()
  //          .fkreasonType(DEFAULT_FKREASON_TYPE)
            .rejectReasons(DEFAULT_REJECT_REASONS);
        return rejectedReasons;
    }

    @Before
    public void initTest() {
        rejectedReasons = createEntity(em);
    }

    @Test
    @Transactional
    public void createRejectedReasons() throws Exception {
        int databaseSizeBeforeCreate = rejectedReasonsRepository.findAll().size();

        // Create the RejectedReasons
        RejectedReasonsDTO rejectedReasonsDTO = rejectedReasonsMapper.toDto(rejectedReasons);
        restRejectedReasonsMockMvc.perform(post("/api/rejected-reasons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rejectedReasonsDTO)))
            .andExpect(status().isCreated());

        // Validate the RejectedReasons in the database
        List<RejectedReasons> rejectedReasonsList = rejectedReasonsRepository.findAll();
        assertThat(rejectedReasonsList).hasSize(databaseSizeBeforeCreate + 1);
        RejectedReasons testRejectedReasons = rejectedReasonsList.get(rejectedReasonsList.size() - 1);
  //      assertThat(testRejectedReasons.getFkreasonType()).isEqualTo(DEFAULT_FKREASON_TYPE);
        assertThat(testRejectedReasons.getRejectReasons()).isEqualTo(DEFAULT_REJECT_REASONS);
    }

    @Test
    @Transactional
    public void createRejectedReasonsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = rejectedReasonsRepository.findAll().size();

        // Create the RejectedReasons with an existing ID
        rejectedReasons.setId(1L);
        RejectedReasonsDTO rejectedReasonsDTO = rejectedReasonsMapper.toDto(rejectedReasons);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRejectedReasonsMockMvc.perform(post("/api/rejected-reasons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rejectedReasonsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<RejectedReasons> rejectedReasonsList = rejectedReasonsRepository.findAll();
        assertThat(rejectedReasonsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRejectedReasons() throws Exception {
        // Initialize the database
        rejectedReasonsRepository.saveAndFlush(rejectedReasons);

        // Get all the rejectedReasonsList
        restRejectedReasonsMockMvc.perform(get("/api/rejected-reasons?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rejectedReasons.getId().intValue())))
            .andExpect(jsonPath("$.[*].fkreasonType").value(hasItem(DEFAULT_FKREASON_TYPE)))
            .andExpect(jsonPath("$.[*].rejectReasons").value(hasItem(DEFAULT_REJECT_REASONS.toString())));
    }

    @Test
    @Transactional
    public void getRejectedReasons() throws Exception {
        // Initialize the database
        rejectedReasonsRepository.saveAndFlush(rejectedReasons);

        // Get the rejectedReasons
        restRejectedReasonsMockMvc.perform(get("/api/rejected-reasons/{id}", rejectedReasons.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(rejectedReasons.getId().intValue()))
            .andExpect(jsonPath("$.fkreasonType").value(DEFAULT_FKREASON_TYPE))
            .andExpect(jsonPath("$.rejectReasons").value(DEFAULT_REJECT_REASONS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRejectedReasons() throws Exception {
        // Get the rejectedReasons
        restRejectedReasonsMockMvc.perform(get("/api/rejected-reasons/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRejectedReasons() throws Exception {
        // Initialize the database
        rejectedReasonsRepository.saveAndFlush(rejectedReasons);
        int databaseSizeBeforeUpdate = rejectedReasonsRepository.findAll().size();

        // Update the rejectedReasons
        RejectedReasons updatedRejectedReasons = rejectedReasonsRepository.findOne(rejectedReasons.getId());
        updatedRejectedReasons
    //        .fkreasonType(UPDATED_FKREASON_TYPE)
            .rejectReasons(UPDATED_REJECT_REASONS);
        RejectedReasonsDTO rejectedReasonsDTO = rejectedReasonsMapper.toDto(updatedRejectedReasons);

        restRejectedReasonsMockMvc.perform(put("/api/rejected-reasons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rejectedReasonsDTO)))
            .andExpect(status().isOk());

        // Validate the RejectedReasons in the database
        List<RejectedReasons> rejectedReasonsList = rejectedReasonsRepository.findAll();
        assertThat(rejectedReasonsList).hasSize(databaseSizeBeforeUpdate);
        RejectedReasons testRejectedReasons = rejectedReasonsList.get(rejectedReasonsList.size() - 1);
 //       assertThat(testRejectedReasons.getFkreasonType()).isEqualTo(UPDATED_FKREASON_TYPE);
        assertThat(testRejectedReasons.getRejectReasons()).isEqualTo(UPDATED_REJECT_REASONS);
    }

    @Test
    @Transactional
    public void updateNonExistingRejectedReasons() throws Exception {
        int databaseSizeBeforeUpdate = rejectedReasonsRepository.findAll().size();

        // Create the RejectedReasons
        RejectedReasonsDTO rejectedReasonsDTO = rejectedReasonsMapper.toDto(rejectedReasons);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRejectedReasonsMockMvc.perform(put("/api/rejected-reasons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rejectedReasonsDTO)))
            .andExpect(status().isCreated());

        // Validate the RejectedReasons in the database
        List<RejectedReasons> rejectedReasonsList = rejectedReasonsRepository.findAll();
        assertThat(rejectedReasonsList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteRejectedReasons() throws Exception {
        // Initialize the database
        rejectedReasonsRepository.saveAndFlush(rejectedReasons);
        int databaseSizeBeforeDelete = rejectedReasonsRepository.findAll().size();

        // Get the rejectedReasons
        restRejectedReasonsMockMvc.perform(delete("/api/rejected-reasons/{id}", rejectedReasons.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RejectedReasons> rejectedReasonsList = rejectedReasonsRepository.findAll();
        assertThat(rejectedReasonsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RejectedReasons.class);
        RejectedReasons rejectedReasons1 = new RejectedReasons();
        rejectedReasons1.setId(1L);
        RejectedReasons rejectedReasons2 = new RejectedReasons();
        rejectedReasons2.setId(rejectedReasons1.getId());
        assertThat(rejectedReasons1).isEqualTo(rejectedReasons2);
        rejectedReasons2.setId(2L);
        assertThat(rejectedReasons1).isNotEqualTo(rejectedReasons2);
        rejectedReasons1.setId(null);
        assertThat(rejectedReasons1).isNotEqualTo(rejectedReasons2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RejectedReasonsDTO.class);
        RejectedReasonsDTO rejectedReasonsDTO1 = new RejectedReasonsDTO();
        rejectedReasonsDTO1.setId(1L);
        RejectedReasonsDTO rejectedReasonsDTO2 = new RejectedReasonsDTO();
        assertThat(rejectedReasonsDTO1).isNotEqualTo(rejectedReasonsDTO2);
        rejectedReasonsDTO2.setId(rejectedReasonsDTO1.getId());
        assertThat(rejectedReasonsDTO1).isEqualTo(rejectedReasonsDTO2);
        rejectedReasonsDTO2.setId(2L);
        assertThat(rejectedReasonsDTO1).isNotEqualTo(rejectedReasonsDTO2);
        rejectedReasonsDTO1.setId(null);
        assertThat(rejectedReasonsDTO1).isNotEqualTo(rejectedReasonsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(rejectedReasonsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(rejectedReasonsMapper.fromId(null)).isNull();
    }
}
