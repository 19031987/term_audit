package com.term.ddetails.web.rest;

import com.term.ddetails.TermAuditAoApp;

import com.term.ddetails.domain.TermAudit;
import com.term.ddetails.repository.TermAuditRepository;
import com.term.ddetails.service.dto.TermAuditDTO;
import com.term.ddetails.service.mapper.TermAuditMapper;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the TermAuditResource REST controller.
 *
 * @see TermAuditResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TermAuditAoApp.class)
public class TermAuditResourceIntTest {

    private static final String DEFAULT_MOBILENUMBER = "AAAAAAAAAA";
    private static final String UPDATED_MOBILENUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_TERMNO = "AAAAAAAAAA";
    private static final String UPDATED_TERMNO = "BBBBBBBBBB";

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_CONNECTION = "AAAAAAAAAA";
    private static final String UPDATED_CONNECTION = "BBBBBBBBBB";

    private static final String DEFAULT_CONNECTIONTYPE = "AAAAAAAAAA";
    private static final String UPDATED_CONNECTIONTYPE = "BBBBBBBBBB";

    private static final String DEFAULT_BARCODE = "AAAAAAAAAA";
    private static final String UPDATED_BARCODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAMEOFCUSTOMER = "AAAAAAAAAA";
    private static final String UPDATED_NAMEOFCUSTOMER = "BBBBBBBBBB";

    private static final String DEFAULT_FATHERNAME = "AAAAAAAAAA";
    private static final String UPDATED_FATHERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_DOA = "AAAAAAAAAA";
    private static final String UPDATED_DOA = "BBBBBBBBBB";

    private static final String DEFAULT_AONAME = "AAAAAAAAAA";
    private static final String UPDATED_AONAME = "BBBBBBBBBB";

    private static final String DEFAULT_CHECKAVAILABILTYOFCAF = "AAAAAAAAAA";
    private static final String UPDATED_CHECKAVAILABILTYOFCAF = "BBBBBBBBBB";

    private static final Boolean DEFAULT_PHOTO = false;
    private static final Boolean UPDATED_PHOTO = true;

    private static final String DEFAULT_IDENTITYPROOF = "AAAAAAAAAA";
    private static final String UPDATED_IDENTITYPROOF = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESSPROOF = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESSPROOF = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_AUDITDATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_AUDITDATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_AUDITBY = "AAAAAAAAAA";
    private static final String UPDATED_AUDITBY = "BBBBBBBBBB";

    private static final String DEFAULT_CAF = "AAAAAAAAAA";
    private static final String UPDATED_CAF = "BBBBBBBBBB";

    private static final String DEFAULT_ACTIVATIONDATE = "AAAAAAAAAA";
    private static final String UPDATED_ACTIVATIONDATE = "BBBBBBBBBB";

    private static final String DEFAULT_ACTIVATIONSOURCE = "AAAAAAAAAA";
    private static final String UPDATED_ACTIVATIONSOURCE = "BBBBBBBBBB";

    private static final String DEFAULT_VENDOR = "AAAAAAAAAA";
    private static final String UPDATED_VENDOR = "BBBBBBBBBB";

    private static final String DEFAULT_DOB = "AAAAAAAAAA";
    private static final String UPDATED_DOB = "BBBBBBBBBB";

    private static final Boolean DEFAULT_CUSTOMERNAMESTATUS = false;
    private static final Boolean UPDATED_CUSTOMERNAMESTATUS = true;

    private static final Boolean DEFAULT_FATHERNAMESTATUS = false;
    private static final Boolean UPDATED_FATHERNAMESTATUS = true;

    private static final Boolean DEFAULT_DOASTATUS = false;
    private static final Boolean UPDATED_DOASTATUS = true;

    private static final Boolean DEFAULT_AONAMESTATUS = false;
    private static final Boolean UPDATED_AONAMESTATUS = true;

    private static final Boolean DEFAULT_IDENTITYPROOFSTATUS = false;
    private static final Boolean UPDATED_IDENTITYPROOFSTATUS = true;

    private static final Boolean DEFAULT_ADDRESSPROOFSTATUS = false;
    private static final Boolean UPDATED_ADDRESSPROOFSTATUS = true;

    private static final String DEFAULT_REJECTEDREASON = "AAAAAAAAAA";
    private static final String UPDATED_REJECTEDREASON = "BBBBBBBBBB";

    @Autowired
    private TermAuditRepository termAuditRepository;

    @Autowired
    private TermAuditMapper termAuditMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTermAuditMockMvc;

    private TermAudit termAudit;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TermAuditResource termAuditResource = new TermAuditResource(termAuditRepository, termAuditMapper);
        this.restTermAuditMockMvc = MockMvcBuilders.standaloneSetup(termAuditResource)
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
    public static TermAudit createEntity(EntityManager em) {
        TermAudit termAudit = new TermAudit()
            .mobilenumber(DEFAULT_MOBILENUMBER)
            .termno(DEFAULT_TERMNO)
            .state(DEFAULT_STATE)
            .connection(DEFAULT_CONNECTION)
            .connectiontype(DEFAULT_CONNECTIONTYPE)
            .barcode(DEFAULT_BARCODE)
            .nameofcustomer(DEFAULT_NAMEOFCUSTOMER)
            .fathername(DEFAULT_FATHERNAME)
            .doa(DEFAULT_DOA)
            .aoname(DEFAULT_AONAME)
          ///  .checkavailabiltyofcaf(DEFAULT_CHECKAVAILABILTYOFCAF)
            .photo(DEFAULT_PHOTO)
            .identityproof(DEFAULT_IDENTITYPROOF)
            .addressproof(DEFAULT_ADDRESSPROOF)
            .address(DEFAULT_ADDRESS)
            .auditdate(DEFAULT_AUDITDATE)
            .auditby(DEFAULT_AUDITBY)
            .caf(DEFAULT_CAF)
           // .activationdate(DEFAULT_ACTIVATIONDATE)
            .activationsource(DEFAULT_ACTIVATIONSOURCE)
            .vendor(DEFAULT_VENDOR)
            .dob(DEFAULT_DOB)
            .customernamestatus(DEFAULT_CUSTOMERNAMESTATUS)
            .fathernamestatus(DEFAULT_FATHERNAMESTATUS)
            .doastatus(DEFAULT_DOASTATUS)
            .aonamestatus(DEFAULT_AONAMESTATUS)
            .identityproofstatus(DEFAULT_IDENTITYPROOFSTATUS)
            .addressproofstatus(DEFAULT_ADDRESSPROOFSTATUS)
            .rejectedreason(DEFAULT_REJECTEDREASON);
        return termAudit;
    }

    @Before
    public void initTest() {
        termAudit = createEntity(em);
    }

    @Test
    @Transactional
    public void createTermAudit() throws Exception {
        int databaseSizeBeforeCreate = termAuditRepository.findAll().size();

        // Create the TermAudit
        TermAuditDTO termAuditDTO = termAuditMapper.toDto(termAudit);
        restTermAuditMockMvc.perform(post("/api/term-audits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(termAuditDTO)))
            .andExpect(status().isCreated());

        // Validate the TermAudit in the database
        List<TermAudit> termAuditList = termAuditRepository.findAll();
        assertThat(termAuditList).hasSize(databaseSizeBeforeCreate + 1);
        TermAudit testTermAudit = termAuditList.get(termAuditList.size() - 1);
        assertThat(testTermAudit.getMobilenumber()).isEqualTo(DEFAULT_MOBILENUMBER);
        assertThat(testTermAudit.getTermno()).isEqualTo(DEFAULT_TERMNO);
        assertThat(testTermAudit.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testTermAudit.getConnection()).isEqualTo(DEFAULT_CONNECTION);
        assertThat(testTermAudit.getConnectiontype()).isEqualTo(DEFAULT_CONNECTIONTYPE);
        assertThat(testTermAudit.getBarcode()).isEqualTo(DEFAULT_BARCODE);
        assertThat(testTermAudit.getNameofcustomer()).isEqualTo(DEFAULT_NAMEOFCUSTOMER);
        assertThat(testTermAudit.getFathername()).isEqualTo(DEFAULT_FATHERNAME);
        assertThat(testTermAudit.getDoa()).isEqualTo(DEFAULT_DOA);
        assertThat(testTermAudit.getAoname()).isEqualTo(DEFAULT_AONAME);
      //  assertThat(testTermAudit.getCheckavailabiltyofcaf()).isEqualTo(DEFAULT_CHECKAVAILABILTYOFCAF);
        assertThat(testTermAudit.isPhoto()).isEqualTo(DEFAULT_PHOTO);
        assertThat(testTermAudit.getIdentityproof()).isEqualTo(DEFAULT_IDENTITYPROOF);
        assertThat(testTermAudit.getAddressproof()).isEqualTo(DEFAULT_ADDRESSPROOF);
        assertThat(testTermAudit.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testTermAudit.getAuditdate()).isEqualTo(DEFAULT_AUDITDATE);
        assertThat(testTermAudit.getAuditby()).isEqualTo(DEFAULT_AUDITBY);
        assertThat(testTermAudit.getCaf()).isEqualTo(DEFAULT_CAF);
       //. assertThat(testTermAudit.getActivationdate()).isEqualTo(DEFAULT_ACTIVATIONDATE);
        assertThat(testTermAudit.getActivationsource()).isEqualTo(DEFAULT_ACTIVATIONSOURCE);
        assertThat(testTermAudit.getVendor()).isEqualTo(DEFAULT_VENDOR);
        assertThat(testTermAudit.getDob()).isEqualTo(DEFAULT_DOB);
        assertThat(testTermAudit.isCustomernamestatus()).isEqualTo(DEFAULT_CUSTOMERNAMESTATUS);
        assertThat(testTermAudit.isFathernamestatus()).isEqualTo(DEFAULT_FATHERNAMESTATUS);
        assertThat(testTermAudit.isDoastatus()).isEqualTo(DEFAULT_DOASTATUS);
        assertThat(testTermAudit.isAonamestatus()).isEqualTo(DEFAULT_AONAMESTATUS);
        assertThat(testTermAudit.isIdentityproofstatus()).isEqualTo(DEFAULT_IDENTITYPROOFSTATUS);
        assertThat(testTermAudit.isAddressproofstatus()).isEqualTo(DEFAULT_ADDRESSPROOFSTATUS);
        assertThat(testTermAudit.getRejectedreason()).isEqualTo(DEFAULT_REJECTEDREASON);
    }

    @Test
    @Transactional
    public void createTermAuditWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = termAuditRepository.findAll().size();

        // Create the TermAudit with an existing ID
        termAudit.setId(1L);
        TermAuditDTO termAuditDTO = termAuditMapper.toDto(termAudit);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTermAuditMockMvc.perform(post("/api/term-audits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(termAuditDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<TermAudit> termAuditList = termAuditRepository.findAll();
        assertThat(termAuditList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkMobilenumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = termAuditRepository.findAll().size();
        // set the field null
        termAudit.setMobilenumber(null);

        // Create the TermAudit, which fails.
        TermAuditDTO termAuditDTO = termAuditMapper.toDto(termAudit);

        restTermAuditMockMvc.perform(post("/api/term-audits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(termAuditDTO)))
            .andExpect(status().isBadRequest());

        List<TermAudit> termAuditList = termAuditRepository.findAll();
        assertThat(termAuditList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTermnoIsRequired() throws Exception {
        int databaseSizeBeforeTest = termAuditRepository.findAll().size();
        // set the field null
        termAudit.setTermno(null);

        // Create the TermAudit, which fails.
        TermAuditDTO termAuditDTO = termAuditMapper.toDto(termAudit);

        restTermAuditMockMvc.perform(post("/api/term-audits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(termAuditDTO)))
            .andExpect(status().isBadRequest());

        List<TermAudit> termAuditList = termAuditRepository.findAll();
        assertThat(termAuditList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStateIsRequired() throws Exception {
        int databaseSizeBeforeTest = termAuditRepository.findAll().size();
        // set the field null
        termAudit.setState(null);

        // Create the TermAudit, which fails.
        TermAuditDTO termAuditDTO = termAuditMapper.toDto(termAudit);

        restTermAuditMockMvc.perform(post("/api/term-audits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(termAuditDTO)))
            .andExpect(status().isBadRequest());

        List<TermAudit> termAuditList = termAuditRepository.findAll();
        assertThat(termAuditList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkConnectionIsRequired() throws Exception {
        int databaseSizeBeforeTest = termAuditRepository.findAll().size();
        // set the field null
        termAudit.setConnection(null);

        // Create the TermAudit, which fails.
        TermAuditDTO termAuditDTO = termAuditMapper.toDto(termAudit);

        restTermAuditMockMvc.perform(post("/api/term-audits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(termAuditDTO)))
            .andExpect(status().isBadRequest());

        List<TermAudit> termAuditList = termAuditRepository.findAll();
        assertThat(termAuditList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkConnectiontypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = termAuditRepository.findAll().size();
        // set the field null
        termAudit.setConnectiontype(null);

        // Create the TermAudit, which fails.
        TermAuditDTO termAuditDTO = termAuditMapper.toDto(termAudit);

        restTermAuditMockMvc.perform(post("/api/term-audits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(termAuditDTO)))
            .andExpect(status().isBadRequest());

        List<TermAudit> termAuditList = termAuditRepository.findAll();
        assertThat(termAuditList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameofcustomerIsRequired() throws Exception {
        int databaseSizeBeforeTest = termAuditRepository.findAll().size();
        // set the field null
        termAudit.setNameofcustomer(null);

        // Create the TermAudit, which fails.
        TermAuditDTO termAuditDTO = termAuditMapper.toDto(termAudit);

        restTermAuditMockMvc.perform(post("/api/term-audits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(termAuditDTO)))
            .andExpect(status().isBadRequest());

        List<TermAudit> termAuditList = termAuditRepository.findAll();
        assertThat(termAuditList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTermAudits() throws Exception {
        // Initialize the database
        termAuditRepository.saveAndFlush(termAudit);

        // Get all the termAuditList
        restTermAuditMockMvc.perform(get("/api/term-audits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(termAudit.getId().intValue())))
            .andExpect(jsonPath("$.[*].mobilenumber").value(hasItem(DEFAULT_MOBILENUMBER.toString())))
            .andExpect(jsonPath("$.[*].termno").value(hasItem(DEFAULT_TERMNO.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())))
            .andExpect(jsonPath("$.[*].connection").value(hasItem(DEFAULT_CONNECTION.toString())))
            .andExpect(jsonPath("$.[*].connectiontype").value(hasItem(DEFAULT_CONNECTIONTYPE.toString())))
            .andExpect(jsonPath("$.[*].barcode").value(hasItem(DEFAULT_BARCODE.toString())))
            .andExpect(jsonPath("$.[*].nameofcustomer").value(hasItem(DEFAULT_NAMEOFCUSTOMER.toString())))
            .andExpect(jsonPath("$.[*].fathername").value(hasItem(DEFAULT_FATHERNAME.toString())))
            .andExpect(jsonPath("$.[*].doa").value(hasItem(DEFAULT_DOA.toString())))
            .andExpect(jsonPath("$.[*].aoname").value(hasItem(DEFAULT_AONAME.toString())))
            .andExpect(jsonPath("$.[*].checkavailabiltyofcaf").value(hasItem(DEFAULT_CHECKAVAILABILTYOFCAF.toString())))
            .andExpect(jsonPath("$.[*].photo").value(hasItem(DEFAULT_PHOTO.booleanValue())))
            .andExpect(jsonPath("$.[*].identityproof").value(hasItem(DEFAULT_IDENTITYPROOF.toString())))
            .andExpect(jsonPath("$.[*].addressproof").value(hasItem(DEFAULT_ADDRESSPROOF.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].auditdate").value(hasItem(DEFAULT_AUDITDATE.toString())))
            .andExpect(jsonPath("$.[*].auditby").value(hasItem(DEFAULT_AUDITBY.toString())))
            .andExpect(jsonPath("$.[*].caf").value(hasItem(DEFAULT_CAF.toString())))
            .andExpect(jsonPath("$.[*].activationdate").value(hasItem(DEFAULT_ACTIVATIONDATE.toString())))
            .andExpect(jsonPath("$.[*].activationsource").value(hasItem(DEFAULT_ACTIVATIONSOURCE.toString())))
            .andExpect(jsonPath("$.[*].vendor").value(hasItem(DEFAULT_VENDOR.toString())))
            .andExpect(jsonPath("$.[*].dob").value(hasItem(DEFAULT_DOB.toString())))
            .andExpect(jsonPath("$.[*].customernamestatus").value(hasItem(DEFAULT_CUSTOMERNAMESTATUS.booleanValue())))
            .andExpect(jsonPath("$.[*].fathernamestatus").value(hasItem(DEFAULT_FATHERNAMESTATUS.booleanValue())))
            .andExpect(jsonPath("$.[*].doastatus").value(hasItem(DEFAULT_DOASTATUS.booleanValue())))
            .andExpect(jsonPath("$.[*].aonamestatus").value(hasItem(DEFAULT_AONAMESTATUS.booleanValue())))
            .andExpect(jsonPath("$.[*].identityproofstatus").value(hasItem(DEFAULT_IDENTITYPROOFSTATUS.booleanValue())))
            .andExpect(jsonPath("$.[*].addressproofstatus").value(hasItem(DEFAULT_ADDRESSPROOFSTATUS.booleanValue())))
            .andExpect(jsonPath("$.[*].rejectedreason").value(hasItem(DEFAULT_REJECTEDREASON.toString())));
    }

    @Test
    @Transactional
    public void getTermAudit() throws Exception {
        // Initialize the database
        termAuditRepository.saveAndFlush(termAudit);

        // Get the termAudit
        restTermAuditMockMvc.perform(get("/api/term-audits/{id}", termAudit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(termAudit.getId().intValue()))
            .andExpect(jsonPath("$.mobilenumber").value(DEFAULT_MOBILENUMBER.toString()))
            .andExpect(jsonPath("$.termno").value(DEFAULT_TERMNO.toString()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()))
            .andExpect(jsonPath("$.connection").value(DEFAULT_CONNECTION.toString()))
            .andExpect(jsonPath("$.connectiontype").value(DEFAULT_CONNECTIONTYPE.toString()))
            .andExpect(jsonPath("$.barcode").value(DEFAULT_BARCODE.toString()))
            .andExpect(jsonPath("$.nameofcustomer").value(DEFAULT_NAMEOFCUSTOMER.toString()))
            .andExpect(jsonPath("$.fathername").value(DEFAULT_FATHERNAME.toString()))
            .andExpect(jsonPath("$.doa").value(DEFAULT_DOA.toString()))
            .andExpect(jsonPath("$.aoname").value(DEFAULT_AONAME.toString()))
            .andExpect(jsonPath("$.checkavailabiltyofcaf").value(DEFAULT_CHECKAVAILABILTYOFCAF.toString()))
            .andExpect(jsonPath("$.photo").value(DEFAULT_PHOTO.booleanValue()))
            .andExpect(jsonPath("$.identityproof").value(DEFAULT_IDENTITYPROOF.toString()))
            .andExpect(jsonPath("$.addressproof").value(DEFAULT_ADDRESSPROOF.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.auditdate").value(DEFAULT_AUDITDATE.toString()))
            .andExpect(jsonPath("$.auditby").value(DEFAULT_AUDITBY.toString()))
            .andExpect(jsonPath("$.caf").value(DEFAULT_CAF.toString()))
            .andExpect(jsonPath("$.activationdate").value(DEFAULT_ACTIVATIONDATE.toString()))
            .andExpect(jsonPath("$.activationsource").value(DEFAULT_ACTIVATIONSOURCE.toString()))
            .andExpect(jsonPath("$.vendor").value(DEFAULT_VENDOR.toString()))
            .andExpect(jsonPath("$.dob").value(DEFAULT_DOB.toString()))
            .andExpect(jsonPath("$.customernamestatus").value(DEFAULT_CUSTOMERNAMESTATUS.booleanValue()))
            .andExpect(jsonPath("$.fathernamestatus").value(DEFAULT_FATHERNAMESTATUS.booleanValue()))
            .andExpect(jsonPath("$.doastatus").value(DEFAULT_DOASTATUS.booleanValue()))
            .andExpect(jsonPath("$.aonamestatus").value(DEFAULT_AONAMESTATUS.booleanValue()))
            .andExpect(jsonPath("$.identityproofstatus").value(DEFAULT_IDENTITYPROOFSTATUS.booleanValue()))
            .andExpect(jsonPath("$.addressproofstatus").value(DEFAULT_ADDRESSPROOFSTATUS.booleanValue()))
            .andExpect(jsonPath("$.rejectedreason").value(DEFAULT_REJECTEDREASON.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTermAudit() throws Exception {
        // Get the termAudit
        restTermAuditMockMvc.perform(get("/api/term-audits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTermAudit() throws Exception {
        // Initialize the database
        termAuditRepository.saveAndFlush(termAudit);
        int databaseSizeBeforeUpdate = termAuditRepository.findAll().size();

        // Update the termAudit
        TermAudit updatedTermAudit = termAuditRepository.findOne(termAudit.getId());
        updatedTermAudit
            .mobilenumber(UPDATED_MOBILENUMBER)
            .termno(UPDATED_TERMNO)
            .state(UPDATED_STATE)
            .connection(UPDATED_CONNECTION)
            .connectiontype(UPDATED_CONNECTIONTYPE)
            .barcode(UPDATED_BARCODE)
            .nameofcustomer(UPDATED_NAMEOFCUSTOMER)
            .fathername(UPDATED_FATHERNAME)
            .doa(UPDATED_DOA)
            .aoname(UPDATED_AONAME)
       //     .checkavailabiltyofcaf(UPDATED_CHECKAVAILABILTYOFCAF)
            .photo(UPDATED_PHOTO)
            .identityproof(UPDATED_IDENTITYPROOF)
            .addressproof(UPDATED_ADDRESSPROOF)
            .address(UPDATED_ADDRESS)
            .auditdate(UPDATED_AUDITDATE)
            .auditby(UPDATED_AUDITBY)
            .caf(UPDATED_CAF)
         //   .activationdate(UPDATED_ACTIVATIONDATE)
            .activationsource(UPDATED_ACTIVATIONSOURCE)
            .vendor(UPDATED_VENDOR)
            .dob(UPDATED_DOB)
            .customernamestatus(UPDATED_CUSTOMERNAMESTATUS)
            .fathernamestatus(UPDATED_FATHERNAMESTATUS)
            .doastatus(UPDATED_DOASTATUS)
            .aonamestatus(UPDATED_AONAMESTATUS)
            .identityproofstatus(UPDATED_IDENTITYPROOFSTATUS)
            .addressproofstatus(UPDATED_ADDRESSPROOFSTATUS)
            .rejectedreason(UPDATED_REJECTEDREASON);
        TermAuditDTO termAuditDTO = termAuditMapper.toDto(updatedTermAudit);

        restTermAuditMockMvc.perform(put("/api/term-audits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(termAuditDTO)))
            .andExpect(status().isOk());

        // Validate the TermAudit in the database
        List<TermAudit> termAuditList = termAuditRepository.findAll();
        assertThat(termAuditList).hasSize(databaseSizeBeforeUpdate);
        TermAudit testTermAudit = termAuditList.get(termAuditList.size() - 1);
        assertThat(testTermAudit.getMobilenumber()).isEqualTo(UPDATED_MOBILENUMBER);
        assertThat(testTermAudit.getTermno()).isEqualTo(UPDATED_TERMNO);
        assertThat(testTermAudit.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testTermAudit.getConnection()).isEqualTo(UPDATED_CONNECTION);
        assertThat(testTermAudit.getConnectiontype()).isEqualTo(UPDATED_CONNECTIONTYPE);
        assertThat(testTermAudit.getBarcode()).isEqualTo(UPDATED_BARCODE);
        assertThat(testTermAudit.getNameofcustomer()).isEqualTo(UPDATED_NAMEOFCUSTOMER);
        assertThat(testTermAudit.getFathername()).isEqualTo(UPDATED_FATHERNAME);
        assertThat(testTermAudit.getDoa()).isEqualTo(UPDATED_DOA);
        assertThat(testTermAudit.getAoname()).isEqualTo(UPDATED_AONAME);
     //   assertThat(testTermAudit.getCheckavailabiltyofcaf()).isEqualTo(UPDATED_CHECKAVAILABILTYOFCAF);
        assertThat(testTermAudit.isPhoto()).isEqualTo(UPDATED_PHOTO);
        assertThat(testTermAudit.getIdentityproof()).isEqualTo(UPDATED_IDENTITYPROOF);
        assertThat(testTermAudit.getAddressproof()).isEqualTo(UPDATED_ADDRESSPROOF);
        assertThat(testTermAudit.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testTermAudit.getAuditdate()).isEqualTo(UPDATED_AUDITDATE);
        assertThat(testTermAudit.getAuditby()).isEqualTo(UPDATED_AUDITBY);
        assertThat(testTermAudit.getCaf()).isEqualTo(UPDATED_CAF);
       // assertThat(testTermAudit.getActivationdate()).isEqualTo(UPDATED_ACTIVATIONDATE);
        assertThat(testTermAudit.getActivationsource()).isEqualTo(UPDATED_ACTIVATIONSOURCE);
        assertThat(testTermAudit.getVendor()).isEqualTo(UPDATED_VENDOR);
        assertThat(testTermAudit.getDob()).isEqualTo(UPDATED_DOB);
        assertThat(testTermAudit.isCustomernamestatus()).isEqualTo(UPDATED_CUSTOMERNAMESTATUS);
        assertThat(testTermAudit.isFathernamestatus()).isEqualTo(UPDATED_FATHERNAMESTATUS);
        assertThat(testTermAudit.isDoastatus()).isEqualTo(UPDATED_DOASTATUS);
        assertThat(testTermAudit.isAonamestatus()).isEqualTo(UPDATED_AONAMESTATUS);
        assertThat(testTermAudit.isIdentityproofstatus()).isEqualTo(UPDATED_IDENTITYPROOFSTATUS);
        assertThat(testTermAudit.isAddressproofstatus()).isEqualTo(UPDATED_ADDRESSPROOFSTATUS);
        assertThat(testTermAudit.getRejectedreason()).isEqualTo(UPDATED_REJECTEDREASON);
    }

    @Test
    @Transactional
    public void updateNonExistingTermAudit() throws Exception {
        int databaseSizeBeforeUpdate = termAuditRepository.findAll().size();

        // Create the TermAudit
        TermAuditDTO termAuditDTO = termAuditMapper.toDto(termAudit);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTermAuditMockMvc.perform(put("/api/term-audits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(termAuditDTO)))
            .andExpect(status().isCreated());

        // Validate the TermAudit in the database
        List<TermAudit> termAuditList = termAuditRepository.findAll();
        assertThat(termAuditList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteTermAudit() throws Exception {
        // Initialize the database
        termAuditRepository.saveAndFlush(termAudit);
        int databaseSizeBeforeDelete = termAuditRepository.findAll().size();

        // Get the termAudit
        restTermAuditMockMvc.perform(delete("/api/term-audits/{id}", termAudit.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TermAudit> termAuditList = termAuditRepository.findAll();
        assertThat(termAuditList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TermAudit.class);
        TermAudit termAudit1 = new TermAudit();
        termAudit1.setId(1L);
        TermAudit termAudit2 = new TermAudit();
        termAudit2.setId(termAudit1.getId());
        assertThat(termAudit1).isEqualTo(termAudit2);
        termAudit2.setId(2L);
        assertThat(termAudit1).isNotEqualTo(termAudit2);
        termAudit1.setId(null);
        assertThat(termAudit1).isNotEqualTo(termAudit2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TermAuditDTO.class);
        TermAuditDTO termAuditDTO1 = new TermAuditDTO();
        termAuditDTO1.setId(1L);
        TermAuditDTO termAuditDTO2 = new TermAuditDTO();
        assertThat(termAuditDTO1).isNotEqualTo(termAuditDTO2);
        termAuditDTO2.setId(termAuditDTO1.getId());
        assertThat(termAuditDTO1).isEqualTo(termAuditDTO2);
        termAuditDTO2.setId(2L);
        assertThat(termAuditDTO1).isNotEqualTo(termAuditDTO2);
        termAuditDTO1.setId(null);
        assertThat(termAuditDTO1).isNotEqualTo(termAuditDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(termAuditMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(termAuditMapper.fromId(null)).isNull();
    }
}
