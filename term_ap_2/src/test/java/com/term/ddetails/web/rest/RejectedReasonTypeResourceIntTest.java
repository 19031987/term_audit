package com.term.ddetails.web.rest;

import com.term.ddetails.TermAuditAoApp;

import com.term.ddetails.domain.RejectedReasonType;
import com.term.ddetails.repository.RejectedReasonTypeRepository;
import com.term.ddetails.service.dto.RejectedReasonTypeDTO;
import com.term.ddetails.service.mapper.RejectedReasonTypeMapper;
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
 * Test class for the RejectedReasonTypeResource REST controller.
 *
 * @see RejectedReasonTypeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TermAuditAoApp.class)
public class RejectedReasonTypeResourceIntTest {

    private static final Integer DEFAULT_REASON_TYPE = 1;
    private static final Integer UPDATED_REASON_TYPE = 2;

    private static final String DEFAULT_REJECT_REASON_DESC = "AAAAAAAAAA";
    private static final String UPDATED_REJECT_REASON_DESC = "BBBBBBBBBB";

    @Autowired
    private RejectedReasonTypeRepository rejectedReasonTypeRepository;

    @Autowired
    private RejectedReasonTypeMapper rejectedReasonTypeMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRejectedReasonTypeMockMvc;

    private RejectedReasonType rejectedReasonType;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        RejectedReasonTypeResource rejectedReasonTypeResource = new RejectedReasonTypeResource(rejectedReasonTypeRepository, rejectedReasonTypeMapper);
        this.restRejectedReasonTypeMockMvc = MockMvcBuilders.standaloneSetup(rejectedReasonTypeResource)
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
    public static RejectedReasonType createEntity(EntityManager em) {
        RejectedReasonType rejectedReasonType = new RejectedReasonType()
            .reasonType(DEFAULT_REASON_TYPE)
            .rejectReasonDesc(DEFAULT_REJECT_REASON_DESC);
        return rejectedReasonType;
    }

    @Before
    public void initTest() {
        rejectedReasonType = createEntity(em);
    }

    @Test
    @Transactional
    public void createRejectedReasonType() throws Exception {
        int databaseSizeBeforeCreate = rejectedReasonTypeRepository.findAll().size();

        // Create the RejectedReasonType
        RejectedReasonTypeDTO rejectedReasonTypeDTO = rejectedReasonTypeMapper.toDto(rejectedReasonType);
        restRejectedReasonTypeMockMvc.perform(post("/api/rejected-reason-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rejectedReasonTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the RejectedReasonType in the database
        List<RejectedReasonType> rejectedReasonTypeList = rejectedReasonTypeRepository.findAll();
        assertThat(rejectedReasonTypeList).hasSize(databaseSizeBeforeCreate + 1);
        RejectedReasonType testRejectedReasonType = rejectedReasonTypeList.get(rejectedReasonTypeList.size() - 1);
        assertThat(testRejectedReasonType.getReasonType()).isEqualTo(DEFAULT_REASON_TYPE);
        assertThat(testRejectedReasonType.getRejectReasonDesc()).isEqualTo(DEFAULT_REJECT_REASON_DESC);
    }

    @Test
    @Transactional
    public void createRejectedReasonTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = rejectedReasonTypeRepository.findAll().size();

        // Create the RejectedReasonType with an existing ID
        rejectedReasonType.setId(1L);
        RejectedReasonTypeDTO rejectedReasonTypeDTO = rejectedReasonTypeMapper.toDto(rejectedReasonType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRejectedReasonTypeMockMvc.perform(post("/api/rejected-reason-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rejectedReasonTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<RejectedReasonType> rejectedReasonTypeList = rejectedReasonTypeRepository.findAll();
        assertThat(rejectedReasonTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRejectedReasonTypes() throws Exception {
        // Initialize the database
        rejectedReasonTypeRepository.saveAndFlush(rejectedReasonType);

        // Get all the rejectedReasonTypeList
        restRejectedReasonTypeMockMvc.perform(get("/api/rejected-reason-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rejectedReasonType.getId().intValue())))
            .andExpect(jsonPath("$.[*].reasonType").value(hasItem(DEFAULT_REASON_TYPE)))
            .andExpect(jsonPath("$.[*].rejectReasonDesc").value(hasItem(DEFAULT_REJECT_REASON_DESC.toString())));
    }

    @Test
    @Transactional
    public void getRejectedReasonType() throws Exception {
        // Initialize the database
        rejectedReasonTypeRepository.saveAndFlush(rejectedReasonType);

        // Get the rejectedReasonType
        restRejectedReasonTypeMockMvc.perform(get("/api/rejected-reason-types/{id}", rejectedReasonType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(rejectedReasonType.getId().intValue()))
            .andExpect(jsonPath("$.reasonType").value(DEFAULT_REASON_TYPE))
            .andExpect(jsonPath("$.rejectReasonDesc").value(DEFAULT_REJECT_REASON_DESC.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRejectedReasonType() throws Exception {
        // Get the rejectedReasonType
        restRejectedReasonTypeMockMvc.perform(get("/api/rejected-reason-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRejectedReasonType() throws Exception {
        // Initialize the database
        rejectedReasonTypeRepository.saveAndFlush(rejectedReasonType);
        int databaseSizeBeforeUpdate = rejectedReasonTypeRepository.findAll().size();

        // Update the rejectedReasonType
        RejectedReasonType updatedRejectedReasonType = rejectedReasonTypeRepository.findOne(rejectedReasonType.getId());
        updatedRejectedReasonType
            .reasonType(UPDATED_REASON_TYPE)
            .rejectReasonDesc(UPDATED_REJECT_REASON_DESC);
        RejectedReasonTypeDTO rejectedReasonTypeDTO = rejectedReasonTypeMapper.toDto(updatedRejectedReasonType);

        restRejectedReasonTypeMockMvc.perform(put("/api/rejected-reason-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rejectedReasonTypeDTO)))
            .andExpect(status().isOk());

        // Validate the RejectedReasonType in the database
        List<RejectedReasonType> rejectedReasonTypeList = rejectedReasonTypeRepository.findAll();
        assertThat(rejectedReasonTypeList).hasSize(databaseSizeBeforeUpdate);
        RejectedReasonType testRejectedReasonType = rejectedReasonTypeList.get(rejectedReasonTypeList.size() - 1);
        assertThat(testRejectedReasonType.getReasonType()).isEqualTo(UPDATED_REASON_TYPE);
        assertThat(testRejectedReasonType.getRejectReasonDesc()).isEqualTo(UPDATED_REJECT_REASON_DESC);
    }

    @Test
    @Transactional
    public void updateNonExistingRejectedReasonType() throws Exception {
        int databaseSizeBeforeUpdate = rejectedReasonTypeRepository.findAll().size();

        // Create the RejectedReasonType
        RejectedReasonTypeDTO rejectedReasonTypeDTO = rejectedReasonTypeMapper.toDto(rejectedReasonType);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRejectedReasonTypeMockMvc.perform(put("/api/rejected-reason-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rejectedReasonTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the RejectedReasonType in the database
        List<RejectedReasonType> rejectedReasonTypeList = rejectedReasonTypeRepository.findAll();
        assertThat(rejectedReasonTypeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteRejectedReasonType() throws Exception {
        // Initialize the database
        rejectedReasonTypeRepository.saveAndFlush(rejectedReasonType);
        int databaseSizeBeforeDelete = rejectedReasonTypeRepository.findAll().size();

        // Get the rejectedReasonType
        restRejectedReasonTypeMockMvc.perform(delete("/api/rejected-reason-types/{id}", rejectedReasonType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RejectedReasonType> rejectedReasonTypeList = rejectedReasonTypeRepository.findAll();
        assertThat(rejectedReasonTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RejectedReasonType.class);
        RejectedReasonType rejectedReasonType1 = new RejectedReasonType();
        rejectedReasonType1.setId(1L);
        RejectedReasonType rejectedReasonType2 = new RejectedReasonType();
        rejectedReasonType2.setId(rejectedReasonType1.getId());
        assertThat(rejectedReasonType1).isEqualTo(rejectedReasonType2);
        rejectedReasonType2.setId(2L);
        assertThat(rejectedReasonType1).isNotEqualTo(rejectedReasonType2);
        rejectedReasonType1.setId(null);
        assertThat(rejectedReasonType1).isNotEqualTo(rejectedReasonType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RejectedReasonTypeDTO.class);
        RejectedReasonTypeDTO rejectedReasonTypeDTO1 = new RejectedReasonTypeDTO();
        rejectedReasonTypeDTO1.setId(1L);
        RejectedReasonTypeDTO rejectedReasonTypeDTO2 = new RejectedReasonTypeDTO();
        assertThat(rejectedReasonTypeDTO1).isNotEqualTo(rejectedReasonTypeDTO2);
        rejectedReasonTypeDTO2.setId(rejectedReasonTypeDTO1.getId());
        assertThat(rejectedReasonTypeDTO1).isEqualTo(rejectedReasonTypeDTO2);
        rejectedReasonTypeDTO2.setId(2L);
        assertThat(rejectedReasonTypeDTO1).isNotEqualTo(rejectedReasonTypeDTO2);
        rejectedReasonTypeDTO1.setId(null);
        assertThat(rejectedReasonTypeDTO1).isNotEqualTo(rejectedReasonTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(rejectedReasonTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(rejectedReasonTypeMapper.fromId(null)).isNull();
    }
}
